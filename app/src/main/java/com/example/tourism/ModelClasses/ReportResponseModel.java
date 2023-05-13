package com.example.tourism.ModelClasses;

public class ReportResponseModel
{
    private Booking booking;
    private Route route;

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return "ReportResponseModel{" +
                "booking=" + booking +
                ", route=" + route +
                '}';
    }
}
