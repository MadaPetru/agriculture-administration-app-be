package ro.adi.user.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ro.adi.user.dto.request.LoginUserRequestDto;
import ro.adi.user.dto.request.RegisterUserRequestDto;
import ro.adi.user.dto.request.UpdateUserRequestDto;
import ro.adi.user.dto.request.UserSearchRequestDto;
import ro.adi.user.dto.response.UserLoginResponseDto;
import ro.adi.user.dto.response.UserRegisterResponseDto;
import ro.adi.user.dto.response.UserResponseDto;

@RequestMapping(path = "/v1/users")
public interface UserController {

    @PostMapping("/register")
    UserRegisterResponseDto register(@RequestBody RegisterUserRequestDto requestDto);

    @PostMapping("/login")
    UserLoginResponseDto authenticate(@RequestBody LoginUserRequestDto requestDto);

    @PostMapping("/search")
    Page<UserResponseDto> search(@RequestBody UserSearchRequestDto requestDto);

    @PutMapping
    void updateUser(@RequestBody UpdateUserRequestDto requestDto);

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Integer id);

    @PutMapping("/{id}/reset")
    void resetPassword(@PathVariable Integer id);
}
