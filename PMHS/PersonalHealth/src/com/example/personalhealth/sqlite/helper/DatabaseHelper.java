package com.example.personalhealth.sqlite.helper;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.personalhealth.sqlite.model.Storage;
import com.example.personalhealth.sqlite.model.Type;

public class DatabaseHelper extends SQLiteOpenHelper {
	 
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "storageManager";
 
    // Table Names
    private static final String TABLE_STORAGE = "storage";
    private static final String TABLE_TYPE = "type";
    private static final String TABLE_STORAGE_TYPE = "storage_type";
 
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
 
    // STORAGE Table - column names
    private static final String KEY_NAME = "name";
    private static final String KEY_URL = "url";
    private static final String KEY_STATUS = "status";
 
    // TYPE Table - column names
    private static final String KEY_TYPE_NAME = "type_name";
 
    // STORAGE_TYPE Table - column names
    private static final String KEY_STORAGE_ID = "storage_id";
    private static final String KEY_TYPE_ID = "type_id";
 
    // Table Create Statements
    // STORAGE table create statement
    private static final String CREATE_TABLE_STORAGE = "CREATE TABLE "
            + TABLE_STORAGE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_URL + " URL," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";
 /*
    // TYPE table create statement
    private static final String CREATE_TABLE_TYPE = "CREATE TABLE " + TABLE_TYPE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME," + ")";
 */
    
    // TYPE table create statement
    private static final String CREATE_TABLE_TYPE = "CREATE TABLE " + TABLE_TYPE
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";
    
    // storage_type table create statement
    private static final String CREATE_TABLE_STORAGE_TYPE = "CREATE TABLE "
            + TABLE_STORAGE_TYPE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_STORAGE_ID + " INTEGER," + KEY_TYPE_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";
 
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
        db.execSQL(CREATE_TABLE_STORAGE);
        db.execSQL(CREATE_TABLE_TYPE);
        db.execSQL(CREATE_TABLE_STORAGE_TYPE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORAGE_TYPE);
 
        // create new tables
        onCreate(db);
    }
    
    /*
     * Creating a storage
     */
    public long createStorage(Storage storage, long[] type_ids) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, storage.getName());
        values.put(KEY_URL, storage.getUrl());
        values.put(KEY_STATUS, storage.getStatus());
        values.put(KEY_CREATED_AT, getDateTime());
     
        // insert row
        long storage_id = db.insert(TABLE_STORAGE, null, values);
     
        // assigning type to storage
        for (long type_id : type_ids) {
            createStorageType(storage_id, type_id);
        }
     
        return storage_id;
    }
    
    /*
     * get single storage
     */
    public Storage getStorage(long storage_id) {
        SQLiteDatabase db = this.getReadableDatabase();
     
        String selectQuery = "SELECT * FROM " + TABLE_STORAGE + " WHERE "
                + KEY_ID + " = " + storage_id;
     
        Log.e(LOG, selectQuery);
     
        Cursor c = db.rawQuery(selectQuery, null);
     
        if (c != null)
            c.moveToFirst();
     
        Storage st = new Storage();
        st.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        st.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        st.setUrl((c.getString(c.getColumnIndex(KEY_URL))));
        st.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
     
        return st;
    }
    
    /*
     * getting all storage
     * */
    public List<Storage> getAllStorage() {
        List<Storage> storages = new ArrayList<Storage>();
        String selectQuery = "SELECT * FROM " + TABLE_STORAGE;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Storage st = new Storage();
                st.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                st.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                st.setUrl((c.getString(c.getColumnIndex(KEY_URL))));
                st.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
     
                // adding to storage list
                storages.add(st);
            } while (c.moveToNext());
        }
        return storages;
    }
    
    /*
     * getting all storage under single type
     * */
    public List<Storage> getAllStorageByType(String type_name) {
        List<Storage> storages = new ArrayList<Storage>();

        String selectQuery = "SELECT * FROM " + TABLE_STORAGE + " st, "
                + TABLE_TYPE + " ty, " + TABLE_STORAGE_TYPE + " sy WHERE ty."
                + KEY_TYPE_NAME + " = '" + type_name + "'" + " AND ty." + KEY_ID
                + " = " + "sy." + KEY_TYPE_ID + " AND st." + KEY_ID + " = "
                + "sy." + KEY_STORAGE_ID;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Storage st = new Storage();
                st.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                st.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                st.setUrl((c.getString(c.getColumnIndex(KEY_URL))));
                st.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
     
                // adding to storages list
                storages.add(st);
            } while (c.moveToNext());
        }
     
        return storages;
    }
    
    /**
     * getting storage count
     */
    public int getStorageCount() {
        String countQuery = "SELECT * FROM " + TABLE_STORAGE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
 
        int count = cursor.getCount();
        cursor.close();
 
        // return count
        return count;
    }
    
    /*
     * Updating a storage
     */
    public int updateStorage(Storage storage) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, storage.getName());
        values.put(KEY_URL, storage.getUrl());
        values.put(KEY_STATUS, storage.getStatus());
     
        // updating row
        return db.update(TABLE_STORAGE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(storage.getId()) });
    }
    
    /*
     * Deleting a storage
     */
    public void deleteStorage(long storage_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STORAGE, KEY_ID + " = ?",
                new String[] { String.valueOf(storage_id) });
    }
    
    /*
     * Creating type
     */
    public long createType(Type type) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE_NAME, type.getTypeName());
        values.put(KEY_CREATED_AT, getDateTime());
     
        // insert row
        long type_id = db.insert(TABLE_TYPE, null, values);
     
        return type_id;
    }
    
    /**
     * getting all type
     * */
    public List<Type> getAllType() {
        List<Type> types = new ArrayList<Type>();
        String selectQuery = "SELECT * FROM " + TABLE_TYPE;
     
        Log.e(LOG, selectQuery);
     
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
     
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Type t = new Type();
                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
                t.setTypeName(c.getString(c.getColumnIndex(KEY_TYPE_NAME)));
     
                // adding to types list
                types.add(t);
            } while (c.moveToNext());
        }
        return types;
    }
    
    /*
     * Updating a type
     */
    public int updateType(Type type) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE_NAME, type.getTypeName());
     
        // updating row
        return db.update(TABLE_TYPE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(type.getId()) });
    }
    
    /*
     * Deleting a type, also deletes storage with same type
     */
    public void deleteType(Type type, boolean should_delete_all_type_storage) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        // before deleting type
        // check if storages under this type should also be deleted
        if (should_delete_all_type_storage) {
            // get all storages under this type
            List<Storage> allTypeStorage = getAllStorageByType(type.getTypeName());
     
            // delete all storages
            for (Storage storage : allTypeStorage) {
                // delete storage
                deleteStorage(storage.getId());
            }
        }
     
        // now delete the type
        db.delete(TABLE_TYPE, KEY_ID + " = ?",
                new String[] { String.valueOf(type.getId()) });
    }
    
    /*
     * Creating storage_type
     */
    public long createStorageType(long storage_id, long type_id) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_STORAGE_ID, storage_id);
        values.put(KEY_TYPE_ID, type_id);
        values.put(KEY_CREATED_AT, getDateTime());
 
        long id = db.insert(TABLE_STORAGE_TYPE, null, values);
 
        return id;
    }
    
    /*
     * Updating a storage type
     */
    public int updateNameType(long id, long type_id) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE_ID, type_id);
     
        // updating row
        return db.update(TABLE_STORAGE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }
    
    /**
     * Deleting a storage type
     */
    public void deleteStorageType(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STORAGE, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }
    
 // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    
    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    

    
}
