package com.example.do28;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyLocationProvider extends AppWidgetProvider {


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        int number = 0;
        RemoteViews r_view = new RemoteViews(context.getPackageName(),R.layout.location_appwidget);

        for(int widgetIds : appWidgetIds){
                    Intent intent = new Intent(context, MyLocationProvider.class);
                    intent.setAction("button1onclick");
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
                    r_view.setOnClickPendingIntent(R.id.txtInfo,pendingIntent);
                    appWidgetManager.updateAppWidget(appWidgetIds[number++], r_view);

        }

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(intent.getAction().equals("button1onclick")){
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.location_appwidget);
            remoteViews.setTextViewText(R.id.txtInfo,"도착");
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);


            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, MyLocationProvider.class));
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

            Intent serviceIntent = new Intent(context,GpsLocationService.class);
            context.startService(serviceIntent);
            context.stopService(serviceIntent);

        }
    }

}
