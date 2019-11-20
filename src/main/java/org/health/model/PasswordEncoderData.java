package org.health.model;

import org.health.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The class wraps the User object to implement password data encryption
 */
public class PasswordEncoderData {
    private PasswordEncoder passwordEncoder;

    public PasswordEncoderData() {
    }

    public User encodeUserPassword(User user) {
        User userData = new User(user);
        if (userData.getPassword() != null && !userData.getPassword().equals("")) {
            userData.setPassword(passwordEncoder.encode(userData.getPassword()));
        }

        return userData;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}
