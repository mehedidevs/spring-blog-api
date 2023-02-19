package com.mehedi.blog.services;

import com.mehedi.blog.entities.User;
import com.mehedi.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, Long id);

    UserDto getUserByID(Long id);

    List<UserDto> getAllUser();

    UserDto deleteUser(Long id);
}
