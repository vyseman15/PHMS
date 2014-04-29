package com.example.personalhealth;


import com.example.personalhealth.userInfoDB.Row;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class UserInformation extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "medical.app";
	String username;
	//Transition page from User Information to Update User Information
	public void update_user_data(View view){
		//pass username to the UpdateUserInformation class
		Intent update_user_info_intent = new Intent(this, UpdateUserInformation.class);
		update_user_info_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(update_user_info_intent);
		finish();
	}
	
	/*
	//Transition page from User Information to Main Page
	public void return_to_main_page(View view){
		//Pass username to the main page and go to the main page
		Intent return_to_main_page_intent = new Intent(this, MainPage.class);
		return_to_main_page_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(return_to_main_page_intent);
		finish();
	}
	*/
	
	//Set all of the user information in the textviews
	public void set_textviews_for_user_information(Row userInfo)
	{
		setContentView(R.layout.fragment_user_information);
		
		//This will put all of the data in the Row class/ userInfo object, which contains a row query, onto the screen in textviews
		TextView Username_textview = (TextView)findViewById(R.id.Username_textview);
		Username_textview.setText("Username: "+username);

		TextView Password_textview = (TextView)findViewById(R.id.Password_textview);
	    Password_textview.setText("Password: "+userInfo.Password);
	    
	    TextView First_Name_textview = (TextView)findViewById(R.id.First_Name_textview);
	    First_Name_textview.setText("First Name: "+userInfo.First_Name);
	    
	    TextView Last_Name_textview = (TextView)findViewById(R.id.Last_Name_textview);
	    Last_Name_textview.setText("Last Name: "+userInfo.Last_Name);
	    
	    TextView Email_textview = (TextView)findViewById(R.id.Email_textview);
	    Email_textview.setText("Email: "+userInfo.Email);
	    
	    TextView Height_Feet_textview = (TextView)findViewById(R.id.Height_Feet_textview);
	    Height_Feet_textview.setText("Height Feet: "+String.valueOf(userInfo.Height_Feet));
	    
	    TextView Height_Inches_textview = (TextView)findViewById(R.id.Height_Inches_textview);
	    Height_Inches_textview.setText("Height Inches: "+String.valueOf(userInfo.Height_Inches));
	    
	    TextView Weight_textview = (TextView)findViewById(R.id.Weight_textview);
	    Weight_textview.setText("Weight: "+String.valueOf(userInfo.Weight));
	    
	    TextView Age_textview = (TextView)findViewById(R.id.Age_textview);
	    Age_textview.setText("Age: "+String.valueOf(userInfo.Age));
	    
	    TextView Gender_textview = (TextView)findViewById(R.id.Gender_textview);
	    Gender_textview.setText("Gender: "+userInfo.Gender);
	    
	    TextView Street_Name_textview = (TextView)findViewById(R.id.Street_Name_textview);
	    Street_Name_textview.setText("Street Name: "+userInfo.Street_Address);
	    
	    TextView City_textview = (TextView)findViewById(R.id.City_textview);
	    City_textview.setText("City: "+userInfo.City);
	    
	    TextView State_textview = (TextView)findViewById(R.id.State_textview);
	    State_textview.setText("State: "+userInfo.State);
	    
	    TextView Zipcode_textview = (TextView)findViewById(R.id.Zipcode_textview);
	    Zipcode_textview.setText("Zipcode: "+String.valueOf(userInfo.Zipcode));
	    /*
	    TextView Doctor_Name_textview = (TextView)findViewById(R.id.Doctor_Name_textview);
	    Doctor_Name_textview.setText("Doctor Name: "+userInfo.Doctors_Name);
	    
	    TextView Doctor_Email_textview = (TextView)findViewById(R.id.Doctor_Email_textview);
	    Doctor_Email_textview.setText("Doctor Email: "+userInfo.Doctors_Email);
	    
	    TextView Doctor_Phone_textview = (TextView)findViewById(R.id.Doctor_Phone_textview);
	    Doctor_Phone_textview.setText("Doctor Phone: "+userInfo.Doctors_Phone);
	    
	    TextView Guest_Password_textview = (TextView)findViewById(R.id.Guest_Password_textview);
	    Guest_Password_textview.setText("Guest Password: "+userInfo.Guest_Password);
	    */
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Row userInformation;
		super.onCreate(savedInstanceState);
		//gets the username from the main page
		Intent intent = getIntent();
		username = intent.getStringExtra(MainPage.EXTRA_MESSAGE);
		//opens the Database for this class
		userInfoDB Db = new userInfoDB(this);
		//assigns userInformation to be equal to row object which contains the data from a single row query
		userInformation = Db.getsingleRow(username);
		
		//function that will put this data on screen so user can see it
		set_textviews_for_user_information(userInformation);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
