package com.example.personalhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewDoctor extends Activity {
	public final static String EXTRA_MESSAGE = "medical.app";
	String Username;
	
	
	public void save_new_doctor(View view)
	{
		userInfoDB db = new userInfoDB(this);
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
			Doctor_Phone_text.setError("Doctor Type is Required");
		}else{
		
		
		EditText Doctor_type_text = (EditText)findViewById(R.id.Doctor_Type);
		String Doctor_Type = Doctor_Phone_text.getText().toString();
		if(Doctor_Type == null || Doctor_Type.equals(""))
		{
			Doctor_type_text.setError("Doctor Type is Required");
		}else{
		
		if(db.checkDoctorExists(Username, Doctor_Name)==0)
		{
			db.createDoctorRow(Username, Doctor_Name, Doctor_Phone, Doctor_Email, Doctor_Type);
		}
		else{
			db.updateDoctorRow(Username, Doctor_Name, Doctor_Phone, Doctor_Email, Doctor_Type);
		}
		Intent save_user_doctor_info_intent = new Intent(this, ViewDoctors.class);
		save_user_doctor_info_intent.putExtra(EXTRA_MESSAGE,Username);
		startActivity(save_user_doctor_info_intent);
		
	}}}}
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_doctor);
		//get username from ViewDoctors class
		Intent intent = getIntent();
		Username = intent.getStringExtra(UserInformation.EXTRA_MESSAGE);
		
	}
}
