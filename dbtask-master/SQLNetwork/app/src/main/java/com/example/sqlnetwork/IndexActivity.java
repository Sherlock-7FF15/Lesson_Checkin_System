package com.example.sqlnetwork;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlnetwork.adapters.GetClassListAdapter;
import com.example.sqlnetwork.domain.ClassResult;
import com.example.sqlnetwork.domain.CommonResult;
import com.example.sqlnetwork.domain.StudentClassResult;
import com.example.sqlnetwork.util.CommonUtil;
import com.example.sqlnetwork.util.UrlEnum;
import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.List;

public class IndexActivity extends AppCompatActivity {

    private GetClassListAdapter adapter;
    private String sid;
    TextView allClass;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        final Intent intent = getIntent();
        sid = intent.getStringExtra("sid");
        allClass = findViewById(R.id.textView18);
        allClass.setMovementMethod(ScrollingMovementMethod.getInstance());
        System.out.println(sid);
        initView();
        new init(sid).run();
    }

    public void initView(){
        RecyclerView view = this.findViewById(R.id.recyclerView);
        view.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GetClassListAdapter();
        adapter.setOnItemClickListener(new GetClassListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(IndexActivity.this, ClassInfoActivity.class);
                intent.putExtra("cid",adapter.getData().get(position).getCid());
                intent.putExtra("sid",sid);
                startActivity(intent);
            }
        });
        view.setAdapter(adapter);
    }

    public void updateUI(final List<ClassResult.Class> classResult){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.setData(classResult);
            }
        });
    }

    public class init extends Thread {
        private String sid;

        init(String sid){
            this.sid = sid;
        }

        @Override
        public void run() {
            Request request = CommonUtil.getRequest(UrlEnum.ALL_CLASSES_BY_SID.getUrl() + sid);
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
                    StudentClassResult classResult = gson.fromJson(body.string(), StudentClassResult.class);
                    updateUI(classResult.getData());
                }
            });

            Request request1 = CommonUtil.getRequest(UrlEnum.GET_ALL_CLASS.getUrl());
            Call call1 = CommonUtil.getClient().newCall(request1);
            call1.enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    ResponseBody body = response.body();
                    String string = body.string();
                    StudentClassResult studentClassResult = CommonUtil.getGson().fromJson(string, StudentClassResult.class);
                    List<ClassResult.Class> data = studentClassResult.getData();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (ClassResult.Class aclass :
                            data) {
                        stringBuffer.append(aclass.getClassName()+"     课程码："+aclass.getCid()+"     授课教师："+aclass.getTeacher()+"\n");
                    }
                    updateUI1(stringBuffer.toString());
                }
            });

        }
    }

    public void updateUI1(final String string){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                allClass.setText(string);
            }
        });
    }

    public void addClass(View view){
        EditText editText = findViewById(R.id.Class_Code);
        String classCode = editText.getText().toString();
        final Toast t1 = Toast.makeText(this.getApplicationContext(), "添加课程成功！", Toast.LENGTH_SHORT);

        System.out.println(UrlEnum.ADD_TO_CLASS.getUrl() + sid + "/" + classCode);
        final Request request = CommonUtil.getRequest(UrlEnum.ADD_TO_CLASS.getUrl() + sid + "/" + classCode);
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
                    CommonResult commonResult = gson.fromJson(body.string(), CommonResult.class);
                    if("200".equals(commonResult.getCode())){
                        System.out.println("添加成功");
                        t1.show();
                        Request request = CommonUtil.getRequest(UrlEnum.ALL_CLASSES_BY_SID.getUrl() + sid);
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
                                StudentClassResult classResult = gson.fromJson(body.string(), StudentClassResult.class);
                                updateUI(classResult.getData());
                            }
                        });
                    }
            }
        });
    }
}
