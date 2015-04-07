package com.feemng.flybird;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class StartActivity extends Activity{
	private RelativeLayout re=null;
	private AddViewWord adw=null;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start);
        re=(RelativeLayout)findViewById(R.id.wordRE);
        String str[]=this.getResources().getStringArray(R.array.aFeiZhengZhuan2);
        adw=new AddViewWord(this,re,str);
        adw.start();
        
		
        
	}
	
	



	





	public void openMainActivity(View view){
		Intent set=new Intent(this,MainActivity.class);
    	startActivity(set);
		this.finish();
	}
}
