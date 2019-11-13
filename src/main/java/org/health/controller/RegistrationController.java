package org.health.controller;

import org.health.dto.ResponseUserServiceDto;
import org.health.dto.UserDto;
import org.health.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserController userController;

    @RequestMapping()
    public String getRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseUserServiceDto addUser(@RequestBody User user) {
        return this.userController.addUser(user);
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}
