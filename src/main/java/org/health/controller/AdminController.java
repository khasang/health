package org.health.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.health.dto.UserDto;
import org.health.entity.userdb.User;
import org.health.entity.userdb.extend.Admin;
import org.health.service.AdminService;
import org.health.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@Controller
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Admin restApiPost(@RequestBody Admin admin) {
        adminService.addAdmin(admin);

        return admin;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Admin> restApiGetAll() {
        System.out.println("restApiGetAll");
        return null;
//        return adminService.getAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Admin restApiGetId(@PathVariable("id") long id) {
        System.out.println("restApiGetId");
        return null;
//        return adminService.getUser(id);
    }

    @ApiOperation("Update user")
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Admin restApiPut(@RequestBody Admin admin) {
        System.out.println("restApiPut");
        return null;
//        return adminService.updateUser(userDto);
    }

    @ApiOperation("Delete user by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Admin restApiDelete(@PathVariable("id") long id) {
        System.out.println("restApiDelete");
        return null;
//        return adminService.deleteUser(id);
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
}
