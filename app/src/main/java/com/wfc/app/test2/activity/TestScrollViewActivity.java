package com.wfc.app.test2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.wfc.app.test2.widget.DateSelectDialog;
import com.wfc.app.test2.widget.ListSelectDialog;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by wangfengchen on 16/9/22.
 */

public class TestScrollViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DateSelectDialog mChangeBirthDialog = new DateSelectDialog(
//                TestScrollViewActivity.this);
//        mChangeBirthDialog.setDate(2015, 3, 29);
//        mChangeBirthDialog.show();
//        mChangeBirthDialog.setCallback(new DateSelectDialog.Callback() {
//
//            @Override
//            public void onSelect(String year, String month, String day) {
//                Toast.makeText(TestScrollViewActivity.this,
//                        year + "-" + month + "-" + day,
//                        Toast.LENGTH_LONG).show();
//            }
//        });
        ListSelectDialog dialog = new ListSelectDialog(this);
        dialog.setCancelable(true);
        dialog.setList(Arrays.asList("123", "456", "789", "123", "456", "789", "123", "456", "789", "123", "456", "789"));
        dialog.setCallback(new ListSelectDialog.Callback() {
            @Override
            public void onSelect(int id) {
                Toast.makeText(TestScrollViewActivity.this,
                       ""+id,
                        Toast.LENGTH_LONG).show();
            }
        });
        dialog.show();
    }
}
