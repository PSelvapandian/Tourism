package com.example.tourism.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.tourism.Database.ApiClient;
import com.example.tourism.Database.ApiInterface;
import com.example.tourism.Enum.RolesEnum;
import com.example.tourism.ModelClasses.Booking;
import com.example.tourism.ModelClasses.BookingRequestModel;
import com.example.tourism.ModelClasses.CustomUser;
import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.ModelClasses.LoginRequestBuilder;
import com.example.tourism.ModelClasses.ReportResponseModel;
import com.example.tourism.ModelClasses.Room;
import com.example.tourism.ModelClasses.Route;
import com.example.tourism.ResponseClass.AllBookingData;
import com.example.tourism.ResponseClass.AllHotels;
import com.example.tourism.ResponseClass.AllRooms;
import com.example.tourism.ResponseClass.AllRouts;
import com.example.tourism.ResponseClass.BookingResponse;
import com.example.tourism.ResponseClass.ErrorResponse;
import com.example.tourism.ResponseClass.HotelResponse;
import com.example.tourism.ResponseClass.ReportResponse;
import com.example.tourism.ResponseClass.RoomResponse;
import com.example.tourism.ResponseClass.RouteResponse;
import com.example.tourism.ResponseClass.TourBookingResponse;
import com.example.tourism.ResponseClass.UserResponse;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitViewModel extends AndroidViewModel
{
    private final SharedPreferenceHelper sharedPreferenceHelper = SharedPreferenceHelper.getInstance(getApplication());
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
    public MutableLiveData<CustomUser> loginResult = new MutableLiveData<>(null);
    public MutableLiveData<String> successResponse = new MutableLiveData<>("");
    public MutableLiveData<List<Hotel>> allHotels = new MutableLiveData<>(null);
    public MutableLiveData<List<Route>> allRouts = new MutableLiveData<>(null);
    public MutableLiveData<List<Room>> allRooms = new MutableLiveData<>(null);
    public MutableLiveData<List<Booking>> allBookingLiveData = new MutableLiveData<>(null);
    public MutableLiveData<Map<String, List<ReportResponseModel>>> allReportData = new MutableLiveData<>(null);
    public MutableLiveData<BookingResponse> bookingSuccess = new MutableLiveData<>(null);
    public MutableLiveData<String> errorResponse = new MutableLiveData<>("");

    public RetrofitViewModel(@NonNull Application application)
    {
        super(application);
    }

    public void insertHotel(Hotel hotel)
    {
        Call<HotelResponse> call = apiInterface.insertHotel(hotel);
        call.enqueue(new Callback<HotelResponse>() {
            @Override
            public void onResponse(@NonNull Call<HotelResponse> call, @NonNull Response<HotelResponse> response) {
                if (response.body() != null)
                {
                    HotelResponse hotelResponse = response.body();
                    if (hotelResponse.getStatus() == 200)
                    {
                        successResponse.setValue(hotelResponse.getMessage());
                    }
                    else
                    {
                        errorResponse.setValue(hotelResponse.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<HotelResponse> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void getAllHotels()
    {
        Call<AllHotels> call = apiInterface.getAllHotels();
        call.enqueue(new Callback<AllHotels>() {
            @Override
            public void onResponse(@NonNull Call<AllHotels> call, @NonNull Response<AllHotels> response)
            {
                if (response.body() != null)
                {
                    AllHotels allHotelsModel = response.body();
                    if (allHotelsModel.getStatus() == 200)
                    {
                        allHotels.setValue(allHotelsModel.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(allHotelsModel.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllHotels> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void insertRoute(Route route)
    {
        Call<RouteResponse> call = apiInterface.insertRoute(route);
        call.enqueue(new Callback<RouteResponse>() {
            @Override
            public void onResponse(@NonNull Call<RouteResponse> call, @NonNull Response<RouteResponse> response) {
                if (response.body() != null)
                {
                    RouteResponse routeResponse = response.body();
                    if (routeResponse.getStatus() == 200)
                    {
                        successResponse.setValue(routeResponse.getMessage());
                    }
                    else
                    {
                        errorResponse.setValue(routeResponse.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RouteResponse> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void getAllRouts()
    {
        Call<AllRouts> call = apiInterface.getAllRouts();
        call.enqueue(new Callback<AllRouts>() {
            @Override
            public void onResponse(@NonNull Call<AllRouts> call, @NonNull Response<AllRouts> response)
            {
                if (response.body() != null)
                {
                    AllRouts allRoutsModel = response.body();
                    if (allRoutsModel.getStatus() == 200)
                    {
                        allRouts.setValue(allRoutsModel.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(allRoutsModel.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllRouts> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void createUser(CustomUser customUser)
    {
        Call<UserResponse> call = apiInterface.insertUser(customUser);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response)
            {
                if (response.body() != null)
                {
                    UserResponse userResponse = response.body();
                    if (userResponse.getStatus() == 200)
                    {
                        loginResult.setValue(userResponse.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(userResponse.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void loginAccount(LoginRequestBuilder customUser)
    {
        Call<UserResponse> call = apiInterface.loginUser(customUser);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response)
            {
                if (response.body() != null)
                {
                    UserResponse userResponse = response.body();
                    if (userResponse.getStatus() == 200)
                    {
                        sharedPreferenceHelper.setLoginStatus(true);
                        sharedPreferenceHelper.setUserId(String.valueOf(userResponse.getPayload().getUserId()));
                        sharedPreferenceHelper.setUserName(userResponse.getPayload().getName());
                        sharedPreferenceHelper.setRole(RolesEnum.USER.name());
                        loginResult.setValue(userResponse.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(userResponse.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t)
            {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void getUserData(String userId)
    {
        Call<UserResponse> call = apiInterface.getUserData(userId);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response)
            {
                if (response.body() != null)
                {
                    UserResponse userResponse = response.body();
                    if (userResponse.getStatus() == 200)
                    {
                        loginResult.setValue(userResponse.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(userResponse.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t)
            {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void insertRoom(Room room)
    {
        Call<RoomResponse> call = apiInterface.insertRoom(room);
        call.enqueue(new Callback<RoomResponse>() {
            @Override
            public void onResponse(@NonNull Call<RoomResponse> call, @NonNull Response<RoomResponse> response) {
                if (response.body() != null)
                {
                    RoomResponse roomResponse= response.body();
                    if (roomResponse.getStatus() == 200)
                    {
                        successResponse.setValue(roomResponse.getMessage());
                    }
                    else
                    {
                        errorResponse.setValue(roomResponse.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<RoomResponse> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void getAllRooms()
    {
        Call<AllRooms> call = apiInterface.getAllRooms();
        call.enqueue(new Callback<AllRooms>() {
            @Override
            public void onResponse(@NonNull Call<AllRooms> call, @NonNull Response<AllRooms> response) {
                if (response.body() != null)
                {
                    AllRooms allRoomModel = response.body();
                    if (allRoomModel.getStatus() == 200)
                    {
                        allRooms.setValue(allRoomModel.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(allRoomModel.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllRooms> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void insertBooking(BookingRequestModel bookingRequestModel)
    {
        Call<TourBookingResponse> call = apiInterface.insertBooking(bookingRequestModel);
        call.enqueue(new Callback<TourBookingResponse>() {
            @Override
            public void onResponse(@NonNull Call<TourBookingResponse> call, @NonNull Response<TourBookingResponse> response)
            {
                if (response.body() != null)
                {
                    TourBookingResponse tourBookingResponse = response.body();
                    if (tourBookingResponse.getStatus() == 200)
                    {
                        bookingSuccess.setValue(tourBookingResponse.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(tourBookingResponse.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<TourBookingResponse> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void getAllBookings()
    {
        Call<AllBookingData> call = apiInterface.getAllBooking();
        call.enqueue(new Callback<AllBookingData>() {
            @Override
            public void onResponse(@NonNull Call<AllBookingData> call, @NonNull Response<AllBookingData> response) {
                if (response.body() != null)
                {
                    AllBookingData allBookingData = response.body();
                    if (allBookingData.getStatus() == 200)
                    {
                        allBookingLiveData.setValue(allBookingData.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(allBookingData.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllBookingData> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void getAllBookingsByUserId(String userId)
    {
        Call<AllBookingData> call = apiInterface.getAllBookingByUserId(userId);
        call.enqueue(new Callback<AllBookingData>() {
            @Override
            public void onResponse(@NonNull Call<AllBookingData> call, @NonNull Response<AllBookingData> response) {
                if (response.body() != null)
                {
                    AllBookingData allBookingData = response.body();
                    if (allBookingData.getStatus() == 200)
                    {
                        allBookingLiveData.setValue(allBookingData.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(allBookingData.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AllBookingData> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }

    public void getAllReport(String fromDate, String toDate)
    {
        Call<ReportResponse> call = apiInterface.getAllReports(fromDate, toDate);
        call.enqueue(new Callback<ReportResponse>() {
            @Override
            public void onResponse(@NonNull Call<ReportResponse> call, @NonNull Response<ReportResponse> response) {
                if (response.body() != null)
                {
                    ReportResponse reportResponse = response.body();
                    if (reportResponse.getStatus() == 200)
                    {
                        allReportData.setValue(reportResponse.getPayload());
                    }
                    else
                    {
                        errorResponse.setValue(reportResponse.getMessage());
                    }
                }
                if (!response.isSuccessful())
                {
                    Gson gson = new GsonBuilder().create();
                    ErrorResponse errorResponseModel;
                    try
                    {
                        errorResponseModel = gson.fromJson(Objects.requireNonNull(response.errorBody()).string(), ErrorResponse.class);
                        errorResponse.setValue(errorResponseModel.getError());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ReportResponse> call, @NonNull Throwable t) {
                errorResponse.setValue(t.getMessage());
            }
        });
    }
}