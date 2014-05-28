package com.example.povmt;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.povmt.classes.Ti;

public class MainActivity extends Activity {

	private static ArrayList<Ti> TisDoUsuario;
	private static String user = "login";

	public static ArrayList<Ti> getTisDoUsuario() {
		return TisDoUsuario;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ti_main);

		Button adicionarTI = (Button) findViewById(R.id.Button_AddTI);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, TIActivity.class);
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
