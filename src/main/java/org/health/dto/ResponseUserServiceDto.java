package org.health.dto;

import org.health.model.ResponseServiceUser;

public class ResponseUserServiceDto {
    private boolean validation;
    private String message;
    private UserDto userDto;

    public ResponseUserServiceDto() {
    }

    public ResponseUserServiceDto(ResponseServiceUser responseServiceUser) {
        this.userDto = new UserDto(responseServiceUser.getUser());
        this.message = responseServiceUser.getMessage();
        this.validation = responseServiceUser.isValidation();
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public String getMessage() {
        return message;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public void setMessage(String textNoValidation) {
        this.message = textNoValidation;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
