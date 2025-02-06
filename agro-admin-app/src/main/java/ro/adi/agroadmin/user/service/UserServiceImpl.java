package ro.adi.agroadmin.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.adi.agroadmin.common.entity.UserRole;
import ro.adi.agroadmin.config.JwtProperties;
import ro.adi.agroadmin.user.converter.UserMapper;
import ro.adi.agroadmin.user.dto.request.LoginUserRequest;
import ro.adi.agroadmin.user.dto.request.RegisterUserRequest;
import ro.adi.agroadmin.user.dto.request.UpdateUserRequest;
import ro.adi.agroadmin.user.dto.request.UserSearchRequest;
import ro.adi.agroadmin.user.dto.response.UserLoginResponse;
import ro.adi.agroadmin.user.dto.response.UserRegisterResponse;
import ro.adi.agroadmin.user.dto.response.UserResponse;
import ro.adi.agroadmin.user.jpa.UserRepository;
import ro.adi.agroadmin.user.jpa.UserRoleRepository;
import ro.adi.agroadmin.user.jpa.entity.UserEntity;
import ro.adi.agroadmin.user.jpa.entity.UserRoleEntity;
import ro.adi.agroadmin.user.jpa.key.UserRoleKey;
import ro.adi.agroadmin.user.specification.UserSpecificationUtility;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public UserRegisterResponse register(RegisterUserRequest request) {
        var user = new UserEntity();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        var saved = userRepository.save(user);
        var userRoleEntity = new UserRoleEntity();
        UserRoleKey userRoleKey = new UserRoleKey();
        userRoleKey.setUserId(saved.getId());
        userRoleKey.setRole(UserRole.USER.name());
        userRoleEntity.setId(userRoleKey);
        userRoleRepository.save(userRoleEntity);
        return userMapper.toUserRegisterResponse(saved);
    }

    @Override
    @Transactional
    public UserLoginResponse authenticate(LoginUserRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var userEntity = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var roles = userRoleRepository.findAllByUserId(userEntity.getId());
        return userMapper.toUserLoginResponse(userEntity, roles);
    }

    @Override
    public PageImpl<UserResponse> search(UserSearchRequest request) {
        var pageable = request.getPageable();
        var specification = UserSpecificationUtility.search(request);
        Page<UserEntity> entities = userRepository.findAll(specification, pageable);
        var responses = userMapper.toPageImplUserResponse(entities);
        responses.forEach(response -> {
            var roles = userRoleRepository.findAllByUserId(response.getId());
            response.setRoles(roles);
        });
        return responses;
    }

    @Override
    @Transactional
    public void updateUser(UpdateUserRequest request) {
        updateUserRoles(request);
        var entity = userRepository.findById(request.getId()).get();
        entity.setEmail(request.getEmail());
        entity.setVersion(request.getVersion());
        userRepository.save(entity);
    }

    @Override
    public String findUserEmailById(Integer id) {
        return userRepository.findById(id).get().getEmail();
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserEntityByEmail(email);
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        userRoleRepository.deleteAllById_UserId(id);
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void resetPassword(Integer id) {
        var entity = userRepository.findById(id).get();
        entity.setPassword(jwtProperties.getValueForPasswordReset());
        userRepository.save(entity);
    }

    private void updateUserRoles(UpdateUserRequest request) {
        var roles = request.getRoles();
        userRoleRepository.deleteAllById_UserId(request.getId());
        var userRoleEntities = new ArrayList<UserRoleEntity>();
        roles.forEach(role -> {
            var userRoleEntity = new UserRoleEntity();
            var userRoleKey = new UserRoleKey();
            userRoleKey.setRole(role.name());
            userRoleKey.setUserId(request.getId());
            userRoleEntity.setId(userRoleKey);
            userRoleEntities.add(userRoleEntity);
        });
        userRoleRepository.saveAll(userRoleEntities);
    }
}