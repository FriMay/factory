package org.arbinka.factory.dto;

public class AnswerTransfer {
    private Object data;
    private String message;

    public AnswerTransfer(String message) {
        this.data = null;
        this.message = message;
    }

    public AnswerTransfer(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
