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
    public ResponseServiceUser updateUser(UserDto userDto) {
        ResponseServiceUser responseServiceUser = new ResponseServiceUser();
        User user = userDao.getEntity(userDto.getId());

        if (!responseServiceUser.validationOnEmptinessFields(user)) {
            return responseServiceUser;
        }

        // Переключаем состояние объекта ResponseUserService, проверка пройдена
        responseServiceUser.requestSave();
        // Получаем объект по id
        User userByIdDto = userDto.create(userDao.getEntity(userDto.getId()));
        // передаем данные на запись, далее получаем и проверяем состоние полей
        responseServiceUser.validationOnEmptinessFields(userDao.updateEntity(userByIdDto, userByIdDto.getId()));

        return responseServiceUser;
    }

    @Override
    public UserDto getUser(long id) {
        return new UserDto(userDao.getEntity(id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        userDao.getAllEntities().iterator().forEachRemaining(user -> userDtoList.add(new UserDto(user)));

        return userDtoList;
    }

    @Override
    public ResponseServiceUser deleteUser(long id) {
        ResponseServiceUser responseServiceUser = new ResponseServiceUser();
        responseServiceUser.requestSave();
        // передаем данные на запись, далее получаем и проверяем состоние полей
        responseServiceUser.validationOnEmptinessFields(userDao.deleteEntity(userDao.getEntity(id)));
        return responseServiceUser;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
