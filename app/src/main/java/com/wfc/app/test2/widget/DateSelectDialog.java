package com.wfc.app.test2.widget;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wfc.app.test2.R;
import com.wfc.app.test2.widget.adapters.AbstractWheelTextAdapter;
import com.wfc.app.test2.widget.views.OnWheelChangedListener;
import com.wfc.app.test2.widget.views.OnWheelScrollListener;
import com.wfc.app.test2.widget.views.WheelView;

public class DateSelectDialog extends Dialog implements View.OnClickListener {

	private Context context;
	private WheelView monthView;
	private WheelView dayView;

	private ArrayList<String> years = new ArrayList<>();
	private ArrayList<String> months = new ArrayList<>();
	private ArrayList<String> days = new ArrayList<>();
	private CalendarTextAdapter mYearAdapter;
	private CalendarTextAdapter mMonthAdapter;
	private CalendarTextAdapter mDayAdapter;

	private int month;
	private int day;

	private int currentYear = getYear();
	private int currentMonth = 1;
	private int currentDay = 1;

	private int maxTextSize = 24;
	private int minTextSize = 14;

	private boolean issetdata = false;

	private String selectYear;
	private String selectMonth;
	private String selectDay;

	private Callback callback;

	public DateSelectDialog(Context context) {
		super(context, R.style.ShareDialog);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_date_select);
		WheelView yearView = (WheelView) findViewById(R.id.wv_birth_year);
		monthView = (WheelView) findViewById(R.id.wv_birth_month);
		dayView = (WheelView) findViewById(R.id.wv_birth_day);
		View sureBtn = findViewById(R.id.btn_myinfo_sure);
		View cancelBtn = findViewById(R.id.btn_myinfo_cancel);
		sureBtn.setOnClickListener(this);
		cancelBtn.setOnClickListener(this);

		if (!issetdata) {
			initData();
		}
		initYears();
		mYearAdapter = new CalendarTextAdapter(context, years, setYear(currentYear), maxTextSize, minTextSize);
		yearView.setVisibleItems(5);
		yearView.setViewAdapter(mYearAdapter);
		yearView.setCurrentItem(setYear(currentYear));

		initMonths(month);
		mMonthAdapter = new CalendarTextAdapter(context, months, setMonth(currentMonth), maxTextSize, minTextSize);
		monthView.setVisibleItems(5);
		monthView.setViewAdapter(mMonthAdapter);
		monthView.setCurrentItem(setMonth(currentMonth));

		initDays(day);
		mDayAdapter = new CalendarTextAdapter(context, days, currentDay - 1, maxTextSize, minTextSize);
		dayView.setVisibleItems(5);
		dayView.setViewAdapter(mDayAdapter);
		dayView.setCurrentItem(currentDay - 1);

		yearView.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
				selectYear = currentText;
				setTextViewSize(currentText, mYearAdapter);
				currentYear = Integer.parseInt(currentText);
				setYear(currentYear);
				initMonths(month);
				mMonthAdapter = new CalendarTextAdapter(context, months, 0, maxTextSize, minTextSize);
				monthView.setVisibleItems(5);
				monthView.setViewAdapter(mMonthAdapter);
				monthView.setCurrentItem(0);
			}
		});

		yearView.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) mYearAdapter.getItemText(wheel.getCurrentItem());
				setTextViewSize(currentText, mYearAdapter);
			}
		});

		monthView.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
				selectMonth = currentText;
				setTextViewSize(currentText, mMonthAdapter);
				setMonth(Integer.parseInt(currentText));
				initDays(day);
				mDayAdapter = new CalendarTextAdapter(context, days, 0, maxTextSize, minTextSize);
				dayView.setVisibleItems(5);
				dayView.setViewAdapter(mDayAdapter);
				dayView.setCurrentItem(0);
			}
		});

		monthView.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) mMonthAdapter.getItemText(wheel.getCurrentItem());
				setTextViewSize(currentText, mMonthAdapter);
			}
		});

		dayView.addChangingListener(new OnWheelChangedListener() {

			@Override
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				String currentText = (String) mDayAdapter.getItemText(wheel.getCurrentItem());
				setTextViewSize(currentText, mDayAdapter);
				selectDay = currentText;
			}
		});

		dayView.addScrollingListener(new OnWheelScrollListener() {

			@Override
			public void onScrollingStarted(WheelView wheel) {

			}

			@Override
			public void onScrollingFinished(WheelView wheel) {
				String currentText = (String) mDayAdapter.getItemText(wheel.getCurrentItem());
				setTextViewSize(currentText, mDayAdapter);
			}
		});

	}

	private void initYears() {
		for (int i = getYear(); i > 1950; i--) {
			years.add(i + "");
		}
	}

	private void initMonths(int months) {
		this.months.clear();
		for (int i = 1; i <= months; i++) {
			this.months.add(i + "");
		}
	}

	private void initDays(int days) {
		this.days.clear();
		for (int i = 1; i <= days; i++) {
			this.days.add(i + "");
		}
	}

	private class CalendarTextAdapter extends AbstractWheelTextAdapter {
		ArrayList<String> list;

		CalendarTextAdapter(Context context, ArrayList<String> list, int currentItem, int maxsize, int minsize) {
			super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem, maxsize, minsize);
			this.list = list;
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

	public void setCallback(Callback callback) {
		this.callback = callback;
	}

	@Override
	public void onClick(View v) {


		callback.onSelect(selectYear, selectMonth, selectDay);
	}

	public interface Callback {
		void onSelect(String year, String month, String day);
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

	private int getYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	private int getMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}

	private int getDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DATE);
	}

	private void initData() {
		setDate(getYear(), getMonth(), getDay());
		this.currentDay = 1;
		this.currentMonth = 1;
	}

	/**
	 * 设置年月日
	 */
	public void setDate(int year, int month, int day) {
		selectYear = year + "";
		selectMonth = month + "";
		selectDay = day + "";
		issetdata = true;
		this.currentYear = year;
		this.currentMonth = month;
		this.currentDay = day;
		if (year == getYear()) {
			this.month = getMonth();
		} else {
			this.month = 12;
		}
		calDays(year, month);
	}

	/**
	 * 设置年份
	 */
	private int setYear(int year) {
		int yearIndex = 0;
		if (year != getYear()) {
			this.month = 12;
		} else {
			this.month = getMonth();
		}
		for (int i = getYear(); i > 1950; i--) {
			if (i == year) {
				return yearIndex;
			}
			yearIndex++;
		}
		return yearIndex;
	}

	/**
	 * 设置月份
	 */
	private int setMonth(int month) {
		int monthIndex = 0;
		calDays(currentYear, month);
		for (int i = 1; i < this.month; i++) {
			if (month == i) {
				return monthIndex;
			} else {
				monthIndex++;
			}
		}
		return monthIndex;
	}

	/**
	 * 计算每月多少天
	 */
	private void calDays(int year, int month) {
		boolean leayyear = year % 4 == 0 && year % 100 != 0;
		for (int i = 1; i <= 12; i++) {
			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				this.day = 31;
				break;
			case 2:
				if (leayyear) {
					this.day = 29;
				} else {
					this.day = 28;
				}
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				this.day = 30;
				break;
			}
		}
		if (year == getYear() && month == getMonth()) {
			this.day = getDay();
		}
	}
}