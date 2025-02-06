package ro.adi.agroadmin.user.service;

import org.springframework.data.domain.PageImpl;
import ro.adi.agroadmin.user.dto.request.LoginUserRequest;
import ro.adi.agroadmin.user.dto.request.RegisterUserRequest;
import ro.adi.agroadmin.user.dto.request.UpdateUserRequest;
import ro.adi.agroadmin.user.dto.request.UserSearchRequest;
import ro.adi.agroadmin.user.dto.response.UserLoginResponse;
import ro.adi.agroadmin.user.dto.response.UserRegisterResponse;
import ro.adi.agroadmin.user.dto.response.UserResponse;

public interface UserService {

    UserRegisterResponse register(RegisterUserRequest request);

    UserLoginResponse authenticate(LoginUserRequest request);

    PageImpl<UserResponse> search(UserSearchRequest requestDto);

    void updateUser(UpdateUserRequest request);
    String findUserEmailById(Integer id);
    boolean existsUserByEmail(String email);

    void deleteUser(Integer id);

    void resetPassword(Integer id);
}
