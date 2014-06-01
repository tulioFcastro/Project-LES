package com.br.les.activities;

import java.util.List;

import android.app.ListActivity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.br.les.database.UserOperations;
import com.br.les.povmt.R;
import com.br.les.timeitup.User;

public class CreateUser extends ListActivity {

	UserOperations userDBOperations;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_user);

		userDBOperations = new UserOperations(this);
		userDBOperations.open();

		List<User> values = userDBOperations.getAllUser();

		ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@SuppressWarnings("unchecked")
	public void addUser(View view) {

		ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();

		EditText name = (EditText) findViewById(R.id.textName);
		EditText email = (EditText) findViewById(R.id.textEmail);
		User user = userDBOperations.addUser(name.getText().toString(), email
				.getText().toString());

		adapter.add(user);

	}
	
	public void cancel(View view){
		Intent i = new Intent(CreateUser.this, Login.class);
		startActivity(i);
		finish();
	}

	@Override
	protected void onResume() {
		userDBOperations.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		userDBOperations.close();
		super.onPause();
	}
}
