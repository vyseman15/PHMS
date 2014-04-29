package com.example.personalhealth;



import com.example.personalhealth.MainPage;
import com.example.personalhealth.New_Medication;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Medication_Screen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medication__screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medication__screen, menu);
		return true;
	}
	public void Go_To_Main(View view)
    {
    	Intent intent = new Intent(this, MainPage.class);
    	startActivity(intent);
    	finish();
    }
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
}
