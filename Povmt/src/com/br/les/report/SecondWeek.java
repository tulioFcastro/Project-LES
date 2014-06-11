package com.br.les.report;

import com.br.les.activities.WeeklyMonitoring;
import com.br.les.database.UserOperations;
import com.br.les.povmt.R;
import com.br.les.timeitup.User;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondWeek extends Fragment {
	ListView listView;
	private User usuarioAtual;
	private UserOperations userDBOperations;
	private String jogador;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater
				.inflate(R.layout.second_week, container, false);

		this.userDBOperations = new UserOperations(this.getActivity()
				.getApplicationContext());
		userDBOperations.open();

		this.jogador = ((WeeklyMonitoring) getActivity()).getJogador();
		this.usuarioAtual = this.userDBOperations.getUser(this.jogador);

		userDBOperations.close();

		// Get ListView object from xml
		listView = (ListView) rootView.findViewById(R.id.listViewWeek2);
		String[] values = usuarioAtual.getSecondWeek().tiRank();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				rootView.getContext(), android.R.layout.simple_list_item_1,
				android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		return rootView;
	}
}
