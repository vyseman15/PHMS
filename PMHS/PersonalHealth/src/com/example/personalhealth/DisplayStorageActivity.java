package com.example.personalhealth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.personalhealth.sqlite.helper.DatabaseHelper;
import com.example.personalhealth.sqlite.model.Storage;
import com.example.personalhealth.sqlite.model.Type;

public class DisplayStorageActivity extends Activity {
	
    private static final String DATABASE_NAME = "storageManager";
    // Database Helper
    DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_storage);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		db = new DatabaseHelper(getApplicationContext());
		
        // Creating types
        Type type1 = new Type("Recipes");
        Type type2 = new Type("Diets");
        Type type3 = new Type("Articles");
        
        
        // Inserting types in db
        long type1_id = db.createType(type1);
        long type2_id = db.createType(type2);
        long type3_id = db.createType(type3);
        
        Log.d("Type Count", "Type Count: " + db.getAllType().size());
        
        
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
        
        dbWrite(DATABASE_NAME);
        
        db.closeDB();
        
        
        
        
	}
	public void dbWrite(String DBNAME)
    {
	    Context context = getApplicationContext();
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







