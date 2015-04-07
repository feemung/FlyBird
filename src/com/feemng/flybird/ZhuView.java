package com.feemng.flybird;




import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.util.DisplayMetrics;
import android.view.View;

public class ZhuView extends View{
	public float currentX =0;
	public float currentY =0;
	public int width,height;
	private Bitmap bitmap_role;
	
	int direction =0;

	public ZhuView(Context context) {
		super(context);
		
		Bitmap bitmap=BitmapFactory.decodeResource(context.getResources(), R.drawable.zhua);
		
		int bmpW = bitmap.getWidth();
		int bmpH = bitmap.getHeight();
		
		DisplayMetrics dm =context.getResources().getDisplayMetrics();		
		int w_screen = dm.widthPixels;		
		int h_screen = dm.heightPixels;	
		
		float scale_screenW=(float)w_screen/(Settings.getMainWindowWidth(context));
		float scale_screenH=(float)h_screen/(Settings.getMainWindowHeight(context));
		float scale_bmpW=(float)bmpW/(Settings.getZhuWidth(context));
		float scale_bmpH=(float)bmpH/(Settings.getZhuHeight(context));
		
		float scaleW=1.0f;
		float scaleH=1.0f;
		scaleW=scale_screenW/scale_bmpW;
		scaleH=scale_screenH/scale_bmpH;
		this.currentY=scaleH*-360;
		 android.graphics.Matrix mt = new android.graphics.Matrix();
		 mt.postScale(scaleW, scaleH);
		 bitmap_role = Bitmap.createBitmap(bitmap, 0, 0, bmpW, bmpH, mt, true);
		
		// TODO Auto-generated constructor stub
	}
	 
	@Override
	public void onDraw(Canvas canvas){
		
		canvas.drawBitmap(bitmap_role, currentX, currentY, null);
		super.onDraw(canvas);
	}
	
}
