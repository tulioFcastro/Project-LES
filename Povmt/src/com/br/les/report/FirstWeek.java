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

public class FirstWeek extends Fragment {
	private ListView listView;

	private String userName;
	private User currentUser;

	public void setUserName(String str) {
		this.userName = str;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.first_week, container, false);




		this.userName = ((WeeklyMonitoring) getActivity()).getUserName();
		this.currentUser = User.getInstance();


		listView = (ListView) rootView.findViewById(R.id.listViewWeek1);


		String[] tiList = currentUser.getWeekAtual().tiRank();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				rootView.getContext(), android.R.layout.simple_list_item_1,
				android.R.id.text1, tiList);

		// Assign adapter to ListView
		listView.setAdapter(adapter);


		return rootView;
	}

}
