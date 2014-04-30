package com.example.personalhealth;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class medicationDB {

	
	class Row extends Object {
		public String Username;
		public String PillName;
		public String OTCName;
		public String TOD;
		public String TPD;
		public String Dose;
		public String Special_Instructions;
		public String Known_Conflicts;
    }

	
    private static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS USERDATA(Place INTEGER PRIMARY KEY AUTOINCREMENT, "
            		+ "Username TEXT, "
                    + "PillName TEXT, "
                    + "OTCName TEXT, "
                    + "TOD TEXT, "
                    + "TPD TEXT, "
                    + "Dose TEXT, "
                    + "Special_Instructions TEXT, "
                    + "Known_Conflicts"
                    +");";

    private static final String DATABASE_NAME = "USERDB_Medication";

    private static final String DATABASE_TABLE = "USERDATA";

    private SQLiteDatabase db;

    public medicationDB(Context ctx) {
        db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);
        if(dbFile.exists()==true)
        {
        	db.execSQL(DATABASE_CREATE);
        }
    }
    public void close() {
        db.close();
    }

    public void createRow(String Username, String PillName, String OTCName,
            String TOD, String TPD, String Dose, String Special_Instructions, String Known_Conflicts) 
    {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("PillName", PillName);
        userValues.put("OTCName", OTCName);
        userValues.put("TOD", TOD);
        userValues.put("TPD", TPD);
        userValues.put("Dose", Dose);
        userValues.put("Special_Instructions", Special_Instructions);
        userValues.put("Known_Conflicts", Known_Conflicts);
        db.insert(DATABASE_TABLE, null, userValues);
    }

    public void deleteRow(Integer place) {
        db.delete(DATABASE_TABLE, "Place=" + place, null);
    }
    public int checkExists(Integer place) {
    	int exists=0;
    	Cursor c = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+DATABASE_TABLE+" WHERE Place ='"+place+"')",null);
    	if ((c != null) && (c.moveToFirst())) {
    		exists = c.getInt(0);
    	}
    	return exists;
    }

    public void updateRow(Integer Place, String Username, String PillName, String OTCName,
            String TOD, String TPD, String Dose, String Special_Instructions, String Known_Conflicts) 
    {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("PillName", PillName);
        userValues.put("OTCName", OTCName);
        userValues.put("TOD", TOD);
        userValues.put("TPD", TPD);
        userValues.put("Dose", Dose);
        userValues.put("Special_Instructions", Special_Instructions);
        userValues.put("Known_Conflicts", Known_Conflicts);
        db.update(DATABASE_TABLE, userValues, "Place ='"+Place+"'", null);
    }
    public Row getsingleRow(Integer place) {
        Row row = new Row();
        Cursor c =
        		db.query(DATABASE_TABLE, new String[] {"Place","Username","PillName","OTCName","TOD",
        				"TPD","Dose","Special_Instructions","Known_Conflicts"}, "Place ='"+place+"'", null, null, null, null);
        
        if ((c != null) && (c.moveToFirst())) {
            row.Username = c.getString(1);
            row.PillName = c.getString(2);
            row.OTCName = c.getString(3);
            row.TOD = c.getString(4);
            row.TPD = c.getString(5);
            row.Dose = c.getString(6);
            row.Special_Instructions = c.getString(7);
            row.Known_Conflicts = c.getString(8);
            c.close();
        } 
        return row;
    }
    public Cursor GetAllRows() {
        try {
            return db.query(DATABASE_TABLE, new String[] {"Place","Username","PillName","OTCName","TOD",
    				"TPD","Dose","Special_Instructions","Known_Conflicts"}, null, null, null, null, null);
        } catch (SQLException e) {
            Log.e("Exception on query", e.toString());
            return null;
        }
    }
    public int lastEntry()
    {
    	int lastPlace=0;
    	Cursor c=db.rawQuery("SELECT Place FROM USERDATA ORDER BY Place DESC LIMIT 1", null);
    	if ((c != null) && (c.moveToFirst())) 
    	{
    		lastPlace=c.getInt(0);
    	}
    	return lastPlace;
    }
}
