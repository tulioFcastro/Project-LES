package com.example.povmt.db;

import com.example.povmt.classes.Ti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;


public class DbAdapter {
	
	private SQLiteDatabase database;
	private DbHelper dbHelper;
	private String[] allColumns = { DbHelper.ID, DbHelper.ATIVIDADE, DbHelper.TEMPO};
	
	public DbAdapter(Context context) {          
		dbHelper = new DbHelper(context);
	}
	
	public Ti createTi(String atividade, int hora, int minutos) { 
        ContentValues values = new ContentValues(); 
        values.put(DbHelper.ATIVIDADE, atividade); 
        values.put(DbHelper.TEMPO, hora + ":" + minutos); 
        long insertId = database.insert(DbHelper.TABLE_NAME, null, values); 
       // To show how to query 
       Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns, DbHelper.ID + " = " + 
       insertId, null,null, null, null); 
       cursor.moveToFirst(); 
       return cursorToTi(cursor); 
	}
	
	public void eliminaTi (int idTI){ 
        database.delete(DbHelper.TABLE_NAME, DbHelper.ID + " = " + idTI, 
         null); 
	}
	
	private Ti cursorToTi(Cursor cursor) { 
        Ti tempoInvestido = new Ti(cursor.getString(0),cursor.getInt(1),cursor.getInt(2)); 
        return tempoInvestido; 
	}
	
	public Cursor getTis(){ 
        Cursor cursor = database.rawQuery("select _id,atividade,tempo from TempoInvestido", null); 
        return cursor; 
	}
	
	public Ti getTi (int idTi){ 
        Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns, DbHelper.ID + " = " + 
        								idTi, null,null, null, null); 
        cursor.moveToFirst(); 
        return cursorToTi(cursor); 
	}
}
