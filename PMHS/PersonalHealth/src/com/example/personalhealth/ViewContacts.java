package com.example.personalhealth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.personalhealth.ViewDietInformation.StableArrayAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewContacts extends Activity {
	
	public final static String EXTRA_MESSAGE = "medical.app";
	String Username;
	userInfoDB db;
	
	//currently selected contacts info
	String bigContact_Phone = " ";
	String bigContact_Email = " ";
	String bigContact_Name = " ";
	
	public void update_Contact_info(View view){
		//pass Username to the UpdateUserInformation class
		Intent update_user_info_intent = new Intent(this, AddNewContact.class);
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
	
	public void TextMessage(View view)
	{
		Intent openTheTextEditor = new Intent(Intent.ACTION_VIEW);		
		openTheTextEditor.setType("vnd.android-dir/mms-sms");
		openTheTextEditor.putExtra("address", bigContact_Phone.toString());
		startActivity(openTheTextEditor);
	}
	
	public void PhoneCall(View view)
	{
		Intent callContact = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + bigContact_Phone.toString()));
		startActivity(callContact);
	}
	
	public void Email(View view)
	{
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setType("text/html");
		emailIntent.putExtra(Intent.EXTRA_EMAIL, bigContact_Email.toString());
		startActivity(emailIntent);
	}
	
	public void DeleteContact(View view)
	{
		db.deleteContactRow(Username, bigContact_Name);
	}
	
	public void displayContactInformation()
	{
		int rowcount;
		String Contact_Name, Contact_Email, Contact_Type, Contact_Phone;
		Cursor c;
		db = new userInfoDB(this);
		c = db.getContactInformation(Username);
		rowcount = c.getCount();
		String [] dataall=new String[rowcount];
		if ((c != null) && (c.moveToFirst()))
		{
			for(int i=0;i<rowcount;i++)
			{
				Contact_Name = c.getString(c.getColumnIndex("Contact_Name"));
				Contact_Email = c.getString(c.getColumnIndex("Contact_Email"));
				Contact_Phone = c.getString(c.getColumnIndex("Contact_Phone"));
				Contact_Type = c.getString(c.getColumnIndex("Contact_Type"));
				c.moveToNext();
				
				
				String indivData="";
			    String dataBreaker="";
			    
				//if(dietInfo.Username.equals("q"));
				
			    
				dataBreaker=String.valueOf(Contact_Name);
			    indivData=indivData.concat("Contact_Name: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Contact_Email);
			    indivData=indivData.concat("Contact_Email: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Contact_Phone);
			    indivData=indivData.concat("Contact_Phone: "+dataBreaker+"\n");
			    dataBreaker=String.valueOf(Contact_Type);
			    indivData=indivData.concat("Contact_Type: "+dataBreaker+"\n");
	
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
		setContentView(R.layout.activity_view_contacts);
		Intent intent = getIntent();
		Username = intent.getStringExtra(UserInformation.EXTRA_MESSAGE);
		displayContactInformation();
		registerClickCallback();
		//setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list));
	}
	
	private void registerClickCallback()
	{
		ListView listview = (ListView) findViewById(R.id.listview1);
		
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			
			@Override
			public void onItemClick(AdapterView<?> Parent, View viewClicked, int position,long id) {
				TextView textView = (TextView) viewClicked;
				
				// TODO Auto-generated method stub
				
				int rowcount;
				
				Cursor c;
				//userInfoDB db = new userInfoDB(this);
				c = db.getContactInformation(Username);
				rowcount = c.getCount();
				if ((c != null) && (c.moveToFirst()))
				{
					for(int i=0;i<(position+1);i++)
					{
						bigContact_Email = c.getString(c.getColumnIndex("Contact_Email"));
						bigContact_Phone = c.getString(c.getColumnIndex("Contact_Phone"));
						bigContact_Name = c.getString(c.getColumnIndex("Contact_Name"));
						c.moveToNext();
					}
				}
				String message = "You clicked #" + position + "which is string" + bigContact_Phone.toString();
				Toast.makeText(ViewContacts.this, message, Toast.LENGTH_LONG).show();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    //Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contacts, menu);
		return true;
	}

}

