package com.example.tourism.ModelClasses;

import com.example.tourism.Enum.BookingStatus;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable
{
    private Long bookingId;
    private String bookingReferenceNo;
    private String bookerName;
    private Long userId;
    private Long routeId;
    private Long roomHistoryId;
    private BookingStatus status;
    private String bookedDate;
    private Double fareAmt;
    private Date createAt;
    private Date updateAt;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingReferenceNo() {
        return bookingReferenceNo;
    }

    public void setBookingReferenceNo(String bookingReferenceNo) {
        this.bookingReferenceNo = bookingReferenceNo;
    }

    public String getBookerName() {
        return bookerName;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public Long getRoomHistoryId() {
        return roomHistoryId;
    }

    public void setRoomHistoryId(Long roomHistoryId) {
        this.roomHistoryId = roomHistoryId;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Double getFareAmt() {
        return fareAmt;
    }

    public void setFareAmt(Double fareAmt) {
        this.fareAmt = fareAmt;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingReferenceNo='" + bookingReferenceNo + '\'' +
                ", bookerName='" + bookerName + '\'' +
                ", userId=" + userId +
                ", routeId=" + routeId +
                ", roomHistoryId=" + roomHistoryId +
                ", status=" + status +
                ", bookedDate='" + bookedDate + '\'' +
                ", fareAmt=" + fareAmt +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
