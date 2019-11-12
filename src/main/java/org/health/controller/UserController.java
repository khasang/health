package org.health.controller;

import java.util.*;
import io.swagger.annotations.*;
import org.health.dto.*;
import org.health.entity.*;
import org.health.model.UserEncode;
import org.health.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Api
@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @ApiOperation("Add user")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDto addUser(@RequestBody User user) {
        if (user != null) {
            UserEncode userEncode = new UserEncode(passwordEncoder);
            User addUser = userEncode.encodePassword(user);

            // TODO определиться кто (какой класс) за что отвечает, контроллер делегирует методы другим классам, а логика в моделе? тогда переделать в модель (сервис)
            // TODO перед записью в БД реализовать чтение данных на предмет перирегистрации существующего пользователя
            return new UserDto().getUserDto(userService.addUser(addUser));
        }

        return null;
    }

    @ApiOperation("Update user")
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
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
    public List<User> getAllEmployees() {
        return userService.getAllUsers();
    }

    @ApiOperation("Delete user by id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public User deleteUserById(@PathVariable("id") long id) {
        return userService.deleteUser(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
