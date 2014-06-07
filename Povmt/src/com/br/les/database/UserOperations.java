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

	public UserOperations(Context context) {
		System.out.println("#CONTEXT: " + context);
		this.dbWrapper = new DataBaseWrapper(context);
	}

	public void open() throws SQLException {
		db = dbWrapper.getWritableDatabase();
	}

	public void close() {
		dbWrapper.close();
	}

	public void addUser(String user_json, String email) {
		ContentValues values = new ContentValues();

		values.put(DataBaseWrapper.USER_NAME, user_json);
		values.put(DataBaseWrapper.USER_EMAIL, email);

		long userID = db.insert(DataBaseWrapper.TABLE_USER, null, values);

		Cursor cursor = db.query(DataBaseWrapper.TABLE_USER, USER_TABLE_COLUMS,
				DataBaseWrapper.USER_ID + " = " + userID, null, null, null,
				null);

		cursor.moveToFirst();

//		User newUser = parseUser(cursor);
//		cursor.close();
//
//		return newUser;
	}

	public void deleteUser(User user) {
		long id = user.getId();
		db.delete(DataBaseWrapper.TABLE_USER, DataBaseWrapper.USER_ID + " = "
				+ id, null);
	}

	public List<User> getAllUser() {
		List<User> users = new ArrayList<User>();

		Cursor cursor = db.query(DataBaseWrapper.TABLE_USER, USER_TABLE_COLUMS,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			System.out.println("########->entrou");
			User user = parseUser(cursor);
			users.add(user);
			cursor.moveToNext();
		}

		cursor.close();
		return users;
	}

	private User parseUser(Cursor cursor) {

		String user_json = cursor.getString(1);
		System.out.println("############# user_json====== "+cursor.getString(1));
		Gson gson = new Gson();
		User user  = gson.fromJson(user_json, User.class);
//		User user = new User();
//		user.setId(cursor.getInt(0));
//		user.setName(cursor.getString(1));
//		user.setEmail(cursor.getString(2));


		return user;
	}

}
