
package com.br.les.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.br.les.timeitup.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class UserOperations {

    private final DataBaseWrapper dbWrapper;
    private final String[] USER_TABLE_COLUMS = {
            DataBaseWrapper.USER_ID,
            DataBaseWrapper.USER_NAME, DataBaseWrapper.USER_EMAIL
    };
    private SQLiteDatabase db;

    private final Gson gson;

    /**
     * Creator of a new UserOperations
     * 
     * @param context - The context where the operations will be issued
     */
    public UserOperations(Context context) {
        dbWrapper = new DataBaseWrapper(context);
        gson = new Gson();
    }

    /**
     * Method responsible for opening the Data Base
     * 
     * @throws SQLException
     */
    public void open() throws SQLException {
        db = dbWrapper.getWritableDatabase();
    }

    /**
     * Method that closes the Data Base
     */
    public void close() {
        dbWrapper.close();
    }

    /**
     * Adds a new User in the Data Base
     * 
     * @param user - The new User to be Add
     * @param email - The user email
     */
    public void addUser(User user, String email) {
        String user_json = gson.toJson(user);
        ContentValues values = new ContentValues();

        values.put(DataBaseWrapper.USER_NAME, user_json);
        values.put(DataBaseWrapper.USER_EMAIL, email);

        long userID = db.insert(DataBaseWrapper.TABLE_USER, null, values);
        user.setId(userID);
        Cursor cursor = db.query(DataBaseWrapper.TABLE_USER, USER_TABLE_COLUMS,
                DataBaseWrapper.USER_ID + " = " + userID, null, null, null,
                null);

        cursor.moveToFirst();
    }

    /**
     * Deletes a Existent user from the Data Base
     * 
     * @param user - The user to be deleted
     */
    public void deleteUser(User user) {
        long id = user.getId();
        db.delete(DataBaseWrapper.TABLE_USER, DataBaseWrapper.USER_ID + "="
                + id, null);
    }

    /**
     * Updates the information of a User on the Data Base
     * 
     * @param user - User whom the information will be updated
     */
    public void updateUser(User user) {

        String user_json = gson.toJson(user);
        ContentValues values = new ContentValues();
        values.put(DataBaseWrapper.USER_NAME, user_json); // get author
//        values.put(DataBaseWrapper.USER_EMAIL, user.getEmail()); // get title

        // 3. updating row
        db.update(DataBaseWrapper.TABLE_USER, // table
                values, // column/value
                DataBaseWrapper.USER_ID + " = ?", // selections
                new String[] {
                    String.valueOf(user.getId())
                });
    }

    /**
     * Search and returns all the Users that are registered on the Data Base
     * 
     * @return - List with all the Users
     */
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

    /**
     * Parses the user from a Cursor
     * 
     * @param cursor - The Cursor with the information to be parsed
     * @return - The new User
     */
    private User parseUser(Cursor cursor) {

        String user_json = cursor.getString(1);
        User user = gson.fromJson(user_json, User.class);

        return user;
    }

}
