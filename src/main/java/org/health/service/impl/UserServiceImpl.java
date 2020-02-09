package org.health.service.impl;

import java.util.*;

import org.health.dao.*;
import org.health.dto.UserDto;
import org.health.entity.userdb.User;
import org.health.model.PasswordEncoderData;
import org.health.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDto userDto;
    private UserDao userDao;
    private SessionFactory sessionFactory;
    private PasswordEncoderData passwordEncoderData;

    /**
     * <p>Before writing the data, check the presence of all fields filled, if at least one field is empty, cancel
     * the record and return the data on the incorrect field </p>
     *
     * @param user - user for adding
     * @return ResponseUserService
     */
    @Override
    public User add(User user) {
        return userDao.addEntity(passwordEncoderData.encodeUserPassword(user));
    }


    @Override
    public User updateUser(User user) {
        //  get user by id and update
        User userUpdate = userDao.updateEntity(user);
        return userUpdate;
    }

    @Override
    public UserDto getUser(long id) {
        return new UserDto(userDao.getEntity(id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = new ArrayList<>();
        userDao.getAllEntities().iterator().forEachRemaining(u -> userDtoList.add(new UserDto(u)));

        return userDtoList;
    }

    @Override
    public UserDto deleteUser(long id) {
        return new UserDto(userDao.deleteEntity(userDao.getEntity(id)));
    }

    @Override
    public User checkLoginFree(String login) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> userLoginCriteria = builder.createQuery(User.class);
        Root<User> userLoginRoot = userLoginCriteria.from(User.class);
        userLoginCriteria.select(userLoginRoot);
        userLoginCriteria.where(builder.equal(userLoginRoot.get("login"), login));

        List<User> resultList = sessionFactory.openSession().createQuery(userLoginCriteria).getResultList();
        return resultList.size() == 0
                ? null
                : resultList.get(0);
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setUserService(PasswordEncoderData passwordEncoderData) {
        this.passwordEncoderData = passwordEncoderData;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    @Autowired
    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
