package com.example.personalhealth;

import java.util.ArrayList;

import com.example.personalhealth.New_Medication_3;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class New_Medication_2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__medication_2);
		String[] TOD = {"Morning","Afternoon","Evening"};
		final ArrayList<String> TODlist = new ArrayList<String>();
	    for (int i = 0; i < TOD.length; i++) 
	    {
	    	TODlist.add(TOD[i]);
	    }
		
	}
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__medication_2, menu);
		return true;
	}

	public void Go_To_Next_3(View view)
    {
    	Intent intent = new Intent(this, New_Medication_3.class);
    	startActivity(intent);
    	finish();
    }

}
