package org.health.service.impl;

import java.util.*;

import org.health.dao.*;
import org.health.dto.*;
import org.health.entity.*;
import org.health.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserDto userDto;
    
    @Override
    public User addUser(User user) {
        return userDao.addEntity(user);
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateEntity(user);
    }

    @Override
    public UserDto getUser(long id) {
        return userDto.getUserDto(userDao.getEntity(id));
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllEntities();
    }

    @Override
    public User deleteUser(long id) {
        return userDao.deleteEntity(userDao.getEntity(id));
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
