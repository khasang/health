package org.health.dto;

import org.health.model.ResponseServiceUser;

public class ResponseUserServiceDto {
    private boolean requestSave;
    private String textNoValidation;
    private UserDto userDto;

    public ResponseUserServiceDto() {
    }

    public ResponseUserServiceDto(ResponseServiceUser responseServiceUser) {
        this.userDto = new UserDto(responseServiceUser.getUser());
        this.textNoValidation = responseServiceUser.getTextNoValidation();
        this.requestSave = responseServiceUser.isRequestSave();
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public String getTextNoValidation() {
        return textNoValidation;
    }

    public boolean isRequestSave() {
        return requestSave;
    }

    public void setRequestSave(boolean requestSave) {
        this.requestSave = requestSave;
    }

    public void setTextNoValidation(String textNoValidation) {
        this.textNoValidation = textNoValidation;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
