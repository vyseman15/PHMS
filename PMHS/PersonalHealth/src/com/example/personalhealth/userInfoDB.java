package com.example.personalhealth;


import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class userInfoDB {

	
	class Row extends Object {
        public String Username,Password,Guest_Password;
        public String First_Name;
        public String Last_Name,Email;
        public String Gender;
        public String Street_Address, City, State, Doctors_Name, Doctors_Email;
        public String Doctors_Phone;
        public Integer Age, Weight, Height_Feet, Height_Inches, Zipcode,Guest_User;
    }

	
    private static final String DATABASE_CREATE =
        "CREATE TABLE IF NOT EXISTS USERDATA(Username TEXT PRIMARY KEY, "
        	+ "Password TEXT, "
            + "First_Name TEXT, "
            + "Last_Name TEXT, "
            + "Email TEXT, "
            + "Street_Address TEXT, "
            + "City TEXT, "
            + "State TEXT, "
            + "Zipcode INTEGER, "
            + "Age INTEGER, "
            + "Gender TEXT, "
            + "Weight INTEGER, "
            + "Height_Feet INTEGER, "
            + "Height_Inches TEXT, "
            + "Doctors_Name TEXT, "
            + "Doctors_Email TEXT, "
            + "Doctors_Phone TEXT, "
            + "Guest_Password TEXT"
            +");";

    private static final String DATABASE_NAME = "USERDB";

    private static final String DATABASE_TABLE = "USERDATA";

    private SQLiteDatabase db;

    public userInfoDB(Context ctx) {
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

    public void createRow(
    		String Username, String Password,String First_Name, String Last_Name,String Email,
    		String Street_Address, String City,String State, int Zipcode,
    		int Age, String Gender,int Weight, int Height_Feet,
    		int Height_Inches, String Doctors_Name,String Doctors_Email, String Doctors_Phone) {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("Password", Password);
        userValues.put("First_Name", First_Name);
        userValues.put("Last_Name", Last_Name);
        userValues.put("Email", Email);
        userValues.put("Street_Address", Street_Address);
        userValues.put("City", City);
        userValues.put("State", State);
        userValues.put("Zipcode", Zipcode);
        userValues.put("Age", Age);
        userValues.put("Gender", Gender);
        userValues.put("Weight", Weight);
        userValues.put("Height_Feet", Height_Feet);
        userValues.put("Height_Inches", Height_Inches);
        userValues.put("Doctors_Name", Doctors_Name);
        userValues.put("Doctors_Email", Doctors_Email);
        userValues.put("Doctors_Phone", Doctors_Phone);
        userValues.put("Guest_Password", "");
        db.insert(DATABASE_TABLE, null, userValues);
    }

    public void deleteRow(String Username) {
        db.delete(DATABASE_TABLE, "Username=" + Username, null);
    }
    public int checkExists(String Username) {
    	int exists=0;
    	Cursor c = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+DATABASE_TABLE+" WHERE Username ='"+Username+"')",null);
    	if ((c != null) && (c.moveToFirst())) {
    		exists = c.getInt(0);
    	}
    	return exists;
    }


    public void updateRow(
    		String Username, String Password,String First_Name, String Last_Name,String Email,
    		String Street_Address, String City,String State, int Zipcode,
    		int Age, String Gender,int Weight, int Height_Feet,
    		int Height_Inches, String Doctors_Name,String Doctors_Email, String Doctors_Phone, String Guest_Password) {
        ContentValues userValues = new ContentValues();
        userValues.put("Username", Username);
        userValues.put("Password", Password);
        userValues.put("First_Name", First_Name);
        userValues.put("Last_Name", Last_Name);
        userValues.put("Email", Email);
        userValues.put("Street_Address", Street_Address);
        userValues.put("City", City);
        userValues.put("State", State);
        userValues.put("Zipcode", Zipcode);
        userValues.put("Age", Age);
        userValues.put("Gender", Gender);
        userValues.put("Weight", Weight);
        userValues.put("Height_Feet", Height_Feet);
        userValues.put("Height_Inches", Height_Inches);
        userValues.put("Doctors_Name", Doctors_Name);
        userValues.put("Doctors_Email", Doctors_Email);
        userValues.put("Doctors_Phone", Doctors_Phone);
        userValues.put("Guest_Password", Guest_Password);
        db.update(DATABASE_TABLE, userValues, "Username ='"+Username+"'", null);
    }
    public Row getsingleRow(String username) {
        Row row = new Row();
        Cursor c =
        		db.query(DATABASE_TABLE, new String[] {
                        "Username", "Password", "First_Name",
                        "Last_Name","Email", "Street_Address", "City",
                        "State", "Zipcode", "Age",
                        "Gender", "Weight", "Height_Feet",
                        "Height_Inches", "Doctors_Name", "Doctors_Email","Doctors_Phone","Guest_Password"}, "Username ='"+username+"'", null, null, null, null);
        
        if ((c != null) && (c.moveToFirst())) {
            row.Username = c.getString(0);
            row.Password = c.getString(1);
            row.First_Name = c.getString(2);
            row.Last_Name = c.getString(3);
            row.Email = c.getString(4);
            row.Street_Address = c.getString(5);
            row.City = c.getString(6);
            row.State = c.getString(7);
            row.Zipcode = c.getInt(8);
            row.Age = c.getInt(9);
            row.Gender = c.getString(10);
            row.Weight = c.getInt(11);
            row.Height_Feet = c.getInt(12);
            row.Height_Inches = c.getInt(13);
            row.Doctors_Name = c.getString(14);
            row.Doctors_Email = c.getString(15);
            row.Doctors_Phone = c.getString(16);
            row.Guest_Password = c.getString(17);
            c.close();
        } 
        return row;
    }
    
    public Row checkUserNameandPassword(String username) {
        Row loginCredentials = new Row();
        Cursor c =
        		db.query(DATABASE_TABLE, new String[] {
                        "Username", "Password","Guest_Password"}, "Username ='"+username+"'", null, null, null, null);
        
        if ((c != null) && (c.moveToFirst())) {
        	loginCredentials.Username = c.getString(0);
        	loginCredentials.Password = c.getString(1);
        	loginCredentials.Guest_Password = c.getString(2);
            c.close();
        } 
        return loginCredentials;
    }
    
    public Cursor GetAllRows() {
        try {
            return db.query(DATABASE_TABLE, new String[] {
                    "Username", "Password", "First_Name",
                    "Last_Name", "Email", "Street_Address", "City",
                    "State", "Zipcode", "Age",
                    "Gender", "Weight", "Height_Feet",
                    "Height_Inches", "Doctors_Name", "Doctors_Email","Doctors_Phone","Guest_Password"}, null, null, null, null, null);
        } catch (SQLException e) {
            Log.e("Exception on query", e.toString());
            return null;
        }
    }
}
