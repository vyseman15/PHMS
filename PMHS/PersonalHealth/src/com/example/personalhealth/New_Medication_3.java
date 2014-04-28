package com.example.personalhealth;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class New_Medication_3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__medication_3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__medication_3, menu);
		return true;
	}

	public void Go_To_Checker(View view)
	{
		Intent intent = new Intent(this, New_Medication_Checker.class);
		startActivity(intent);
		finish();
	}

}