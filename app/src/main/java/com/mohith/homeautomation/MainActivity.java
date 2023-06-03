package com.mohith.homeautomation;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.os.CountDownTimer;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.text.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity {



     CompoundButton mode ;
     CompoundButton relay1;
    CompoundButton relay2 ;
    CompoundButton relay3;
    CompoundButton relay4;
    WebView web;
    Button saveButton ;

    private static final  String SHARED_PREFS = "sharedPrefs";

    private static final String SWITCH1 = "switch1";
    private static final String SWITCH2 = "switch2";
    private static final String SWITCH3 = "switch3";
    private static final String SWITCH4 = "switch4";
    private static final String SWITCH5 = "switch5";


    boolean switchOnOff ;
    boolean switchOnOff1 ;
    boolean switchOnOff2;
    boolean switchOnOff3;
    boolean switchOnOff4;




    SharedPreferences myPreferences ;
    SharedPreferences.Editor myEditor ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        web = findViewById(R.id.webview);
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web.setWebViewClient(new Callback());

        mode  =findViewById(R.id.mode);
        relay1 =findViewById(R.id.relay1);
        relay2 =findViewById(R.id.relay2);
        relay3 =findViewById(R.id.relay3);
        relay4 =findViewById(R.id.relay4);
        saveButton = (Button)findViewById(R.id.save_button);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDate();
            }
        });




        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (mode.isChecked()) {
                            web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=57");

                        } else {
                            web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=48");
                        }
            }

        });

        relay1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (relay1.isChecked()) {
                    web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=50");


                } else {
                    web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=49");
                }
            }

        });

        relay2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (relay2.isChecked()) {
                    web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=52");
                } else {
                    web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=51");
                }
            }

        });

        relay3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (relay3.isChecked()) {
                    web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=54");
                } else {
                    web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=53");
                }
            }

        });
        relay4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (relay4.isChecked()) {

                    web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=56");
                } else {
                    web.loadUrl("https://api.thingspeak.com/update?api_key=AW1T13DI73SCZJ45&field1=55");
                }
            }

        });


        loadData();
        updateViews();



        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true ;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int item_id = item.getItemId();

        if(item_id == R.id.about){
            Uri uri = Uri.parse("https://vitap.ac.in/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

            return true;

        }
        else if(item_id == R.id.team){
            Intent intent = new Intent(MainActivity.this,team.class);
            startActivity(intent);
            return true ;

        }
        else if(item_id == R.id.report){
            Uri uri = Uri.parse("https://drive.google.com/file/d/14Uf-6X5z9D7Fg1NBQ4xMkLii1igo4vo5/view?usp=share_link");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);

            return true;

        }
        else if(item_id == R.id.guide){
            Intent intent = new Intent(MainActivity.this,guide.class);
            startActivity(intent);
            return true ;

        }
        return true;
    }

    public void saveDate() {
           SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
           SharedPreferences.Editor editor = sharedPreferences.edit();

         editor.putBoolean(SWITCH1,mode.isChecked());
            editor.putBoolean(SWITCH2,relay1.isChecked());
            editor.putBoolean(SWITCH3,relay2.isChecked());
            editor.putBoolean(SWITCH4,relay3.isChecked());
          editor.putBoolean(SWITCH5,relay4.isChecked());


           editor.apply();

           Toast.makeText(this,"Data Saved", Toast.LENGTH_SHORT).show();
        }

        public void loadData(){
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            switchOnOff = sharedPreferences.getBoolean(SWITCH1,false);
            switchOnOff1= sharedPreferences.getBoolean(SWITCH2,false);
            switchOnOff2 = sharedPreferences.getBoolean(SWITCH3,false);
            switchOnOff3 = sharedPreferences.getBoolean(SWITCH4,false);
            switchOnOff4 = sharedPreferences.getBoolean(SWITCH5,false);

        }

        public void updateViews() {
            mode.setChecked(switchOnOff);
            relay1.setChecked(switchOnOff1);
            relay2.setChecked(switchOnOff2);
            relay3.setChecked(switchOnOff3);
            relay4.setChecked(switchOnOff4);

        }
    private class Callback extends WebViewClient{
        @Override
        public boolean shouldOverrideKeyEvent(WebView view , KeyEvent event){
            return false ;
        }

    }






    }

