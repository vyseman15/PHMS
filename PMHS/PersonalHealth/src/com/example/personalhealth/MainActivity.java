package com.example.personalhealth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;


public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.example.personalhealth.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);

    }
    
    
    /** Called when the user clicks the Storage button */
    public void gotoStorage(View view) {
    	Intent intent = new Intent(this, DisplayStorageActivity.class);
    	startActivity(intent);
    }
    
}

