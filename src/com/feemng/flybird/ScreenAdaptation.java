package com.feemng.flybird;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenAdaptation {
	public static float dip2pxX(Activity a,int temp){
		
		DisplayMetrics dm =a.getResources().getDisplayMetrics();		
		int w = dm.widthPixels;	
		
		float wx= (float)w/480;
		float t=(temp*wx+0.5f);
		return t;
	}
	
	public static float dip2pxY(Activity a,int temp){
			
			DisplayMetrics dm =a.getResources().getDisplayMetrics();		
			int h = dm.heightPixels;		
			float hx=(float)h/800;
			//Log.d("width",Integer.toString(h));
			//Log.d("width", Integer.toString(h));
			float t=(temp*hx+0.5f);
			return t;
		}
	
	
}
