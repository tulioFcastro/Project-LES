package com.example.povmt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper{
	
	public static final String TABLE_NAME = "TempoInvestido";
	public static final String DATABASE_NAME = "LES20141";
	private static final int DATABASE_VERSION = 1;
	public static final String ID = "_id";
	
	public static final String ATIVIDADE = "atividade";
	public static final String TEMPO = "tempo";
	private static final String DATABASE_CREATE = "create table "
	+ TABLE_NAME + "( " + ID
	+ " integer primary key autoincrement, " + ATIVIDADE
	+ " text not null, " + TEMPO + " text not null);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	@Override 
	public void onCreate(SQLiteDatabase db) { 
	          db.execSQL(DATABASE_CREATE); 
	} 
	@Override 
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
	          Log.w(DbHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " 
	        		  + newVersion + ", which will destroy all old data"); 
	          db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); 
	onCreate(db); 
	}
	
}
