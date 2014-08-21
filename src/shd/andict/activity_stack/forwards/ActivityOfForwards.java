package shd.andict.activity_stack.forwards;

import android.app.Activity;
import android.content.Intent;

public class ActivityOfForwards extends Activity{
	/**
	 * 关闭当前Activity，跳转到新的Activity
	 * @param classObj
	 */
	private void forward (Class<?> classObj) {
		Intent intent = new Intent();
		intent.setClass(this, classObj);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		this.startActivity(intent);
		this.finish();//保障finish当前Activity，无论当前Activity采用的是多例模式还是单例模式
	}
	
	private void quitApp(){
		Intent intent = new Intent();
		intent.setClass(ActivityOfForwards.this, ActivityToQuitApp.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		this.finish();
	}
	
}
