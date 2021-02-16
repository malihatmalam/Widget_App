package com.project.best.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    /*Object dari CustomReceiver */
    private CustomReceiver mReceiver = new CustomReceiver();
    /*Label untuk lokal broadcastnya*/
    private static  final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID+".ACTION_CUSTOM_BROADCAST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        /*untuk meregristasi sehingga bisa menerima*/
        this.registerReceiver(mReceiver,filter);

        /*ini meregristasi yang lokal maksudnya hanya bisa di akses oleh lokal aplikasi (internal)*/
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

    }

    @Override
    protected void onDestroy() {
        /*biar engga keluar terus*/
        this.unregisterReceiver(mReceiver);
        /*ini yang unregister lokal*/
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    /*Ketika tombol di tekan*/
    public void sendCustomBroadcast(View view) {

        /*ini seperti sama seperti fungsi intent (parsing data ke activity lain)*/
        Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
        customBroadcastIntent.putExtra("DATA","Data broadcast");
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
}
