package com.gaojin.view;

import org.apache.cordova.DroidGap;

import android.app.Activity;
import android.os.Bundle;

public class WebapponeActivity extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
      //Æô¶¯»­Ãæ
		super.setIntegerProperty("splashscreen", R.drawable.m1);

		//super.loadUrl("file:///android_asset/www/html/login.html");
		super.loadUrl("file:///android_asset/www/html/login.html", 2*1000);

    }
}