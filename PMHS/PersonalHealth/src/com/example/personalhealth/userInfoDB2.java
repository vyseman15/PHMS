package com.example.personalhealth;

import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

public class userInfoDB2 {
	class Row extends Object {
		public String Username;
        public Integer Glucose;
        public Integer BPS;
        public Integer BPD;
        public Integer Cholesterol;
        public Integer Temperature;
        public String Status;
    }

	
    private static final String DATABASE_CREATE =
        "CREATE TABLE IF NOT EXISTS USERDATA(Place INTEGER PRIMARY KEY AUTOINCREMENT, "
    		+ "Username TEXT, "
            + "Glucose INTEGER, "
            + "BPS INTEGER, "
            + "BPD INTEGER, "
            + "Cholesterol INTEGER, "
            + "Temperature INTEGER, "
            + "Status TEXT"
            +");";

    private static final String DATABASE_NAME = "USERDB_Vitals";

    private static final String DATABASE_TABLE = "USERDATA";

    private SQLiteDatabase db;

    public userInfoDB2(Context ctx) {
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

    public void createRow(String Username, Integer Glucose, Integer BPS,
            Integer BPD, Integer Cholesterol, Integer Temperature, String Status) {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("BPS", BPS);
        userValues.put("BPD", BPD);
        userValues.put("Cholesterol", Cholesterol);
        userValues.put("Temperature", Temperature);
        userValues.put("Glucose", Glucose);
        userValues.put("Status", Status);
        
        db.insert(DATABASE_TABLE, null, userValues);
    }

    public void deleteRow(String Username) {
        db.delete(DATABASE_TABLE, "Username=" + Username, null);
    }
    public void deleteRow(int k) {
        db.delete(DATABASE_TABLE, "Place=" + k, null);
    }
    public int checkExists(int k) {
    	int exists=0;
    	Cursor c = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+DATABASE_TABLE+" WHERE Place ='"+k+"')",null);
    	if ((c != null) && (c.moveToFirst())) {
    		exists = c.getInt(0);
    	}
    	return exists;
    }


    public void updateRow(Integer Place, String Username, Integer Glucose, Integer BPS,
            Integer BPD, Integer Cholesterol, Integer Temperature, String Status) 
    {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("Glucose", Glucose);
        userValues.put("BPS", BPS);
        userValues.put("BPD", BPD);
        userValues.put("Cholesterol", Cholesterol);
        userValues.put("Temperature", Temperature);
        userValues.put("Status", Status);
        db.update(DATABASE_TABLE, userValues, "Place ='"+Place+"'", null);
    }
    public Row getsingleRow(String username) 
    {
        Row row = new Row();
        Cursor c =
        		db.query(DATABASE_TABLE, new String[] {"Place","Username","Glucose","BPS","BPD","Cholesterol",
        				"Temperature","Status"}, "Username ='"+username+"'", null, null, null, null);
        
        if ((c != null) && (c.moveToFirst())) {
        	row.Username = c.getString(1);
        	row.Glucose = c.getInt(2);
            row.BPS = c.getInt(3);
            row.BPD = c.getInt(4);
            row.Cholesterol = c.getInt(5);
            row.Temperature = c.getInt(6);
            row.Status = c.getString(7);
            c.close();
        } 
        return row;
    }
    public Row getsingleRow(int i) 
    {
        Row row = new Row();
        Cursor c =
        		db.query(DATABASE_TABLE, new String[] {"Place","Username","Glucose","BPS","BPD","Cholesterol",
        				"Temperature","Status"}, "Place ='"+i+"'", null, null, null, null);
        
        if ((c != null) && (c.moveToFirst())) {
        	row.Username = c.getString(1);
        	row.Glucose = c.getInt(2);
            row.BPS = c.getInt(3);
            row.BPD = c.getInt(4);
            row.Cholesterol = c.getInt(5);
            row.Temperature = c.getInt(6);
            row.Status = c.getString(7);
            c.close();
        } 
        return row;
    }
    public Cursor GetAllRows() {
        try {
            return db.query(DATABASE_TABLE, new String[] {"Place","Username","Glucose","BPS","BPD","Cholesterol",
    				"Temperature","Status"}, null, null, null, null, null);
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
