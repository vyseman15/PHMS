package com.example.personalhealth;


import com.example.personalhealth.userInfoDB.Row;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.os.Build;



public class Login extends ActionBarActivity {

	public final static String EXTRA_MESSAGE = "medical.app";
	int count=0;
	long start_wait_period=0;
	
	
	
	/** Called when the user clicks the Send button */
	//Transition page from Login to Register
	public void Register_New_User(View view) {
	    Intent login_intent = new Intent(this, Register.class);
	    startActivity(login_intent);
	}	
	
	//Transition from Login to Main Page
	public void sign_in_user(View view){
		
		
		//Holds login credentials
		Row loginCredentials;
		
		//creates or opens the database
		userInfoDB user_Information = new userInfoDB(this);
		
		//get username from edittext
		EditText username_text = (EditText)findViewById(R.id.username);
		String username = username_text.getText().toString();
		
		//get password from edittext
		EditText Password_text = (EditText)findViewById(R.id.password);
		String Password = Password_text.getText().toString();
		if (count == 4)
		{
			start_wait_period = System.currentTimeMillis();
			username_text.setError("You have enterted to many incorrect passwords, the app will not let you attempt login for 15 minutes.");
			Password_text.setError("You have enterted to many incorrect passwords, the app will not let you attempt login for 15 minutes.");
			count = count + 1;
		}
		if (count > 4)
		{
			if((System.currentTimeMillis()-start_wait_period)>=900000)
			{
				count = 0;
				username_text.setError("You can now try to enter a new username and password.");
				Password_text.setError("You can now try to enter a new username and password.");
			}
		}
		else if(count < 4)
		{
		//make sure username is not blank
		if(username == null || username.equals(""))
		{
			username_text.setError("Valid Username is Required");
		}else{
		
		//check database for existing username
		if(user_Information.checkExists(username)==1)
		{
		
		//do query for the password and username in the database
		loginCredentials = user_Information.checkUserNameandPassword(username);
		
		//check that password is valid
		if(loginCredentials.Password.equals(Password))
		{
		Intent signin_intent = new Intent(this, MainPage.class);
		signin_intent.putExtra(EXTRA_MESSAGE,username);
		startActivity(signin_intent);
		finish();
		}
		//Give warnings to user if password or username is bad
		else
		{
			count = count + 1;
			Password_text.setError("Please enter a valid username and password.");
		}}
		else
		{
			username_text.setError("Valid Username is Required");
		}}}
	}
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        userInfoDB Db = new userInfoDB(this);
        Db.close();
    }
}
    /* DO NOT WORRY ABOUT THIS
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
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
     
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_login, container, false);
            return rootView;
        }
    }

}
    */