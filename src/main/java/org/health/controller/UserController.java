package org.health.controller;

import java.util.*;

import io.swagger.annotations.*;
import org.health.dto.ResponseUserServiceDto;
import org.health.dto.UserDto;
import org.health.entity.*;
import org.health.model.PasswordEncoderData;
import org.health.model.ResponseServiceUser;
import org.health.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Api
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private PasswordEncoderData passwordEncoderData;

    @ApiOperation("Add user")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseUserServiceDto addUser(@RequestBody User user) {
        return new ResponseUserServiceDto(userService.addUser(passwordEncoderData.encodeUserPassword(user)));
    }

    @ApiOperation("Update user")
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseUserServiceDto updateUser(@RequestBody UserDto userDto) {
        return new ResponseUserServiceDto(userService.updateUser(userDto));
    }

    @ApiOperation("Get user by id")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDto getUserById(@PathVariable("id") long id) {
        return userService.getUser(id);
    }

    @ApiOperation("Get all users")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<UserDto> getAllEmployees() {
        return userService.getAllUsers();
    }

    @ApiOperation("Delete user by id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseServiceUser deleteUserById(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

    @Autowired
    public void setUserService(UserService userService, PasswordEncoderData passwordEncoderData) {
        this.userService = userService;
        this.passwordEncoderData = passwordEncoderData;
    }
}
