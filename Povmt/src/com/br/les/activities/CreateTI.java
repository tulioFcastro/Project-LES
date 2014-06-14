package com.br.les.activities;

import android.app.Activity;
import android.content.Intent;
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

	private NumberPicker minutes;
	private User currentUser;
	private UserOperations userDBOperations;
	private String userName;
	private final String USER_NAME = "NameUser";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_ti);

		userDBOperations = new UserOperations(this);
		Bundle bunble = getIntent().getExtras();
		userName = bunble.getString(USER_NAME);

		// Tirar essa conexão com o BD Local
		userDBOperations.open();
		currentUser = userDBOperations.getUser(userName);
		userDBOperations.close();

		hours = (NumberPicker) findViewById(R.id.hours);
		hours.setMaxValue(167);
		hours.setMinValue(0);
		hours.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				hours.setValue(newVal);
			}
		});


		minutes = (NumberPicker) findViewById(R.id.minutes);
		minutes.setMaxValue(59);
		minutes.setMinValue(0);
		minutes.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				minutes.setValue(newVal);
			}
		});


		Button addTI = (Button) findViewById(R.id.button_create);
		addTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText name = (EditText) findViewById(R.id.name_field);

				// Passando os dois valores do NumberPicker para float

				// Mensagem pro usuário
				Toast toast = Toast.makeText(getApplicationContext(),
						R.string.register_ok, Toast.LENGTH_SHORT);
				toast.show();

				my_activity_ti = new ActivityTI(name.getText().toString(),
						hours.getValue(), minutes.getValue());
				currentUser.isActualWeek();
				currentUser.getWeekAtual().addTI(my_activity_ti);

				// Tirar essa conexão com o BD Local e fazer a com o Servidor
				userDBOperations.open();
				userDBOperations.updateUser(currentUser);
				userDBOperations.close();

				Intent i = new Intent(CreateTI.this, WeeklyMonitoring.class);
				i.putExtra(USER_NAME, userName);
				finish();
				startActivity(i);


			}
		});

		Button cancelTI = (Button) findViewById(R.id.button_cancel);
		cancelTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(CreateTI.this, WeeklyMonitoring.class);
				i.putExtra(USER_NAME, userName);
				finish();
				startActivity(i);
			}
		});
	}

	/**
	 * If back button pressed, finalize Activity.
	 */
	@Override
	public final void onBackPressed() {
		Intent i = new Intent(CreateTI.this, WeeklyMonitoring.class);
		i.putExtra(USER_NAME, userName);
		finish();
		startActivity(i);
	}

}
