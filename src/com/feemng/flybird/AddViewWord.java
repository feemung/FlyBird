package com.feemng.flybird;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AddViewWord {
	private StartActivity st=null;
	private RelativeLayout re=null;
	public TextView tv[]=null;
	private String str[]=null;
	private int ii=0;
	private Handler mHandler=null;
	public AddViewWord(StartActivity a,RelativeLayout re,String str[]){
		this.st=a;
		this.re=re;
		this.str=str;
		tv=new TextView[str.length];
		
	}
			class AddViewTask extends TimerTask{
				
				public void run(){
						if(ii>str.length-2){
							this.cancel();
						}
						 Message message = new Message();  
				         message.what = 1;				         
				         mHandler.sendMessage(message); 
				         Log.d("time", Integer.toString(ii));				         
				         ii++;
					}
				}
			@SuppressLint("HandlerLeak")
			public void start(){
				
				int j=5;
				for(int i=0;i<str.length;i++){
				if(i==0){
					  tv[i]=new TextView(st);
					  tv[i].setText(str[i]);
					  
					  tv[i].setTextSize(25);
					  tv[i].setId(5);                    
					  RelativeLayout.LayoutParams lp1 = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);           
					   lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
					   
					  lp1.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE); 
					  tv[i].setVisibility(View.GONE);    
					  re.addView(tv[i], lp1 );
					  
					}
					if(i>0){
						  tv[i]=new TextView(st);
						  boolean f=true;
						  while(f){
							  if(!(str[i]==null)){
								  Log.d("word", str[i]);
								  f=false;
							  }
						  }
						  tv[i].setText(str[i]);
						  
						  tv[i].setTextSize(25);
						  tv[i].setId(j);                    
						  RelativeLayout.LayoutParams lp2 = new  RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);           
						  
							  lp2.addRule(RelativeLayout.BELOW, tv[i-1].getId());
							  lp2.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE); 
						  
						  tv[i].setVisibility(View.GONE); 
						 re.addView(tv[i], lp2 );
						  
						}
					
					
					j++;
					
					
					}
			
				mHandler = new Handler(){ 
					int temp=0;
					@Override
			        public void handleMessage(Message msg) {  
			             	
			            	
			            	Log.d("handler===", Integer.toString(temp));
			            	tv[temp].setVisibility(View.VISIBLE); 
			            
			               temp++;
			           
			            
			        };  
			    };
				Timer t = new Timer() ;	
				AddViewTask timet=new AddViewTask();
				t.schedule(timet,1000,1000) ;	
				
			}
			
	
}
