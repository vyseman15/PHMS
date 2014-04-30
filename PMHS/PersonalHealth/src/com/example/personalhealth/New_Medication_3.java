package com.example.personalhealth;



import com.example.personalhealth.MainPage;
import com.example.personalhealth.medicationDB.Row;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class New_Medication_3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__medication_3);
		

	    EditText editText7 = (EditText) findViewById(R.id.KnownConflict);
		String message7 = editText7.getText().toString();
		
		medicationDB Db = new medicationDB(this);
		Row newmed = Db.getsingleRow(Db.lastEntry());
		Db.updateRow(Db.lastEntry(),newmed.Username, newmed.PillName, newmed.OTCName, newmed.TOD, newmed.TPD, newmed.Dose, newmed.Special_Instructions, message7);			
		
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
