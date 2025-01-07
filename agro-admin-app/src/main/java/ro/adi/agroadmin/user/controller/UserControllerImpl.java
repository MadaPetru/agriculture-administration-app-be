package ro.adi.agroadmin.user.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.RestController;
import ro.adi.agroadmin.config.JwtProperties;
import ro.adi.agroadmin.user.dto.response.UserLoginResponse;
import ro.adi.agroadmin.user.service.JwtService;
import ro.adi.agroadmin.user.service.UserService;
import ro.adi.user.controller.UserController;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;

import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final JwtService jwtService;
    private final UserService userService;
    private final JwtProperties jwtProperties;

    @Override
    public User register(RegisterUserRequestDto requestDto) {
        UserLoginResponse signup = userService.signup(requestDto);
        return null;
    }

    @Override
    public UserLoginResponseDto authenticate(LoginUserRequestDto requestDto) {
        var response = userService.authenticate(requestDto);
        var generateToken = jwtService.generateToken(response);
        return UserLoginResponseDto.builder()
                .token(generateToken)
                .expiresIn(Instant.now().toEpochMilli() + jwtProperties.getExpirationTime())
                .build();
    }
}
