package com.mehedi.blog.controllers;

import com.mehedi.blog.payloads.ApiResponse;
import com.mehedi.blog.payloads.UserDto;
import com.mehedi.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUSer(
            @RequestBody UserDto userDto
    ) {
        UserDto createUserDto = userService.createUser(userDto);

        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @RequestBody UserDto userDto,
            @PathVariable("id") Long uid
    ) {
        UserDto upadteUserDto = this.userService.updateUser(userDto, uid);

        return new ResponseEntity<>(upadteUserDto, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable("id") Long uid
    ) {
        UserDto upadteUserDto = this.userService.getUserByID(uid);

        return new ResponseEntity<UserDto>(upadteUserDto, HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(
            @PathVariable("id") Long uid
    ) {
        UserDto upadteUserDto = this.userService.deleteUser(uid);

        return new ResponseEntity<>(new ApiResponse("User deleted Successfully !", true), HttpStatus.OK);

    }


    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(
            @RequestBody UserDto userDto
    ) {
        List<UserDto> getAllUserDto = this.userService.getAllUser();

        return new ResponseEntity<>(getAllUserDto, HttpStatus.OK);

    }

}
