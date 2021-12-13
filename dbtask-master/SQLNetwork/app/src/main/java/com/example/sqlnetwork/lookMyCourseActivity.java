//package com.example.sqlnetwork;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.example.sqlnetwork.util.CommonUtil;
//import com.example.sqlnetwork.util.Result;
//import com.squareup.okhttp.Call;
//import com.squareup.okhttp.Callback;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.Response;
//import com.squareup.okhttp.ResponseBody;
//
//import org.w3c.dom.Text;
//
//import java.io.IOException;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import android.content.Intent;
//import android.os.Bundle;
//
//public class lookMyCourseActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_look_my_course);
//        Intent it = getIntent();
//        String sid =  it.getStringExtra("sid");
//        URL url = null;
//        try {
//            url = new URL("http://hailicy.xyz/clasip/mycourse/"+sid);//localhost:8080
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        Request req = CommonUtil.getRequest(url.toString());
//        Call call = CommonUtil.getClient().newCall(req);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                TextView tx2 = findViewById(R.id.textView4);
//                tx2.setText("请求数据失败，请重试！");
//                System.out.println("bbbbb");
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                ResponseBody body = response.body();
//                Gson gson = CommonUtil.getGson();
//                Result str = gson.fromJson(body.string(), Result.class);
//                TextView tx2 = findViewById(R.id.textView4);
//
//                tx2.setText(str.getData().toString());
//                System.out.println("aaaaa");
//            }
//        });
//
//
//
//    }
//}