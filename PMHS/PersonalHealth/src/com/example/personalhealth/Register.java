package com.example.personalhealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends ActionBarActivity {

	//Transition page from Register to Login and add data to database
	public void complete_user_registration(View view) {
		
		userInfoDB Db = new userInfoDB(this);
		
		//Get Username from Text Box
		
		EditText username_text = (EditText)findViewById(R.id.username);
		String username = username_text.getText().toString();
		if(username == null || username.equals(""))
		{
			username_text.setError("Valid Username is Required");
		}else{
		
		//Get Password from Text Box
			
		EditText password_text = (EditText)findViewById(R.id.password);
		String password = password_text.getText().toString();
		if(password == null || password.equals(""))
		{
			password_text.setError("Password is Required");
		}else{
		
		//Get Check Password from Text Box
			
		EditText check_password_text = (EditText)findViewById(R.id.password_check);
		String password_check = check_password_text.getText().toString();
		if((password_check == null || password_check.equals(""))&&!(password_check.equals(password)))
		{
			check_password_text.setError("Same Password is Required");
		}else{
		
		///Get first name from text box
			
		EditText First_Name_text = (EditText)findViewById(R.id.First_Name);
		String First_Name = First_Name_text.getText().toString();
		if(First_Name == null || First_Name.equals(""))
		{
			First_Name_text.setError("First Name is Required");
		}else{
		
		///get last name from text box
			
		EditText Last_Name_text = (EditText)findViewById(R.id.Last_Name);
		String Last_Name = Last_Name_text.getText().toString();
		if(Last_Name == null || Last_Name.equals(""))
		{
			Last_Name_text.setError("Last Name is Required");
		}else{
		
		///Get email address from text box
			
		EditText Email_Address_text = (EditText)findViewById(R.id.Email_Address);
		String Email_Address = Email_Address_text.getText().toString();
		if(Email_Address == null || Email_Address.equals(""))
		{
			Email_Address_text.setError("Email is Required");
		}else{
		
		///get height in feet from text box
			
		EditText Height_Feet_input = (EditText)findViewById(R.id.Height_Feet);
		String Height_Ft = Height_Feet_input.getText().toString();
		if(Height_Ft == null || Height_Ft.equals(""))
		{
			Height_Feet_input.setError("Valid Height in Feet is Required");
		}else
		{		
		int Height_Feet = Integer.parseInt(Height_Feet_input.getText().toString());
		if(Height_Feet<2 || Height_Feet>8)
		{
			Height_Feet_input.setError("Valid Height in Feet is Required");
		}else{
		
		///get height in inches from text box
			
		EditText Height_Inches_input = (EditText)findViewById(R.id.Height_Inches);
		String Height_In = Height_Inches_input.getText().toString();
		if(Height_In == null || Height_In.equals(""))
		{
			Height_Inches_input.setError("Valid Height in Inches is Required");
		}else
		{
		int Height_Inches = Integer.parseInt(Height_Inches_input.getText().toString());
		if(Height_Inches<0 || Height_Inches>12)
		{
			Height_Inches_input.setError("Valid Height in Inches is Required");
		}else{
		
		///get weight from text box
			
		EditText Weight_input = (EditText)findViewById(R.id.Weight);
		String Weight_string = Weight_input.getText().toString();
		if(Weight_string == null || Weight_string.equals(""))
		{
			Weight_input.setError("Valid Weight is Required");
		}else
		{
		int Weight = Integer.parseInt(Weight_input.getText().toString());
		if(Weight < 50 || Weight > 1000)
		{
			Weight_input.setError("Valid Weight is Required");
		}else{
		
		///get age from text box
			
		EditText Age_input = (EditText)findViewById(R.id.Age);
		String Age_string = Age_input.getText().toString();
		if(Age_string == null || Age_string.equals(""))
		{
			Age_input.setError("Valid Age is Required");
		}else
		{
		int Age = Integer.parseInt(Age_input.getText().toString());
		if(Age<18 || Age > 130)
		{
			Age_input.setError("Valid Age is Required");
		}else{
		
		///get gender from text box
			
		EditText Gender_text = (EditText)findViewById(R.id.Gender);
		String Gender = Gender_text.getText().toString();
		if(Gender == null || Gender.equals(""))
		{
			Gender_text.setError("Valid Gender is Required(M or F)");
		}else{
		
		///get street name from text box
		
		EditText Street_Name_text = (EditText)findViewById(R.id.Street_Name);
		String Street_Name = Street_Name_text.getText().toString();
		if(Street_Name == null || Street_Name.equals(""))
		{
			Street_Name_text.setError("Street Name is Required");
		}else{
		
		///get city from text box
		
		EditText City_text = (EditText)findViewById(R.id.City);
		String City = City_text.getText().toString();
		if(City == null || City.equals(""))
		{
			City_text.setError("City is Required");
		}else{
		
		///get state from text box
		
		EditText State_text = (EditText)findViewById(R.id.State);
		String State = State_text.getText().toString();
		if(State == null || State.equals(""))
		{
			State_text.setError("State is Required");
		}else{
		
		///get zipcode from text box
		
		EditText Zipcode_input = (EditText)findViewById(R.id.Zipcode);
		String zipcode_string = Zipcode_input.getText().toString();
		if(zipcode_string == null || zipcode_string.equals(""))
		{
			Age_input.setError("Valid Zipcode is Required");
		}else
		{
		int Zipcode = Integer.parseInt(Zipcode_input.getText().toString());
		if(Zipcode<10000 || Zipcode>99999)
		{
			Zipcode_input.setError("Valid Zipcode is Required");
		}else{
		
			/*
		///	get doctor name from text box
			
		EditText Doctor_Name_text = (EditText)findViewById(R.id.Doctor_Name);
		String Doctor_Name = Doctor_Name_text.getText().toString();
		if(Doctor_Name == null || Doctor_Name.equals(""))
		{
			Doctor_Name_text.setError("Doctor Name is Required");
		}else{
		
		///get doctor email from text box
			
		EditText Doctor_Email_text = (EditText)findViewById(R.id.Doctor_Email);
		String Doctor_Email = Doctor_Email_text.getText().toString();
		if(Doctor_Email == null || Doctor_Email.equals(""))
		{
			Doctor_Email_text.setError("Doctor Email is Required");
		}else{
		
		///	get doctor phone from text box
			
		EditText Doctor_Phone_text = (EditText)findViewById(R.id.Doctor_Phone);
		String Doctor_Phone = Doctor_Phone_text.getText().toString();
		if(Doctor_Phone == null || Doctor_Phone.equals(""))
		{
			Doctor_Phone_text.setError("Doctor Phone is Required");
		}else{
		*/
		//Check to make sure the username does not already exist	
		
		if(Db.checkExists(username)==1)
		{
			username_text.setError("Username is Taken, Input Another Username");
		}
		else{
		///Create the new row in the database
		
			
		Db.createRow(username,password,First_Name,Last_Name,
				Email_Address,Street_Name,City,State,Zipcode,
				Age,Gender,Weight,Height_Feet,Height_Inches);
	    Intent intent = new Intent(this, Login.class);
	    startActivity(intent);
	    finish();
		}}}}}}}}}}}}}}}}}}}}}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_register);
	}
}