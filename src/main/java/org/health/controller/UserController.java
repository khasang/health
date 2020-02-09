package org.health.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.health.dto.UserDto;
import org.health.entity.userdb.User;
import org.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @ApiOperation("Add user")
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDto addUser(@RequestBody User user) {
        User userAdd = userService.add(user);
        UserDto userDto = new UserDto(userAdd);
        return userDto;
    }

    @ApiOperation("Update user")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDto updateUser(@RequestBody User user) {
        User userUpdate = userService.updateUser(user);
        UserDto userDto = new UserDto(userUpdate);
        return userDto;
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
    public UserDto deleteUserById(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
