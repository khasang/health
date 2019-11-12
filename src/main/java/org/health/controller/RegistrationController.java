package org.health.controller;

import org.health.dto.UserDto;
import org.health.entity.User;
import org.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @RequestMapping()
    public String getRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDto addUser(@RequestBody User user) {
        UserController userController = new UserController();
        userController.setUserService(this.userService);
        userController.setPasswordEncoder(this.passwordEncoder);

        // TODO как передать в объект поля по умолчанию, не используя @Autowired в классе в котором создается экземпляр объекта, чтоб в конструкторе инициализировались по умолчанию поля
        return userController.addUser(user);
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
