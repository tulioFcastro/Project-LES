package com.br.les.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.Toast;

import com.br.les.povmt.R;
import com.br.les.timeitup.ActivityTI;
import com.br.les.timeitup.User;

public class CreateTI extends Activity {

	private ActivityTI my_activity_ti;
	private NumberPicker hours;

	private NumberPicker minutes;
	private User currentUser;
	private String userName;
	private int priority;
	private final String USER_NAME = "NameUser";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_ti);

		Bundle bunble = getIntent().getExtras();
		userName = bunble.getString(USER_NAME);
		priority = 2;
		// Tirar essa conexão com o BD Local
		currentUser = User.getInstance();


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
				Toast toast = Toast.makeText(getApplicationContext(),
						R.string.register_ok, Toast.LENGTH_SHORT);
				toast.show();

				my_activity_ti = new ActivityTI(name.getText().toString(),
						hours.getValue(), minutes.getValue(), priority);
				my_activity_ti.setPriority(priority);
				currentUser.isActualWeek();
				currentUser.getWeekAtual().addTI(my_activity_ti);

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

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();
		switch (view.getId()) {
		case R.id.hiPriority:
			if (checked)
				priority = 2;
			break;
		case R.id.mediumPriority:
			if (checked)
				priority = 1;

			break;
		case R.id.lowPriority:
			if (checked)
				priority = 0;
			break;
		}
	}

}
