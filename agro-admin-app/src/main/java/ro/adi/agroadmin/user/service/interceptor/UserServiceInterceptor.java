package ro.adi.agroadmin.user.service.interceptor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.request.UpdateUserRequestDto;
import ro.adi.user.dto.request.UserSearchRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;
import ro.adi.user.dto.response.UserResponseDto;

public interface UserServiceInterceptor {

    UserRegisterResponseDto register(RegisterUserRequestDto requestDto);

    UserLoginResponseDto authenticate(LoginUserRequestDto requestDto);

    Page<UserResponseDto> search(UserSearchRequestDto requestDto);

    void updateUser(UpdateUserRequestDto requestDto);

    void deleteUser(Integer id);

    void resetPassword(Integer id);
}
