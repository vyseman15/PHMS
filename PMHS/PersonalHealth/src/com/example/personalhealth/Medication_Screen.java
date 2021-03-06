package com.example.personalhealth;



import com.example.personalhealth.MainPage;
import com.example.personalhealth.New_Medication;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Medication_Screen extends Activity {
	public final static String EXTRA_MESSAGE = "medical.app";
	String userName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication__screen);
		Intent intent = getIntent();
		userName = intent.getStringExtra(Login.EXTRA_MESSAGE);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medication__screen, menu);
		return super.onCreateOptionsMenu(menu);
	}
	/*
	public void Go_To_Main(View view)
    {
    	Intent intent = new Intent(this, MainPage.class);
		intent.putExtra(EXTRA_MESSAGE,userName);
    	startActivity(intent);
    }
    */
	public void Go_To_New_Med(View view)
    {
    	Intent intent = new Intent(this, New_Medication.class);
    	startActivity(intent);
    	finish();
    }
	public void Go_To_Checker(View view)
	{
		Intent intent = new Intent(this, Current_Medication.class);
		startActivity(intent);
		finish();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
