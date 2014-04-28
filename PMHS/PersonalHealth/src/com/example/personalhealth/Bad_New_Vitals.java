package com.example.personalhealth;

import com.example.personalhealth.userInfoDB2.Row;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Bad_New_Vitals extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bad__new__vitals);
		Row userInformation;
		//opens the Database for this class
		userInfoDB2 Db = new userInfoDB2(this);
		//assigns userInformation to be equal to row object which contains the data from a single row query
		userInformation = Db.getsingleRow(Db.lastEntry());
		TextView BPsys3 = (TextView)findViewById(R.id.BPsys3);
	    BPsys3.setText("Blood Pressure:Systolic: "+userInformation.BPS);
	    TextView BPdia3 = (TextView)findViewById(R.id.BPdia3);
	    BPdia3.setText("Blood Pressure:Diastolic: "+userInformation.BPD);
	    TextView chol3 = (TextView)findViewById(R.id.chol3);
	    chol3.setText("Cholesterol: "+userInformation.Cholesterol);
	    TextView glut3 = (TextView)findViewById(R.id.glut3);
	    glut3.setText("Glucose: "+userInformation.Glucose);
	    TextView temp3 = (TextView)findViewById(R.id.temp3);
	    temp3.setText("Temperature: "+userInformation.Temperature);
	    TextView stats3 = (TextView)findViewById(R.id.stats3);
	    stats3.setText("Status: "+userInformation.Status);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bad__new__vitals, menu);
		return true;
	}
	public void Go_To_Vital_Screen(View view)
    {
    	Intent intent = new Intent(this, Vitals_Screen.class);
    	startActivity(intent);
    	finish();
    }

}