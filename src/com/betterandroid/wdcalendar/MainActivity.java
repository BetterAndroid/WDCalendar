package com.betterandroid.wdcalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.betterandroid.wdcalendar.WDCalendar.OnCalendarClickListener;
import com.betterandroid.wdcalendar.WDCalendar.OnCalendarDateChangedListener;

public class MainActivity extends Activity {

	private String date = null;// 设置默认选中的日期 格式为 “2014-04-05” 标准DATE格式
	private WDCalendar calendar;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wd_calendar);
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		calendar = (WDCalendar) this.findViewById(R.id.calendar);
		// calendar.setThisday(new Date(2011, 12, 22));
		// view.startAnimation(AnimationUtils.loadAnimation(this,
		// R.anim.translucent_zoom_in));
		// LinearLayout ll_popup = (LinearLayout)
		// this.findViewById(R.id.ll_popup);
		// ll_popup.startAnimation(AnimationUtils.loadAnimation(this,
		// R.anim.push_top_in));

		final TextView calendar_month = (TextView) this
				.findViewById(R.id.calendar_month);

		calendar_month.setText(calendar.getCalendarYear() + "年"
				+ calendar.getCalendarMonth() + "月");

		// if (null != date) {
		//
		// int years = Integer.parseInt(date.substring(0, date.indexOf("-")));
		// int month = Integer.parseInt(date.substring(date.indexOf("-") + 1,
		// date.lastIndexOf("-")));
		// popupwindow_calendar_month.setText(years + "年" + month + "月");
		//
		// calendar.showCalendar(years, month);
		// // calendar.setCalendarDayBgColor(date,
		// R.drawable.calendar_date_focused);
		// calendar.setCalendarDayBgColor(date, Color.parseColor("#fe8c8c"));
		// //
		// }
		
		/**
		 * 设置标记列表 
		 */
		List<String> list = new ArrayList<String>(); 
		java.util.Date d = new java.util.Date(System.currentTimeMillis());
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		list.add(ft.format(d));
		calendar.addMarks(list, 0);

		// 监听所选中的日期
		calendar.setOnCalendarClickListener(new OnCalendarClickListener() {

			@SuppressLint("ResourceAsColor")
			public void onCalendarClick(int row, int col, String dateFormat) {
				int month = Integer.parseInt(dateFormat.substring(
						dateFormat.indexOf("-") + 1,
						dateFormat.lastIndexOf("-")));
				if (calendar.getCalendarMonth() - month == 1
						|| calendar.getCalendarMonth() - month == -11) { // 上月跳转
					calendar.lastMonth();

				} else if (month - calendar.getCalendarMonth() == 1 // 下月跳转
						|| month - calendar.getCalendarMonth() == -11) {
					calendar.nextMonth();

				} else {
					calendar.removeAllBgColor();
					// calendar.setCalendarDayBgColor(dateFormat,
					// R.drawable.calendar_date_focused);
					// calendar.setCalendarDayBgColor(dateFormat,
					// R.drawable.calendar_date_focused);
					calendar.setCalendarDayBgColor(dateFormat,
							R.color.current_item_bg);

					date = dateFormat;// 最后返回给全局 date
					Log.e("Date", date);
					Toast.makeText(MainActivity.this, "可选" + date,
							Toast.LENGTH_SHORT).show();
				}
			}

			@Override
			public void onCalendarNorClick() {
				Toast.makeText(MainActivity.this, "不可选" + date,
						Toast.LENGTH_SHORT).show();
			}
		});

		// 监听当前月份
		calendar.setOnCalendarDateChangedListener(new OnCalendarDateChangedListener() {
			public void onCalendarDateChanged(int year, int month) {
				calendar_month.setText(year + "年" + month + "月");
			}
		});

		// 上月监听按钮
		Button popupwindow_calendar_last_month = (Button) this
				.findViewById(R.id.left);
		popupwindow_calendar_last_month
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						calendar.lastMonth();
					}

				});

		// 下月监听按钮
		Button popupwindow_calendar_next_month = (Button) this
				.findViewById(R.id.right);
		popupwindow_calendar_next_month
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						calendar.nextMonth();
					}
				});
		// // 月监听按钮
		// TextView popupwindow_calendar_now = (TextView) this
		// .findViewById(R.id.popupwindow_calendar_now);
		// popupwindow_calendar_now.setOnClickListener(new OnClickListener() {
		//
		// public void onClick(View v) {
		// calendar.toNow();
		// }
		// });
	}
	
}
