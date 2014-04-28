package com.example.personalhealth;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class New_Medication_2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__medication_2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__medication_2, menu);
		return true;
	}

}
