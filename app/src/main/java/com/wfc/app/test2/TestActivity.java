package com.wfc.app.test2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestActivity extends Activity {

    private EditText phoneEt;
    private Button clearBtn;
    private RadioGroup roleRg;

    private String roleType = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initViews();
        bindListeners();
    }

    void initViews() {
        phoneEt = (EditText) findViewById(R.id.test_et_phone);
        clearBtn = (Button) findViewById(R.id.test_btn_clear);
        roleRg = (RadioGroup) findViewById(R.id.test_rg_role);
    }

    void bindListeners() {
        roleRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                switch (radioButtonId) {
                    case R.id.test_rb_user:
                        roleType = "1";
                        break;
                    case R.id.test_rb_enterprise:
                        roleType = "2";
                        break;
                }
            }
        });

        phoneEt.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(11)});

        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.test_btn_clear:
                postClear();
                break;
        }
    }

    void postClear() {
        String phone = phoneEt.getText().toString();
        RequestBody formBody = new FormBody.Builder()
                .add("phone", phone)
                .add("type", roleType)
                .build();
        final Request request = new Request.Builder()
                .url("http://backendtest.shangshaban.com/backend/test/clear.htm")
                .post(formBody)
                .build();
        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String msg = e.getMessage();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TestActivity.this, result, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
