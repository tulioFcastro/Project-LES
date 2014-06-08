package com.br.les.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.br.les.timeitup.User;
import com.google.gson.Gson;

public class UserOperations {

	private final DataBaseWrapper dbWrapper;
	private final String[] USER_TABLE_COLUMS = { DataBaseWrapper.USER_ID,
			DataBaseWrapper.USER_NAME, DataBaseWrapper.USER_EMAIL };
	private SQLiteDatabase db;

	private Gson gson;

	public UserOperations(Context context) {
		this.dbWrapper = new DataBaseWrapper(context);
		gson = new Gson();
	}

	public void open() throws SQLException {
		db = dbWrapper.getWritableDatabase();
	}

	public void close() {
		dbWrapper.close();
	}

	public void addUser(User user, String email) {
		String user_json = gson.toJson(user);
		ContentValues values = new ContentValues();

		values.put(DataBaseWrapper.USER_NAME, user_json);
		values.put(DataBaseWrapper.USER_EMAIL, email);

		long userID = db.insert(DataBaseWrapper.TABLE_USER, null, values);
		Cursor cursor = db.query(DataBaseWrapper.TABLE_USER, USER_TABLE_COLUMS,
				DataBaseWrapper.USER_ID + " = " + userID, null, null, null,
				null);

		cursor.moveToFirst();
	}

	public void deleteUser(User user) {
		int id = user.getId();
		db.delete(DataBaseWrapper.TABLE_USER, DataBaseWrapper.USER_ID + "="
				+ id, null);
	}

	public void updateUser(User user) {
		
		String user_json = gson.toJson(user);
		ContentValues values = new ContentValues();
		values.put(DataBaseWrapper.USER_NAME, user_json); // get author
		values.put(DataBaseWrapper.USER_EMAIL, user.getEmail()); // get title

		// 3. updating row
		db.update(DataBaseWrapper.TABLE_USER, // table
				values, // column/value
				DataBaseWrapper.USER_ID + " = ?", // selections
				new String[] { String.valueOf(user.getId()) });
	}

	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();

		Cursor cursor = db.query(DataBaseWrapper.TABLE_USER, USER_TABLE_COLUMS,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			User user = parseUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}

		cursor.close();
		return users;
	}

	private User parseUser(Cursor cursor) {

		String user_json = cursor.getString(1);
		User user = gson.fromJson(user_json, User.class);

		return user;
	}

}
