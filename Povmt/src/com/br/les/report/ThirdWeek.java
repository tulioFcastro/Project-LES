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

public class ThirdWeek extends Fragment {

	private ListView listView;
	private UserOperations userDBOperations;
	private String userName;
	private User currentUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.third_week, container, false);

		this.userDBOperations = new UserOperations(this.getActivity()
				.getApplicationContext());
		userDBOperations.open();

		this.userName = ((WeeklyMonitoring) getActivity()).getUserName();
		this.currentUser = this.userDBOperations.getUser(this.userName);


		userDBOperations.close();

		// Get ListView object from xml
		listView = (ListView) rootView.findViewById(R.id.listViewWeek3);

		String[] values = currentUser.getThirdWeek().tiRank();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				rootView.getContext(), android.R.layout.simple_list_item_1,
				android.R.id.text1, values);

		// Assign adapter to ListView
		listView.setAdapter(adapter);

		return rootView;
	}

}