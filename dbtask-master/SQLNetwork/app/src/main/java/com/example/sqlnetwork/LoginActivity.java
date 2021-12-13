package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.domain.CommonResult;
import com.example.sqlnetwork.domain.StrResult;
import com.example.sqlnetwork.domain.Token;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    EditText sid;
    EditText pwd;
    Button login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题栏
        setContentView(R.layout.activity_login);
        sid = findViewById(R.id.login_sid);
        pwd = findViewById(R.id.login_pwd);
        login = findViewById(R.id.login);

    }

    public void login(View view){
        Token token = new Token(sid.getText().toString(), pwd.getText().toString());
        final Toast t1 = Toast.makeText(this.getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT);
        final Toast t2 = Toast.makeText(this.getApplicationContext(), "登录失败，请重新登录！", Toast.LENGTH_SHORT);
        String json = CommonUtil.getGson().toJson(token);
        System.out.println(json);
        final Request request = CommonUtil.postRequest(UrlEnum.LOGIN.getUrl(), json);

        Call call = CommonUtil.getClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.code() == 200){
                    System.out.println("请求成功");
                    ResponseBody body = response.body();
                    Gson gson = CommonUtil.getGson();
                    CommonResult commonResult = gson.fromJson(body.string(), CommonResult.class);
                    if("200".equals(commonResult.getCode())){
                        System.out.println("登录成功");
                        t1.show();
//                        Toast.makeText(LoginActivity.this,"登陆成功！",Toast.LENGTH_SHORT).show();
//                        Toast.makeText(LoginActivity.this.getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(), "默认的Toast", Toast.LENGTH_SHORT);
                        Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                        intent.putExtra("sid",sid.getText().toString());
                        startActivity(intent);
                    } else {
                        System.out.println("登录失败");
                        t2.show();
                        System.out.println(commonResult.toString());
                    }

                } else {
                    System.out.println("请求失败");
                    t2.show();
                    System.out.println(response.body().string());
                }
            }
        });

    }

    public void goRegister(View view){
        startActivity(new Intent(LoginActivity.this,Register.class));
    }

    public void teacherLogin(View view){

        Request request = CommonUtil.getRequest(UrlEnum.TEACHER_LOGIN.getUrl() + sid.getText().toString() + "/" + pwd.getText().toString());
        Call call = CommonUtil.getClient().newCall(request);
        final Toast t1 = Toast.makeText(this.getApplicationContext(), "登录成功！", Toast.LENGTH_SHORT);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {

                System.out.println(response.code());
                ResponseBody body = response.body();
                Gson gson = CommonUtil.getGson();
                StrResult commonResult = gson.fromJson(body.string(),StrResult.class);
                if("200".equals(commonResult.getCode())){
                    System.out.println(commonResult.getMessage());
                    t1.show();
                    Intent intent = new Intent(LoginActivity.this, TeacherActivity.class);
                    intent.putExtra("tid",sid.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }

}
