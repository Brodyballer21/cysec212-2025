package edu.gannon.quiz01application;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

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

       
    }
}