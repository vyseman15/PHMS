package com.example.personalhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Vitals_Screen extends Activity {
	public final static String EXTRA_MESSAGE = "medical.app";
	String userName;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vitals__screen);
		Intent intent = getIntent();
		userName = intent.getStringExtra(Login.EXTRA_MESSAGE);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vitals__screen, menu);
		return true;
	}
	public void Go_To_New_Vitals(View view)
	{
		Intent intent = new Intent(this, New_Vitals.class);
		startActivity(intent);
		finish();
	}
	public void Go_To_View_Vitals(View view)
	{
		Intent intent = new Intent(this, View_Vital_Signs.class);
		startActivity(intent);
		finish();
	}
/*
	public void Go_To_Main(View view)
    {
    	Intent intent = new Intent(this, MainPage.class);
		intent.putExtra(EXTRA_MESSAGE,userName);
    	startActivity(intent);
    	finish();
    }
*/
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
