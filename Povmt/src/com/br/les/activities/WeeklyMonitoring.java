
package com.br.les.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.br.les.povmt.R;
import com.br.les.report.TabsPagerAdapter;
import com.br.les.timeitup.User;
import com.br.les.util.HttpURLConnectionGET;
import com.br.les.util.HttpURLConnectionPOST;
import com.google.gson.Gson;

public class WeeklyMonitoring extends FragmentActivity implements TabListener {

    private final String JSON_USER = "JsonUser";
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    TimePickerDialog timePickerDialog;
    final static int RQS_1 = 1;

    // Tab titles
    private static final String[] TABS = {
            "Current", "Last", "Before last"
    };
    private User currentUser;
    private String json;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_monitoring);

        if (!isConnected()) {
            dialogError(R.string.connection_error,
                    R.string.try_again_connection);
        } else {

            Bundle bundle = getIntent().getExtras();

            if (bundle != null) {
                json = bundle.getString(JSON_USER);
                Gson gson = new Gson();
                currentUser = gson.fromJson(json, User.class);
            } else {
                String possibleEmail = "";
                try {
                    Account[] accounts = AccountManager.get(this)
                            .getAccountsByType("com.google");

                    if (accounts.length > 0) {
                        possibleEmail += accounts[0].name;
                    }
                } catch (Exception e) {
                    Log.i("Exception", "Exception:" + e);
                }

                HttpURLConnectionGET connGET = new HttpURLConnectionGET();
                HttpURLConnectionPOST connPOST = new HttpURLConnectionPOST();
                // No Google account logged
                if ("".equals(possibleEmail)) {
                    dialogError(R.string.user_login_error,
                            R.string.try_again_user_login);
                } else {
                    try {
                        json = connGET.requestJson(possibleEmail);

                        Gson gson = new Gson();
                        if (json != null
                                && "User not found".equals(json)) {
                            currentUser = new User(possibleEmail,
                                    possibleEmail);
                            String userJson = gson.toJson(currentUser);
                            // Falta enviar pro servidor...
                            connPOST.execute(userJson, possibleEmail);

                        } else if (json == null) {
                            dialogError(R.string.request_error,
                                    R.string.request_error_dialog);
                        } else {
                            currentUser = gson.fromJson(json,
                                    User.class);
                        }

                    } catch (Exception e) {
                        Log.e("WEEKLY MONITORING", e.getMessage());
                    }
                }
            }
            InitiatingView();
        }

    }

    @SuppressLint("NewApi")
    private void InitiatingView() {
//        System.out.println("####CURRENTUSER: " + currentUser);
        viewPager = (ViewPager) findViewById(R.id.weekly_monitoring);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tabName : TABS) {
            actionBar.addTab(actionBar.newTab().setText(tabName)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
                    throws UnsupportedOperationException {

            }

            @Override
            public void onPageScrollStateChanged(int arg0)
                    throws UnsupportedOperationException {
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
    public void onTabSelected(Tab tab, FragmentTransaction ft)
            throws UnsupportedOperationException {
    }

    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft)
            throws UnsupportedOperationException {

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft)
            throws UnsupportedOperationException {

    }

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
     * If some error, creates dialog for the user
     */
    public final void dialogError(int title, int message) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.exit,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(final DialogInterface dialog,
                                    final int which) {
                                finish();
                            }
                        }).show().setCancelable(false);

    }

    /**
     * Check internet connection
     * 
     * @return the connection
     */
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    
    public String getJson() {
        return json;

    }

    /**
     * Event Handling for Individual menu item selected Identify single menu
     * item by it's id
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_createTI:
                Toast.makeText(WeeklyMonitoring.this, "Create a new TI",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WeeklyMonitoring.this, CreateTI.class);
                i.putExtra(JSON_USER, json);
                finish();
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
