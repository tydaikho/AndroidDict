package lmc.lonely.base;
import lmc.lonely.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
public class BCheckBox2 extends Activity {
	private CheckBox box_new = null;
	private CheckBox box_no = null;
	private CheckBox box_ev = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_box2);
        box_new = (CheckBox) super.findViewById(R.id.box_new);
        box_no = (CheckBox) super.findViewById(R.id.box_no);
        box_ev = (CheckBox) super.findViewById(R.id.box_ev);
        box_new.setText("�����������ݼ���ѡ");
        box_new.setChecked(true);
        box_no.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				box_no.setText(box_no.isChecked()?"ִ��ѡ��":"ȡ��ѡ");
			}
		});
        box_ev.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton v, boolean isChecked) {
				box_ev.setText(isChecked?"ִ��ѡ��":"ȡ��ѡ");
			}
		});
    }
}