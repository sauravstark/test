package com.example.leaderboard.controller;

import java.util.List;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.leaderboard.model.user.CreateUserRequest;
import com.example.leaderboard.model.user.UpdateUserRequest;
import com.example.leaderboard.model.user.UserDto;
import com.example.leaderboard.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam int page,
                                                     @RequestParam int count) {
        val userDtoList = userService.findUsersByPage(page, count).stream()
                .map(UserDto::fromUserEntity)
                .toList();
        return ResponseEntity.ok(userDtoList);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        val optionalUserDto = userService.findUserByUsername(username).map(UserDto::fromUserEntity);
        return ResponseEntity.of(optionalUserDto);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest createUserRequest) {
        val userDto = UserDto.fromUserEntity(userService.createUser(createUserRequest));
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String username,
                                              @RequestBody UpdateUserRequest updateUserRequest) {
        val optionalUserDto = userService.updateUser(username, updateUserRequest)
                .map(UserDto::fromUserEntity);
        return ResponseEntity.of(optionalUserDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        val optionalUserDto = userService.deleteUser(username).map(UserDto::fromUserEntity);
        if (optionalUserDto.isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
