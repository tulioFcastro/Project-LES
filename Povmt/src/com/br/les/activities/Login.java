package com.br.les.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.les.database.UserOperations;
import com.br.les.povmt.R;

public class Login extends Activity {
	EditText name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button loginUser = (Button) findViewById(R.id.loginButton);
		loginUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				name = (EditText) findViewById(R.id.user_name);
				if (name.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(),
							getString(R.string.login_error), Toast.LENGTH_SHORT)
							.show();
				} else {
					UserOperations userDBOperations = new UserOperations(
							Login.this);
					userDBOperations.open();

					// Vejo se o usuário está no banco, se não estiver o retorno
					// é null
					if (userDBOperations.getUser(name.getText().toString()) != null) {
						Intent i = new Intent(Login.this, MainActivity.class);
						i.putExtra("NameUser", name.getText().toString());
						startActivity(i);
						Toast.makeText(getApplicationContext(),
								getString(R.string.login_sucess),
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(),
								getString(R.string.inexistent_user),
								Toast.LENGTH_SHORT).show();
					}
					userDBOperations.close();
				}
			}
		});

		Button createUser = (Button) findViewById(R.id.createButton);
		createUser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(Login.this, CreateUser.class);
				startActivity(i);
			}
		});

	}

	/**
	 * If back button pressed, finalize Activity.
	 */
	@Override
	public final void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(R.string.quit_search)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(final DialogInterface dialog,
									final int which) {
								finish();
							}
						}).setNegativeButton(R.string.no, null).show();
	}

}
