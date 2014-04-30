package com.example.personalhealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.personalhealth.ViewDietInformation.StableArrayAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewDoctors extends Activity {

	public final static String EXTRA_MESSAGE = "medical.app";
	String Username;
	
	public void update_doctor_info(View view){
		//pass Username to the UpdateUserInformation class
		Intent update_user_info_intent = new Intent(this, AddNewDoctor.class);
		update_user_info_intent.putExtra(EXTRA_MESSAGE,Username);
		startActivity(update_user_info_intent);
		finish();
	}
	
	
	
	///This is the adapter for the string of data
	private class StableArrayAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }

	  }
	
	public void displayDoctorInformation()
	{
		int rowcount;
		String Doctor_Name, Doctor_Email, Doctor_Type, Doctor_Phone;
		Cursor c;
		userInfoDB db = new userInfoDB(this);
		c = db.getDoctorInformation(Username);
		rowcount = c.getCount();
		String [] dataall=new String[rowcount];
		if ((c != null) && (c.moveToFirst()))
		{
			for(int i=0;i<rowcount;i++)
			{
				Doctor_Name = c.getString(c.getColumnIndex("Doctor_Name"));
				Doctor_Email = c.getString(c.getColumnIndex("Doctor_Email"));
				Doctor_Phone = c.getString(c.getColumnIndex("Doctor_Phone"));
				Doctor_Type = c.getString(c.getColumnIndex("Doctor_Type"));
				c.moveToNext();
				
				
				String indivData="";
			    String dataBreaker="";
			    
				//if(dietInfo.Username.equals("q"));
				
			    
				dataBreaker=String.valueOf(Doctor_Name);
			    indivData=indivData.concat("Doctor_Name: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Doctor_Email);
			    indivData=indivData.concat("Doctor_Email: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Doctor_Phone);
			    indivData=indivData.concat("Doctor_Phone: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Doctor_Type);
			    indivData=indivData.concat("Doctor_Type: "+dataBreaker+"\n");
	
			    dataall[i]=indivData;
				
				
			    ListView listview = (ListView) findViewById(R.id.listview1);
				
			    final ArrayList<String> list = new ArrayList<String>();
			    for (int j = 0; j < dataall.length; ++j) 
			    {
			      list.add(dataall[j]);
			    }
			    final StableArrayAdapter adapter = new StableArrayAdapter(this,
			        android.R.layout.simple_list_item_1, list);
			    listview.setAdapter(adapter);
				
			}
		}
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_doctors);
		Intent intent = getIntent();
		Username = intent.getStringExtra(UserInformation.EXTRA_MESSAGE);
		displayDoctorInformation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_doctors, menu);
		return true;
	}

}
