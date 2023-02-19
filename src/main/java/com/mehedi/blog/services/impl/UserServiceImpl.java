package com.mehedi.blog.services.impl;

import com.mehedi.blog.entities.User;
import com.mehedi.blog.exceptions.ResourceNotFoundException;
import com.mehedi.blog.payloads.UserDto;
import com.mehedi.blog.repositories.UserRepo;
import com.mehedi.blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = this.userRepo.findById(id.intValue())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", id)
                );
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto getUserByID(Long id) {

        User user = this.userRepo.findById(id.intValue())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", id)
                );

        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users = this.userRepo.findAll();

        return users.stream().map(user ->
                this.userToDto(user)
        ).collect(Collectors.toList());
    }

    @Override
    public UserDto deleteUser(Long id) {

        User user = this.userRepo.findById(id.intValue())
                .orElseThrow(
                        () -> new ResourceNotFoundException("User", "id", id)
                );

        this.userRepo.delete(user);

        return userToDto(user);


    }


    private User dtoToUser(UserDto userDto) {

        //user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return modelMapper.map(userDto, User.class);
    }


    private UserDto userToDto(User user) {

        //userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return modelMapper.map(user, UserDto.class);

    }
}
