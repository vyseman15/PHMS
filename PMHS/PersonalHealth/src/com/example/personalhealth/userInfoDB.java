package com.example.personalhealth;


import java.io.File;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class userInfoDB {

	//Class used to pass data from the databases
	class Row extends Object {
        public String Username,Password;
        public String First_Name;
        public String Last_Name,Email;
        public String Gender;
        public String Street_Address, City, State, Doctor_Name, Doctor_Email, Doctor_Type, Appointment_Date;
        public String Doctor_Phone;
        public Integer Age, Weight, Height_Feet, Height_Inches, Zipcode;
    }

	///Define the structure of the database
    private static final String DATABASE_CREATE =
        "CREATE TABLE IF NOT EXISTS USERSTUFF(Username TEXT PRIMARY KEY, "
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
            + "Height_Inches TEXT"
            +");";

    private static final String DOCTOR_TABLE_CREATE = 
    		"CREATE TABLE IF NOT EXISTS DOCTOR_DATA(key INTEGER PRIMARY KEY AUTOINCREMENT, "
    				+ "Username TEXT, "
    				+ "Doctor_Name TEXT, "
    				+ "Doctor_Email TEXT, "
    	            + "Doctor_Phone TEXT, " 
    	            + "Doctor_Type TEXT"
    	            +");";
    private static final String APPOINTMENT_TABLE_CREATE = 
    		"CREATE TABLE IF NOT EXISTS APPOINTMENT_DATA(key INTEGER PRIMARY KEY AUTOINCREMENT, "
    				+ "Username TEXT, "
    				+ "Doctor_Name TEXT, "
    				+ "Appointment_Date TEXT, "
    				+ "Appointment_Time TEXT"
    	            +");";
    
    //Database and table names
    private static final String DATABASE_NAME = "MainUserDB";
    private static final String DATABASE_TABLE = "USERSTUFF";
    private static final String DOCTOR_TABLE ="DOCTOR_DATA";
    private static final String APPOINTMENT_TABLE ="APPOINTMENT_DATA";
    private SQLiteDatabase db;

    public userInfoDB(Context ctx) {
        db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
        File dbFile = ctx.getDatabasePath(DATABASE_NAME);
        if(dbFile.exists()==true)
        {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(DOCTOR_TABLE_CREATE);
        db.execSQL(APPOINTMENT_TABLE_CREATE);
        }
    }
    public void close() {
        db.close();
    }

    
    ///All the functions which operate on the Appointment Table such as create, update, view, and delete row
    public void createAppointmentrRow(String Username, String Doctor_Name,String Appointment_Date, String Appointment_Time)
    {
    	ContentValues userValues = new ContentValues();
    	userValues.put("Username", Username);
    	userValues.put("Doctor_Name", Doctor_Name);
    	userValues.put("Appointment_Date", Appointment_Date);
    	userValues.put("Appointment_Time", Appointment_Time);
    	db.insert(APPOINTMENT_TABLE, null, userValues);
    }
    public void updateAppointmentrRow(String Username, String Doctor_Name,String Appointment_Date, String Appointment_Time)
    {
    	ContentValues userValues = new ContentValues();
    	userValues.put("Username", Username);
    	userValues.put("Doctor_Name", Doctor_Name);
    	userValues.put("Appointment_Date", Appointment_Date);
    	userValues.put("Appointment_Time", Appointment_Time);
    	db.update(APPOINTMENT_TABLE, userValues, "Username ='"+Username+"' AND Doctor_Name ='"+Doctor_Name+"' AND Appointment_Date ='"+Appointment_Date+"'", null);
    }
    public void deleteAppointmentRow(String Username, String Doctor_Name, String Appointment_Date, String Appointment_Time) {
        db.delete(APPOINTMENT_TABLE, "Username ='"+Username+"' AND Doctor_Name ='"+Doctor_Name+"' AND Appointment_Date ='"+Appointment_Date+"' AND Appointment_Time ='"+Appointment_Time+"'", null);
    }
    public int checkAppointmentExists(String Username, String Doctor_Name, String Appointment_Date, String Appointment_Time){
    	int exists=0;
    	Cursor c = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+APPOINTMENT_TABLE+" WHERE Username ='"+Username+"' AND Doctor_Name ='"+Doctor_Name+"' AND Appointment_Date ='"+Appointment_Date+"' AND Appointment_Time ='"+Appointment_Time+"')",null);
    	if ((c != null) && (c.moveToFirst())) {
    		exists = c.getInt(0);
    	}
    	return exists;
    }
    public Cursor getAppointmentInformation(String Username)
    {
    	Cursor c =
        		db.query(APPOINTMENT_TABLE, new String[] {"Doctor_Name","Appointment_Date","Appointment_Time"},
        				"Username ='"+Username+"'",null,null,null,null,null);
    	
    	return c;
    }
    
    
    
    ///All the functions which operate on the Doctor Table such as create, update, view, and delete row
    public void createDoctorRow(String Username, String Doctor_Name,String Doctor_Phone, String Doctor_Email, String Doctor_Type)
    {
    	ContentValues userValues = new ContentValues();
    	userValues.put("Username", Username);
    	userValues.put("Doctor_Name", Doctor_Name);
    	userValues.put("Doctor_Phone", Doctor_Phone);
    	userValues.put("Doctor_Email", Doctor_Email);
    	userValues.put("Doctor_Type", Doctor_Type);
    	db.insert(DOCTOR_TABLE, null, userValues);
    }
    public void updateDoctorRow(String Username, String Doctor_Name,String Doctor_Phone, String Doctor_Email, String Doctor_Type)
    {
    	ContentValues userValues = new ContentValues();
    	userValues.put("Username", Username);
    	userValues.put("Doctor_Name", Doctor_Name);
    	userValues.put("Doctor_Phone", Doctor_Phone);
    	userValues.put("Doctor_Email", Doctor_Email);
    	userValues.put("Doctor_Type", Doctor_Type);
    	db.update(DOCTOR_TABLE, userValues, "Doctor_Name ='"+Doctor_Name+"' AND Username ='"+Username+"'", null);
    }
    public void deleteDoctorRow(String Username, String Doctor_Name) {
        db.delete(DOCTOR_TABLE, "Doctor_Name ='"+Doctor_Name+"' AND Username ='"+Username+"'", null);
    }
    public int checkDoctorExists(String Username, String Doctor_Name){
    	int exists=0;
    	Cursor c = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+DOCTOR_TABLE+" WHERE Doctor_Name ='"+Doctor_Name+"' AND Username ='"+Username+"')",null);
    	if ((c != null) && (c.moveToFirst())) {
    		exists = c.getInt(0);
    	}
    	return exists;
    }
    public Cursor getDoctorInformation(String Username)
    {
    	Cursor c =
        		db.query(DOCTOR_TABLE, new String[] {"Doctor_Name","Doctor_Phone","Doctor_Email","Doctor_Type"},
        				"Username ='"+Username+"'",null,null,null,null,null);
    	
    	return c;
    }
    
    
    ///All the functions which operate on the User Table such as create, update, view, and delete row
    public void createRow(
    		String Username, String Password,String First_Name, String Last_Name,String Email,
    		String Street_Address, String City,String State, int Zipcode,
    		int Age, String Gender,int Weight, int Height_Feet,
    		int Height_Inches) {
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
        db.insert(DATABASE_TABLE, null, userValues);
    }

    public void deleteUserRow(String Username) {
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
    		int Height_Inches) {
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
        db.update(DATABASE_TABLE, userValues, "Username ='"+Username+"'", null);
    }
    public Row getsingleRow(String Username) {
        Row row = new Row();
        Cursor c =
        		db.query(DATABASE_TABLE, new String[] {
                        "Username", "Password", "First_Name",
                        "Last_Name","Email", "Street_Address", "City",
                        "State", "Zipcode", "Age",
                        "Gender", "Weight", "Height_Feet",
                        "Height_Inches"}, "Username ='"+Username+"'", null, null, null, null);
        
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
            c.close();
        } 
        return row;
    }
    
    public Row checkUserNameandPassword(String username) {
        Row loginCredentials = new Row();
        Cursor c =
        		db.query(DATABASE_TABLE, new String[] {
                        "Username", "Password"}, "Username ='"+username+"'", null, null, null, null);
        
        if ((c != null) && (c.moveToFirst())) {
        	loginCredentials.Username = c.getString(0);
        	loginCredentials.Password = c.getString(1);
            c.close();
        } 
        return loginCredentials;
    }
    
    public Cursor GetAllUserRows() {
        try {
            return db.query(DATABASE_TABLE, new String[] {
                    "Username", "Password", "First_Name",
                    "Last_Name", "Email", "Street_Address", "City",
                    "State", "Zipcode", "Age",
                    "Gender", "Weight", "Height_Feet",
                    "Height_Inches"}, null, null, null, null, null);
        } catch (SQLException e) {
            Log.e("Exception on query", e.toString());
            return null;
        }
    }
}
