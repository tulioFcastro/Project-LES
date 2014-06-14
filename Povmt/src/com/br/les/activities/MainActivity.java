package com.br.les.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.br.les.database.UserOperations;
import com.br.les.povmt.R;
import com.br.les.timeitup.User;

public class MainActivity extends Activity {

	UserOperations userDBOperations;
	private User usuarioAtual;
	private String jogador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ti_main);

		Bundle bunble = getIntent().getExtras();
		// Getting the value stored in the name "NAME"
		jogador = bunble.getString("NameUser");

		this.userDBOperations = new UserOperations(this);
		this.userDBOperations.open();
		this.usuarioAtual = userDBOperations.getUser(jogador);
		userDBOperations.close();

		Button lookWeeks = (Button) findViewById(R.id.Button_WeeklyMonitoring);
		lookWeeks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, WeeklyMonitoring.class);
				startActivity(i);
			}
		});

		Button addTI = (Button) findViewById(R.id.Button_AddTI);
		addTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CreateTI.class);
				i.putExtra("NameUser", jogador);
				startActivity(i);
			}
		});

		Button weeklyMonitoring = (Button) findViewById(R.id.Button_WeeklyMonitoring);
		weeklyMonitoring.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, WeeklyMonitoring.class);
				i.putExtra("NameUser", jogador);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ti_main, menu);
		return true;
	}

}
