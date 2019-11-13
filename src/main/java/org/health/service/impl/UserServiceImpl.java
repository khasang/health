package org.health.service.impl;

import java.util.*;

import org.health.dao.*;
import org.health.dto.UserDto;
import org.health.entity.*;
import org.health.model.ResponseServiceUser;
import org.health.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserDto userDto;

    /**
     * Перед записью данных, проверяем наличие заполненных всех полей, если хотябя одно поле пустое, отмена записи и
     * возвращаем данные по некорректному полю
     *
     * @param user - user for adding
     * @return ResponseUserService
     */
    @Override
    public ResponseServiceUser addUser(User user) {
        ResponseServiceUser responseServiceUser = new ResponseServiceUser();
        // Проверяем состояние полей, если проверку не прошли запись отменить
        if (!responseServiceUser.validationOnEmptinessFields(user)) {
            return responseServiceUser;
        }

        // Переключаем состояние объекта ResponseUserService, проверка пройдена
        responseServiceUser.requestSave();
        // передаем данные на запись, далее получаем и проверяем состоние полей
        responseServiceUser.validationOnEmptinessFields(userDao.addEntity(user));

        return responseServiceUser;
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
