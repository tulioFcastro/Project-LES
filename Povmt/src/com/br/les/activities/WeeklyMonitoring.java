
package com.br.les.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.br.les.povmt.R;
import com.br.les.report.TabsPagerAdapter;
import com.br.les.timeitup.User;
import com.br.les.util.HttpURLConnectionGET;
import com.br.les.util.HttpURLConnectionPOST;
import com.br.les.util.MyBroadcastReceiver;
import com.google.gson.Gson;

import java.util.Calendar;

public class WeeklyMonitoring extends FragmentActivity implements TabListener {

    private final String JSON_USER = "JsonUser";
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private int mDayOfLastTI;
    TimePickerDialog timePickerDialog;
    final static int RQS_1 = 1;

    // Tab titles
    private final String[] tabs = {
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
                if (possibleEmail.equals("")) {
                    dialogError(R.string.user_login_error,
                            R.string.try_again_user_login);
                } else {
                    try {
                        json = connGET.requestJson(possibleEmail);

                        Gson gson = new Gson();
                        if (json != null
                                && json.equals("User not found")) {
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
                        Log.i("Exception", "Exception:" + e);
                    }
                }
            }

            mDayOfLastTI = currentUser.getlastDayWithTI();

            // Iniciando toda a view
            InitiatingView();

        }

    }

    @SuppressLint("NewApi")
    private void InitiatingView() {
        System.out.println("####CURRENTUSER: " + currentUser);
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

    }

    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {

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
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public String getJson() {
        return json;

    }

    /**
     * Event Handling for Individual menu item selected Identify single menu
     * item by it's id
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int mHour;
        int mMinute;
        switch (item.getItemId())
        {
            case R.id.action_createTI:
                // Single menu item is selected do something
                // Ex: vai criar a nova ti
                Toast.makeText(WeeklyMonitoring.this, "Criar nova ti", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(WeeklyMonitoring.this, CreateTI.class);
                i.putExtra(JSON_USER, json);
                finish();
                startActivity(i);
                return true;

                /*
                 * case R.id.action_setAlarm: // setar o novo alarme
                 * Toast.makeText(WeeklyMonitoring.this, "setando alarme",
                 * Toast.LENGTH_SHORT).show(); // Process to get Current Time
                 * final Calendar c = Calendar.getInstance(); mHour =
                 * c.get(Calendar.HOUR_OF_DAY); mMinute =
                 * c.get(Calendar.MINUTE); // Launch Time Picker Dialog
                 * TimePickerDialog tpd = new TimePickerDialog(this, new
                 * TimePickerDialog.OnTimeSetListener() {
                 * @Override public void onTimeSet(TimePicker view, int
                 * hourOfDay, int minute) { Intent intent = new
                 * Intent(WeeklyMonitoring.this, MyBroadcastReceiver.class);
                 * intent.putExtra("lastdaywithTI", mDayOfLastTI);
                 * c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                 * c.set(Calendar.MINUTE, minute); c.set(Calendar.SECOND, 00);
                 * long when = c.getTimeInMillis(); // TODO // fazer conta para
                 * fazer o alarmes PendingIntent pendingIntent =
                 * PendingIntent.getBroadcast( getApplicationContext(),
                 * 234324243, intent, 0); AlarmManager alarmManager =
                 * (AlarmManager) getSystemService(ALARM_SERVICE);
                 * alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, when,
                 * 20000, pendingIntent); System.out.println("XX Alarm set in "
                 * + hourOfDay + " : " + minute); } }, mHour, mMinute, false);
                 * tpd.show(); openTimePickerDialog(false); return true; case
                 * R.id.action_setings: Toast.makeText(WeeklyMonitoring.this,
                 * "Criando alarme", Toast.LENGTH_SHORT).show(); Intent it = new
                 * Intent(WeeklyMonitoring.this, Alarm.class); finish();
                 * startActivity(it); return true;
                 */
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void openTimePickerDialog(boolean is24r) {
        Calendar calendar = Calendar.getInstance();

        timePickerDialog = new TimePickerDialog(
                WeeklyMonitoring.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24r);
        timePickerDialog.setTitle("Set Alarm Time");

        timePickerDialog.show();

    }

    OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            Calendar calNow = Calendar.getInstance();
            Calendar calSet = (Calendar) calNow.clone();

            calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calSet.set(Calendar.MINUTE, minute);
            calSet.set(Calendar.SECOND, 0);
            calSet.set(Calendar.MILLISECOND, 0);

            if (calSet.compareTo(calNow) <= 0) {
                // Today Set time passed, count to tomorrow
                calSet.add(Calendar.DATE, 1);
            }

            setAlarm(calSet);
        }
    };

    private void setAlarm(Calendar targetCal) {

        Intent intent = new Intent(getBaseContext(), MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent
                .getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);
        System.out.println("XX Alarm set in "
                + targetCal.getTimeInMillis());

    }

}
