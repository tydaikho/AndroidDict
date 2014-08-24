package lmc.lonely.base;
import lmc.adater.BListView3Adapter;
import lmc.lonely.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
public class BListView3 extends Activity {
	private ExpandableListView list_3view = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_list3);
        list_3view = (ExpandableListView) super.findViewById(R.id.list_3view);
        list_3view.setAdapter(new BListView3Adapter(this));
        super.registerForContextMenu(list_3view);
        list_3view.setOnGroupClickListener(new OnGroupClickListener() {
			@Override
			public boolean onGroupClick(ExpandableListView par, View v, int group, long id) {
				Toast.makeText(BListView3.this,"�����ڵ�\n��ڵ�:"+group,Toast.LENGTH_SHORT).show();
				return false;
			}
		});
        list_3view.setOnGroupExpandListener(new OnGroupExpandListener() {
			@Override
			public void onGroupExpand(int group) {
				Toast.makeText(BListView3.this,"��չ��\n��ڵ�:"+group,Toast.LENGTH_SHORT).show();
			}
		});
        list_3view.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView par, View v, int group, int child, long id) {
				Toast.makeText(BListView3.this,"����ӽڵ�\n��ڵ�:"+group+",�ӽڵ�:"+child,Toast.LENGTH_SHORT).show();
				return false;
			}
		});
        list_3view.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			@Override
			public void onGroupCollapse(int group) {
				Toast.makeText(BListView3.this,"��ر�\n��ڵ�:"+group,Toast.LENGTH_SHORT).show();
			}
		});
    }
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo info) {
		super.onCreateContextMenu(menu, v, info);
		ExpandableListContextMenuInfo eInfo = (ExpandableListContextMenuInfo) info;
		int type = ExpandableListView.getPackedPositionType(eInfo.packedPosition);
		int group = ExpandableListView.getPackedPositionGroup(eInfo.packedPosition);
		int child = ExpandableListView.getPackedPositionChild(eInfo.packedPosition);
		Toast.makeText(this,"�����Ĳ˵�\n����:"+type+"\n��ڵ�:"+group+"\n�ӽڵ�:"+child,Toast.LENGTH_SHORT).show();
    }
}