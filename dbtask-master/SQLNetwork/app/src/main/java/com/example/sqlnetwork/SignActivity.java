package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.domain.OpenClassResult;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class SignActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String cid = intent.getStringExtra("cid");


    }


    private class init extends Thread {
        private String cid;

        init(String cid){
            this.cid = cid;
        }

        @Override
        public void run() {
            Request request = CommonUtil.getRequest(UrlEnum.GET_CLASS_BY_CID + cid);
            Call call = CommonUtil.getClient().newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    ResponseBody body = response.body();
                    Gson gson = CommonUtil.getGson();
                    OpenClassResult openClassResult = gson.fromJson(body.toString(), OpenClassResult.class);
                }
            });
        }
    }
}
