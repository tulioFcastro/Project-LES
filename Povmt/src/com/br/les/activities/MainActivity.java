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
import com.br.les.timeitup.Week;

public class MainActivity extends Activity {

	UserOperations userDBOperations;
	private User usuarioAtual;
	private String jogador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ti_main);

		Bundle bunble = getIntent().getExtras();
		if (bunble != null) {
			// Getting the value stored in the name "NAME"
			jogador = bunble.getString("NameUser");
		}

		// Aqui eu pego o usu�rio cm o login passado na tela anterior
		// ou o email fornecido pelo google
		// e aqui � criado o singleton para a aplica��o

		this.userDBOperations = new UserOperations(this);
		this.userDBOperations.open();
		System.out.println("#JOGADOR: " + jogador);
		this.usuarioAtual = userDBOperations.getUser(jogador);
		userDBOperations.close();

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

	public void weeklyMonitoring(View v){
		Intent i = new Intent(MainActivity.this, WeeklyMonitoring.class);
		startActivity(i);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ti_main, menu);
		return true;
	}

}
