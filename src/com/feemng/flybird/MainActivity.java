package com.feemng.flybird;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity{
	public BirdView bird=null;
	private PointMove pm=null;
	public  Handler mHandler =null;
	private RelativeLayout re=null;
	private ZhuView zhu1a=null;
	private ZhuViewb zhu1b=null;
	private ZhuView zhu2a=null;
	private ZhuViewb zhu2b=null;
	
	private int zhuyy=0;
	private int h=0;
	private TextView gameOverStoreInt=null;
	private TextView gameOverStoreString=null;
    @SuppressLint("HandlerLeak")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        zhuyy=Settings.getZhuYY(this);
    	 h=Settings.getZhuHeight(this);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        re=(RelativeLayout)findViewById(R.id.root);
        re.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pm.birdUp();
			}
			
		});
        gameOverStoreInt=(TextView)findViewById(R.id.gameOverStoreInt);
    	gameOverStoreString=(TextView)findViewById(R.id.gameOverStoreString);
         this.createBird();
         this.createZhu();
		 pm=new PointMove(this);
		Thread t=new Thread(pm);
		t.start();
		Button start=(Button)findViewById(R.id.start);
		start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				gameStart();
				
			}
			
		});
		Button upBut=(Button)findViewById(R.id.upbird);
		
		
		upBut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				pm.birdUp();
			}
			
		});
		Button resetStoreBut=(Button)findViewById(R.id.resetStore);
		resetStoreBut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Settings.setMaxStore(getBaseContext(),0);
			}
			
		});
		
		mHandler = new Handler(){  
	          
			@Override
	        public void handleMessage(Message msg) {  
	            switch (msg.what) {  
	            case 1:  
	                moveBird();  
	                break;  
	            case 2:
	            	moveZhu();
	            	break;
	            case 3:
	            	showStore();
	            	break;
	            case 4:
	            	gameOver();
	            	break;
	            }
	        };  
	    };
    }
    
    private void gameStart(){
    	gameOverStoreInt.setVisibility(View.GONE) ;
    	gameOverStoreString.setVisibility(View.GONE) ;
    	zhu1a.setVisibility(View.VISIBLE);
        zhu2a.setVisibility(View.VISIBLE);
        zhu1b.setVisibility(View.VISIBLE);
        zhu2b.setVisibility(View.VISIBLE);
    	pm.start();
    	showStore();
    }
    private void gameOver(){
    	gameOverStoreInt.setVisibility(View.VISIBLE) ;
    	gameOverStoreString.setVisibility(View.VISIBLE) ;
    	int s=pm.getStore();
    	gameOverStoreInt.setText(Integer.toString(s));
    	gameOverStoreString.setText("Store");
    	if(s>Settings.getMaxStore(this)){
    		gameOverStoreString.setText("New Store");
    		Settings.setMaxStore(this,s);
    	}
    	zhu1a.setVisibility(View.GONE);
        zhu2a.setVisibility(View.GONE);
        zhu1b.setVisibility(View.GONE);
        zhu2b.setVisibility(View.GONE);
    }
    private void showStore(){
    	TextView store=(TextView)findViewById(R.id.store);
    	int s=pm.getStore();
    	store.setText(Integer.toString(s));
    }
    
    private void createBird(){
    	bird=new BirdView(this);
        re.addView(bird);
    }
    
    private void moveBird(){
    	bird.currentX=ScreenAdaptation.dip2pxX(this,pm.getBirdX());
    	bird.currentY=ScreenAdaptation.dip2pxY(this,pm.getPoint());
    	bird.invalidate();   
    }
  
    private void createZhu(){
    	zhu1a=new ZhuView(this);
    	
        re.addView(zhu1a);
        zhu1b=new ZhuViewb(this);
        re.addView(zhu1b);
    	zhu2a=new ZhuView(this);
        re.addView(zhu2a);
        zhu2b=new ZhuViewb(this);
        re.addView(zhu2b);
        zhu1a.setVisibility(View.GONE);
        zhu2a.setVisibility(View.GONE);
        zhu1b.setVisibility(View.GONE);
        zhu2b.setVisibility(View.GONE);
    	
    }
    
    private void moveZhu(){
    	int temp[]=pm.getTemp();
		
		zhu1a.currentX=ScreenAdaptation.dip2pxX(this, temp[0]);
		zhu1a.currentY=ScreenAdaptation.dip2pxY(this, temp[2]);
		
    	zhu1a.invalidate();
    	zhu1b.currentX=ScreenAdaptation.dip2pxX(this, temp[0]);
		zhu1b.currentY=ScreenAdaptation.dip2pxY(this, temp[2]+h+zhuyy);
		
    	zhu1b.invalidate();
    	
    	zhu2a.currentX=ScreenAdaptation.dip2pxX(this, temp[1]);
		zhu2a.currentY=ScreenAdaptation.dip2pxY(this, temp[3]);
		
    	zhu2a.invalidate();
    	zhu2b.currentX=ScreenAdaptation.dip2pxX(this, temp[1]);
		zhu2b.currentY=ScreenAdaptation.dip2pxY(this, temp[3]+h+zhuyy);
	
    	zhu2b.invalidate();    
    }
    public void openSetActivity(View view){
    	Intent set=new Intent(this,SetActivity.class);
    	startActivity(set);
    	this.finish();
    }
    public void reset(View view){
    	Settings.reset(this);
    }
	
    @SuppressWarnings("deprecation")
	public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
            // 锟斤拷锟斤拷锟剿筹拷锟皆伙拷锟斤拷  
            AlertDialog isExit = new AlertDialog.Builder(this).create();  
            // 锟斤拷锟矫对伙拷锟斤拷锟斤拷锟�  
            isExit.setTitle("你确定退出游戏吗");  
            // 锟斤拷锟矫对伙拷锟斤拷锟斤拷息  
            isExit.setMessage(" ");  
            // 锟斤拷锟窖★拷锟脚ワ拷锟阶拷锟斤拷锟斤拷  
            isExit.setButton("确定", listener);  
            isExit.setButton2("取消", listener);  
            // 锟斤拷示锟皆伙拷锟斤拷  
            isExit.show();  
  
        }  
          
        return false;  
          
    } 
    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
    {  
        public void onClick(DialogInterface dialog, int which)  
        {  
            switch (which)  
            {  
            case AlertDialog.BUTTON_POSITIVE:// "确锟斤拷"锟斤拷钮锟剿筹拷锟斤拷锟斤拷  
                finish();  
                break;  
            case AlertDialog.BUTTON_NEGATIVE:// "取锟斤拷"锟节讹拷锟斤拷锟斤拷钮取锟斤拷锟皆伙拷锟斤拷  
                break;  
            default:  
                break;  
            }  
        }  
    };    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	
    
}
