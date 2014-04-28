package com.example.personalhealth;

import com.example.personalhealth.Medication_Screen;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class New_Medication_Checker extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__medication__checker);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__medication__checker, menu);
		return true;
	}
	
	public void Go_To_Med_Menu(View view)
	{
		Intent intent = new Intent(this, Medication_Screen.class);
		startActivity(intent);
		finish();
	}
}