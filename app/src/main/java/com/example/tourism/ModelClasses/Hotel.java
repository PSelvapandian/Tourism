package com.example.tourism.ModelClasses;

import java.util.Date;

public class Hotel
{
    private Long hotelId;
    private String hotelName;
    private String hotelLocation;
    private Address address;
    private String hotelMobile;
    private Integer numberOfRoom;
    private Date createAt;
    private Date updateAt;

    public void insertHotel(String hotelName, String hotelLocation, Address address, String hotelMobile, Integer numberOfRoom) {
        this.hotelName = hotelName;
        this.hotelLocation = hotelLocation;
        this.address = address;
        this.hotelMobile = hotelMobile;
        this.numberOfRoom = numberOfRoom;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelLocation() {
        return hotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        this.hotelLocation = hotelLocation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getHotelMobile() {
        return hotelMobile;
    }

    public void setHotelMobile(String hotelMobile) {
        this.hotelMobile = hotelMobile;
    }

    public Integer getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(Integer numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
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
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelLocation='" + hotelLocation + '\'' +
                ", address=" + address +
                ", hotelMobile='" + hotelMobile + '\'' +
                ", numberOfRoom=" + numberOfRoom +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
