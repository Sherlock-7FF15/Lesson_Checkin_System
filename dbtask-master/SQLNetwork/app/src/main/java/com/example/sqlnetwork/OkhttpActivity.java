package com.example.sqlnetwork;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.sqlnetwork.domain.Result;
import com.example.sqlnetwork.util.CommonUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

public class OkhttpActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private String locationProvider;

    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);

        btn = findViewById(R.id.button2);
    }

    public void getRequest(View view) {
        //创建客户端
        OkHttpClient okHttpClient = CommonUtil.getClient();
        //创建请求
        Request request = CommonUtil.getRequest("https://hailicy.xyz/clasip/allstudents");
        //创建请求任务
        Call call = okHttpClient.newCall(request);
        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                ResponseBody body = response.body();
                Gson gson = CommonUtil.getGson();
                System.out.println(body.string());
                Result result = gson.fromJson(body.string(), Result.class);
                List<Result.Student> students = result.getData();
                for (Result.Student student : students) {
                    System.out.println(students.toString());
                }
                getLocationInfo();
            }
        });
    }


    private void getLocationInfo() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        // 获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if (providers.contains(LocationManager.GPS_PROVIDER)) {
            // 如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            // 如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        } else {
            System.out.println("没有可用Gps");
            return;
        }
        // 获取Location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationProvider);
        if (location != null) {
            // 不为空,显示地理位置经纬度
            double jd =location.getLongitude();
            double wd =location.getLatitude();
            System.out.println(jd+" "+wd);
        } else {
            System.out.println("GPS未定位到位置,请查看是否打开了GPS ？");
        }
    }
}
