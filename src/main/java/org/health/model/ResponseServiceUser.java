package org.health.model;

import org.health.entity.User;

/**
 * <p>The object is checked before writing to the beginning of filling in all fields</p>
 */
public class ResponseServiceUser {
    private boolean validation = true;
    private String message = "No check Status of fields";
    private User user;

    public ResponseServiceUser() {
    }

    public ResponseServiceUser(User user) {
        this.user = new User(user);
    }

    public boolean checkStatusFields() {
        try {
            message = "No data first name";
            this.checkingOnEmptiness(user.getFirstName());
            message = "No data last name";
            this.checkingOnEmptiness(user.getLastName());
            message = "No data patronymic";
            this.checkingOnEmptiness(user.getPatronymic());
            message = "No data login";
            this.checkingOnEmptiness(user.getLogin());
            message = "No data password";
            this.checkingOnEmptiness(user.getPassword());
            message = "No data role id";
            this.checkingOnEmptiness(String.valueOf(user.getRoleId()));

        } catch (RuntimeException e) {
            this.validation = false;
            return false;
        }

        message = "Ok";
        this.validation = true;
        return true;
    }

    private void checkingOnEmptiness(String text) throws RuntimeException {
        if (text.equals("")) {
            throw new RuntimeException();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValidation() {
        return this.validation;
    }
}
