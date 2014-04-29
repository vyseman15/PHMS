package com.example.personalhealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.personalhealth.medicationDB.Row;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Current_Medication extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_current__medication);

		Row userInformation;
		medicationDB Db = new medicationDB(this);
		String [] dataall=new String[Db.lastEntry()];
		userInformation = Db.getsingleRow(0);
		for(int i=0;i<Db.lastEntry();i++)
		{
			
			String indivData="";
			
			userInformation = Db.getsingleRow(i);
			//if(userInformation.Username.equals("q"));
			
		    
		    
		    indivData=indivData.concat("Pill Name: "+userInformation.PillName+"\n");
		    indivData=indivData.concat("OTC Name: "+userInformation.OTCName+"\n");
		    indivData=indivData.concat("Time(s) of day: "+userInformation.TOD+"\n");
		    indivData=indivData.concat("Day(s) per week: "+userInformation.TPD+"\n");
		    indivData=indivData.concat("Dosage: "+userInformation.Dose+"\n");
		    indivData=indivData.concat("Special Instructions: "+userInformation.Special_Instructions+"\n");
		    indivData=indivData.concat("Known Conflicts: "+userInformation.Known_Conflicts+"\n");
		    dataall[i]=indivData;
		}
	    
		ListView listviewMeds = (ListView) findViewById(R.id.listviewMeds);

	    final ArrayList<String> listMeds = new ArrayList<String>();
	    for (int i = 0; i < dataall.length; ++i) 
	    {
	      listMeds.add(dataall[i]);
	    }
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_2, listMeds);
	    listviewMeds.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.current__medication, menu);
		return true;
	}
	public void Go_To_Med_Menu(View view)
	{
		Intent intent = new Intent(this, Medication_Screen.class);
		startActivity(intent);
		finish();
	}
	public void ridAll(View view)
	{
		medicationDB Db = new medicationDB(this);
		for(int i=0; i<Db.lastEntry()+1;i++)
		{
			Db.deleteRow(i);
		}
	}

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
