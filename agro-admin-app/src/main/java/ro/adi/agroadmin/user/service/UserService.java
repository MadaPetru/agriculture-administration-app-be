package ro.adi.agroadmin.user.service;

import ro.adi.agroadmin.user.dto.request.LoginUserRequest;
import ro.adi.agroadmin.user.dto.request.RegisterUserRequest;
import ro.adi.agroadmin.user.dto.response.UserLoginResponse;
import ro.adi.agroadmin.user.dto.response.UserRegisterResponse;

public interface UserService {

    UserRegisterResponse register(RegisterUserRequest request);

    UserLoginResponse authenticate(LoginUserRequest request);
}
