package com.example.tourism.Database;

import com.example.tourism.ModelClasses.BookingRequestModel;
import com.example.tourism.ModelClasses.CustomUser;
import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.ModelClasses.LoginRequestBuilder;
import com.example.tourism.ModelClasses.Room;
import com.example.tourism.ModelClasses.Route;
import com.example.tourism.ResponseClass.AllBookingData;
import com.example.tourism.ResponseClass.AllHotels;
import com.example.tourism.ResponseClass.AllRooms;
import com.example.tourism.ResponseClass.AllRouts;
import com.example.tourism.ResponseClass.HotelResponse;
import com.example.tourism.ResponseClass.ReportResponse;
import com.example.tourism.ResponseClass.RoomResponse;
import com.example.tourism.ResponseClass.RouteResponse;
import com.example.tourism.ResponseClass.TourBookingResponse;
import com.example.tourism.ResponseClass.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface
{
    @POST("hotel")
    Call<HotelResponse> insertHotel(@Body Hotel hotel);

    @POST("route")
    Call<RouteResponse> insertRoute(@Body Route route);

    @GET("hotel")
    Call<AllHotels> getAllHotels();

    @GET("route")
    Call<AllRouts> getAllRouts();

    @POST("user")
    Call<UserResponse> insertUser(@Body CustomUser customUser);

    @POST("room")
    Call<RoomResponse> insertRoom(@Body Room room);

    @GET("room")
    Call<AllRooms> getAllRooms();

    @POST("login")
    Call<UserResponse> loginUser(@Body LoginRequestBuilder customUser);

    @POST("booking")
    Call<TourBookingResponse> insertBooking(@Body BookingRequestModel bookingRequestModel);

    @GET("user/{id}")
    Call<UserResponse> getUserData(@Path("id") String userId);

    @GET("booking/findAllByUserId/{userId}")
    Call<AllBookingData> getAllBookingByUserId(@Path("userId") String userId);

    @GET("booking")
    Call<AllBookingData> getAllBooking();

    @GET("booking/report")
    Call<ReportResponse> getAllReports(@Query("from") String fromDate, @Query("to") String toDate);

}