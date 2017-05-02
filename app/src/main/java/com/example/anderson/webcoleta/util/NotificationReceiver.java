package com.example.anderson.webcoleta.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.TaskStackBuilder;
//import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.example.anderson.webcoleta.MainActivity;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // criar minha notificação
        ColetaNotification notification = ColetaNotification.getInstance(context);
        notification.createNotification();

        throw new UnsupportedOperationException("Not yet implemented");
    }


}
