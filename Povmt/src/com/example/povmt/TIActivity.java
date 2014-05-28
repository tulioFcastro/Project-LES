package com.example.povmt;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.povmt.classes.Ti;

public class TIActivity extends Activity {

	private final ArrayList<Ti> TisDoUsuario = MainActivity.getTisDoUsuario();
	private int tempo;
	NumberPicker horas, minutos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ti);
		horas = (NumberPicker) findViewById(R.id.hours);

		horas.setMaxValue(23);
		horas.setMinValue(0);
		horas.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				horas.setValue(newVal);
			}
		});

		minutos = (NumberPicker) findViewById(R.id.minutes);

		minutos.setMaxValue(59);
		minutos.setMinValue(0);
		minutos.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal,
					int newVal) {
				minutos.setValue(newVal);
			}
		});

		Button adicionarTI = (Button) findViewById(R.id.buttonOk);
		adicionarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText nome = (EditText) findViewById(R.id.editText1);
				tempo = horas.getValue() * 60 + minutos.getValue();

				CharSequence text = "Atividade: " + nome.getText().toString()
						+ " tempo :" + String.valueOf(tempo);

				Toast toast = Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT);
				toast.show();

				// TODO: do something
				// guardar na semana
				// guardar no banco de dados e enviar para o servidor
			}
		});

		Button cancelarTI = (Button) findViewById(R.id.buttonCancel);
		cancelarTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ti, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
