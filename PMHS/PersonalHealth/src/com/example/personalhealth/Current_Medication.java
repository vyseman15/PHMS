package com.example.personalhealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.personalhealth.medicationDB2.Row;

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

		String username;
		int count=0;
		Row userInformationMeds;
		medicationDB2 Db = new medicationDB2(this);
		String [] dataallMeds=new String[Db.lastEntry()+1];
		username="q";
		userInformationMeds = Db.getsingleRow(0);
		for(int i=1;i<Db.lastEntry()+1;i++)
		{
			
			String indivData="";
			
			userInformationMeds = Db.getsingleRow(i);
			//if(userInformation.Username.equals("q"));
			
		    
		    
			indivData=indivData.concat("Pill Name: "+userInformationMeds.PillName+"\n");
		    indivData=indivData.concat("OTC Name: "+userInformationMeds.OTCName+"\n");
		    indivData=indivData.concat("Time(s) of day: "+userInformationMeds.TOD+"\n");
		    indivData=indivData.concat("Day(s) per week: "+userInformationMeds.TPD+"\n");
		    indivData=indivData.concat("Dosage: "+userInformationMeds.Dose+"\n");
		    indivData=indivData.concat("Special Instructions: "+userInformationMeds.Special_Instructions+"\n");
		    indivData=indivData.concat("Known Conflicts: "+userInformationMeds.Known_Conflicts+"\n");
		    dataallMeds[count]=indivData;
		    count++;
		}
	    
		ListView listviewMeds = (ListView) findViewById(R.id.listviewMeds);

	    final ArrayList<String> listMeds = new ArrayList<String>();
	    for (int i = 0; i < dataallMeds.length; ++i) 
	    {
	      listMeds.add(dataallMeds[i]);
	    }
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, listMeds);
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
