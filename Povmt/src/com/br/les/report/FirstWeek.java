package com.br.les.report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.br.les.activities.WeeklyMonitoring;
import com.br.les.database.UserOperations;
import com.br.les.povmt.R;
import com.br.les.timeitup.User;

public class FirstWeek extends Fragment {
	private ListView listView;
	private String jogador;
	private User usuarioAtual;
	private UserOperations userDBOperations;

	public void setJogador(String str) {
		this.jogador = str;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.first_week, container, false);

		this.userDBOperations = new UserOperations(this.getActivity()
				.getApplicationContext());
		userDBOperations.open();

		this.jogador = ((WeeklyMonitoring) getActivity()).getJogador();
		this.usuarioAtual = this.userDBOperations.getUser(this.jogador);

		userDBOperations.close();

		listView = (ListView) rootView.findViewById(R.id.listViewWeek1);

		String[] tiList = usuarioAtual.getWeekAtual().tiRank();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				rootView.getContext(), android.R.layout.simple_list_item_1,
				android.R.id.text1, tiList);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		this.userDBOperations.close();

		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
}
