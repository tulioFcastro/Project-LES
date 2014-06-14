package com.br.les.report;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.br.les.activities.WeeklyMonitoring;

import com.br.les.povmt.R;
import com.br.les.timeitup.User;

public class SecondWeek extends Fragment {
	private ListView listView;
	private User currentUser;
	private String userName;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater
				.inflate(R.layout.second_week, container, false);



		this.userName = ((WeeklyMonitoring) getActivity()).getUserName();
		this.currentUser = User.getInstance();

		// Get ListView object from xml
		listView = (ListView) rootView.findViewById(R.id.listViewWeek2);

		String[] values = currentUser.getSecondWeek().tiRank();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				rootView.getContext(), android.R.layout.simple_list_item_1,
				android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		return rootView;
	}
}
