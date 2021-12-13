package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlnetwork.domain.CommonResult;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

public class AddClassActivity extends AppCompatActivity {
    EditText cid;
    EditText className;
    EditText teacher;
    EditText time;
    EditText info;
    Button submit;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        cid = findViewById(R.id.editTextTextPersonName);
        className = findViewById(R.id.editTextTextPersonName2);
        teacher = findViewById(R.id.editTextTextPersonName3);
        time = findViewById(R.id.editTextTextPersonName4);
        info = findViewById(R.id.editTextTextPersonName5);
        submit = findViewById(R.id.button5);


    }


    public void add(View view){
        final Toast t1 = Toast.makeText(this.getApplicationContext(), "添加课程成功！", Toast.LENGTH_SHORT);

        Request request = CommonUtil.getRequest(UrlEnum.TEACHER_CLASS.getUrl() + cid.getText().toString() + "/" + className.getText().toString() + "/" + teacher.getText().toString() + "/" + time.getText().toString() + "/" + info.getText().toString());
        Call call = CommonUtil.getClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(response.code()==200){
                    ResponseBody body = response.body();
                    String string = body.string();
                    CommonResult commonResult = CommonUtil.getGson().fromJson(string, CommonResult.class);
                    if("200".equals(commonResult.getCode())){
                        t1.show();
                        Intent intent = new Intent(AddClassActivity.this, TeacherActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
