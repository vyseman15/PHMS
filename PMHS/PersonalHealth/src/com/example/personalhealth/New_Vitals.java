package com.example.personalhealth;

import com.example.personalhealth.userInfoDB2.Row;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.os.Build;

public class New_Vitals extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__vitals);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__vitals, menu);
		return true;
	}
	public void Go_To_Checker(View view)
	{
		
		EditText editText = (EditText) findViewById(R.id.Cholesterol);
		String message = editText.getText().toString();
		int cholest=numberize(message);

		EditText editText2 = (EditText) findViewById(R.id.Systolic);
		String message2 = editText2.getText().toString();
		int BPsys=numberize(message2);

		EditText editText3 = (EditText) findViewById(R.id.Diastolic);
		String message3 = editText3.getText().toString();
		int BPdia=numberize(message3);

		EditText editText4 = (EditText) findViewById(R.id.Glucose);
		String message4 = editText4.getText().toString();
		int gluc=numberize(message4);

		EditText editText5 = (EditText) findViewById(R.id.Temperature);
		String message5 = editText5.getText().toString();
		int temper=numberize(message5);
		
		boolean good=true;
		String stats= "";
		if(cholest>200&&cholest<240)
		{
			good=false;
			stats=stats.concat("\nCholesterol is high");
			//warning yellow high
		}
		else if(cholest>240)
		{
			good=false;
			stats=stats.concat("\nCholesterol is very high");
			//warning red very high
		}
		if(BPsys>180||BPdia>110)
		{
			good=false;
			stats=stats.concat("\nBlood Pressure is at Hypertensive Crisis state");
			//warning red hypertensive crisis
			
		}
		else if(BPsys>160||BPdia>100)
		{
			good=false;
			stats=stats.concat("\nBlood Pressure is Hypertensive stage 2");
			//hypertension stage 2 orange
		}
		else if(BPsys>140||BPdia>90)
		{
			good=false;
			stats=stats.concat("\nBlood Pressure is Hypertensive stage 1");
			//hypertension stage 1 orange
		}
		else if(BPsys>120||BPdia>80)
		{
			good=false;
			stats=stats.concat("\nBlood Pressure is Prehypertensive");
			//prehypertension yellow
		}
		if(gluc<80)
		{
			good=false;
			stats=stats.concat("\nGlucose Level is Hypoglycemic");
			//hypoglycemic
		}
		else if(gluc>180)
		{
			good=false;
			stats=stats.concat("\nGlucose Level is dangerously Hyperglycemic");
			//dangerously high
		}
		else if(gluc>150)
		{
			good=false;
			stats=stats.concat("\nGlucose Level is Hyperglycemic");
			//hyperglycemic
		}
		if(temper>100)
		{
			good=false;
			stats=stats.concat("\nTemperature is above normal range");
			//possible fever or over temp normal
		}
		else if(temper<97)
		{
			good=false;
			stats=stats.concat("\nTemperature is below normal range");
			//under
		}
		userInfoDB2 Db = new userInfoDB2(this);
		String username="q";
		if(good)
		{
			stats=stats.concat("Healthy");
			Db.createRow(username,gluc,BPsys,BPdia,cholest,temper,stats);
			Intent intent = new Intent(this, Vitals_Check_Screen.class);
			startActivity(intent);
			finish();
		}
		else
		{
			Db.createRow(username,gluc,BPsys,BPdia,cholest,temper,stats);			
			Intent intent = new Intent(this, Bad_New_Vitals.class);
			startActivity(intent);
			finish();
		}
	}
	public int numberize(String content)
	{
		int num=0;
		for(int i=0;i<content.length();i++)
		{
			num*=10;
			if(content.charAt(i)=='1')
			{
				num+=1;
			}
			else if(content.charAt(i)=='2')
			{
				num+=2;
			}
			else if(content.charAt(i)=='3')
			{
				num+=3;
			}
			else if(content.charAt(i)=='4')
			{
				num+=4;
			}
			else if(content.charAt(i)=='5')
			{
				num+=5;
			}
			else if(content.charAt(i)=='6')
			{
				num+=6;
			}
			else if(content.charAt(i)=='7')
			{
				num+=7;
			}
			else if(content.charAt(i)=='8')
			{
				num+=8;
			}
			else if(content.charAt(i)=='9')
			{
				num+=9;
			}
		}
		return num;
	}

}
