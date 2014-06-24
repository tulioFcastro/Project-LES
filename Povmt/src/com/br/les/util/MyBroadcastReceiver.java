
package com.br.les.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Calendar;

public class MyBroadcastReceiver extends BroadcastReceiver {
    final private Calendar c = Calendar.getInstance();

    @Override
    public void onReceive(Context context, Intent intent) {
        /*
         * int mDayOfLastTI = 0; Bundle bundle = intent.getExtras(); if (bundle
         * != null) { mDayOfLastTI = bundle.getInt("lastdaywithTI"); }
         */
        Toast.makeText(
                context,
                "Hey, there is no TI yesterday. Is that right????????????????????????????????????????????????",
                Toast.LENGTH_LONG).show();

        Vibrator vibrator = (Vibrator)
                context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);

        /*
         * if (c.get(Calendar.DAY_OF_YEAR) != mDayOfLastTI &&
         * c.get(Calendar.DAY_OF_YEAR) > mDayOfLastTI &&
         * c.get(Calendar.DAY_OF_YEAR) - mDayOfLastTI > 1) {
         * Toast.makeText(context,
         * "Hey, there is no TI yesterday. Is that right?",
         * Toast.LENGTH_LONG).show(); Vibrator vibrator2 = (Vibrator)
         * context.getSystemService(Context.VIBRATOR_SERVICE);
         * vibrator2.vibrate(2000); // AQUI VAI ALARAR }
         */

    }

}
