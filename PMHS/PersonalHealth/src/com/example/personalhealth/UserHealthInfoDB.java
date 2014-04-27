package com.example.personalhealth;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserHealthInfoDB{

	
	class Row extends Object {
        public int Health_Key,Calories,Weight,BMI,Glucose,BPS,BPD,Temperature,Cholesterol,Workout_Plan,Calories_Burned;
        public String Username, Date;
    }
	public Row rowNullConstructor()
	{
		String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		Row row = new Row();
		row.Calories=0;
		row.Weight=0;
		row.BMI=0;
		row.Glucose=0;
		row.BPS=0;
		row.BPD=0;
		row.Temperature=0;
		row.Cholesterol=0;
		row.Workout_Plan=0;
		row.Calories_Burned=0;
		row.Username="";
		row.Date=date;
		
		
		return row;
	}
	

	
    private static final String DATABASE_CREATE =
        "CREATE TABLE IF NOT EXISTS USERHEALTHDATA(Health_Key INTEGER PRIMARY KEY AUTOINCREMENT, "
        	+ "Username TEXT, "
            + "Date TEXT, "
            + "Weight INTEGER, "
            + "Calories INTEGER, "
            + "BMI INTEGER, "
            + "Cholesterol INTEGER, "
            + "Glucose INTEGER, "
            + "BPS INTEGER, "
            + "BPD INTEGER, "
            + "Temperature INTEGER, "
            + "Workout_Plan INTEGER,"
            + "Calories_Burned INTEGER"
            +");";

    private static final String DATABASE_NAME = "HEALTHDB";

    private static final String DATABASE_TABLE = "USERHEALTHDATA";

    private SQLiteDatabase db;

    public UserHealthInfoDB(Context ctx) {
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

    public void createRow(String Username, String Date) {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("Date", Date);
        db.insert(DATABASE_TABLE, null, userValues);
    }

    public void deleteRow(String Username,String Date) {
        db.delete(DATABASE_TABLE, "Username ='"+Username+"'"+" AND "+"Date ='"+Date+"'", null);
    }
    public int checkExists(String Username, String Date) {
    	int exists=0;
    	Cursor c = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+DATABASE_TABLE+" WHERE Username ='"+Username+"' AND Date ='"+Date+"')",null);
    	if ((c != null) && (c.moveToFirst())) {
    		exists = c.getInt(0);
    	}
    	return exists;
    }


    public void updateDietInfo(
    		String Username, String Date,int Weight, int Calories,
    		int BMI,int Workout_Plan, int Calories_Burned) {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("Date", Date);
        userValues.put("Weight", Weight);
        userValues.put("Calories", Calories);
        userValues.put("BMI", BMI);
        userValues.put("Workout_Plan", Workout_Plan);
        userValues.put("Calories_Burned", Calories_Burned);
        db.update(DATABASE_TABLE, userValues, "Username ='"+Username+"'"+" AND "+"Date ='"+Date+"'", null);
    }
    public void updateVitalInfo(
    		String Username, String Date,int Weight, int Calories,
    		int BMI,int Cholesterol, int Glucose,
    		int BPS, int BPD, int Temperature) {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("Date", Date);
        userValues.put("Cholesterol", Cholesterol);
        userValues.put("Glucose", Glucose);
        userValues.put("BPS", BPS);
        userValues.put("BPD", BPD);
        userValues.put("Temperature", Temperature);
        db.update(DATABASE_TABLE, userValues, "Username ='"+Username+"'"+" AND "+"Date ='"+Date+"'", null);
    }
    public Row getsingleHealthRow(String Username, String Date) {
        Row row = new Row();
        Cursor c =
        		db.query(DATABASE_TABLE, new String[] {
                        "Username", "Date", "Weight",
                        "Calories","BMI", "Cholesterol",
                        "Glucose", "BPS", "BPD",
                        "Temperature","Workout_Plan","Calories_Burned"}, "Username ='"+Username+"'"+" AND "+"Date ='"+Date+"'", null, null, null, null);
        
        if ((c != null) && (c.moveToFirst())) {
            row.Username = c.getString(0);
            row.Date = c.getString(1);
            row.Weight = c.getInt(2);
            row.Calories = c.getInt(3);
            row.BMI = c.getInt(4);
            row.Cholesterol = c.getInt(5);
            row.Glucose = c.getInt(6);
            row.BPS = c.getInt(7);
            row.BPD = c.getInt(8);
            row.Temperature = c.getInt(9);
            row.Workout_Plan = c.getInt(10);
            row.Calories_Burned = c.getInt(11);
            c.close();
        } 
        return row;
    }
    
    
    public Cursor GetAllRows() {
        try {
            return db.query(DATABASE_TABLE, new String[] {
                    "Username", "Date", "Weight",
                    "Calories","BMI", "Cholesterol",
                    "Glucose", "BPS", "BPD",
                    "Temperature","Workout_Plan","Calories_Burned"}, null, null, null, null, null);
        } catch (SQLException e) {
            Log.e("Exception on query", e.toString());
            return null;
        }
    }
}
