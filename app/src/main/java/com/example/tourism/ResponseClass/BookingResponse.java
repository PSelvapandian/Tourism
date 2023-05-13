package com.example.tourism.ResponseClass;

import com.example.tourism.ModelClasses.Booking;
import com.example.tourism.ModelClasses.Room;

public class BookingResponse
{
    private Long userId;
    private Room room;
    private Booking booking;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "userId=" + userId +
                ", room=" + room +
                ", booking=" + booking +
                '}';
    }
}
