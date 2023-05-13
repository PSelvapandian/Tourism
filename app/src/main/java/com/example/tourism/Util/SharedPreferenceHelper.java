package com.example.tourism.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceHelper
{
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String LOGIN_STATUS = "login_status";
    private static final String ROLE = "role";
    private static final String ROUTE_INFO = "route_info";
    private static final String HOTEL_INFO = "hotel_info";
    private static final String ROOM_INFO = "room_info";

    private static SharedPreferenceHelper instance;
    private final SharedPreferences preferences;

    public SharedPreferenceHelper(Context context)
    {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferenceHelper getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new SharedPreferenceHelper(context);
        }
        return instance;
    }

    public void setUserId(String userId)
    {
        preferences.edit().putString(USER_ID, userId).apply();
    }

    public String getUserId()
    {
        return preferences.getString(USER_ID, "");
    }

    public void setUserName(String userName)
    {
        preferences.edit().putString(USER_NAME, userName).apply();
    }

    public String getUserName()
    {
        return preferences.getString(USER_NAME, "");
    }

    public void setLoginStatus(Boolean loginStatus)
    {
        preferences.edit().putBoolean(LOGIN_STATUS, loginStatus).apply();
    }
    public Boolean getLoginStatus()
    {
        return preferences.getBoolean(LOGIN_STATUS, false);
    }

    public void setRole(String role)
    {
        preferences.edit().putString(ROLE, role).apply();
    }

    public String getRole()
    {
        return preferences.getString(ROLE, "");
    }

    public void setRouteInfo(String routeInfo)
    {
        preferences.edit().putString(ROUTE_INFO, routeInfo).apply();
    }

    public String getRouteInfo()
    {
        return preferences.getString(ROUTE_INFO, "");
    }

    public void setHotelInfo(String hotelInfo)
    {
        preferences.edit().putString(HOTEL_INFO, hotelInfo).apply();
    }

    public String getHotelInfo()
    {
        return preferences.getString(HOTEL_INFO, "");
    }

    public void setRoomInfo(String roomInfo)
    {
        preferences.edit().putString(ROOM_INFO, roomInfo).apply();
    }

    public String getRoomInfo()
    {
        return preferences.getString(ROOM_INFO, "");
    }

}
