package com.ohih.scheduler.user;

import com.ohih.scheduler.user.dto.Login;
import com.ohih.scheduler.user.dto.LoginInfo;
import com.ohih.scheduler.user.dto.LoginResult;
import com.ohih.scheduler.user.dto.Register;
import com.ohih.scheduler.utility.Utility;
import com.ohih.scheduler.webConstant.ResponseCode;
import com.ohih.scheduler.webConstant.UrlConst;
import com.ohih.scheduler.webConstant.ValidationPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;


    private boolean isEmailUnique(String email) {
        return userMapper.checkEmailDuplication(email) == 0;
    }

    private int checkRegisterValidation(Register register) {
        int responseCode;

        boolean isEmailUnique = isEmailUnique(register.getEmail());
        boolean isEmailValid = Utility.isValidated(ValidationPattern.EMAIL_DOMAIN_PATTERN, register.getEmail());
        boolean isPasswordValid = Utility.isValidated(ValidationPattern.PASSWORD_PATTERN, register.getPassword());
        boolean isUsernameValid = Utility.isValidated(ValidationPattern.USERNAME_PATTERN, register.getUsername());

        if (!isEmailUnique) {
            responseCode = ResponseCode.EMAIL_DUPLICATION_ERROR;
        } else if (!isEmailValid) {
            responseCode = ResponseCode.EMAIL_VALIDATION_ERROR;
        } else if (!isPasswordValid) {
            responseCode = ResponseCode.PASSWORD_VALIDATION_ERROR;
        } else if (!isUsernameValid) {
            responseCode = ResponseCode.USERNAME_VALIDATION_ERROR;
        } else {
            responseCode = ResponseCode.REGISTER_IS_VALIDATED;
        }
        return responseCode;
    }

    public int register(Register register) {
        int responseCode = checkRegisterValidation(register);

        if (responseCode == ResponseCode.REGISTER_IS_VALIDATED) {
            userMapper.register(register);
            responseCode = ResponseCode.REGISTER_SUCCESS;
        }

        return responseCode;
    }

    private int checkLoginValidation(Login login) {
        int responseCode;

        boolean isEmailValid = Utility.isValidated(ValidationPattern.EMAIL_DOMAIN_PATTERN, login.getEmail());
        boolean isPasswordValid = Utility.isValidated(ValidationPattern.PASSWORD_PATTERN, login.getPassword());

        if (!isEmailValid) {
            responseCode = ResponseCode.EMAIL_VALIDATION_ERROR;
        } else if (!isPasswordValid) {
            responseCode = ResponseCode.PASSWORD_VALIDATION_ERROR;
        } else {
            responseCode = ResponseCode.LOGIN_IS_VALIDATED;
        }

        return responseCode;
    }

    public LoginResult login(Login login) {
        LoginResult loginResult = new LoginResult();

        int responseCode = checkLoginValidation(login);
        if (responseCode != ResponseCode.LOGIN_IS_VALIDATED) {
            loginResult.setResponseCode(responseCode);
            loginResult.setRedirectUrl(UrlConst.LOGIN);
            return loginResult;
        }

        LoginInfo loginInfo = userMapper.login(login);
        if (loginInfo == null) {
            loginResult.setResponseCode(ResponseCode.LOGIN_FAILURE);
            loginResult.setRedirectUrl(UrlConst.LOGIN);
        } else {
            loginResult.setResponseCode(ResponseCode.LOGIN_SUCCESS);
            loginResult.setRedirectUrl(UrlConst.SCHEDULER);
            loginResult.setLoginInfo(loginInfo);
        }

        return loginResult;
    }
}
