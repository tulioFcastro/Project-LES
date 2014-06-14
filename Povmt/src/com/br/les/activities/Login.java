package com.br.les.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.br.les.database.UserOperations;
import com.br.les.povmt.R;
import com.br.les.timeitup.HttpURLConnectionExample;

public class Login extends Activity {
	private EditText name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (!this.isConnected()) {
			new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle(R.string.quit_search)
					.setPositiveButton(R.string.yes,
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(
										final DialogInterface dialog,
										final int which) {
									// precisa fazer a conexao
								}
							}).setNegativeButton(R.string.no, null).show();
			System.out.println("####SEM INTERNET");
		} else {
			System.out.println("COM CONEXAO");

			String possibleEmail = "";
			try {
				Account[] accounts = AccountManager.get(this)
						.getAccountsByType("com.google");

				if (accounts.length > 0) {
					possibleEmail += accounts[0].name;
				}
			} catch (Exception e) {
				Log.i("Exception", "Exception:" + e);
			}

			System.out.println("#####EMAIL: " + possibleEmail);

			// HttpURLConnectionExample con = new HttpURLConnectionExample();
			//
			//
			// try {
			// System.out.println("###ANTES DO TRY");
			// System.out.println("#JSON: " + con.sendGet("raif@gmail.com"));
			// System.out.println("###DEPOIS DO TRY");
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			//

			Button loginUser = (Button) findViewById(R.id.loginButton);
			loginUser.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					name = (EditText) findViewById(R.id.user_name);
					if (name.getText().toString().equals("")) {
						Toast.makeText(getApplicationContext(),
								getString(R.string.login_error),
								Toast.LENGTH_SHORT).show();
					} else {
						UserOperations userDBOperations = new UserOperations(
								Login.this);
						userDBOperations.open();

						// Vejo se o usuário está no banco, se não estiver o
						// retorno
						// é null
						if (userDBOperations.getUser(name.getText().toString()) != null) {
							Intent i = new Intent(Login.this,
									WeeklyMonitoring.class);
//							i.putExtra("NameUser", name.getText().toString());
							i.putExtra("NameUser", "a");
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
		}
	}

	public boolean isConnected() {
		System.out.println("CONEXAO");
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if ((networkInfo != null) && networkInfo.isConnected()) {
			return true;
		} else {
			return false;
		}
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
