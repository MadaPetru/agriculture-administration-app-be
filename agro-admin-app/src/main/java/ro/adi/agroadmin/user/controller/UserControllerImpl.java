package ro.adi.agroadmin.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.agroadmin.user.service.interceptor.UserServiceInterceptor;
import ro.adi.user.controller.UserController;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserServiceInterceptor userServiceInterceptor;

    @Override
    public UserRegisterResponseDto register(RegisterUserRequestDto requestDto) {
        return userServiceInterceptor.register(requestDto);
    }

    @Override
    public UserLoginResponseDto authenticate(LoginUserRequestDto requestDto) {
        return userServiceInterceptor.authenticate(requestDto);
    }
}
