package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class myService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Intent intent1 = new Intent(this.getApplicationContext(),AppActivity.class);
        intent1.putExtra("a",a);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1,null);


        return null;


    }
    PackageManager manager;
    ArrayList<Item> apps;
    String a="heisarki";


    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("running");





        //Intent intent = new Intent(this,AppActivity.class);

        manager = getPackageManager();
        apps = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(intent, 0);
        Log.i("nameLoad1",availableActivities.toString());

        for (ResolveInfo resolveInfo : availableActivities) {

            Item app = new Item();
            app.label = resolveInfo.activityInfo.packageName;
            app.name = resolveInfo.loadLabel(manager);
            app.icon = resolveInfo.loadIcon(manager);
            apps.add(app);
            Log.i("name",app.name.toString());
           //Log.i("name1",app.label.toString());
            //Log.i("name2",app.icon.toString());

        }


        Log.i("nameLoad","Loading apps");
        System.out.println(apps.size());
        System.out.println(apps.getClass().getName());









    }




}
