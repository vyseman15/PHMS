package com.example.personalhealth;

import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
//import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
//import android.os.Build;



public class InteractContact extends ActionBarActivity {
	String Phonenumber = " ";
	String Email = " ";

	public void Email(View view)
	{
		
	}
	
	public void TextMessage(View view)
	{
		Intent openTheTextEditor = new Intent(Intent.ACTION_VIEW);		
		openTheTextEditor.setType("vnd.android-dir/mms-sms");
		openTheTextEditor.putExtra("address", Phonenumber);
		startActivity(openTheTextEditor);
	}
	
	public void PhoneCall(View view)
	{
		
	}
	
	public void Delete(View view)
	{
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Get the message from the intent
	    Intent intent = getIntent();
	    Phonenumber = intent.getStringExtra(ViewContacts.EXTRA_MESSAGE);

	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        //@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
              View rootView = inflater.inflate(R.layout.fragment_display_message,
                      container, false);
              return rootView;
        	
        }
    }
}