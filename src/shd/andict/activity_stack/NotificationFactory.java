package shd.andict.activity_stack;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;


/**
 * 生产各种Notification
 * @author Leon
 * 2014-04-14 创建。这里能生成各种Notification，
 * 但是启动Notication的模式要在Service中选定开启方式。
 * TODO,增加枚举，列举一些常用的通知flag组合。
 * 2014-05-08 使用静态工厂模式改造
 */


public class NotificationFactory {
	/////////////////////////////////////
	//Flags:整理Notification的Flags组合
	private class NotiFlags{
	}
	
	
	/////////////////////////////////////
	//初始化变量
	//工厂模式的成品
	public Notification notification;
	
	
	///////////////////////////////////
	//域读写器
	public Notification getNotification() {
		return notification;
	}
	
	///////////////////////////////////
	//静态工厂变量
	public static NotificationFactory getNotificationFactoryBindingService(
			Context context,int icon,
			Intent intentBackToActivty){
		int flags = 0;
		NotificationFactory notificationFactory = new NotificationFactory(context,icon,flags,intentBackToActivty);
		
		return notificationFactory;
	}
	

	////////////////////////////////
	//构造函数
	public NotificationFactory(Context context,int icon,int flags,Intent intentBackToActivty) {
		notification = createNotification(context, icon, flags, intentBackToActivty);
	}
	
	//生成Notifcation
	private Notification createNotification(Context context,int icon,int flags,Intent intentBackToActivty) {
		
        Notification notification = new Notification(icon, "开始定位...",System.currentTimeMillis());

        //notification.flags |= flags;
        notification.flags |= Notification.FLAG_NO_CLEAR;

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intentBackToActivty, PendingIntent.FLAG_UPDATE_CURRENT);    //点击下拉出来的列表后需要跳转的页面

        notification.setLatestEventInfo(context, "Location_Tracker","正在记录...", contentIntent);
        //_nm.notify(NOTIFICATION_ID, notification);	
        
        return notification;
	}




}
