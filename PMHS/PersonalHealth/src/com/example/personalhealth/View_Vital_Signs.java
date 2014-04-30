package com.example.personalhealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.personalhealth.userInfoDB2.Row;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class View_Vital_Signs extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__vital__signs);
		
		String username;
		int count=0;
		Row userInformationVitals;
		userInfoDB2 Db = new userInfoDB2(this);
		String [] dataall=new String[Db.lastEntry()+1];
		userInformationVitals = Db.getsingleRow(0);
		username="q";
		for(int i=1;i<Db.lastEntry()+1;i++)
		{
			
			String indivData="";
		    String dataBreaker="";
		    
			userInformationVitals = Db.getsingleRow(i);
			//if(userInformation.Username.equals("q"));
			
		    
		    
		    dataBreaker=String.valueOf(userInformationVitals.BPS);
		    indivData=indivData.concat("Blood Pressure Systolic: "+dataBreaker+"\n");
		    dataBreaker=String.valueOf(userInformationVitals.BPD);
		    indivData=indivData.concat("Blood Pressure diastolic: "+dataBreaker+"\n");
		    dataBreaker=String.valueOf(userInformationVitals.Cholesterol);
		    indivData=indivData.concat("Cholesterol:"+dataBreaker+"\n");
		    dataBreaker=String.valueOf(userInformationVitals.Glucose);
		    indivData=indivData.concat("Glucose:"+dataBreaker+"\n");
		    dataBreaker=String.valueOf(userInformationVitals.Temperature);
		    indivData=indivData.concat("Temperature:"+dataBreaker+"\n");

		    dataall[count]=indivData;
		    count++;
		}
	    
		ListView listview2 = (ListView) findViewById(R.id.listview2);

	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 0; i < dataall.length; ++i) 
	    {
	      list.add(dataall[i]);
	    }
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, list);
	    listview2.setAdapter(adapter);
	    /*
	    TextView BPsys3 = (TextView)findViewById(R.id.BPsys3);
	    BPsys3.setText("Blood Pressure:Systolic: "+userInformation.BPS);
	    */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view__vital__signs, menu);
		return true;
	}
	public void Go_To_Vital_Screen(View view)
    {
    	Intent intent = new Intent(this, Vitals_Screen.class);
    	startActivity(intent);
    	finish();
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
	public void ridAll(View view)
	{
		userInfoDB2 Db = new userInfoDB2(this);
		for(int i=0; i<Db.lastEntry()+1;i++)
		{
			Db.deleteRow(i);
		}
	}
}
