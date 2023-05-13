package com.example.tourism.ModelClasses;

import java.util.Date;

public class Route
{
    private Long routeId;
    private String fromRoute;
    private String toRoute;
    private String reservationType;
    private String pickUpPoint;
    private String pickUpTime;
    private String dropPoint;
    private String dropTime;
    private Double fare;
    private Date createAt;
    private Date updateAt;

    public void insertRoute(String fromRoute, String toRoute, String reservationType, String pickUpPoint, String pickUpTime, String dropPoint, String dropTime, Double fare) {
        this.fromRoute = fromRoute;
        this.toRoute = toRoute;
        this.reservationType = reservationType;
        this.pickUpPoint = pickUpPoint;
        this.pickUpTime = pickUpTime;
        this.dropPoint = dropPoint;
        this.dropTime = dropTime;
        this.fare = fare;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public String getFromRoute() {
        return fromRoute;
    }

    public void setFromRoute(String fromRoute) {
        this.fromRoute = fromRoute;
    }

    public String getToRoute() {
        return toRoute;
    }

    public void setToRoute(String toRoute) {
        this.toRoute = toRoute;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(String pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getDropPoint() {
        return dropPoint;
    }

    public void setDropPoint(String dropPoint) {
        this.dropPoint = dropPoint;
    }

    public String getDropTime() {
        return dropTime;
    }

    public void setDropTime(String dropTime) {
        this.dropTime = dropTime;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
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

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", fromRoute='" + fromRoute + '\'' +
                ", toRoute='" + toRoute + '\'' +
                ", reservationType='" + reservationType + '\'' +
                ", pickUpPoint='" + pickUpPoint + '\'' +
                ", pickUpTime='" + pickUpTime + '\'' +
                ", dropPoint='" + dropPoint + '\'' +
                ", dropTime='" + dropTime + '\'' +
                ", fare=" + fare +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
}
