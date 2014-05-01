package com.example.personalhealth;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


	public class AddNewContact extends Activity 
	{
		public final static String EXTRA_MESSAGE = "medical.app";
		String Username;
		
		
		public void save_new_contact(View view)
		{
			userInfoDB db = new userInfoDB(this);
			///	get Contact name from text box
			
			
			EditText Contact_Name_text = (EditText)findViewById(R.id.Contact_Name);
			String Contact_Name = Contact_Name_text.getText().toString();
			if(Contact_Name == null || Contact_Name.equals(""))
			{
				Contact_Name_text.setError("Contact Name is Required");
			}else{
			
			///get Contact email from text box
				
			EditText Contact_Email_text = (EditText)findViewById(R.id.Contact_Email);
			String Contact_Email = Contact_Email_text.getText().toString();
			if(Contact_Email == null || Contact_Email.equals(""))
			{
				Contact_Email_text.setError("Contact Email is Required");
			}else{
			
			///	get Contact phone from text box
				
			EditText Contact_Phone_text = (EditText)findViewById(R.id.Contact_Phone);
			String Contact_Phone = Contact_Phone_text.getText().toString();
			if(Contact_Phone == null || Contact_Phone.equals(""))
			{
				Contact_Phone_text.setError("Contact Type is Required");
			}else{
			
			
			EditText Contact_type_text = (EditText)findViewById(R.id.Contact_Type);
			String Contact_Type = Contact_Phone_text.getText().toString();
			if(Contact_Type == null || Contact_Type.equals(""))
			{
				Contact_type_text.setError("Contact Type is Required");
			}else{
			
			if(db.checkContactExists(Username, Contact_Name)==0)
			{
				db.createContactRow(Username, Contact_Name, Contact_Phone, Contact_Email, Contact_Type);
			}
			else{
				db.updateContactRow(Username, Contact_Name, Contact_Phone, Contact_Email, Contact_Type);
			}
			Intent save_user_Contact_info_intent = new Intent(this, ViewContacts.class);
			save_user_Contact_info_intent.putExtra(EXTRA_MESSAGE,Username);
			startActivity(save_user_Contact_info_intent);
			finish();
		}}}}
		}

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_add_new_contact);
			//get username from ViewContacts class
			Intent intent = getIntent();
			Username = intent.getStringExtra(UserInformation.EXTRA_MESSAGE);
			
		}
	}
