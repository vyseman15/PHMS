package com.example.personalhealth;


import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainPage extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "medical.app";
	String username;
	
	public void view_user_data(View view){
		//this will bring you to view user information page and pass the username to that page
		Intent view_user_info_intent = new Intent(this, UserInformation.class);
		view_user_info_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(view_user_info_intent);
		finish();
	}
	public void sign_out_user(View view){
		//This will bring you back to sign in page
		Intent sign_out_user_intent = new Intent(this, Login.class);
		startActivity(sign_out_user_intent);
		finish();
	}
	public void view_diet_info(View view){
		//This will bring you back to sign in page
		Intent view_diet_information_intent = new Intent(this, ViewDietInformation.class);
		view_diet_information_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(view_diet_information_intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//get username from login
		Intent intent = getIntent();
		username = intent.getStringExtra(Login.EXTRA_MESSAGE);
		setContentView(R.layout.fragment_main_page);
		TextView text = (TextView)findViewById(R.id.textView2);
		text.setText("Username: "+username);
	}
}

