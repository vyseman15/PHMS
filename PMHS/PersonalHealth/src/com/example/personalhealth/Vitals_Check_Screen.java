package com.example.personalhealth;

import com.example.personalhealth.userInfoDB2.Row;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Vitals_Check_Screen extends Activity {

	String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		Row userInformation;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vitals__check__screen);
		username = "q";
		//opens the Database for this class
		userInfoDB2 Db = new userInfoDB2(this);
		//assigns userInformation to be equal to row object which contains the data from a single row query
		userInformation = Db.getsingleRow(Db.lastEntry());
		TextView BPsys2 = (TextView)findViewById(R.id.BPsys2);
	    BPsys2.setText("Blood Pressure:Systolic: "+userInformation.BPS);
	    TextView BPdia2 = (TextView)findViewById(R.id.BPdia2);
	    BPdia2.setText("Blood Pressure:Diastolic: "+userInformation.BPD);
	    TextView chol2 = (TextView)findViewById(R.id.chol2);
	    chol2.setText("Cholesterol: "+userInformation.Cholesterol);
	    TextView glut2 = (TextView)findViewById(R.id.glut2);
	    glut2.setText("Glucose: "+userInformation.Glucose);
	    TextView temp2 = (TextView)findViewById(R.id.temp2);
	    temp2.setText("Temperature: "+userInformation.Temperature);
	    TextView stats2 = (TextView)findViewById(R.id.stats2);
	    stats2.setText("Status: "+userInformation.Status);
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.vitals__check__screen, menu);
		return true;
	}
	public void Go_To_Vital_Screen(View view)
    {
    	Intent intent = new Intent(this, Vitals_Screen.class);
    	startActivity(intent);
    	finish();
    }
}
