package com.br.les.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {

	public static final String TABLE_USER = "User";
	public static final String USER_ID = "_id";
	public static final String USER_NAME = "_name";
	public static final String USER_EMAIL = "_email";

	private static final String DATABASE_NAME = "LES.db";
	private static final int DATABASE_VERSION = 1;

	private static final String DATABASE_CREATE = "create table" + TABLE_USER
			+ "(" + USER_ID + " integer primary key autoincrement, "
			+ USER_NAME + " text not null, " + USER_EMAIL + "text not null);";

	public DataBaseWrapper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		onCreate(db);
	}
}
