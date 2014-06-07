package com.br.les.activities;

import java.util.List;

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ti_main);
		
		userDBOperations = new UserOperations(this);
		userDBOperations.open();
		List<User> usuarios = userDBOperations.getAllUser();
		
		for (User user : usuarios) {
			if(user.getEmail().equals("i@i.com")) usuarioAtual = user;
		}
		

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
