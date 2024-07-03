package com.ohih.scheduler.user;

import com.ohih.scheduler.user.dto.*;
import com.ohih.scheduler.utility.Utility;
import com.ohih.scheduler.webConstant.ResponseCode;
import com.ohih.scheduler.webConstant.UrlConst;
import com.ohih.scheduler.webConstant.ValidationPattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.ohih.scheduler.webConstant.ResponseCode.LOGOUT_SUCCESS;
import static com.ohih.scheduler.webConstant.UrlConst.LOGIN;
import static com.ohih.scheduler.webConstant.UrlConst.SCHEDULER;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final Utility utility;


    private boolean isEmailUnique(String email) {
        return userMapper.checkEmailDuplication(email) == 0;
    }

    private int checkRegisterValidation(Register register) {
        int responseCode;

        boolean isEmailUnique = isEmailUnique(register.getEmail());
        boolean isEmailValid = utility.isValidated(ValidationPattern.EMAIL_DOMAIN_PATTERN, register.getEmail());
        boolean isPasswordValid = utility.isValidated(ValidationPattern.PASSWORD_PATTERN, register.getPassword());
        boolean isUsernameValid = utility.isValidated(ValidationPattern.USERNAME_PATTERN, register.getUsername());

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

    public UserResponse register(Register register) {
        UserResponse userResponse = new UserResponse();
        userResponse.setResponseCode(checkRegisterValidation(register));

        if (userResponse.getResponseCode() == ResponseCode.REGISTER_IS_VALIDATED) {
            userMapper.register(register);
            userResponse.setResponseCode(ResponseCode.REGISTER_SUCCESS);
            userResponse.setRedirectUrl(UrlConst.LOGIN);
        } else {
            userResponse.setRedirectUrl(UrlConst.REGISTER);
        }

        return userResponse;
    }

    private int checkLoginValidation(Login login) {
        int responseCode;

        boolean isEmailValid = utility.isValidated(ValidationPattern.EMAIL_DOMAIN_PATTERN, login.getEmail());
        boolean isPasswordValid = utility.isValidated(ValidationPattern.PASSWORD_PATTERN, login.getPassword());

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
            loginResult.setRedirectUrl(SCHEDULER);
            loginResult.setLoginInfo(loginInfo);
        }

        return loginResult;
    }

    public UserResponse logout() {
        UserResponse userResponse = new UserResponse();
        userResponse.setResponseCode(LOGOUT_SUCCESS);
        userResponse.setRedirectUrl(LOGIN);

        return userResponse;
    }
}
