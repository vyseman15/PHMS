package com.example.personalhealth;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Vitals_Screen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vitals__screen);
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

	public void Go_To_Main(View view)
    {
    	Intent intent = new Intent(this, MainPage.class);
    	startActivity(intent);
    	finish();
    }
}
