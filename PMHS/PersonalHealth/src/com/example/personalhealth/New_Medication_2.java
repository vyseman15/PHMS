package com.example.personalhealth;

import java.util.ArrayList;

import com.example.personalhealth.New_Medication_3;
import com.example.personalhealth.medicationDB.Row;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class New_Medication_2 extends Activity {


	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__medication_2);
		String message4;
		String message5="";
		String message6="";
		
		CheckBox checkBoxM = (CheckBox) findViewById(R.id.CBM);
        if (checkBoxM.isChecked()) {
            checkBoxM.setChecked(false);
            message5=message5.concat("Morning");
            
        }
        CheckBox checkBoxA = (CheckBox) findViewById(R.id.CBA);
        if (checkBoxA.isChecked()) {
            checkBoxA.setChecked(false);
            if(message5.equalsIgnoreCase(""))
            {
            	message5=message5.concat("Afternoon");
            }
            else
            {
            	message5=message5.concat(", Afternoon");
            }
        }
        CheckBox checkBoxE = (CheckBox) findViewById(R.id.CBE);
        if (checkBoxE.isChecked()) {
            checkBoxE.setChecked(false);

            if(message5.equalsIgnoreCase(""))
            {
            	message5=message5.concat("Evening");
            }
            else
            {
            	message5=message5.concat(", Evening");
            }
            
        }
	    EditText editText4 = (EditText) findViewById(R.id.MedDosage);
		message4 = editText4.getText().toString();
		
		
		CheckBox Mon = (CheckBox) findViewById(R.id.CBMon);
        if (Mon.isChecked()) {
            checkBoxM.setChecked(false);
            message6=message6.concat("Monday");
            
        }
        CheckBox Tues = (CheckBox) findViewById(R.id.CBTues);
        if (Tues.isChecked()) {
            Tues.setChecked(false);
            if(message5.equalsIgnoreCase(""))
            {
            	message6=message6.concat("Tuesday");
            }
            else
            {
            	message6=message6.concat(", Tuesday");
            }
        }
        CheckBox Wednes = (CheckBox) findViewById(R.id.CBWednes);
        if (Wednes.isChecked()) {
            Wednes.setChecked(false);

            if(message6.equalsIgnoreCase(""))
            {
            	message6=message6.concat("Wednesday");
            }
            else
            {
            	message6=message6.concat(", Wednesday");
            }
            
        }
        CheckBox Thurs = (CheckBox) findViewById(R.id.CBThurs);
        if (Thurs.isChecked()) {
            Thurs.setChecked(false);
            if(message6.equalsIgnoreCase(""))
            {
            	message6=message6.concat("Thursday");
            }
            else
            {
            	message6=message6.concat(", Thursday");
            }
        }
        CheckBox Fri = (CheckBox) findViewById(R.id.CBFri);
        if (Fri.isChecked()) {
            Fri.setChecked(false);
            if(message6.equalsIgnoreCase(""))
            {
            	message6=message6.concat("Friday");
            }
            else
            {
            	message6=message6.concat(", Friday");
            }
        }
        CheckBox Sat = (CheckBox) findViewById(R.id.CBSat);
        if (Sat.isChecked()) {
            Sat.setChecked(false);

            if(message6.equalsIgnoreCase(""))
            {
            	message6=message6.concat("Saturday");
            }
            else
            {
            	message6=message6.concat(", Saturday");
            }
            
        }
        CheckBox Sun = (CheckBox) findViewById(R.id.CBSun);
        if (Sun.isChecked()) {
            Sun.setChecked(false);

            if(message6.equalsIgnoreCase(""))
            {
            	message6=message6.concat("Sunday");
            }
            else
            {
            	message6=message6.concat(", Sunday");
            }
            
        }
        
        medicationDB Db = new medicationDB(this);
		Row newmed = Db.getsingleRow(Db.lastEntry());
		Db.updateRow(Db.lastEntry(), newmed.Username, newmed.PillName, newmed.OTCName, message5, message6, message4, newmed.Special_Instructions, null);			
		
	}
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new__medication_2, menu);
		return true;
	}

	public void Go_To_Next_3(View view)
    {
    	Intent intent = new Intent(this, New_Medication_3.class);
    	startActivity(intent);
    	finish();
    }

}
