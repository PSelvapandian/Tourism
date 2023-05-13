package com.example.tourism.ModelClasses;

import com.example.tourism.Enum.Facility;
import com.example.tourism.Enum.Food;

import java.util.Date;

public class RoomHistory
{
    private Long historyId;
    private Long userId;
    private String bookingRef;
    private Long rooId;
    private Long hotelId;
    private Food foodType;
    private String bookedDate;
    private Facility facility;
    private Date createAt;
    private Date updateAt;

    public Long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    public Long getRooId() {
        return rooId;
    }

    public void setRooId(Long rooId) {
        this.rooId = rooId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public Food getFoodType() {
        return foodType;
    }

    public void setFoodType(Food foodType) {
        this.foodType = foodType;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
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
        return "RoomHistory{" +
                "historyId=" + historyId +
                ", userId=" + userId +
                ", bookingRef='" + bookingRef + '\'' +
                ", rooId=" + rooId +
                ", hotelId=" + hotelId +
                ", foodType=" + foodType +
                ", bookedDate='" + bookedDate + '\'' +
                ", facility=" + facility +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
