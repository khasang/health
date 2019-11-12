package org.health.model;

import org.health.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserEncode {
    private PasswordEncoder passwordEncoder;

    public UserEncode(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Возвращает clone user, с переопределенным паролем по passwordEncoder
     *
     * @param user clone user and create password encoder
     * @return
     */
    public User encodePassword(User user) {
        User userReturn = new User(user);
        if (!userReturn.getPassword().equals(""))
            userReturn.setPassword(passwordEncoder.encode(userReturn.getPassword()));

        return userReturn;
    }
}
