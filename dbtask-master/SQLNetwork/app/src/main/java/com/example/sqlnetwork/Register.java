package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.pojo.Student;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class Register extends AppCompatActivity {

    EditText name;
    EditText sid;
    EditText password;
    EditText major;
    EditText grade;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        name = findViewById(R.id.name);
        sid = findViewById(R.id.login_sid);
        password = findViewById(R.id.password);
        major = findViewById(R.id.major);
        grade = findViewById(R.id.grade);

    }

    public void register(View view){
        final Toast t1 = Toast.makeText(this.getApplicationContext(), "注册成功，请登录！", Toast.LENGTH_SHORT);
        final Toast t2 = Toast.makeText(this.getApplicationContext(), "该学号已被注册！", Toast.LENGTH_SHORT);
        final Toast t3 = Toast.makeText(this.getApplicationContext(), "请求失败，请重试！", Toast.LENGTH_SHORT);

        Student student = new Student(sid.getText().toString(), password.getText().toString(), name.getText().toString(), major.getText().toString(), grade.getText().toString());
        final String requestBody = CommonUtil.getGson().toJson(student);

        Request postRequest = CommonUtil.postRequest(UrlEnum.REGISTER.getUrl(), requestBody);

        Call call = CommonUtil.getClient().newCall(postRequest);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                t3.show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                ResponseBody body = response.body();
                System.out.println(response.code());
                System.out.println(body.toString());

                if(response.code() == 200){
                    startActivity(new Intent(Register.this,LoginActivity.class));
                    t1.show();
                } else {
                    t2.show();
                }

            }
        });
    }

    public void toLogin(View view){
        Intent intent = new Intent(Register.this, LoginActivity.class);
        startActivity(intent);
    }

}
