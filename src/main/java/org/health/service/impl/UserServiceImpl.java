package org.health.service.impl;

import java.util.*;
import org.health.dao.*;
import org.health.dto.UserDto;
import org.health.entity.*;
import org.health.model.ResponseServiceUser;
import org.health.service.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service("userService")
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private SessionFactory sessionFactory;

    /**
     * <p>Before writing the data, check the presence of all fields filled, if at least one field is empty, cancel
     * the record and return the data on the incorrect field </p>
     *
     * @param user - user for adding
     * @return ResponseUserService
     */
    @Override
    public ResponseServiceUser addUser(User user) {
        ResponseServiceUser responseServiceUser = new ResponseServiceUser(user);
        // Check the status of the fields, if the check did not pass the record.
        if (!responseServiceUser.checkStatusFields()) {
            return responseServiceUser;
        }

        // Checking the presence of the transmitted login
        if (this.isLoginEmpty(user)) {
            responseServiceUser.setMessage("Login exists");
            responseServiceUser.setUser(user);
            return responseServiceUser;
        }

        responseServiceUser.setUser(userDao.addEntity(user));
        return responseServiceUser;
    }

    private boolean isLoginEmpty(User user) {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<User> userLoginCriteria = builder.createQuery(User.class);
        Root<User> userLoginRoot = userLoginCriteria.from(User.class);
        userLoginCriteria.select(userLoginRoot);
        userLoginCriteria.where(builder.equal(userLoginRoot.get("login"), user.getLogin()));
        return sessionFactory.openSession().createQuery(userLoginCriteria).getResultList().size() > 0;
    }

    @Override
    public ResponseServiceUser updateUser(UserDto userDto) {
        //  get user by id and update
        User userByIdDto = userDto.update(userDao.getEntity(userDto.getId()));
        return new ResponseServiceUser(userDao.updateEntity(userByIdDto));
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
        return new ResponseServiceUser(userDao.deleteEntity(userDao.getEntity(id)));
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
