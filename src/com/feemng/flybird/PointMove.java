package com.feemng.flybird;



import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Message;
import android.util.Log;

public class PointMove implements Runnable {
	private int time=0 ;//��ʱ�����ʱ��
	private int point=0 ;//С��y����ֵ��
	private int move=0 ;//ÿ��λʱ��С�����������ֵ
	private int up=0 ;//ÿ��λʱ��С��������ֵ
	private int upMany=0 ;
	private int uptime=0 ;//С������ʱ��ʱ����
	private int x=0;
	private boolean stopflag=true;
	private int x1=450;
	private int x2=450;
	private int zhuMove=0 ;
	private int birdWidth=0 ;
	private int birdHeight=0 ;
	private int birdX=0 ;
	private int zhuWidth=0 ;
	private int zhuHeight=0 ;
	private int zhuYY=0 ;
	private int winx=0 ;//���ڿ��
	@SuppressWarnings("unused")
	private int winy=0 ;//���ڸ߶�
	private int a=0 ;//���������ļ������
	
	@SuppressWarnings("unused")
	private boolean birdflag=true;
	
	private int store=0;      //��������
	private Random ran=new Random();
	private int zhuY=0 ;
	private int zhuy1=0;
	private int zhuy2=0;
	private int zhuRan=0;
	

	
	private boolean upflag=true;
	private int birdupflag=1;
	private MainActivity m;
	/*
	 * ���췽��
	 * */
	public PointMove(MainActivity m){
		this.m=m;
		

		 time=Settings.getZhuTime(m);//��ʱ�����ʱ��
		 point=Settings.getBirdY(m);//С��y����ֵ��
		  move=Settings.getBirdDownMove(m);//ÿ��λʱ��С�����������ֵ
		  up=Settings.getBirdUp(m);//ÿ��λʱ��С��������ֵ
		  upMany=Settings.getBirdUpMany(m);
		  uptime=Settings.getBirdTime(m);//С������ʱ��ʱ����
		
		  zhuMove=Settings.getZhuMove(m);
		  birdWidth=Settings.getBirdWidth(m);
		  birdHeight=Settings.getBirdHeight(m);
		  birdX=Settings.getBirdX(m);
		  zhuWidth=Settings.getZhuWidth(m);
		  zhuHeight=Settings.getZhuHeight(m);
		  zhuYY=Settings.getZhuYY(m);
		  winx=Settings.getMainWindowWidth(m);//���ڿ��
		
		  winy=Settings.getMainWindowHeight(m);//���ڸ߶�
		  a=Settings.getZhuXX(m);//���������ļ������

		  zhuY=Settings.getZhuY(m);
		  zhuRan=Settings.getZhuRan(m);
	}
	
	//==================�ڲ���=======================
			/*
			 * �ϰ����ƶ��Ĳ�����
			 * */
			class ObstacleTask extends TimerTask{	
				public void run(){
					if(!stopflag){
					x=x+zhuMove;
					
					obstacle();
					}
				}
			};
			
			
			/*
			 *bird��ʱ���Ĳ�����
			 * */
			class BirdTask extends TimerTask{	
				private int temp=0;
				private int t=upMany;
				private int i=1;
				private boolean birdstop=false;
				public void run(){
					birdflag=false;
					if(!stopflag){
						birdstop=true;
						if(upflag){
							point=point+move;
							moveBirdHandler();            	
						}else{
						point=point-up;
						moveBirdHandler();
						temp++;
						}
					}else{
						if(birdstop){
							point=point+move;
							moveBirdHandler();
							Log.d("stop", "ssssssssssssssssss");
						}
					}
					if(point>800-80){
						birdstop=false;
					}
					
				
					if(temp>t){
						upflag=true;
						temp=0;
						i=1;
						t=upMany;
					}
					
					if(birdupflag==2){
						if(i>=2){
						t=t+upMany;
						}
						i++;
						birdupflag=1;
					}
					
				}
				public void set(){
					temp=0;
				}
			}
	//=======================�ڲ���end=========================
			
			
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//-------------�����ϰ����ƶ���ʱ��---------------
		Timer t = new Timer() ;	
		ObstacleTask timet=new ObstacleTask();
		t.schedule(timet,0,time) ;
		//-------------end------------------------------
		
