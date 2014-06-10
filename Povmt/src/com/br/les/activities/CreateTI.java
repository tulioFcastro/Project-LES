package com.br.les.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.br.les.database.UserOperations;
import com.br.les.povmt.R;
import com.br.les.timeitup.ActivityTI;
import com.br.les.timeitup.User;

public class CreateTI extends Activity {

	private ActivityTI my_activity_ti;
	private NumberPicker hours;
	private int time;
	private User usuario;
	private UserOperations userDBOperations;
	private String jogador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_ti);

		userDBOperations = new UserOperations(this);
		Bundle bunble = getIntent().getExtras();
		jogador = bunble.getString("NameUser");
		
		userDBOperations.open();
		usuario = userDBOperations.getUser(jogador);
		userDBOperations.close();
		
		hours = (NumberPicker) findViewById(R.id.hours);

		hours.setMaxValue(24);
		hours.setMinValue(1);
		hours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				hours.setValue(newVal);
			}
		});
		Button addTI = (Button) findViewById(R.id.button_create);
		addTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText name = (EditText) findViewById(R.id.name_field);
				time = hours.getValue();
				CharSequence text = "Activity: " + name.getText().toString()
						+ " time :" + String.valueOf(time);
				Toast toast = Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT);
				toast.show();

				my_activity_ti = new ActivityTI(name.getText().toString(), time);
				usuario.isActualWeek();
				usuario.getWeekAtual().addTI(my_activity_ti);

				userDBOperations.open();
				userDBOperations.updateUser(usuario);
				
				userDBOperations.close();

				// TODO Creatte JSON and send to server

				finish();

			}
		});

		Button cancelTI = (Button) findViewById(R.id.button_cancel);
		cancelTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
