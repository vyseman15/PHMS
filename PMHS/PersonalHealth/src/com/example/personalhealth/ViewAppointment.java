package com.example.personalhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ViewAppointment extends Activity {

	public final static String EXTRA_MESSAGE = "medical.app";
	String Username;
	
	public void update_appointment_info(View view){
		//pass username to the UpdateUserInformation class
		Intent update_user_info_intent = new Intent(this, AddNewAppointment.class);
		update_user_info_intent.putExtra(EXTRA_MESSAGE,Username);
		startActivity(update_user_info_intent);
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_appointment);
		Intent intent = getIntent();
		Username = intent.getStringExtra(UserInformation.EXTRA_MESSAGE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_appointment, menu);
		return true;
	}

}
