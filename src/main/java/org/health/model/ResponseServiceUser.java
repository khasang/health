package org.health.model;

import org.health.entity.User;

/**
 * Объект проверяется перед записью на начие заполнения всех полей
 */
public class ResponseServiceUser {
    private boolean requestSave;
    private String textNoValidation = "NoValidation";
    private User user;

    public ResponseServiceUser() {

    }

    public boolean validationOnEmptinessFields(User user) {
        this.user = new User(user);

        try {
            textNoValidation = "FirstName";
            this.checkingOnEmptiness(user.getFirstName());
            textNoValidation = "LastName";
            this.checkingOnEmptiness(user.getLastName());
            textNoValidation = "Patronymic";
            this.checkingOnEmptiness(user.getPatronymic());
            textNoValidation = "Login";
            this.checkingOnEmptiness(user.getLogin());
            textNoValidation = "Password";
            this.checkingOnEmptiness(user.getPassword());
            textNoValidation = "RoleId";
            this.checkingOnEmptiness(String.valueOf(user.getRoleId()));

        } catch (RuntimeException e) {
            return false;
        }

        textNoValidation = "Ok";
        return true;
    }

    private void checkingOnEmptiness(String text) throws RuntimeException {
        if (text.equals("")) {
            throw new RuntimeException();
        }
    }

    public void requestSave() {
        this.requestSave = true;
    }

    public String getTextNoValidation() {
        return textNoValidation;
    }

    /**
     *
     * @return
     */
    public boolean isRequestSave() {
        return this.requestSave;
    }

    public User getUser() {
        return user;
    }
}
