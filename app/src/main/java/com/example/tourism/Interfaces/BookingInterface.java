package com.example.tourism.Interfaces;

import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.ModelClasses.Room;
import com.example.tourism.ModelClasses.Route;

public interface BookingInterface
{
    void bookedHotel(Hotel hotel);

    void bookedRoom(Room room);

    void bookedRoute(Route route);
}
