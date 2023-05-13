package com.example.tourism.ResponseClass;


public class TourBookingResponse
{
    private String timeStamp;
    private BookingResponse payload;
    private String message;
    private Integer status;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public BookingResponse getPayload() {
        return payload;
    }

    public void setPayload(BookingResponse payload) {
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
        return "TourBookingResponse{" +
                "timeStamp='" + timeStamp + '\'' +
                ", payload=" + payload +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
