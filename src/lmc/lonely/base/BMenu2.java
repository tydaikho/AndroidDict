package lmc.lonely.base;
import lmc.lonely.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;
public class BMenu2 extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_men2);
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu oper = menu.addSubMenu("����");
		oper.setHeaderIcon(R.drawable.ico_logo);
		oper.setHeaderTitle("��ѡ��");
		super.getMenuInflater().inflate(R.menu.menu_2men,oper);
		SubMenu other = menu.addSubMenu("����");
		other.setHeaderIcon(R.drawable.ico_logo);
		other.setHeaderTitle("��ѡ��");
		other.add(Menu.NONE,Menu.FIRST+1,1,"����").setIcon(android.R.drawable.ic_menu_info_details);
		other.add(Menu.NONE,Menu.FIRST+2,2,"����").setIcon(android.R.drawable.ic_menu_help);
		other.add(Menu.NONE,Menu.FIRST+3,3,"����").setIcon(android.R.drawable.ic_menu_send);
		menu.add(Menu.NONE,Menu.FIRST+4,4,"���").setIcon(android.R.drawable.ic_menu_add);
		menu.add(Menu.NONE,Menu.FIRST+5,5,"�༭").setIcon(android.R.drawable.ic_menu_edit);
		menu.add(Menu.NONE,Menu.FIRST+6,6,"����").setIcon(android.R.drawable.ic_menu_save);
		menu.add(Menu.NONE,Menu.FIRST+7,7,"ɾ��").setIcon(android.R.drawable.ic_menu_delete);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Toast.makeText(this,"Id:"+item.getItemId()+"\nTitle:"+item.getTitle(),Toast.LENGTH_SHORT).show();
		return true;
	}
    @Override
	public void onOptionsMenuClosed(Menu menu) {
		Toast.makeText(this,"�رղ˵�",Toast.LENGTH_SHORT).show();
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		Toast.makeText(this,"�򿪲˵�",Toast.LENGTH_SHORT).show();
		return true;
	}
}