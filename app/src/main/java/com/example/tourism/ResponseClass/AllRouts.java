package com.example.tourism.ResponseClass;

import com.example.tourism.ModelClasses.Route;

import java.util.List;

public class AllRouts
{
    private String timeStamp;
    private List<Route> payload;
    private String message;
    private Integer status;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<Route> getPayload() {
        return payload;
    }

    public void setPayload(List<Route> payload) {
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AllRouts{" +
                "timeStamp='" + timeStamp + '\'' +
                ", payload=" + payload +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
