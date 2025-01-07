package ro.adi.agroadmin.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.adi.agroadmin.user.dto.response.UserLoginResponse;
import ro.adi.agroadmin.user.jpa.UserRepository;
import ro.adi.agroadmin.user.jpa.entity.UserEntity;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public UserLoginResponse signup(RegisterUserRequestDto input) {
        var user = new UserEntity();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        UserEntity save = userRepository.save(user);
        return x(save);
    }

    public UserLoginResponse authenticate(LoginUserRequestDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        var userEntity = userRepository.findByEmail(input.getEmail())
                .orElseThrow();
        return x(userEntity);
    }

    private UserLoginResponse x(UserEntity y) {
        return UserLoginResponse.builder()
                .email(y.getEmail())
                .password(y.getPassword())
                .build();
    }
}
