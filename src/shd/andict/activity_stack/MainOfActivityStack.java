package shd.andict.activity_stack;

import java.util.List;

import lmc.lonely.R;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * 关于Activity Stack的
 * 1、当前Activity的名称
 * @author Leon
 *
 */
public class MainOfActivityStack extends Activity implements OnClickListener{
	
	TextView logTextView;
	
	Button startServiceButton;
	Button stopServiceButton;
	Button bindServiceButton;
	Button unbindServiceButton;
	
	Button commByBinderButton;
	
	Button startServiceActionButton;
	TextView downloadingTextView;
	
	ServiceWithThreadAndUiComm asService;
	ServiceWithThreadAndUiComm asServiceFromBinder;
	
	private ServiceConnection conn;
	
	private static final String TAG = "MainOfActivityStack";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shd_activity_stack);
		
		////////////////
		
		////////////////////////////////////////
		//初始化
		//控件
		logTextView = (TextView)findViewById(R.id.log_tv_activity_stack);
		startServiceButton = (Button)findViewById(R.id.btn_start_as_service);
		stopServiceButton = (Button)findViewById(R.id.btn_stop_as_service);
		bindServiceButton = (Button)findViewById(R.id.btn_bind_as_service);
		unbindServiceButton = (Button)findViewById(R.id.btn_unbind_as_service);
		
		commByBinderButton = (Button)findViewById(R.id.btn_communication_by_binder);
		
		startServiceActionButton = (Button)findViewById(R.id.btn_service_work);
		downloadingTextView = (TextView)findViewById(R.id.log_tv_downloading);
		
		
		//Service
		asService = new ServiceWithThreadAndUiComm();//这样实例化是不规范的，服务并没有运行。
		//应该在，BindService运行之后，使用IBinder获取Service实例。
		
		///////////////////////////////////
		//绑定控件监听器
		startServiceButton.setOnClickListener(this);
		stopServiceButton.setOnClickListener(this);
		bindServiceButton.setOnClickListener(this);
		unbindServiceButton.setOnClickListener(this);
		commByBinderButton.setOnClickListener(this);
		startServiceActionButton.setOnClickListener(this);
		

		
		/////////////////////////////////
		//ServiceConnection对象:用来bindService
		conn = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				Log.d(TAG, "onServiceDisconnected()");
				
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Log.d(TAG, "onServiceConnected()");
				asServiceFromBinder = ((ServiceWithThreadAndUiComm.MsgBinder)service).getService();
			}
		};
		
		/////////////////////////////////////
		//get the current activity
		ActivityManager am = (ActivityManager)this.getSystemService(ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskInfos = am.getRunningTasks(1);
		ComponentName componentInfo = taskInfos.get(0).topActivity;
		String log = "CURRENT Activity ::" + taskInfos.get(0).topActivity.getClassName()
				+"   Package Name :  "+componentInfo.getPackageName();
		
		Log.d("MainOfActivityStack", "ThreadId:" + getCurrentThreadId());
		
		logTextView.setText(log);
		
	}
	
	public long getCurrentThreadId(){
		return Thread.currentThread().getId();
	}
	
	/**
	 * 获取下载进度
	 */
	public void getProcessForBindingService(){
		/*
		 * TODO
		if (condition) {
			
		}
		*/
		///////////////////////////////
		//新线程
		final Handler mHandler = new Handler();
		final Thread mThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true){
						Thread.sleep(1000);
						mHandler.post(new Runnable() {
							
							@Override
							public void run() {
								//downloadingTextView.setText(""+asService.getProcess());//不规范的
								downloadingTextView.setText("Downloading..."+asServiceFromBinder.getProcess());
							}
						});
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mThread.start();		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start_as_service:
				////////////
				//调整控件
				startServiceButton.setVisibility(View.GONE);
				stopServiceButton.setVisibility(View.VISIBLE);
			
				Intent intent = new Intent(MainOfActivityStack.this,shd.andict.activity_stack.ServiceWithThreadAndUiComm.class);
				startService(intent);
			break;
		case R.id.btn_stop_as_service:
				///////////
				//调整控件
				startServiceButton.setVisibility(View.VISIBLE);
				stopServiceButton.setVisibility(View.GONE);
			
				Intent intent1 = new Intent(MainOfActivityStack.this,shd.andict.activity_stack.ServiceWithThreadAndUiComm.class);
				stopService(intent1);
			break;
		case R.id.btn_bind_as_service:
				/////////////////////////
				//调整控件
				//使用通信按钮可见
				commByBinderButton.setVisibility(View.VISIBLE);
				bindServiceButton.setVisibility(View.GONE);
				unbindServiceButton.setVisibility(View.VISIBLE);
				
				Intent intent11 = new Intent(MainOfActivityStack.this,shd.andict.activity_stack.ServiceWithThreadAndUiComm.class);
				bindService(intent11, conn, Context.BIND_AUTO_CREATE);//Bind之前自动创建服务。
				
				//监听下载进度-显示在Activity界面中。如果要显示在通知栏呢？TODO
				getProcessForBindingService();
			break;
		case R.id.btn_unbind_as_service:
				////////////////////////
				//调整控件
				//使用通信按钮不可见
				commByBinderButton.setVisibility(View.GONE);
				bindServiceButton.setVisibility(View.VISIBLE);
				unbindServiceButton.setVisibility(View.GONE);

				
				unbindService(conn);
				
				
			break;
		case R.id.btn_communication_by_binder:
				asServiceFromBinder.downloadSth();
				int downloadProcess = asService.getProcess();
				Log.d(TAG+"通过Binder通信","process:"+downloadProcess);
			break;
		case R.id.btn_service_work:
				asService.downloadSth();
				int downloadProcess2 = asService.getProcess();
				Log.d("MainOf...通过Service实例方法的通信", "process:"+downloadProcess2);
			break;
		default:
			break;
		}
	}
}
