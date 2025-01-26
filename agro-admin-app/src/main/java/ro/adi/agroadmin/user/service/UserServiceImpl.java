package ro.adi.agroadmin.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.user.converter.UserMapper;
import ro.adi.agroadmin.user.dto.request.LoginUserRequest;
import ro.adi.agroadmin.user.dto.request.RegisterUserRequest;
import ro.adi.agroadmin.user.dto.response.UserLoginResponse;
import ro.adi.agroadmin.user.dto.response.UserRegisterResponse;
import ro.adi.agroadmin.user.jpa.UserRepository;
import ro.adi.agroadmin.user.jpa.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserRegisterResponse register(RegisterUserRequest request) {
        var user = new UserEntity();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var saved = userRepository.save(user);
        return userMapper.toUserRegisterResponse(saved);
    }

    @Override
    public UserLoginResponse authenticate(LoginUserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        return userMapper.toUserLoginResponse(userEntity);
    }
}