	    //-------------����С���ƶ���ʱ��-----------------
		Timer t1=new Timer();
		BirdTask upt=new BirdTask();
		t1.schedule(upt, 0,uptime);
		//-------------end------------------------------
	}
	
	/*
	 * �޸��ϰ����x1��x2��zhu1��zhu2��ֵ
	 * */
	private void obstacle(){
		if(x>0){
			 x1=winx-(x)%winx;
			if(x%winx==1){
				zhuy1=ran.nextInt(zhuRan)+zhuY;
			} 
			 if(x-a>0){
			 x2=winx-(x-a)%winx;
			 }
			if((x-a)%winx==1){
				zhuy2=ran.nextInt(zhuRan)+zhuY;
			}			
			
			touch();
			doStore();
			moveZhuHandler();
		}	
	}
	/*
	 * ����Ƿ������ϰ���
	 * */
	private boolean touch(){
		
		if(x1<birdX+birdWidth&&x1>birdX-zhuWidth&&(point>zhuHeight+zhuy1+zhuYY-birdHeight||point<zhuHeight+zhuy1)){
			touchZhu();
			return true;
		}
		else if(x2<birdX+birdWidth&&x2>birdX-zhuWidth&&(point>zhuHeight+zhuy2+zhuYY-birdHeight||point<zhuHeight+zhuy2)){
			touchZhu();
			return true;
		}else{
			return false;
		}
		
	}
	/*
	 * �޸�store��ֵ
	 * */
	private void doStore(){
		if((birdX-zhuWidth==x1||birdX-zhuWidth==x2)){
			store++;
			showStoreHandler();
		}
	}
	private void touchZhu(){
		this.stop();
		this.gameOverHandler();
	}
	
	
	
	
	/*
	 * -----------��MainActivity�İ�ť���õķ���-----------------
	 * */
	 //С��������ť���õķ���
	public void birdUp(){       
		birdupflag=2;
		
		if(upflag){
			upflag=false;
			
		}
		
	}
	
	//��ͣ��ť�ķ���
	public void stop(){
		if(stopflag){
			stopflag=false;
		}else{
			stopflag=true;
			birdflag=true;
		}
	}
	
	//game��ʼ�ķ���
	public void start(){
		x=0;
		point=Settings.getBirdY(m);
		x=0;
		stopflag=false;
		x1=winx;
		x2=winx;
		store=0;
	}
	//---------------end-----------------------------------
	
	
	/* 
	 * ------------handler����----------------------
	 * */
	//����С���ƶ���handler��Ϣ
	private void moveBirdHandler(){
		 Message message = new Message();  
         message.what = 1;  
         m.mHandler.sendMessage(message);  
           
	}
	// ���������ƶ���handler��Ϣ
	private void moveZhuHandler(){
		 Message message = new Message();  
        message.what = 2;  
        m.mHandler.sendMessage(message);  
          
	}
	//���ͷ�����ʾ��handler��Ϣ
	private void showStoreHandler(){
		 Message message = new Message();  
       message.what = 3;  
       m.mHandler.sendMessage(message);  
	}
	private void gameOverHandler(){
		Message message=new Message();
		message.what=4;
		m.mHandler.sendMessage(message);
	}
	//�˷�����Ҫ����
	public void returnX(){
		x--;
		this.obstacle();
	}
	//-------------����handler��Ϣ�ķ������˽���------------------------------
	
	
	
	
	/*
	 * ------------setter��getter------------------------------
	 * */
	public int getStore(){
		return store;
	}
	public void setTime(int time){
		this.time=time;
	}
	public void setMove(int move){
		this.move=move;
		
	}
	public void setPoint(int point){
		this.point=point;
	}
	public int getMove(){
		return this.move;
	}
	public int getPoint(){
		return this.point;
	}
	public int getX(){
		return this.x;
	}
	public int[] getTemp(){
		int temp[]={x1,x2,zhuy1,zhuy2};
		return temp;
	}
	public int getBirdX(){
		return this.birdX;
	}
	//-------------setter��getter�������˽���---------------------------------
}
