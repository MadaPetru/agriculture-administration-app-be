package ro.adi.agroadmin.user.converter;

import org.mapstruct.Mapper;
import ro.adi.agroadmin.user.dto.request.JwtGenerationDetailsDto;
import ro.adi.agroadmin.user.dto.request.LoginUserRequest;
import ro.adi.agroadmin.user.dto.request.RegisterUserRequest;
import ro.adi.agroadmin.user.dto.response.UserLoginResponse;
import ro.adi.agroadmin.user.dto.response.UserRegisterResponse;
import ro.adi.agroadmin.user.jpa.entity.UserEntity;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserLoginResponse toUserLoginResponse(UserEntity entity);

    UserRegisterResponse toUserRegisterResponse(UserEntity entity);

    default JwtGenerationDetailsDto toJwtGenerationDetailsDto(UserRegisterResponse response) {
        return JwtGenerationDetailsDto.builder()
                .username(response.getEmail())
                .build();
    }

    default JwtGenerationDetailsDto toJwtGenerationDetailsDto(UserLoginResponse response) {
        return JwtGenerationDetailsDto.builder()
                .username(response.getEmail())
                .build();
    }

    LoginUserRequest toLoginUserRequest(LoginUserRequestDto requestDto);

    RegisterUserRequest toRegisterUserRequest(RegisterUserRequestDto requestDto);

    default UserLoginResponseDto toUserLoginResponseDto(String jwt, long expirationTime) {
        return UserLoginResponseDto.builder()
                .token(jwt)
                .expiresIn(Instant.now().toEpochMilli() + expirationTime)
                .build();
    }

    default UserRegisterResponseDto toUserRegisterResponseDto(String jwt, long expirationTime) {
        return UserRegisterResponseDto.builder()
                .token(jwt)
                .expiresIn(Instant.now().toEpochMilli() + expirationTime)
                .build();
    }
}
