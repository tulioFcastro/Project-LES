package com.br.les.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.br.les.povmt.R;
import com.br.les.timeitup.HttpURLConnectionExample;
import com.br.les.timeitup.User;

public class MainActivity extends Activity {

	private User usuarioAtual = new User();
	private String jogador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ti_main);
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
