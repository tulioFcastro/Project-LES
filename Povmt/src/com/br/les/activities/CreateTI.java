package com.br.les.activities;

import com.br.les.timeitup.ActivityTI;
import com.example.povmt.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class CreateTI extends Activity {
	
	ActivityTI my_activity_ti;
	NumberPicker hours;
	private int time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_ti);
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
