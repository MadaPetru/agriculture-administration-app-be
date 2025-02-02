package ro.adi.agroadmin.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.agroadmin.user.service.interceptor.UserServiceInterceptor;
import ro.adi.user.controller.UserController;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.request.UpdateUserRequestDto;
import ro.adi.user.dto.request.UserSearchRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;
import ro.adi.user.dto.response.UserResponseDto;

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

    @Override
    public Page<UserResponseDto> search(UserSearchRequestDto requestDto) {
        return userServiceInterceptor.search(requestDto);
    }

    @Override
    public void updateUser(UpdateUserRequestDto requestDto) {
        userServiceInterceptor.updateUser(requestDto);
    }

    @Override
    public void deleteUser(Integer id) {
        userServiceInterceptor.deleteUser(id);
    }

    @Override
    public void resetPassword(Integer id) {
        userServiceInterceptor.resetPassword(id);
    }
}
