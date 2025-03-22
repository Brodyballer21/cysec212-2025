package edu.gannon.quiz01application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvChargingStatus, tvAirplaneModeStatus, tvInternetStatus;
    private LinearLayout mainLayout;

    private boolean isCharging = false;
    private boolean isConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvChargingStatus = findViewById(R.id.tvChargingStatus);

        tvAirplaneModeStatus = findViewById(R.id.tvAirplaneModeStatus);

        tvInternetStatus = findViewById(R.id.tvInternetStatus);

        mainLayout = findViewById(R.id.mainLayout);

        registerReceiver(powerReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        registerReceiver(airplaneModeReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
        registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private final BroadcastReceiver powerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;

            tvChargingStatus.setText("Charging: " + (isCharging ? "Yes" : "No"));

            Toast.makeText(context, isCharging ? "Charging" : "Not Charging", Toast.LENGTH_SHORT).show();

            updateBackgroundColor();
        }
    };

    private final BroadcastReceiver airplaneModeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);

            tvAirplaneModeStatus.setText("Airplane Mode: " + (isAirplaneModeOn ? "ON" : "OFF"));
            Toast.makeText(context, isAirplaneModeOn ? "Airplane Mode ON" : "Airplane Mode OFF", Toast.LENGTH_SHORT).show();
        }

        private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                tvInternetStatus.setText("Internet: " + (isConnected ? "Connected" : "Disconnected"));
                Toast.makeText(context, isConnected ? "Internet Connected" : "Internet Disconnected", Toast.LENGTH_SHORT).show();

                updateBackgroundColor();
            }
        };

    };


}