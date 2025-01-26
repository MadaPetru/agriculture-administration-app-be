package ro.adi.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;

@RequestMapping(path = "/v1/users")
public interface UserController {

    @PostMapping("/signup")
    UserRegisterResponseDto register(@RequestBody RegisterUserRequestDto requestDto);

    @PostMapping("/login")
    UserLoginResponseDto authenticate(@RequestBody LoginUserRequestDto requestDto);
}
