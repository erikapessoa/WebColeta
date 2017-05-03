package com.example.anderson.webcoleta.util;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.example.anderson.webcoleta.MainActivity;
import com.example.anderson.webcoleta.NotificationActivity;

/**
 * Created by erika on 02/05/2017.
 */

public class ColetaNotification {

    private static ColetaNotification instance;
    private NotificationManager mNotificationManager;
    private Context mContext;

    public static ColetaNotification getInstance(Context c){
        if (instance == null) {
            instance = new ColetaNotification(c);
        }
        return instance;
    }

    private ColetaNotification(Context context){

        mContext = context;
        mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    //Criando a notificação na barra de tarefas
    public void createNotification(){

        Intent resultIntent;
        TaskStackBuilder stackBuilder;
        PendingIntent resultPendingIntent;

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(mContext)
                        .setSmallIcon(android.R.mipmap.sym_def_app_icon)
                        .setContentTitle("WebColeta")
                        .setContentText("Aviso de coleta");

        //Aqui eu preciso chamar minha Activity específica que vai exibir a tela com as informações
        resultIntent = new Intent(mContext, NotificationActivity.class);

        stackBuilder = TaskStackBuilder.create(mContext);

        stackBuilder.addParentStack(NotificationActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        mNotificationManager.notify(GarbageConstants.sID_NOTIFICATION, mBuilder.build());
    }

    //Cancelando a notificação
    public void cancelNotification(){
        mNotificationManager.cancel(GarbageConstants.sID_NOTIFICATION);
    }
}
