package com.br.les.activities;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.br.les.povmt.R;
import com.br.les.report.TabsPagerAdapter;

public class WeeklyMonitoring extends FragmentActivity implements TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;

	// Tab titles
	private final String[] tabs = { "Current", "Last", "Before last" };
	private String userName;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weekly_monitoring);


		Bundle bundle = getIntent().getExtras();
		// Getting the value stored in the name "NAME"
		System.out.println("###BAND: " + bundle);
		userName = bundle.getString("NameUser");
		System.out.println("###JOGADOR: " + userName);

		// Apos a checagem se o usuário tem no servidor, é pra colocar o método
		// para a saída do programa caso ele não exista;
		// dialogNoUserLogged()

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.weekly_monitoring);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}

		/**
		 * on swiping the viewpager make respective tab selected
		 */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

		});

		Button addTI = (Button) findViewById(R.id.createTI);
		addTI.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(WeeklyMonitoring.this, CreateTI.class);
				i.putExtra("NameUser", userName);
				finish();
				startActivity(i);
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ti_main, menu);
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {

		/*
		 * if (mViewPager != null) {
		 * mViewPager.setCurrentItem(tab.getPosition()); }
		 */

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

		// TODO Auto-generated method stub


	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String jogador) {
		this.userName = jogador;
	}

	// Aqui vai ser o dialog para quando não estiver logado com Gmail
	/**
	 * If back button pressed, finalize Activity.
	 */
	@Override
	public final void onBackPressed() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(R.string.quit_search)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(final DialogInterface dialog,
									final int which) {
								finish();
							}
						}).setNegativeButton(R.string.no, null).show();
	}

	/**
	 * Creates dialog for the user to see that there is no logged-mail
	 */
	public final void dialogNoUserLogged() {
		new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(R.string.no_user_logged_gmail)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(final DialogInterface dialog,
									final int which) {
								finish();
							}
						}).show().setCancelable(false);

	}

}
