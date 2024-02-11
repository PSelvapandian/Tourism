package com.example.tourism;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tourism.Util.SharedPreferenceHelper;
import com.example.tourism.databinding.ActivitySpalshScreenBinding;

import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity
{
    ActivitySpalshScreenBinding binding;
    SharedPreferenceHelper sharedPreferenceHelper;


    final int requestCode = 2;
    String ORDER_ID;
    LoaderManager loaderManager;
    String bodyData = "";

    TextView orderID;
    EditText amount;
    float value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySpalshScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferenceHelper = new SharedPreferenceHelper(this);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        clearData();


        binding.btnPay.setOnClickListener(v -> startPayment());

        if (sharedPreferenceHelper.getLoginStatus())
        {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreenActivity.this, UserHomeScreenActivity.class));
                finish();
            }, 4000);
        }
        else
        {
            new Handler().postDelayed(() -> {
                startActivity(new Intent(SplashScreenActivity.this, LoginScreenActivity.class));
                finish();
            }, 4000);
        }
    }

    private void startPayment()
    {
//        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.setMessage("Making Payment....");
//        progressDialog.show();
//        bodyData = getPaytmParams();
//
//        new HttpRequest(activity, Constants.CHECKSUM, HttpRequest.Request.POST, bodyData, new HttpResponseCallback() {
//            @Override
//            public void onResponse(String response) {
//                if(response != null) {
//                    try {
//                        JSONObject paytmParams = new JSONObject();
//
//                        JSONObject head = new JSONObject();
//
//                        String checksum = new JSONObject(response).getString("checksum");
//                        Log.e("checksum", checksum);
//                        head.put("signature", checksum);
//
//                        paytmParams.put("head", head);
//                        paytmParams.put("body", new JSONObject(bodyData));
//
//                        String url = "https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?mid=" + Constants.MERCHANT_ID + "&orderId=" + ORDER_ID;
//
//                        new HttpRequest(activity, url, HttpRequest.Request.POST, paytmParams.toString(), new HttpResponseCallback() {
//                            @Override
//                            public void onResponse(String response) {
//                                if (response != null) {
//                                    try {
//                                        processPaytmTransaction(new JSONObject(response));
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    } finally {
//                                        ORDER_ID = "ID" + System.currentTimeMillis();
//                                        orderID.setText(ORDER_ID);
//                                    }
//                                }
//                                if (progressDialog.isShowing()) progressDialog.dismiss();
//                            }
//                        }).execute();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        if (progressDialog.isShowing()) progressDialog.dismiss();
//                    }
//                } else {
//                    if (progressDialog.isShowing()) progressDialog.dismiss();
//                }
//            }
//        }).execute();
    }

    private void clearData()
    {
        sharedPreferenceHelper.setHotelInfo("");
        sharedPreferenceHelper.setRouteInfo("");
        sharedPreferenceHelper.setRoomInfo("");
    }
}
