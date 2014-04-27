package com.example.personalhealth;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.personalhealth.UserHealthInfoDB.Row;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class ViewDietInformation extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "medical.app";
	String Username;
	
	
	public void main_page_click(View view){
		//This will bring you back to sign in page
		Intent go_to_main_page_intent = new Intent(this, MainPage.class);
		go_to_main_page_intent.putExtra(EXTRA_MESSAGE,Username);
		startActivity(go_to_main_page_intent);
		finish();
	}
	
	public void update_diet_information(View view){
		//This will bring you back to sign in page
		Intent go_to_main_page_intent = new Intent(this, UpdateDietInformation.class);
		go_to_main_page_intent.putExtra(EXTRA_MESSAGE,Username);
		startActivity(go_to_main_page_intent);
		finish();
	}
	
	//Set all of the user information in the textviews
		public void get_textviews_for_user_information(Row dietInfo)
		{
			setContentView(R.layout.fragment_view_diet_information);
			
			//This will put all of the data in the Row class/ userInfo object, which contains a row query, onto the screen in textviews
			TextView Username_textview = (TextView)findViewById(R.id.Username_id);
			Username_textview.setText("Username: "+Username);
			
			TextView Date_textview = (TextView)findViewById(R.id.Date_id);
			Date_textview.setText("Date: "+dietInfo.Date);
			
			TextView Weight_textview = (TextView)findViewById(R.id.Weight_id);
			Weight_textview.setText("Weight: "+String.valueOf(dietInfo.Weight));
			
			TextView Calories_textview = (TextView)findViewById(R.id.Calories_id);
			Calories_textview.setText("Calories Consumed: "+String.valueOf(dietInfo.Calories));
			
			TextView BMI_textview = (TextView)findViewById(R.id.BMI_id);
			BMI_textview.setText("BMI: "+String.valueOf(dietInfo.BMI));
			
			TextView Workout_Plan_textview = (TextView)findViewById(R.id.Workout_Plan_id);
			Workout_Plan_textview.setText("Workout Plan: "+String.valueOf(dietInfo.Workout_Plan));
			
			TextView Calories_Burned_textview = (TextView)findViewById(R.id.Calories_Burned_id);
			Calories_Burned_textview.setText("Calories Burned: "+String.valueOf(dietInfo.Calories_Burned));
		    
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Row dietInfo;
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		super.onCreate(savedInstanceState);
		//gets the username from the main page
		Intent intent = getIntent();
		Username = intent.getStringExtra(MainPage.EXTRA_MESSAGE);
		setContentView(R.layout.fragment_view_diet_information);
		UserHealthInfoDB Db = new UserHealthInfoDB(this);
		dietInfo = Db.rowNullConstructor();
		if(Db.checkExists(Username, date)==1)
		{
			dietInfo = Db.getsingleHealthRow(Username, date);
		}
		get_textviews_for_user_information(dietInfo);
	}
}
		
		
		