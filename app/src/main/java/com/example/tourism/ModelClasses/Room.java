package com.example.tourism.ModelClasses;

import com.example.tourism.Enum.Facility;

import java.util.Date;

public class Room
{
    private Long roomId;
    private String facility;
    private Integer numberOfPerson;
    private Date createAt;
    private Date updateAt;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public Integer getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(Integer numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
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
        return "Room{" +
                "roomId=" + roomId +
                ", facility=" + facility +
                ", numberOfPerson=" + numberOfPerson +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
