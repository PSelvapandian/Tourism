package com.example.tourism;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourism.ModelClasses.Booking;
import com.example.tourism.ModelClasses.BookingRequestModel;
import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.ModelClasses.Room;
import com.example.tourism.ModelClasses.Route;
import com.example.tourism.ResponseClass.BookingResponse;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityUserBookingBinding;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UserBookingActivity extends AppCompatActivity
{
    ActivityUserBookingBinding binding;
    SharedPreferenceHelper sharedPreferenceHelper;
    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    String tourDate = "";
    Hotel bookedHotel = null;
    Room bookedRoom = null;
    Route bookedRoute = null;
    RetrofitViewModel retrofitViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBookingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        getBookingData();

        binding.edtTourDate.setOnClickListener(v -> getDate());

        binding.spinnerFacility.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.hotel_facility)));

        binding.spinnerFoodType.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.food_type)));

        binding.btnSelectHotel.setOnClickListener(v -> startActivityForResult(new Intent(this, ViewHotelInfo.class), 1001));

        binding.btnSelectRoute.setOnClickListener(v -> startActivityForResult(new Intent(this, ViewRouteInfo.class), 1001));

        binding.btnSelectRoom.setOnClickListener(v -> startActivityForResult(new Intent(this, ViewTravelInfo.class), 1001));

        binding.ivBack.setOnClickListener(v -> finish());

        binding.btnBookTour.setOnClickListener(v -> bookMyTour());

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, UserBookingActivity.this);
            }
        });

        retrofitViewModel.bookingSuccess.observe(this, bookingResponse -> {
            if (bookingResponse != null)
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                showSuccessDialog();
//                MessageDialog.showAlertDialog("User Tour Booked Successfully!", UserBookingActivity.this);
                clearData();
            }
        });

    }

    private void showSuccessDialog() {
        Dialog dialog = new Dialog(UserBookingActivity.this);
        dialog.setContentView(R.layout.booking_success_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        ImageView exit = dialog.findViewById(R.id.btn_exit);
        exit.setOnClickListener(v -> {
            dialog.dismiss();
            startActivity(new Intent(this, UserBookingActivity.class));
            finish();
        });

        dialog.show();
    }

    private void clearData()
    {
        sharedPreferenceHelper.setHotelInfo("");
        sharedPreferenceHelper.setRoomInfo("");
        sharedPreferenceHelper.setRouteInfo("");
        tourDate = "";
        bookedRoom = null;
        bookedHotel = null;
        bookedRoute = null;
    }

    @SuppressLint("SimpleDateFormat")
    private void getDate()
    {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("DefaultLocale") DatePickerDialog datePicker = new DatePickerDialog(this, R.style.DatePickerDialogTheme, (view, year, month, dayOfMonth) -> {
            calendar.set(year, month, dayOfMonth);
            String dateString = new SimpleDateFormat("dd-MM-yyyy").format(calendar.getTime());
//            tourDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            tourDate = String.format("%04d-%02d-%02d", year, (month + 1), dayOfMonth);
            binding.edtTourDate.setText(dateString);
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis());
        datePicker.show();
    }

    private void bookMyTour()
    {
        BookingRequestModel bookingRequestModel = new BookingRequestModel();
        Long userId = sharedPreferenceHelper.getUserId() != null && !sharedPreferenceHelper.getUserId().isEmpty() ? Long.parseLong(sharedPreferenceHelper.getUserId()) : 0;
        String userName = sharedPreferenceHelper.getUserName() != null && !sharedPreferenceHelper.getUserId().isEmpty() ? sharedPreferenceHelper.getUserName() : "";
        String foodType = binding.spinnerFoodType.getSelectedItem().toString();
        String facility = binding.spinnerFacility.getSelectedItem().toString();
        if (!tourDate.isEmpty())
        {
            if (bookedRoute != null)
            {
                if (bookedHotel != null)
                {
                    if (bookedRoom != null)
                    {
                        binding.constraintProgressBar.setVisibility(View.VISIBLE);
                        Double fare = bookedRoute.getFare();
                        Long routeId = bookedRoute.getRouteId();

                        Booking booking = new Booking();
                        booking.setFareAmt(fare);
                        booking.setRouteId(routeId);
                        booking.setUserId(userId);
                        booking.setBookerName(userName);
                        booking.setBookedDate(tourDate);

                        bookedHotel = bookedHotel != null ? bookedHotel : new Hotel();
                        bookedRoom = bookedRoom != null ? bookedRoom : new Room();

                        bookingRequestModel.insertBooking(userId, userName, booking, bookedRoom, bookedRoute, bookedHotel, foodType, facility, tourDate, true);
                        retrofitViewModel.insertBooking(bookingRequestModel);
                    }
                    else
                    {
                        MessageDialog.showAlertDialog("Please select Room", UserBookingActivity.this);
                    }
                }
                else
                {
                    if (bookedRoom != null)
                    {
                        MessageDialog.showAlertDialog("Please select Hotel", UserBookingActivity.this);
                    }
                    else
                    {
                        binding.constraintProgressBar.setVisibility(View.VISIBLE);
                        Double fare = bookedRoute.getFare();
                        Long routeId = bookedRoute.getRouteId();

                        Booking booking = new Booking();
                        booking.setFareAmt(fare);
                        booking.setRouteId(routeId);
                        booking.setUserId(userId);
                        booking.setBookerName(userName);
                        booking.setBookedDate(tourDate);

                        bookedHotel = bookedHotel != null ? bookedHotel : new Hotel();
                        bookedRoom = bookedRoom != null ? bookedRoom : new Room();

                        bookingRequestModel.insertBooking(userId, userName, booking, bookedRoom, bookedRoute, bookedHotel, foodType, facility, tourDate,false);
                        retrofitViewModel.insertBooking(bookingRequestModel);
                    }
                }
            }
            else
            {
                MessageDialog.showAlertDialog("Select your travel route!", UserBookingActivity.this);
            }
        }
        else
        {
            MessageDialog.showAlertDialog("Select your tour date!", UserBookingActivity.this);
        }
    }

    private void getBookingData()
    {
        Gson gson = new Gson();
        if (sharedPreferenceHelper.getHotelInfo() != null && !sharedPreferenceHelper.getHotelInfo().isEmpty())
        {
            binding.linearBookedHotel.setVisibility(View.VISIBLE);
            bookedHotel = gson.fromJson(sharedPreferenceHelper.getHotelInfo(), Hotel.class);
            if (bookedHotel != null)
            {
                binding.hotelName.setText(bookedHotel.getHotelName() != null && !bookedHotel.getHotelName().isEmpty() ? bookedHotel.getHotelName() : "-");
                binding.address.setText(bookedHotel.getAddress() != null && bookedHotel.getAddress().getArea() != null && !bookedHotel.getAddress().getArea().isEmpty() ? bookedHotel.getAddress().getArea() : "-");
                binding.mobileNumber.setText(bookedHotel.getHotelMobile() != null && !bookedHotel.getHotelMobile().isEmpty() ? bookedHotel.getHotelMobile(): "-");
            }
        }
        else
        {
            binding.linearBookedHotel.setVisibility(View.GONE);
        }
        if (sharedPreferenceHelper.getRoomInfo() != null && !sharedPreferenceHelper.getRoomInfo().isEmpty())
        {
            binding.linearBookedRoom.setVisibility(View.VISIBLE);
            bookedRoom = gson.fromJson(sharedPreferenceHelper.getRoomInfo(), Room.class);
            if (bookedRoom != null)
            {
                binding.roomFacility.setText(bookedRoom.getFacility() != null && !bookedRoom.getFacility().isEmpty() ? bookedRoom.getFacility() : "NON AC");
                binding.personCount.setText(bookedRoom.getNumberOfPerson() != null ? String.valueOf(bookedRoom.getNumberOfPerson()) : "0");
            }
        }
        else
        {
            binding.linearBookedRoom.setVisibility(View.GONE);
        }
        if (sharedPreferenceHelper.getRouteInfo() != null && !sharedPreferenceHelper.getRouteInfo().isEmpty())
        {
            binding.linearBookedRoute.setVisibility(View.VISIBLE);
            bookedRoute = gson.fromJson(sharedPreferenceHelper.getRouteInfo(), Route.class);
            if (bookedRoute != null)
            {
                binding.from.setText(bookedRoute.getFromRoute() != null && !bookedRoute.getFromRoute().isEmpty() ? bookedRoute.getFromRoute() : "-");
                binding.to.setText(bookedRoute.getToRoute() != null && !bookedRoute.getToRoute().isEmpty() ? bookedRoute.getToRoute() : "-");
                binding.amount.setText(bookedRoute.getFare() != null ? decimalFormat.format(bookedRoute.getFare()) : "00.00");
                binding.pickUpAddress.setText(bookedRoute.getPickUpPoint() != null && !bookedRoute.getPickUpPoint().isEmpty() ? bookedRoute.getPickUpPoint(): "-");
                binding.pickUpTime.setText(bookedRoute.getPickUpTime() != null && !bookedRoute.getPickUpTime().isEmpty() ? bookedRoute.getPickUpTime() + " PM" : "-");
                binding.dropAddress.setText(bookedRoute.getDropPoint() != null && !bookedRoute.getDropPoint().isEmpty() ? bookedRoute.getDropPoint(): "-");
                binding.dropTime.setText(bookedRoute.getDropTime() != null && !bookedRoute.getDropTime().isEmpty() ? bookedRoute.getDropTime() + " AM" : "-");
            }
        }
        else
        {
            binding.linearBookedRoute.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001)
        {
            startActivity(new Intent(this, UserBookingActivity.class));
            finish();
        }
    }
}
