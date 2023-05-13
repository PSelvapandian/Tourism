package com.example.tourism.ModelClasses;

public class BookingRequestModel
{
    private Long userId;
    private String name;
    private Booking booking;
    private Room room;
    private Route route;
    private Hotel hotel;
    private String foodType;
    private String facilityType;
    private String bookedDate;
    private Boolean isHotelBooked = false;

    public void insertBooking(Long userId, String name, Booking booking, Room room, Route route, Hotel hotel, String foodType, String facilityType, String bookedDate, Boolean isHotelBooked)
    {
        this.userId = userId;
        this.name = name;
        this.booking = booking;
        this.room = room;
        this.route = route;
        this.hotel = hotel;
        this.foodType = foodType;
        this.facilityType = facilityType;
        this.bookedDate = bookedDate;
        this.isHotelBooked = isHotelBooked;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(String bookedDate) {
        this.bookedDate = bookedDate;
    }

    public Boolean getHotelBooked() {
        return isHotelBooked;
    }

    public void setHotelBooked(Boolean hotelBooked) {
        isHotelBooked = hotelBooked;
    }

    @Override
    public String toString() {
        return "BookingRequestModel{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", booking=" + booking +
                ", room=" + room +
                ", route=" + route +
                ", hotel=" + hotel +
                ", foodType='" + foodType + '\'' +
                ", facilityType='" + facilityType + '\'' +
                ", bookedDate='" + bookedDate + '\'' +
                ", isHotelBooked=" + isHotelBooked +
                '}';
    }
}
