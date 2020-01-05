package org.health.controller;

import org.health.dto.UserDto;
import org.health.entity.Role;
import org.health.entity.userdb.User;
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
    private RoleController roleController;

    @RequestMapping()
    public String getRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public UserDto addUser(@RequestBody User user) {
        // When registering a new user, the user always gets the default role ROLE_UNKNOWN
        user.setCurrentRole(roleController.getRoleById(1));
        return this.userController.addUser(user);
    }

    @Autowired
    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    @Autowired
    public void setRoleController(RoleController roleController) {
        this.roleController = roleController;
    }
}
