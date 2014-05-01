package com.example.personalhealth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.personalhealth.sqlite.helper.DatabaseHelper;
import com.example.personalhealth.sqlite.model.Storage;
import com.example.personalhealth.sqlite.model.Type;

public class DisplayStorageActivity extends ExpandableListActivity implements
OnChildClickListener  {
	
   static long type1_id = 0;
   static long type2_id = 0;
   static long type3_id = 0;
   String userName;
   //final Context context = getApplicationContext();

	private Button button;
	//private EditText editTextMainScreen;
	final Context context = this;

	 ArrayList<String> groupItem = new ArrayList<String>();
	 ArrayList<Object> childItem = new ArrayList<Object>();
	
    private static final String DATABASE_NAME = "storageManagerNew9";
    // Database Helper
    DatabaseHelper db;
    //Boolean isTrue = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_storage);
		// Show the Up button in the action bar.
		setupActionBar();
		//context.deleteDatabase(DATABASE_NAME);
		db = new DatabaseHelper(getApplicationContext());
		
		Intent intent = getIntent();
		userName = intent.getStringExtra(Login.EXTRA_MESSAGE);
		TextView text = (TextView)findViewById(R.id.textView1);
		text.setText("Username: "+userName);
		
		
        // Creating types
        Type type1 = new Type("Recipes");
        Type type2 = new Type("Diets");
        Type type3 = new Type("Articles");

        if((type1_id == 0) && (type2_id == 0) && (type3_id == 0 ))
        {
        	
            	type1_id = db.createType(type1);
            	type2_id = db.createType(type2);
            	type3_id = db.createType(type3);
        }
        
        Log.d("Type Count", "Type Count: " + db.getAllType().size());
        //Log.e("User Name", userName);
        /*
        // Creating storages
        Storage store1 = new Storage("Nikujaga", 0,"hello1");
        Storage store2 = new Storage("Diet Article1", 0,"hello2");
        Storage store3 = new Storage("Health Article", 0,"hello3");
		
        // Inserting stores in db
        // Inserting stores under 3 Types
        long store1_id = db.createStorage(store1, new long[] { type1_id });
        long store2_id = db.createStorage(store2, new long[] { type2_id });
        long store3_id = db.createStorage(store3, new long[] { type3_id });
        
        Log.e("Store Count", "Store count: " + db.getStorageCount());
        
        
        // "Post new Article" - assigning this under "Article" Type
        // Now this will have - "Diets" and "Article" Types
        db.createStorageType(store2_id, type3_id);
        
        
        // Getting all type names
        Log.d("Get Types", "Getting All Types");
        
        List<Type> allTypes = db.getAllType();
        for (Type type : allTypes) {
            Log.d("Type Name", type.getTypeName());
        }
        
        // Getting all storage
        Log.d("Get Stores", "Getting All Stores");
 
        List<Storage> allStores = db.getAllStorage();
        for (Storage storage : allStores) {
            Log.d("Storage", storage.getName());
        }
        
        // Getting stores under "Diet" tag name
        Log.d("Storage", "Get stores under single Type name");
 
        List<Storage> typeDiets = db.getAllStorageByType(type2.getTypeName());
        for (Storage storage : typeDiets) {
            Log.d("Storage Diets", storage.getName());
        } 
        
        
        // Updating type name
        type3.setTypeName("Health Articles");
        db.updateType(type3);
        
        */
		button = (Button) findViewById(R.id.button1);
		//editTextMainScreen = (EditText) findViewById(R.id.editTextResult);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {

				// get create_prompt.xml view
				LayoutInflater layoutInflater = LayoutInflater.from(context);

				View promptView = layoutInflater.inflate(R.layout.create_prompt, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				// set prompts.xml to be the layout file of the alertdialog builder
				alertDialogBuilder.setView(promptView);

				//final EditText inputType = (EditText) promptView.findViewById(R.id.userInput);
				final EditText inputName = (EditText) promptView.findViewById(R.id.userInputName);
				final EditText inputUrl = (EditText) promptView.findViewById(R.id.userInputUrl);

				// setup a dialog window
				final CharSequence[] items = { "Recipe", "Diet", "Article" };
				alertDialogBuilder
						.setTitle("Create Diet, Article, or Recipe")
						.setSingleChoiceItems(items, 0,new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int item) {
		                        //Toast.makeText(getApplicationContext(), items[item],
		                        //      Toast.LENGTH_SHORT).show();
								type1_id = item + 1;
								Toast.makeText(getBaseContext(),"type1_id is" +type1_id, Toast.LENGTH_LONG).show();
						}
						})
						.setCancelable(false)
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										// get user input and set it to result
										//editTextMainScreen.setText(input.getText());
										String stringName = inputName.getText().toString();
										String stringUrl = inputUrl.getText().toString();										
										Storage store1 = new Storage(stringName, 0,stringUrl,userName);
								        
								        long store1_id = db.createStorage(store1, new long[] { type1_id });
								        

									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,	int id) {
										dialog.cancel();
									}
								});

				// create an alert dialog
				AlertDialog alertD = alertDialogBuilder.create();

				alertD.show();

			}
		});
		
		
		
		  ExpandableListView expandbleLis = getExpandableListView();
		  expandbleLis.setDividerHeight(2);
		  expandbleLis.setGroupIndicator(null);
		  expandbleLis.setClickable(true);
		  

		  groupItem.add(db.getSingleType("Recipes"));
		  Toast.makeText(getBaseContext(),"type1 is " +db.getSingleType("Recipes"), Toast.LENGTH_LONG).show();
		  groupItem.add(db.getSingleType("Diets"));
		  Toast.makeText(getBaseContext(),"type2 is " +db.getSingleType("Diets"), Toast.LENGTH_LONG).show();
		  groupItem.add(db.getSingleType("Articles"));
		  Toast.makeText(getBaseContext(),"type3 is " +db.getSingleType("Articles"), Toast.LENGTH_LONG).show();
		  
		  /**
		   * Add Data For Recipes
		   */
		  ArrayList<String> child = new ArrayList<String>();

		  Cursor cR = db.getAllStorageByTypeCursor("Recipes", userName);
		  int count = cR.getCount();
		  if((cR != null) && (cR.moveToFirst()))
		  {
			  for(int i = 0; i < count; i++)
				 {
			 
				 child.add(i, cR.getString(cR.getColumnIndex("name")));
				 cR.moveToNext();
				 }
		  }

		  childItem.add(child);

		  /**
		   * Add Data For Diets
		   */
		  child = new ArrayList<String>();

		  Cursor cD = db.getAllStorageByTypeCursor("Diets", userName);
		  int count2 = cD.getCount();
		  if((cD != null) && (cD.moveToFirst()))
		  {
			  for(int i = 0; i < count2; i++)
				 {
			 
				 child.add(i, cD.getString(cD.getColumnIndex("name")));
				 cD.moveToNext();
				 }
		  }

		  childItem.add(child);
		  /**
		   * Add Data For Articles
		   */
		  child = new ArrayList<String>();

		  Cursor cA = db.getAllStorageByTypeCursor("Articles", userName);
		  int count3 = cD.getCount();
		  if((cA != null) && (cA.moveToFirst()))
		  {
			  for(int i = 0; i < count3; i++)
				 {
			 
				 child.add(i, cA.getString(cA.getColumnIndex("name")));
				 cA.moveToNext();
				 }
		  }
		  childItem.add(child);

		 
		
		  NewAdapter mNewAdapter = new NewAdapter(groupItem, childItem);
		  mNewAdapter
		    .setInflater(
		      (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE),
		      this);
		  getExpandableListView().setAdapter(mNewAdapter);
		  expandbleLis.setOnChildClickListener(this);
		 
	    dbWrite(DATABASE_NAME);
        db.closeDB();

       //context.deleteDatabase(DATABASE_NAME);

      //dbWrite(DATABASE_NAME);
    }



	 

	/*
	 public void setChildGroupData() {
			Intent intent = getIntent();
			userName = intent.getStringExtra(Login.EXTRA_MESSAGE);
		  
		  // Add Data For Recipes
		   
		  ArrayList<String> child = new ArrayList<String>();

		  Cursor cR = db.getAllStorageByTypeCursor("Recipes", userName);
		  int count = cR.getCount();
		  if((cR != null) && (cR.moveToFirst()))
		  {
			  for(int i = 0; i < count; i++)
				 {
			 
				 child.add(i, cR.getString(cR.getColumnIndex("name")));
				 cR.moveToNext();
				 }
		  }

		  childItem.add(child);

		  
		   //Add Data For Diets
		   
		  child = new ArrayList<String>();

		  Cursor cD = db.getAllStorageByTypeCursor("Diets", userName);
		  int count2 = cD.getCount();
		  if((cD != null) && (cD.moveToFirst()))
		  {
			  for(int i = 0; i < count2; i++)
				 {
			 
				 child.add(i, cD.getString(cD.getColumnIndex("name")));
				 cD.moveToNext();
				 }
		  }

		  childItem.add(child);
		  /
		   //Add Data For Articles
		   
		  child = new ArrayList<String>();

		  Cursor cA = db.getAllStorageByTypeCursor("Articles", userName);
		  int count3 = cD.getCount();
		  if((cA != null) && (cA.moveToFirst()))
		  {
			  for(int i = 0; i < count3; i++)
				 {
			 
				 child.add(i, cA.getString(cA.getColumnIndex("name")));
				 cA.moveToNext();
				 }
		  }
		  childItem.add(child);

		 }
	*/

	 @Override
	 public boolean onChildClick(ExpandableListView parent, View v,
	   int groupPosition, int childPosition, long id) {
	  //Toast.makeText(MainActivity.this, "Clicked On Child",Toast.LENGTH_SHORT).show();
	  return true;
	 }
	
public void dbWrite(String DBNAME)
    {
		//Context context = getApplicationContext();
	    String packageName = context.getPackageName();
	    
	    try {
	        File sd = Environment.getExternalStorageDirectory();
	        File data = Environment.getDataDirectory();
	
	        if (sd.canWrite()) {
	            String currentDBPath = "//data//"+ packageName +"//databases//"+ DBNAME;
	            String backupDBPath = DBNAME +".sqlite";
	            File currentDB = new File(data, currentDBPath);
	            File backupDB = new File(sd, backupDBPath);
	
	                FileChannel src = new FileInputStream(currentDB).getChannel();
	                FileChannel dst = new FileOutputStream(backupDB).getChannel();
	                dst.transferFrom(src, 0, src.size());
	                src.close();
	                dst.close();
	                Toast.makeText(getBaseContext(), backupDB.toString(), Toast.LENGTH_LONG).show();
	
	        }
	    } catch (Exception e) {
	
	        Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
	
	
	    }
    }

	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint("Search by Name");
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







