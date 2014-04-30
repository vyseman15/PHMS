package com.example.personalhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class ViewDoctors extends Activity {

	public final static String EXTRA_MESSAGE = "medical.app";
	String username;
	
	public void update_doctor_info(View view){
		//pass username to the UpdateUserInformation class
		Intent update_user_info_intent = new Intent(this, AddNewDoctor.class);
		update_user_info_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(update_user_info_intent);
	}
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_doctors);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_doctors, menu);
		return true;
	}

}
