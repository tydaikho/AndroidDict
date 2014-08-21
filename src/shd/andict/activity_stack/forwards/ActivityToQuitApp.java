package shd.andict.activity_stack.forwards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 用来退出程序的Activity
 * 1、本Activity必须是程序启动的第一个Activity才能起到这种立即退出的效果，
 * 因为Intent.FLAG_ACTIVITY_CLEAR_TOP只会把目标Activity的“上面”的Activity清理掉，
 * 而如果目标Activity的“下面”还有Activity（换句话说，目标Activity不在栈底），
 * 则finish后只会到他下面的那个Activity，而不是立即退出的效果了 
 * 
 * 2、可以把A设置成不可见的Acitivity（方法见下面），然后在它的onCreate方法里跳转到“真正”的载入界面 
 * 就可以实现在D中点退出程序按钮时看上去立即退出程序的效果 
 * android:theme=”@android:style/Theme.NoDisplay” 
 * 参考：采用FLAG_ACTIVITY_CLEAR_TOP退出整个程序（多activity） - leihupqrst - 博客园 : 
 * http://www.cnblogs.com/leihupqrst/p/3484908.html
 * @author  Leonn
 * 
 */
public class ActivityToQuitApp extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TODO 跳转到启动画面，这是个初始的在Activity Stack中垫底的Activity
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if ((Intent.FLAG_ACTIVITY_CLEAR_TOP&intent.getFlags())!=0) {//注意与非运算，判断Flags中是否还有...
			finish();
		}
	}
}
