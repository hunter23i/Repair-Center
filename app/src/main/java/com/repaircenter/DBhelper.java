package com.repaircenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DBhelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "User_Phone.db";

    private static final String TABLE_CLIENT = "Client";
    private static final String TABLE_PHONE = "Phone";
    private final static String BARCODE= "Barcode";
    private static final String NAME = "Name";
    private static final String PHONE_NUMBER = "PhoneNumber";
    private static final String PHONE_MODEL = "PhoneModel";
    private static final String IMEI_NUMBER = "IMEI";
    private static final String DESCRIPTION = "Description";
    private static final String STATUS = "Status";

    private final Context context;



    public DBhelper (@Nullable Context context) {
        super(context, "User_Phone.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Client (Barcode TEXT primary key  not null,Name TEXT  not null , PhoneNumber TEXT" +
                "R not null)");
        DB.execSQL("create Table Phone (Barcode TEXT primary key not null, PhoneModel TEXT not null , IMEI TEXT not null, Description TEXT not null, Status TEXT not null)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Client");
        DB.execSQL("drop Table if exists Phone");
        onCreate(DB);
    }

    public Boolean insertuserdata(String barcode, String name, String phnumber){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues v1= new ContentValues();
        v1.put(BARCODE, barcode);
        v1.put(NAME, name);
        v1.put(PHONE_NUMBER, phnumber);

        long result=DB.insert(TABLE_CLIENT, null, v1);
        DB.insert(TABLE_CLIENT, null, v1);
     //   DB.insert("Client", null, v1);

        return result != -1;
    }

    public Boolean insertphonedata(String barcode, String phmodel, String IMEI, String description, String status){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues v1= new ContentValues();
        v1.put(BARCODE, barcode);
        v1.put(PHONE_MODEL, phmodel);
        v1.put(IMEI_NUMBER, IMEI);
        v1.put(DESCRIPTION, description);
        v1.put(STATUS, status);
        long result=DB.insert(TABLE_PHONE,null,v1);
        DB.insert(TABLE_PHONE,null,v1);


      //  long result=DB.insert("Phone", null, v1);
      //  DB.insert("Phone", null, v1);

        return result != -1;

    }

    public void updateClientData(String barcode, String name, String phnumber){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(BARCODE, barcode);
        contentValues.put(NAME, name);
        contentValues.put(PHONE_NUMBER, phnumber);
        long result = DB.update(TABLE_CLIENT, contentValues, "Barcode=?", new String[]{barcode});
        if (result == -1){
            Toast.makeText(context,"Failed to Update.",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Update!", Toast.LENGTH_SHORT).show();
        }
    }

    public void updatePhoneData(String barcode, String phmodel, String imei, String description, String status) {
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BARCODE, barcode);
        contentValues.put(PHONE_MODEL, phmodel);
        contentValues.put(IMEI_NUMBER, imei);
        contentValues.put(DESCRIPTION, description);
        contentValues.put(STATUS, status);
            long result = DB.update(TABLE_PHONE, contentValues, "Barcode=?", new String[]{barcode});
          if (result == -1){
              Toast.makeText(context,"Failed to Update.",Toast.LENGTH_SHORT).show();
          } else {
              Toast.makeText(context, "Successfully Update!", Toast.LENGTH_SHORT).show();
          }
    }

    public void deleteClient(String barcode) {
        SQLiteDatabase DB = this.getWritableDatabase();

        long result = DB.delete(TABLE_CLIENT,"Barcode = ?",new String[]{barcode} );

        if (result == -1){
            Toast.makeText(context,"Failed to Delete.",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Client Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }
    public void deletePhone(String barcode) {
        SQLiteDatabase DB = this.getWritableDatabase();

          long result = DB.delete(TABLE_PHONE,  "Barcode = ?",new String[]{barcode} );
        if (result == -1){
            Toast.makeText(context,"Failed to Delete.",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Phone Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getclientdata(){
        SQLiteDatabase DB = this.getReadableDatabase();
        String query = "SELECT * FROM Client";
        Cursor cursor = null;
        if (DB != null){
            cursor = DB.rawQuery(query,null);
        }
        return cursor;
    }


    Cursor getphonedata(){
        SQLiteDatabase DB = this.getReadableDatabase();
        String query = "SELECT * FROM Phone";
        Cursor cursor = null;
        if (DB != null){
            cursor = DB.rawQuery(query,null);
        }
        return cursor;
    }

    void deleteAllData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("DELETE FROM " + TABLE_PHONE);
        DB.execSQL("DELETE FROM " + TABLE_CLIENT);
    }


}