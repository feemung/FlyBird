package com.feemng.flybird;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Settings {
	private static int mainWindowWidth=480;
	private static int mainWindowHeight=800;
	private static int zhuXX=240;
	private static int birdWidth=55;
	private static int birdHeight=30;
	private static int birdX=100;
	private static int birdY=150;//小鸟y坐标值；
	private static int zhuWidth=82;
	private static int zhuHeight=360;
	@SuppressWarnings("unused")
	private static int zhuX=0;
	private static int zhuY=-215;
	private static int zhuYY=183;
	private static int zhuMove=2;
	private static int zhuTime=16;//计时器间隔时间
	private static int birdDownMove=2;//每单位时间小鸟自由下落的值
	private static int birdUp=1;//每单位时间小鸟上升的值
	private static int birdUpMany=50;
	private static int birdTime=10;//小鸟上升时的时间间隔
	private static int maxStore=0;
	private static int zhuRan=183;
	
	public static void reset(Context context){
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();   
		 
		 
		editor.putInt("maxStore",0);
		editor.putString("bird_StartX",Integer.toString(100)) ;
		editor.putString("bird_StartY",Integer.toString(150)) ;
		editor.putString("bird_Height",Integer.toString(30)) ;
		editor.putString("bird_Width",Integer.toString(55)) ;
		editor.putString("bird_DownMove",Integer.toString(2)) ;
		editor.putString("bird_Time",Integer.toString(10)) ;
		editor.putString("bird_UpMove",Integer.toString(1));
		editor.putString("bird_UpTimes",Integer.toString(50)) ;
		
		editor.putString("zhu_X",Integer.toString(0)) ;
		editor.putString("zhu_Y",Integer.toString(-300)) ;
		editor.putString("zhu_Height",Integer.toString(360)) ;
		editor.putString("zhu_Width",Integer.toString(82)) ;
		editor.putString("zhu_YY",Integer.toString(183)) ;
		editor.putString("zhu_Time",Integer.toString(16)) ;
		editor.putString("zhu_Move",Integer.toString(1)) ;
		editor.putString("zhu_Ran",Integer.toString(183)) ;
		editor.commit(); 
	}
	
	
	public static int getMainWindowHeight(Context context) {
		return mainWindowHeight;
	}
	
	public static int getMainWindowWidth(Context context) {
		return mainWindowWidth;
	}
	public static int getBirdHeight(Context context) {
		return birdHeight;
	}
	public static int getBirdWidth(Context context) {
		return birdWidth;
	}
	public static int getZhuWidth(Context context) {
		return zhuWidth;
	}
	public static int getZhuHeight(Context context) {
		return zhuHeight;
	}
	
	
	public static int getBirdX(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("bird_StartX", "100");
		
		if(str.matches("[0-9]{1,}")){
			birdX=Integer.valueOf(str);
			Log.d("birdx-------", Integer.toString(birdX));
		}
		Log.d("birdx", Integer.toString(birdX));
		return birdX;
	}

	public static int getBirdY(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("bird_StartY", "150");
		
		if(str.matches("[0-9]{1,}")){
			birdY=Integer.valueOf(str);
		}
		Log.d("birdY", Integer.toString(birdY));
		return birdY;
	}

	public static int getZhuTime(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("zhu_Time", "16");
		
		if(str.matches("[0-9]{1,}")){
			zhuTime=Integer.valueOf(str);
			Log.d("zhutime-----", Integer.toString(zhuTime));
		}
		Log.d("zhutime", Integer.toString(zhuTime));
		return zhuTime;
	}

	public static int getBirdDownMove(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("bird_DownMove", "2");
		
		if(str.matches("[0-9]{1,}")){
			birdDownMove=Integer.valueOf(str);
		}
		Log.d("birdDownMove", Integer.toString(birdDownMove));
		return birdDownMove;
	}
	public static int getBirdUp(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("bird_UpMove", "1");
		
		if(str.matches("[0-9]{1,}")){
			birdUp=Integer.valueOf(str);
		}
		Log.d("birdup", Integer.toString(birdUp));
		return birdUp;
	}

	public static int getBirdUpMany(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("bird_UpTimes", "50");
		
		if(str.matches("[0-9]{1,}")){
			birdUpMany=Integer.valueOf(str);
		}
		Log.d("birdupMany", Integer.toString(birdUpMany));
		return birdUpMany;
	}
	public static int getBirdTime(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("bird_UpTime", "10");
		
		if(str.matches("[0-9]{1,}")){
			birdTime=Integer.valueOf(str);
		}
		Log.d("birdTime", Integer.toString(birdTime));
		return birdTime;
	}
	

	public static int getZhuYY(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("zhu_YY", "183");
		
		if(str.matches("[0-9]{1,}")){
			zhuYY=Integer.valueOf(str);
		}
		Log.d("zhuYY", Integer.toString(zhuYY));
		return zhuYY;
	}
	
	public static int getMaxStore(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		maxStore=preferences.getInt("maxStore", -1);
		
		Log.d("settings_get---maxStore", Integer.toString(maxStore));
		
		return maxStore;
	}
	public static void setMaxStore(Context context,int ms) {
		Log.d("settings_setmaxStore", Integer.toString(ms));
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();   
		 
		editor.putInt("maxStore", ms);   
		editor.commit();  
		
	}
	public static int getZhuY(Context context){
		
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("zhu_Y", "-300");
		
		if(str.matches("[0-9]{1,}")){
			zhuY=Integer.valueOf(str);
		}
		Log.d("zhuY", Integer.toString(zhuY));
		return zhuY;
	}
	public static int getZhuXX(Context context) {
		return zhuXX;
	}
	public static int getZhuMove(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("zhu_Move", "1");
		
		if(str.matches("[0-9]{1,}")){
			zhuMove=Integer.valueOf(str);
		}
		Log.d("zhuMove", Integer.toString(zhuMove));
		return zhuMove;
	}


	public static int getZhuRan(Context context) {
		SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
		String str=preferences.getString("zhu_Ran", "183");
		
		if(str.matches("[0-9]{1,}")){
			zhuRan=Integer.valueOf(str);
		}
		return zhuRan;
	}


}
