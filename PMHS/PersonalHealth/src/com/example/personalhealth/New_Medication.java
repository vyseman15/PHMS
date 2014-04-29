package com.example.personalhealth;

import com.example.personalhealth.New_Medication_2;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class New_Medication extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__medication);
		
		EditText editText = (EditText) findViewById(R.id.MedNameOfficial);
		String message1 = editText.getText().toString();
		EditText editText2 = (EditText) findViewById(R.id.MedNameOTC);
		String message2 = editText2.getText().toString();
		EditText editText3 = (EditText) findViewById(R.id.MedSpecialInstr);
		String message3 = editText3.getText().toString();
		

		medicationDB Db = new medicationDB(this);
		String username="q";
		Db.createRow(username, message1, message2, null, null, null, message3, null);			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__medication, menu);
		return true;
	}
	public void Go_To_Next_2(View view)
    {
		
    	Intent intent = new Intent(this, New_Medication_2.class);
    	startActivity(intent);
    	finish();
    }


}
