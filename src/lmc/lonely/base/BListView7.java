package lmc.lonely.base;
import lmc.lonely.R;
import lmc.lonely.SysConts;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.SlidingDrawer.OnDrawerScrollListener;
import android.widget.Toast;
public class BListView7 extends Activity {
	private SlidingDrawer list_7sd = null;
	private ImageView list_7pre = null;
	private LinearLayout list_lay7 = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_list7);
        list_7sd = (SlidingDrawer) super.findViewById(R.id.list_7sd);
        list_7pre = (ImageView) super.findViewById(R.id.list_7pre);
        list_lay7 = (LinearLayout) super.findViewById(R.id.list_lay7);
        list_7sd.setOnDrawerOpenListener(new OnDrawerOpenListener() {
			@Override
			public void onDrawerOpened() {
				list_7pre.setImageResource(R.drawable.ico_next);
			}
		});
        list_7sd.setOnDrawerCloseListener(new OnDrawerCloseListener() {
			@Override
			public void onDrawerClosed() {
				list_7pre.setImageResource(R.drawable.ico_pre);
			}
		});
        list_7sd.setOnDrawerScrollListener(new OnDrawerScrollListener() {
			@Override
			public void onScrollStarted() {
				Toast.makeText(BListView7.this,"��ʼ�϶�",Toast.LENGTH_SHORT).show();
			}
			@Override
			public void onScrollEnded() {
				Toast.makeText(BListView7.this,"ֹͣ�϶�",Toast.LENGTH_SHORT).show();
			}
		});
        ListView list_cont = new ListView(this);
        list_cont.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,SysConts.datao));
        list_lay7.addView(list_cont);
    }
}