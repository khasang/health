package org.health.service;

import java.util.*;

import org.health.dto.*;
import org.health.entity.*;

public interface UserService {
    /**
     * method required for adding user
     *
     * @param user - user for adding
     * @return created user
     */
    User addUser(User user);

    /**
     * method required for updating user
     *
     * @param user - user for update
     * @return updated user
     */
    User updateUser(User user);

    /**
     * method required for getting user by id
     *
     * @param id - id user got getting
     * @return user by id
     */
    UserDto getUser(long id);

    /**
     * method required for getting all users
     *
     * @return all users
     */
    List<User> getAllUsers();

    /**
     * method required for deletion user by id
     *
     * @param id - id user got delete
     * @return deleted user by id
     */
    User deleteUser(long id);
}
