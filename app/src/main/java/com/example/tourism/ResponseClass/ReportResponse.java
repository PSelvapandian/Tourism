package com.example.tourism.ResponseClass;

import com.example.tourism.ModelClasses.ReportResponseModel;

import java.util.List;
import java.util.Map;

public class ReportResponse
{
    private String timeStamp;
    private Map<String, List<ReportResponseModel>> payload;
    private String message;
    private Integer status;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Map<String, List<ReportResponseModel>> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, List<ReportResponseModel>> payload) {
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
        return "ReportResponse{" +
                "timeStamp='" + timeStamp + '\'' +
                ", payload=" + payload +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
