package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    public void AppActivity(){
        Button button=(Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AppActivity.class);
                startActivity(intent);
            }
        });

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppActivity();
        System.out.println("Oncreat of main Activity");






    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause of Main Activy");
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        System.out.println("BackPressed");

    }

}
