package com.feemng.flybird;


import android.annotation.SuppressLint;
import android.app.Activity;

import android.os.Bundle;

import android.view.Menu;

public class AboutActivity extends Activity{
	
    @SuppressLint("HandlerLeak")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_about);
        
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override 
    protected void onDestroy() { 
        super.onDestroy(); 
      
        //System.exit(0); 
        finish();

    }

	
    
}
