package org.health.controller;

public class Response<T> {
    private T data;
    private int responseNumber = 0;
    private String message = "Ok";

    public Response() {
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public int getResponseNumber() {
        return responseNumber;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Response{" +
                "data=" + data +
                ", responseNumber=" + responseNumber +
                ", message='" + message + '\'' +
                '}';
    }
}
