package com.example.personalhealth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.personalhealth.UserHealthInfoDB.Row;

public class ViewDietInformation extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "medical.app";
	String Username;
	
	
	/*
	public void main_page_click(View view){
		//This will bring you back to sign in page
		Intent go_to_main_page_intent = new Intent(this, MainPage.class);
		go_to_main_page_intent.putExtra(EXTRA_MESSAGE,Username);
		startActivity(go_to_main_page_intent);
		finish();
	}
*/	
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
			String Date,Weight,Calories,BMI,Calories_Burned;
			
			setContentView(R.layout.fragment_view_diet_information);
			UserHealthInfoDB db = new UserHealthInfoDB(this);
			
			int rowcount;
			Cursor c;
			c = db.getHealthRows(Username);
			rowcount = c.getCount();
			String [] dataall=new String[rowcount];
			if ((c != null) && (c.moveToFirst()))
			{
				for(int i=0;i<rowcount;i++)
				{
				String indivData="";
			    String dataBreaker="";
			    
			    Date = c.getString(c.getColumnIndex("Date"));
				Weight = c.getString(c.getColumnIndex("Weight"));
				Calories = c.getString(c.getColumnIndex("Calories"));
				BMI = c.getString(c.getColumnIndex("BMI"));
				Calories_Burned = c.getString(c.getColumnIndex("Calories_Burned"));
				//if(dietInfo.Username.equals("q"));
				
			    
				dataBreaker=String.valueOf(Date);
			    indivData=indivData.concat("Date: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Weight);
			    indivData=indivData.concat("Weight: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Calories);
			    indivData=indivData.concat("Calories: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(BMI);
			    indivData=indivData.concat("BMI:"+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Calories_Burned);
			    indivData=indivData.concat("Caloires Burned:"+dataBreaker+"\n");
			    c.moveToNext();
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
		    
		}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		//gets the username from the main page
		Intent intent = getIntent();
		Username = intent.getStringExtra(MainPage.EXTRA_MESSAGE);
		get_textviews_for_user_information();
	}
	
	
	///This is the adapter for the string of data
	public class StableArrayAdapter extends ArrayAdapter<String> {

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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medication__screen, menu);
		return super.onCreateOptionsMenu(menu);
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
		
		
		