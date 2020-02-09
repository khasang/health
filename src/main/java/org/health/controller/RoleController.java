package org.health.controller;

import org.health.dto.RoleDto;
import org.health.dto.RoleUserDto;
import org.health.entity.Role;
import org.health.service.RoleService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Role updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Role deleteRoleById(@PathVariable("id") long id) {
        return roleService.deleteRole(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Role getRoleById(@PathVariable("id") long id) {
        return roleService.getRole(id);
    }

    /**
     * Метод возвращает все роли в формате JSON, пример ответа
     * <pre>
     * {"data":[
     *      {"id": 1, "name": "ROLE_ADMIN", "description": null },
     *      {"id": 2, "name": "ROLE_DOCTOR", "description": null },
     *      {"id": 3, "name": "ROLE_PATIENT",   "description": null },
     *      {"id": 4, "name": "ROLE_UNKNOWN", "description": null }
     *      ],
     * "responseNumber": 0,
     * "message": "Ok"
     * }</pre>
     *
     * @return RoleDto
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity getAllRoles() {
        Response<List<RoleDto>> response = new Response<>();

        try {
            response.setData(roleService.getAllRoles());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Метод возвращает данные о пользователях занимающих роль по id в формате JSON, пример ответа
     * <pre>
     * {
     *   "data": [
     *      {"id": 1, "firstName": "firstName_1", "lastName": "lastName_1", "patronymic": "patronymic_1", "login": "login_1", "currantRole": null},
     *      {"id": 2, "firstName": "firstName_2", "lastName": "lastName_2", "patronymic": "patronymic_2", "login": "login_2", "currantRole": null},
     *      {"id": 3, "firstName": "firstName_3", "lastName": "lastName_3", "patronymic": "patronymic_3", "login": "login_3", "currantRole": null}
     *      ],
     *   "responseNumber": 0,
     *   "message": "Ok"
     * }
     * </pre>
     * <p>"responseNumber": 0 - все в порядке</p>
     * <p>"responseNumber": 1 - Exception</p>
     * <p>"responseNumber": 2 - ObjectNotFoundException</p>
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity getUsersByIdRole(@PathVariable("id") long id) {
        Response<RoleUserDto> response = new Response<>();

        try {
            response.setData(roleService.getUsersByIdRole(id));
            return ResponseEntity.ok(response);

        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
