package com.br.les.activities;

import com.br.les.povmt.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static String user = "login";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ti_main);

		Button verSemanas = (Button) findViewById(R.id.Button_WeeklyMonitoring);
		verSemanas.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, WeeklyMonitoring.class);
				startActivity(i);
			}
		});
		
		Button adicionarTI = (Button) findViewById(R.id.Button_AddTI);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, CreateTI.class);
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
