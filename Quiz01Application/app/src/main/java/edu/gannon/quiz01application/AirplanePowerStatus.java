package edu.gannon.quiz01application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class AirplanePowerStatus extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = null;

        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            message = (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL)
                    ? "Charging" : "Not Charging";
        }

        else if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);
            message = isAirplaneModeOn ? "Airplane Mode ON" : "Airplane Mode OFF";
        }

        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}

