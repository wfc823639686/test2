package com.wfc.app.test2.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wfc.app.test2.R;
import com.wfc.app.test2.widget.adapters.AbstractWheelTextAdapter;
import com.wfc.app.test2.widget.views.OnWheelChangedListener;
import com.wfc.app.test2.widget.views.OnWheelScrollListener;
import com.wfc.app.test2.widget.views.WheelView;

import java.util.ArrayList;
import java.util.List;

public class ListSelectDialog extends Dialog implements View.OnClickListener {

    private int visibleItems = 5;

    private int index;

    private int maxTextSize = 24;

    private int minTextSize = 14;

    private Context mContext;

    private CalendarTextAdapter listAdapter;

    private List<String> list = new ArrayList<>();

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    private Callback callback;

    public ListSelectDialog(Context context) {
        super(context, R.style.transparentFrameWindowStyle);
        this.mContext = context;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_list_select);

        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        // 设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = ((Activity)mContext).getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        WheelView listView = (WheelView) findViewById(R.id.wv1);
        View sureBtn = findViewById(R.id.btn_myinfo_sure);
        View cancelBtn = findViewById(R.id.btn_myinfo_cancel);
        sureBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);
        listAdapter = new CalendarTextAdapter();
        listView.setVisibleItems(visibleItems);
        listView.setViewAdapter(listAdapter);

        listView.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) listAdapter.getItemText(wheel.getCurrentItem());
                setTextViewSize(currentText, listAdapter);
                index = newValue;
            }
        });

        listView.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) listAdapter.getItemText(wheel.getCurrentItem());
                setTextViewSize(currentText, listAdapter);
            }
        });
    }

    /**
     * 设置字体大小
     *
     */
    private void setTextViewSize(String itemText, CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        System.out.println("size " + size);
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (itemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_myinfo_sure:
                callback.onSelect(index);
                break;
            case R.id.btn_myinfo_cancel:
                cancel();
                break;

        }
    }

    public void setVisibleItems(int visibleItems) {
        this.visibleItems = visibleItems;
    }

    public interface Callback {
        void onSelect(int id);
    }

    private class CalendarTextAdapter extends AbstractWheelTextAdapter {

        CalendarTextAdapter() {
            super(mContext, R.layout.item_birth_year, NO_RESOURCE, 0, maxTextSize, minTextSize);
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            return super.getItem(index, cachedView, parent);
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }

    }
}
