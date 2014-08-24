package lmc.lonely.base;
import lmc.lonely.R;
import lmc.lonely.Wel;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;
public class BWidget1 extends AppWidgetProvider {
	@Override
	public void onUpdate(Context con, AppWidgetManager mgr, int[]ids) {
		super.onUpdate(con, mgr, ids);
		Toast.makeText(con,"ִ�и���A",Toast.LENGTH_SHORT).show();
		for(int i=0;i<ids.length;i++){
			Intent it = new Intent(con,Wel.class);
			RemoteViews rViews = new RemoteViews(con.getPackageName(),R.layout.base_widg);
			PendingIntent pIt = PendingIntent.getActivity(con,0,it,0);
			rViews.setOnClickPendingIntent(R.id.widg_call,pIt);
			mgr.updateAppWidget(ids[i],rViews);
		}
	}
	@Override
	public void onDeleted(Context con, int[]ids) {
		super.onDeleted(con, ids);
		Toast.makeText(con,"�Ƴ�����Ӧ��A",Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onEnabled(Context con) {
		super.onEnabled(con);
		Toast.makeText(con,"ִ������A",Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onDisabled(Context con) {
		super.onDisabled(con);
		Toast.makeText(con,"ִ�н���A",Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onReceive(Context con, Intent it) {
		super.onReceive(con, it);
		Toast.makeText(con,"���ܹ㲥�¼�A",Toast.LENGTH_SHORT).show();
	}
}