package com.example.tourism.ResponseClass;

import com.example.tourism.ModelClasses.Hotel;

public class HotelResponse
{
    private String timeStamp;
    private Hotel payload;
    private String message;
    private Integer status;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Hotel getPayload() {
        return payload;
    }

    public void setPayload(Hotel payload) {
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
        return "HotelResponse{" +
                "timeStamp='" + timeStamp + '\'' +
                ", payload=" + payload +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
