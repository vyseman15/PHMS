package com.example.personalhealth;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import com.example.personalhealth.AddNewAppointment.DatePickerFragment;
import com.example.personalhealth.UserHealthInfoDB.Row;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class UpdateDietInformation extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "medical.app";
	String Username;
	static int DAY, MONTH, YEAR;
	
	
	public void save_user_diet_data(View view){
		Row dietInfo;
		int Weight,Calories,BMI,Calories_Burned;
		boolean W_good=false, C_good=false, BMI_good=false, C_B_good=false;
		//This will bring you back to sign in page
		//String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		UserHealthInfoDB Db = new UserHealthInfoDB(this);
		dietInfo = Db.rowNullConstructor();
		
		Date d = null;
		Calendar cal = GregorianCalendar.getInstance();
		cal.set(YEAR, MONTH, DAY);
		d = cal.getTime();	
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
		String date = sdf.format(d);
		
		
		
		if(Db.checkExists(Username, date)==1)
		{
			dietInfo = Db.getsingleHealthRow(Username, date);
		}
		
		///get weight

		EditText Weight_input = (EditText)findViewById(R.id.Weight_update);
		String Weight_string = Weight_input.getText().toString();
		if(Weight_string == null || Weight_string.equals(""))
		{
			Weight = dietInfo.Weight;
			W_good=true;
		}else
		{
		Weight = Integer.parseInt(Weight_input.getText().toString());
		if(Weight < 50 || Weight > 1000)
		{
			Weight_input.setError("Valid Weight is Required");
		}else{
			W_good=true;
		}}
		
		//get calories
		
		EditText Calories_input = (EditText)findViewById(R.id.Calories_update);
		String Calories_string = Calories_input.getText().toString();
		if(Calories_string == null || Calories_string.equals(""))
		{
			Calories = dietInfo.Calories;
			C_good=true;
		}else
		{
			Calories = Integer.parseInt(Calories_input.getText().toString());
		if(Calories < 0 || Calories > 20000)
		{
			Calories_input.setError("Valid Calories is Required");
		}else{
			C_good=true;
		}}
		
		///Get BMI
		
		EditText BMI_input = (EditText)findViewById(R.id.BMI_update);
		String BMI_string = BMI_input.getText().toString();
		if(BMI_string == null || BMI_string.equals(""))
		{
			BMI = dietInfo.BMI;
			BMI_good=true;
		}else
		{
			BMI = Integer.parseInt(BMI_input.getText().toString());
		if(BMI < 5 || BMI > 70)
		{
			BMI_input.setError("Valid BMI is Required");
		}else{
			BMI_good=true;
		}}
		
		
		///Get Calories Burned
		
		EditText Calories_Burned_input = (EditText)findViewById(R.id.Calories_Burned_update);
		String Calories_Burned_String = Calories_Burned_input.getText().toString();
		if(Calories_Burned_String == null || Calories_Burned_String.equals(""))
		{
			Calories_Burned = dietInfo.Calories_Burned;
			C_B_good=true;
		}else
		{
		Calories_Burned = Integer.parseInt(Calories_Burned_input.getText().toString());
		if(Calories_Burned < 0 || Calories_Burned > 10000)
		{
			Calories_Burned_input.setError("Valid Calories Burned is required.");
		}else{
			C_B_good=true;
		}}
		
		if((W_good==true)&&(C_good==true)&&(BMI_good==true)&&(C_B_good==true))
		{
			
			
			if(Db.checkExists(Username, date)==0)
			{
				Db.createRow(Username, date);
				Db.updateDietInfo(Username, date, Weight, Calories, BMI, Calories_Burned);
			}
			else
			{
				Db.updateDietInfo(Username, date, Weight, Calories, BMI, Calories_Burned);
			}
			
			Intent go_to_main_page_intent = new Intent(this, ViewDietInformation.class);
			go_to_main_page_intent.putExtra(EXTRA_MESSAGE,Username);
			startActivity(go_to_main_page_intent);
			finish();
		}
		
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_update_diet_information);
		//gets the username from the view diet information
		Intent intent = getIntent();
		Username = intent.getStringExtra(ViewDietInformation.EXTRA_MESSAGE);
		
		
	}
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "datePicker");
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
