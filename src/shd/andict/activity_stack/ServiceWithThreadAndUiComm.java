package shd.andict.activity_stack;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


import lmc.lonely.R;
import android.app.ActivityManager;
import android.app.Service;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

/**
 * 通知栏消息点击后返回到Service所绑定的Activity
 * @author Leon
 *
 */
public class ServiceWithThreadAndUiComm extends Service{
	private final static String TAG = "AsService";
	
	NotificationFactory mNotificationFactory;
	
	ThreadDownload downloadThread;
	
	private final static int MAX_PROCESS_NUM = 10000;
	private int iProcess = 0 ;

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate()");
		super.onCreate();
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		Log.d(TAG, "onStart()");
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand()");
		
		Log.d("AsService", "ThreadId:"+getCurrentThreadId());
		
		/////////////////////////////////////
		//get the current activity,要在Manifest添加GET_TASKS权限
		ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskInfos = am.getRunningTasks(1);
		ComponentName componentInfo = taskInfos.get(0).topActivity;
		Log.d("AsService","CURRENT Activity ::" + taskInfos.get(0).topActivity.getClassName()
				+"   Package Name :  "+componentInfo.getPackageName());

		/////////////////////////////////////
		//通知栏消息
		
		Intent intentBackToActivty;
		try {
			intentBackToActivty = new Intent(ServiceWithThreadAndUiComm.this,
					Class.forName(taskInfos.get(0).topActivity.getClassName())//将String转换成Class
					);
			
			mNotificationFactory = NotificationFactory.getNotificationFactoryBindingService(
					ServiceWithThreadAndUiComm.this, 
					R.drawable.ico_logo, 
					intentBackToActivty);
	    	
			//启用通知栏的前台模式
	    	startForeground(1, mNotificationFactory.notification);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return startId;
	};
	
	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind()");
		
		downloadSth();
		
		return new MsgBinder();
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "onUnbind()");
		
		//////////////////////////
		//Unbind服务，服务被销毁了，但是下载线程还在运行。
		try {
			pauseDownloading();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("AsService", "onDestroy()");
	}
	
	/**
	 * 模拟Activity启动的动作-下载
	 * 2014-05-11 如果类的实例调用此方法，同时startService也启动，就会有两个实例线程在操作iProcess这个参数，所以增长数度会成倍。
	 * 结论：服务启动时，downloadSth方法会运行？No!是本服务作为线程的载体时，用实例调用此方法，线程会叠加生成。
	 * 所以，这种方法最好做成私有的，在服务组件的生命周期函数中调用。
	 */
	public synchronized void downloadSth(){
		//开启新线程
		downloadThread = new ThreadDownload();
		downloadThread.start();
	}
	
	private class ThreadDownload extends Thread{
		public volatile boolean exit = false;
		
		@Override
		public void run() {
			
			Log.d("AsService", "Downloading...ThreadId:"+ String.valueOf(getCurrentThreadId()));
			while(!exit&&(iProcess<MAX_PROCESS_NUM)){
				
				iProcess += 1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Log.d(TAG, "ThreadDownload is interrupt!");
					e.printStackTrace();
				}
			

			}
		}
	}
	
	public void pauseDownloading() throws InterruptedException{
		if (downloadThread == null) {
			return;
		}
		//downloadThread.interrupt();//Not work
		stopThreadByFlag(downloadThread);
	}
	
	private void stopThreadByFlag(ThreadDownload theThreadDownload){
		if (theThreadDownload==null) {
			return;
		}
		theThreadDownload.exit = true;
	}
	
	
	private void stopThreadByInterrupt(ThreadDownload theThreadDownload) throws InterruptedException{
		if (theThreadDownload==null) {
			return;
		}
		theThreadDownload.interrupt();
		theThreadDownload.join();
	}
	
	/**
	 * 获取下载进度
	 * @return
	 */
	public int getProcess(){
		return iProcess;
	}
	
	public long getCurrentThreadId(){
		return Thread.currentThread().getId();
	}
	
	///////////////////////////
	//Binder
	public class MsgBinder extends Binder{
		public ServiceWithThreadAndUiComm getService(){
			return ServiceWithThreadAndUiComm.this;
		}
	}
}
