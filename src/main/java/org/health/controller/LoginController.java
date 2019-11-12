package org.health.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping()
    public String getLoginPage() {
        return "login";
    }
}
