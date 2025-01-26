package ro.adi.agroadmin.user.service.interceptor;

import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;

public interface UserServiceInterceptor {

    UserRegisterResponseDto register(RegisterUserRequestDto requestDto);

    UserLoginResponseDto authenticate(LoginUserRequestDto requestDto);
}
