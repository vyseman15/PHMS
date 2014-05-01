package com.example.personalhealth;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.os.Build;
import android.provider.AlarmClock;



public class MainPage extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "medical.app";
	String username;
	TimePicker myTimePicker;
	int alarmHour;
	int alarmMinute;
	
	public void view_user_data(View view){
		//this will bring you to view user information page and pass the username to that page
		Intent view_user_info_intent = new Intent(this, UserInformation.class);
		view_user_info_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(view_user_info_intent);
	}
	public void sign_out_user(View view){
		//This will bring you back to sign in page
		Intent sign_out_user_intent = new Intent(this, Login.class);
		startActivity(sign_out_user_intent);
		finish();
	}
	public void view_diet_info(View view){
		//This will bring you back to sign in page
		Intent view_diet_information_intent = new Intent(this, ViewDietInformation.class);
		view_diet_information_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(view_diet_information_intent);
	}
	
	public void view_contacts_info(View view)
	{
		Intent view_contact_info_intent = new Intent(this,ViewContacts.class);
		view_contact_info_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(view_contact_info_intent);
	}
	
    /** Called when the user clicks the Storage button */
    public void gotoStorage(View view) {
    	Intent intent = new Intent(this, DisplayStorageActivity.class);
		intent.putExtra(EXTRA_MESSAGE,username);
    	startActivity(intent);
    }

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//get username from login
		Intent intent = getIntent();
		username = intent.getStringExtra(Login.EXTRA_MESSAGE);
		setContentView(R.layout.fragment_main_page);
		TextView text = (TextView)findViewById(R.id.textView2);
		text.setText("Username: "+username);
		myTimePicker=(TimePicker)findViewById(R.id.timePicker1);
	
	
		myTimePicker.setOnTimeChangedListener(new OnTimeChangedListener()
		{
			@Override
			public void onTimeChanged(TimePicker view, int aHour, int aMinute)
			{
				//Toast.makeText(getApplicationContext(), "Time is: "+hourOfDay+":"+minute, Toast.LENGTH_SHORT).show();
				alarmHour = aHour;
				alarmMinute = aMinute;
			}
		}
				);
	}

public void Button_set(View view)
{
	Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
	alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, 10);
	alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, 1);
	startActivity(alarmIntent);
}
	public void Go_To_Vitals(View view)
	{
		Intent intent = new Intent(this, Vitals_Screen.class);
		intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(intent);
	}
	public void Go_To_Medication(View view)
	{
		Intent intent = new Intent(this, Medication_Screen.class);
		intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(intent);
	}
}

