package com.example.personalhealth;


import com.example.personalhealth.userInfoDB.Row;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UpdateUserInformation extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "medical.app";
	String username;
	
	//Transition page from Update User Information to User Information
	public void save_user_data(View view){
		
		int Age,Weight,Height_Feet,Height_Inches,Zipcode;
		boolean good_H_Ft=true,good_H_In=true,good_W=true,good_A=true,good_Z=true;
		Row userData;
		userInfoDB userInformation = new userInfoDB(this);
		userData = userInformation.getsingleRow(username);
		
		//Get Password from Text Box
		EditText password_text = (EditText)findViewById(R.id.password);
		String Password = password_text.getText().toString();
		if(Password == null || Password.equals(""))
		{
			Password = userData.Password;
		}
		
		//Get Check Password from Text Box
			
		EditText check_password_text = (EditText)findViewById(R.id.password_check);
		String password_check = check_password_text.getText().toString();
		if(password_check == null || password_check.equals(""))
		{
			password_check = userData.Password;
		}
		if(!password_check.equals(Password))
		{
			check_password_text.setError("Passwords must be the same.");
		}else{
		
		///get password
			
		EditText First_Name_text = (EditText)findViewById(R.id.First_Name);
		String First_Name = First_Name_text.getText().toString();
		if(First_Name == null || First_Name.equals(""))
		{
			First_Name = userData.First_Name;
		}
		
		///get last name
			
		EditText Last_Name_text = (EditText)findViewById(R.id.Last_Name);
		String Last_Name = Last_Name_text.getText().toString();
		if(Last_Name == null || Last_Name.equals(""))
		{
			Last_Name = userData.Last_Name;
		}
		
		///get email
			
		EditText Email_Address_text = (EditText)findViewById(R.id.Email_Address);
		String Email_Address = Email_Address_text.getText().toString();
		if(Email_Address == null || Email_Address.equals(""))
		{
			Email_Address = userData.Email;
		}
		
		///get height feet
			
		EditText Height_Feet_input = (EditText)findViewById(R.id.Height_Feet);
		String Height_Ft = Height_Feet_input.getText().toString();
		if(Height_Ft == null || Height_Ft.equals(""))
		{
			Height_Feet = userData.Height_Feet;
		}else
		{
		Height_Feet = Integer.parseInt(Height_Feet_input.getText().toString());
		if(Height_Feet<2 || Height_Feet>8)
		{
			Height_Feet_input.setError("Valid Height in Feet is Required");
			good_H_Ft = false;
		}}
		
		///get height inches
			
		EditText Height_Inches_input = (EditText)findViewById(R.id.Height_Inches);
		String Height_In = Height_Inches_input.getText().toString();
		if(Height_In == null || Height_In.equals(""))
		{
			Height_Inches = userData.Height_Inches;
		}else
		{
		Height_Inches = Integer.parseInt(Height_Inches_input.getText().toString());
		if(Height_Inches<0 || Height_Inches>12)
		{
			Height_Inches_input.setError("Valid Height in Inches is Required");
			good_H_In = false;
		}}
		
		///get weight
			
		EditText Weight_input = (EditText)findViewById(R.id.Weight);
		String Weight_string = Weight_input.getText().toString();
		if(Weight_string == null || Weight_string.equals(""))
		{
			Weight = userData.Weight;
		}else
		{
		Weight = Integer.parseInt(Weight_input.getText().toString());
		if(Weight < 50 || Weight > 1000)
		{
			Weight_input.setError("Valid Weight is Required");
			good_W = false;
		}}
		
		///get age
			
		EditText Age_input = (EditText)findViewById(R.id.Age);
		String Age_string = Age_input.getText().toString();
		if(Age_string == null || Age_string.equals(""))
		{
			Age = userData.Age;
		}else
		{
		Age = Integer.parseInt(Age_input.getText().toString());
		if(Age<18 || Age > 130)
		{
			Age_input.setError("Valid Age is Required");
			good_A = false;
		}}
		
		///	get gender
			
		EditText Gender_text = (EditText)findViewById(R.id.Gender);
		String Gender = Gender_text.getText().toString();
		if(Gender == null || Gender.equals(""))
		{
			Gender = userData.Gender;
		}
		
		///get street
		
		EditText Street_Name_text = (EditText)findViewById(R.id.Street_Name);
		String Street_Name = Street_Name_text.getText().toString();
		if(Street_Name == null || Street_Name.equals(""))
		{
			Street_Name = userData.Street_Address;
		}
		
		///get city
		
		EditText City_text = (EditText)findViewById(R.id.City);
		String City = City_text.getText().toString();
		if(City == null || City.equals(""))
		{
			City = userData.City;
		}
		
		///get state
		
		EditText State_text = (EditText)findViewById(R.id.State);
		String State = State_text.getText().toString();
		if(State == null || State.equals(""))
		{
			State = userData.State;
		}
		
		///Get zipcode
		
		EditText Zipcode_input = (EditText)findViewById(R.id.Zipcode);
		String zipcode_string = Zipcode_input.getText().toString();
		if(zipcode_string == null || zipcode_string.equals(""))
		{
			Zipcode = userData.Zipcode;
		}else
		{
		Zipcode = Integer.parseInt(Zipcode_input.getText().toString());
		if(Zipcode<10000 || Zipcode>99999)
		{
			Zipcode_input.setError("Valid Zipcode is Required");
			good_Z = false;
		}}
		
		///Get doctors name
			
		EditText Doctor_Name_text = (EditText)findViewById(R.id.Doctor_Name);
		String Doctor_Name = Doctor_Name_text.getText().toString();
		if(Doctor_Name == null || Doctor_Name.equals(""))
		{
			Doctor_Name = userData.Doctors_Name;
		}
		
		///Get doctors email
			
		EditText Doctor_Email_text = (EditText)findViewById(R.id.Doctor_Email);
		String Doctor_Email = Doctor_Email_text.getText().toString();
		if(Doctor_Email == null || Doctor_Email.equals(""))
		{
			Doctor_Email = userData.Doctors_Email;
		}
		
		///Get doctor phone number
			
		EditText Doctor_Phone_text = (EditText)findViewById(R.id.Doctor_Phone);
		String Doctor_Phone = Doctor_Phone_text.getText().toString();
		if(Doctor_Phone == null || Doctor_Phone.equals(""))
		{
			Doctor_Phone = userData.Doctors_Phone;
		}
		
		///Get guest password
		
		EditText Guest_Password_text = (EditText)findViewById(R.id.Guest_Password);
		String Guest_Password = Guest_Password_text.getText().toString();
		if(Guest_Password == null || Guest_Password.equals(""))
		{
			Guest_Password = userData.Guest_Password;
		}
		
		///Update the row based on new information
		//Also checks that input for Height feet, height inches, weight, age, and zipcode are valid
		if((good_H_Ft)&&(good_H_In)&&(good_W)&&(good_A)&&(good_Z))
		{
		userInformation.updateRow(
				username, Password, First_Name, Last_Name,
				Email_Address, Street_Name,
				City, State, Zipcode, Age, Gender, 
				Weight, Height_Feet, Height_Inches, 
				Doctor_Name, Doctor_Email, Doctor_Phone, Guest_Password);
		
		Intent save_user_info_intent = new Intent(this, UserInformation.class);
		//pass username to the UserInformation class
		save_user_info_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(save_user_info_intent);
		finish();
	}}}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_update_user_information);
		//get username from UserInformation class
		Intent intent = getIntent();
		username = intent.getStringExtra(UserInformation.EXTRA_MESSAGE);
		
	}
}
