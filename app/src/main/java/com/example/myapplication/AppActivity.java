package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AppActivity extends AppCompatActivity {

    private GridView gridView;

    myService m= new myService();




   PackageManager manager;
    List<Item> apps;

    public void loadApps() {
        manager = getPackageManager();

        apps = new ArrayList<>();


        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : availableActivities) {

            Item app = new Item();
            app.label = resolveInfo.activityInfo.packageName;
            app.name = resolveInfo.loadLabel(manager);
            app.icon = resolveInfo.loadIcon(manager);
            apps.add(app);
            Log.i("name",app.name.toString());
            //Log.i("name1",app.label.toString());
            //Log.i("name2",app.icon.toString());
            //Log.i("nameLoad","Loading apps");
        }
        Log.i("nameLoad","Loading apps");

    }










    private void loadListView() {

        gridView = (GridView) findViewById(R.id.gridView);

        ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this, android.R.layout.simple_dropdown_item_1line, apps) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                //return super.getView(position, convertView, parent);

                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.item, null);
                }

                ImageView appIcon = (ImageView) convertView.findViewById(R.id.icon);
                appIcon.setImageDrawable(apps.get(position).icon);

                TextView appText = (TextView) convertView.findViewById(R.id.name);
                appText.setText(apps.get(position).name);

                return convertView;
            }
        };
        gridView.setAdapter(arrayAdapter);

        Log.i("nameDIssplea","Displaing app");
    }


    private void  addClickListener(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = manager.getLaunchIntentForPackage(apps.get(position).label.toString());
                startActivity(intent);
            }
        });
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        startService(new Intent(AppActivity.this,myService.class));

        loadApps();
        loadListView();
        addClickListener();


        Intent intent =getIntent();



        System.out.println(intent.getStringExtra("a"));



        //System.out.println(m.);

        String carListAsString = getIntent().getStringExtra("list_as_string");






    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        //stopService(new Intent(AppActivity.this,myService.class));
        System.out.println("Myservice Destroy");




    }
}
