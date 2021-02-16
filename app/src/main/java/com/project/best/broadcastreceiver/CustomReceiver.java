package com.project.best.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    /*Label untuk lokal broadcastnya*/
    private static  final String ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID+".ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {

        /*variable*/
        String intentAction = intent.getAction(); // broadcast yang di dapat (Action)


        if (intentAction != null){

            String toastMessage = "";
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = context.getString(R.string.power_connected);
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = context.getString(R.string.power_disconnected);
                    break;
                case ACTION_CUSTOM_BROADCAST:
                    toastMessage = intent.getStringExtra("DATA");
                    break;

            }

            /*Toast dari broadcast*/
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();

        }

    }
}
