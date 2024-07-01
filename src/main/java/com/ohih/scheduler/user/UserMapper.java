package com.ohih.scheduler.user;

import com.ohih.scheduler.user.dto.Login;
import com.ohih.scheduler.user.dto.LoginInfo;
import com.ohih.scheduler.user.dto.Register;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int checkEmailDuplication(String email);

    int register(Register register);

    LoginInfo login(Login login);
}
