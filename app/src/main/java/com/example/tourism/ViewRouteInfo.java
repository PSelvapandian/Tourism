package com.example.tourism;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tourism.Adapter.RouteInfoAdapter;
import com.example.tourism.Interfaces.BookingInterface;
import com.example.tourism.ModelClasses.Hotel;
import com.example.tourism.ModelClasses.Room;
import com.example.tourism.ModelClasses.Route;
import com.example.tourism.Util.GPSTracker;
import com.example.tourism.Util.MessageDialog;
import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.ViewModel.RetrofitViewModel;
import com.example.tourism.databinding.ActivityViewRouteInfoBinding;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewRouteInfo extends AppCompatActivity implements BookingInterface, RouteInfoAdapter.RouteInterface
{
    ActivityViewRouteInfoBinding binding;
    RetrofitViewModel retrofitViewModel;
    RouteInfoAdapter routeInfoAdapter;
    SharedPreferenceHelper sharedPreferenceHelper;

    GPSTracker gps;
    String fromLatitude = "", fromLongitude = "", toLatitude = "", toLongitude = "";
    int MY_PERMISSIONS_REQUEST_LOCATION = 1001, LOCATION_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityViewRouteInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);
        retrofitViewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        getAllRouts();

        binding.ivBack.setOnClickListener(v -> finish());

        retrofitViewModel.allRouts.observe(this, list -> {
            if (list != null)
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                if (list.size() > 0)
                {
                    setAllRouts(list);
                }
                else
                {
                    setAllRouts(new ArrayList<>());
                }
            }
        });

        retrofitViewModel.errorResponse.observe(this, s -> {
            if (s != null && !s.isEmpty())
            {
                binding.constraintProgressBar.setVisibility(View.GONE);
                MessageDialog.showAlertDialog(s, ViewRouteInfo.this);
            }
        });
    }

    private void setAllRouts(List<Route> list)
    {
        routeInfoAdapter = new RouteInfoAdapter();
        binding.recyclerviewRouteInfo.setLayoutManager(new LinearLayoutManager(this));
        routeInfoAdapter.insertRoute(list, this, this, this);
        binding.recyclerviewRouteInfo.setAdapter(routeInfoAdapter);
        binding.emptyString.setVisibility(list.size() > 0 ? View.GONE : View.VISIBLE);
    }

    private void getAllRouts()
    {
        binding.constraintProgressBar.setVisibility(View.VISIBLE);
        retrofitViewModel.getAllRouts();
    }

    @Override
    public void bookedHotel(Hotel hotel) {

    }

    @Override
    public void bookedRoom(Room room) {

    }

    @Override
    public void bookedRoute(Route route)
    {
        Gson gson = new Gson();
        String roomJson = gson.toJson(route);
        sharedPreferenceHelper.setRouteInfo(roomJson);
        MessageDialog.showAlertDialog("Your Travel Route Selected Successfully!", ViewRouteInfo.this);
    }

    @Override
    public void passRouteDetails(Route route)
    {
        if (route != null)
        {
            String from = route.getPickUpPoint() != null && !route.getPickUpPoint().isEmpty() ? route.getPickUpPoint() : "";
            String to = route.getDropPoint() != null && !route.getDropPoint().isEmpty() ? route.getDropPoint() : "";
            getLatLonFromAddress(from, to);
            checkLocationPermission();
        }
    }

    private void checkLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
        }
        else
        {
            checkLocationEnableOrNot();
        }
    }

    private void checkLocationEnableOrNot()
    {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            showLocationDialog();
        }
        else
        {
            openGoogleMap();
        }
    }

    private void openGoogleMap()
    {
        gps = new GPSTracker(getApplicationContext());

        double currentLat = gps.getLatitude();
        double currentLon = gps.getLongitude();

        Log.d("Location Latitude"," Longitude : " + fromLatitude + " / " + fromLongitude + " / " + toLatitude + " / " + toLongitude);
        String mapsUrl = "https://www.google.com/maps/dir/?api=1&origin=" + fromLatitude + "," + fromLongitude + "&destination=" + toLatitude + "," + toLongitude;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(mapsUrl)));
    }

    private void showLocationDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(ViewRouteInfo.this);
        builder.setMessage("To continue, turn on device location, Which uses Google's location service");
        builder.setPositiveButton("Ok", (dialog, i) -> {
            startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), LOCATION_REQUEST_CODE);
            dialog.cancel();
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    private void getLatLonFromAddress(String from, String to)
    {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        List<Address> addresses1;
        try
        {
            addresses = geocoder.getFromLocationName(from, 1);
            addresses1 = geocoder.getFromLocationName(to, 1);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        if (addresses.size() > 0)
        {
            fromLatitude = String.valueOf(addresses.get(0).getLatitude());
            fromLongitude = String.valueOf(addresses.get(0).getLongitude());
            Log.d("LatLon from ", "address : " + fromLatitude + " / " + fromLongitude);
        }
        if (addresses1.size() > 0)
        {
            toLatitude = String.valueOf(addresses1.get(0).getLatitude());
            toLongitude = String.valueOf(addresses1.get(0).getLongitude());
            Log.d("LatLon to ", "address : " + toLatitude + " / " + toLongitude);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOCATION_REQUEST_CODE)
        {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
            {
                Toast.makeText(this, "Please enable your location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_LOCATION)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                checkLocationEnableOrNot();
            }
            else
            {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
