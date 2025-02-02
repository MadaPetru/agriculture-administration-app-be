package ro.adi.agroadmin.user.converter;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ro.adi.agroadmin.common.entity.UserRole;
import ro.adi.agroadmin.user.dto.request.*;
import ro.adi.agroadmin.user.dto.response.UserLoginResponse;
import ro.adi.agroadmin.user.dto.response.UserRegisterResponse;
import ro.adi.agroadmin.user.dto.response.UserResponse;
import ro.adi.agroadmin.user.jpa.entity.UserEntity;
import ro.adi.user.dto.common.UserRoleDto;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.request.UpdateUserRequestDto;
import ro.adi.user.dto.request.UserSearchRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;
import ro.adi.user.dto.response.UserResponseDto;

import java.time.Instant;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    default UserLoginResponse toUserLoginResponse(UserEntity entity, Set<UserRole> roles) {
        return UserLoginResponse.builder()
                .email(entity.getEmail())
                .roles(roles)
                .build();
    }

    default UserSearchRequest toUserSearchRequest(UserSearchRequestDto requestDto) {
        var pageRequestDto = requestDto.getPageable();
        var pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        return UserSearchRequest.builder()
                .searchBy(requestDto.getSearchBy())
                .pageable(pageRequest)
                .build();
    }

    default UserEntity toUserEntity(UpdateUserRequest request) {
        var entity = new UserEntity();
        entity.setId(request.getId());
        entity.setEmail(request.getEmail());
        entity.setVersion(request.getVersion());
        return entity;
    }

    UpdateUserRequest toUpdateUserRequest(UpdateUserRequestDto requestDto);

    UserRegisterResponse toUserRegisterResponse(UserEntity entity);

    UserResponseDto toUserResponseDto(UserResponse response);

    UserResponse toUserResponse(UserEntity entity);

    default JwtGenerationDetailsDto toJwtGenerationDetailsDto(UserRegisterResponse response) {
        return JwtGenerationDetailsDto.builder()
                .username(response.getEmail())
                .build();
    }

    default JwtGenerationDetailsDto toJwtGenerationDetailsDto(UserLoginResponse response) {
        return JwtGenerationDetailsDto.builder()
                .username(response.getEmail())
                .roles(response.getRoles())
                .build();
    }

    LoginUserRequest toLoginUserRequest(LoginUserRequestDto requestDto);

    RegisterUserRequest toRegisterUserRequest(RegisterUserRequestDto requestDto);

    default UserLoginResponseDto toUserLoginResponseDto(String jwt, long expirationTime, Set<UserRole> roles) {
        return UserLoginResponseDto.builder()
                .token(jwt)
                .expiresIn(Instant.now().toEpochMilli() + expirationTime)
                .roles(toUserRoleDtoSet(roles))
                .build();
    }

    default UserRegisterResponseDto toUserRegisterResponseDto(String jwt, long expirationTime) {
        return UserRegisterResponseDto.builder()
                .token(jwt)
                .expiresIn(Instant.now().toEpochMilli() + expirationTime)
                .build();
    }

    default PageImpl<UserResponseDto> toPageImplUserResponseDto(PageImpl<UserResponse> responsePage) {
        var responseDtoList = responsePage.stream().map(this::toUserResponseDto).toList();
        return new PageImpl<>(responseDtoList, responsePage.getPageable(), responsePage.getTotalElements());
    }

    default PageImpl<UserResponse> toPageImplUserResponse(Page<UserEntity> responsePage) {
        var responseDtoList = responsePage.stream().map(this::toUserResponse).toList();
        return new PageImpl<>(responseDtoList, responsePage.getPageable(), responsePage.getTotalElements());
    }

    Set<UserRoleDto> toUserRoleDtoSet(Set<UserRole> roles);
}
