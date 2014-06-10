package com.br.les.activities;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

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
		userDBOperations.close();
		ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@SuppressWarnings("unchecked")
	public void addUser(View view) {

		ArrayAdapter<User> adapter = (ArrayAdapter<User>) getListAdapter();

		EditText name = (EditText) findViewById(R.id.textName);
		EditText email = (EditText) findViewById(R.id.textEmail);
		User user = new User(name.getText().toString(), email.getText()
				.toString());
		// Gson gson = new Gson();
		// String user_json = gson.toJson(user);
		// System.out.println("CREATE =="+user_json);
		userDBOperations.open();
		if (!email.getText().toString().equals("")
				&& userDBOperations.getUser(email.getText().toString()) == null) {
			userDBOperations.addUser(user, user.getEmail());
			adapter.add(user);
			Toast.makeText(getApplicationContext(),
					getString(R.string.create_user_sucess), Toast.LENGTH_SHORT)
					.show();
			name.setText("");
			email.setText("");
		} else {
			Toast.makeText(getApplicationContext(),
					getString(R.string.create_user_error), Toast.LENGTH_SHORT)
					.show();
		}
		userDBOperations.close();
		// User user = userDBOperations.addUser(name.getText().toString(), email
		// .getText().toString());

	}

	public void cancel(View view) {
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
