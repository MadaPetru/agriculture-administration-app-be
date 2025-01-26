package ro.adi.agroadmin.user.service.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.user.converter.UserMapper;
import ro.adi.agroadmin.user.service.JwtService;
import ro.adi.agroadmin.user.service.UserService;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;

@Service
@RequiredArgsConstructor
public class UserInterceptorImpl implements UserServiceInterceptor {

    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public UserRegisterResponseDto register(RegisterUserRequestDto requestDto) {
        var request = userMapper.toRegisterUserRequest(requestDto);
        var response = userService.register(request);
        var jwtDetails = userMapper.toJwtGenerationDetailsDto(response);
        var jwt = jwtService.generateToken(jwtDetails);
        return userMapper.toUserRegisterResponseDto(jwt, jwtService.getExpirationTimestampAsMilliseconds());
    }

    @Override
    public UserLoginResponseDto authenticate(LoginUserRequestDto requestDto) {
        var request = userMapper.toLoginUserRequest(requestDto);
        var response = userService.authenticate(request);
        var jwtDetails = userMapper.toJwtGenerationDetailsDto(response);
        var jwt = jwtService.generateToken(jwtDetails);
        return userMapper.toUserLoginResponseDto(jwt, jwtService.getExpirationTimestampAsMilliseconds());
    }
}
