package com.example.personalhealth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.personalhealth.UserHealthInfoDB.Row;

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
		public void get_textviews_for_user_information()
		{
			Row dietInfo;
			long DAY_IN_MS = 1000 * 60 * 60 * 24;
			
			setContentView(R.layout.fragment_view_diet_information);
			UserHealthInfoDB Db = new UserHealthInfoDB(this);
			
			
			String [] dataall=new String[7];
			for(int i=0;i<7;i++)
			{
				long time = System.currentTimeMillis()-(i*60*60*24*1000);
				Date day = new Date(time);
				String date = new SimpleDateFormat("dd-MM-yyyy",Locale.US).format(day);
				dietInfo = Db.rowNullConstructor();
				dietInfo.Date = date;
				if(Db.checkExists(Username, date)==1)
				{
					dietInfo = Db.getsingleHealthRow(Username, date);
				}
				String indivData="";
			    String dataBreaker="";
			    
				//if(dietInfo.Username.equals("q"));
				
			    
				dataBreaker=String.valueOf(dietInfo.Date);
			    indivData=indivData.concat("Date: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(dietInfo.Weight);
			    indivData=indivData.concat("Weight: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(dietInfo.Calories);
			    indivData=indivData.concat("Calories: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(dietInfo.BMI);
			    indivData=indivData.concat("BMI:"+dataBreaker+"\n");
			    dataBreaker=String.valueOf(dietInfo.Workout_Plan);
			    indivData=indivData.concat("Workout Plan:"+dataBreaker+"\n");
			    dataBreaker=String.valueOf(dietInfo.Calories_Burned);
			    indivData=indivData.concat("Caloires Burned:"+dataBreaker+"\n");

			    dataall[i]=indivData;
			}
		    
			ListView listview = (ListView) findViewById(R.id.listview1);

		    final ArrayList<String> list = new ArrayList<String>();
		    for (int i = 0; i < dataall.length; ++i) 
		    {
		      list.add(dataall[i]);
		    }
		    final StableArrayAdapter adapter = new StableArrayAdapter(this,
		        android.R.layout.simple_list_item_1, list);
		    listview.setAdapter(adapter);
			
			//This will put all of the data in the Row class/ userInfo object, which contains a row query, onto the screen in textviews
			TextView Username_textview = (TextView)findViewById(R.id.Username_id);
			Username_textview.setText("Username: "+Username);
			
		    
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//gets the username from the main page
		Intent intent = getIntent();
		Username = intent.getStringExtra(MainPage.EXTRA_MESSAGE);
		get_textviews_for_user_information();
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
}
		
		
		