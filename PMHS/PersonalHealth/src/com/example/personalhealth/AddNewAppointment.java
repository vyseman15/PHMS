package com.example.personalhealth;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class AddNewAppointment extends FragmentActivity {
	public final static String EXTRA_MESSAGE = "medical.app";
	String Username;
	static int DAY,MONTH,YEAR,HOUR,MINUTE;
	
	public void save_new_appointment(View view)
	{
		userInfoDB db = new userInfoDB(this);
		///	get doctor name from text box
		
		
		EditText Doctor_Name_text = (EditText)findViewById(R.id.Doctor_Name);
		String Doctor_Name = Doctor_Name_text.getText().toString();
		if(Doctor_Name == null || Doctor_Name.equals(""))
		{
			Doctor_Name_text.setError("Doctor Name is Required");
		}else{
		
		Date d = null;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(YEAR, MONTH, DAY);
		d = cal.getTime();	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
		String formatedDate = sdf.format(d);
		String formatedTime =HOUR+":"+MINUTE;
		
		
		if(db.checkAppointmentExists(Username, Doctor_Name, formatedDate, formatedTime)==0)
		{
			db.createAppointmentrRow(Username, Doctor_Name, formatedDate, formatedTime);
		}
		else{
			db.updateAppointmentrRow(Username, Doctor_Name, formatedDate, formatedTime);
		}
		Intent save_user_doctor_info_intent = new Intent(this, ViewAppointment.class);
		save_user_doctor_info_intent.putExtra(EXTRA_MESSAGE,Username);
		startActivity(save_user_doctor_info_intent);
		finish();
		}
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}
	
	
	public void showTimePickerDialog(View v) {
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "timePicker");
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_appointment);
		Intent intent = getIntent();
		Username = intent.getStringExtra(ViewAppointment.EXTRA_MESSAGE);
	}
	
	
	
	public static class TimePickerFragment extends DialogFragment
    implements TimePickerDialog.OnTimeSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	// Use the current time as the default values for the picker
	final Calendar c = Calendar.getInstance();
	int hour = c.get(Calendar.HOUR_OF_DAY);
	int minute = c.get(Calendar.MINUTE);
	
	// Create a new instance of TimePickerDialog and return it
	return new TimePickerDialog(getActivity(), this, hour, minute,
	DateFormat.is24HourFormat(getActivity()));
	}
	
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	// Do something with the time chosen by the user
		Log.i("TimePicker", "Time picker set!");
		HOUR = hourOfDay;
		MINUTE = minute;
	}
	}
	public static class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	// Use the current date as the default date in the picker
	final Calendar c = Calendar.getInstance();
	int year = c.get(Calendar.YEAR);
	int month = c.get(Calendar.MONTH);
	int day = c.get(Calendar.DAY_OF_MONTH);
	
	
	// Create a new instance of DatePickerDialog and return it
	return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	public void onDateSet(DatePicker view, int year, int month, int day) {
	// Do something with the date chosen by the user
		Log.i("DatePicker", "Date picker set!");
		DAY = day;
		MONTH = month;
		YEAR = year;
	}
	}
}
