package org.health.service;

import java.util.*;
import org.health.dto.UserDto;
import org.health.entity.userdb.User;

public interface UserService {
    /**
     * method required for adding user
     *
     * @param user - user for adding
     * @return created user
     */
    UserDto addUser(User user);

    /**
     * method required for updating user
     *
     * @param userDto - user for update
     * @return updated user
     */
    UserDto updateUser(UserDto userDto);

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
    List<UserDto> getAllUsers();

    /**
     * method required for deletion user by id
     *
     * @param id - id user got delete
     * @return deleted user by id
     */
    UserDto deleteUser(long id);
}
