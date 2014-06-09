
package com.br.les.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseWrapper extends SQLiteOpenHelper {

    public static final String TABLE_USER = "User"; // The table name.
    public static final String USER_ID = "_id"; //
    public static final String USER_NAME = "_name"; // Database columns
    public static final String USER_EMAIL = "_email"; //

    private static final String DATABASE_NAME = "LES6.db"; // The database name
    private static final int DATABASE_VERSION = 1; // Database version

    private static final String DATABASE_CREATE = "create table " + TABLE_USER //
            + "(" + USER_ID + " integer primary key autoincrement, " // Expression
                                                                     // for the
                                                                     // creation
                                                                     // of the
                                                                     // table
            + USER_NAME + " text not null, " + USER_EMAIL + " text not null);"; // User.

    /**
     * Creator of a DataBaseWrapper, it receives the current context as a
     * parameter
     * 
     * @param context - The current context.
     */
    public DataBaseWrapper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Uses a SQL expression to create a table on a specific database.
     * 
     * @param db - the SQLiteDatabase where the data base will be created.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    /**
     * Updates the database and change its version.
     * 
     * @param db - The database to be upgraded.
     * @param oldVersion - Number to specify the older version of the database.
     * @param newVersion - Number to specify the new version of the database.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }
}
