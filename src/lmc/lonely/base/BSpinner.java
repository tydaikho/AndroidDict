package lmc.lonely.base;
import java.util.ArrayList;
import java.util.List;

import lmc.lonely.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
public class BSpinner extends Activity {
	private Spinner spin_color = null;
	private List<CharSequence>sDatas = null;
	private ArrayAdapter<CharSequence>sAd = null;
	private Spinner spin_stu = null;
	private String prvi = null;
	private String[][]cDatas = new String[][]{{"�����","�人��","������","�˲���","��Ѩ��"},
		{"������","������","��ݸ��"},{"������","������","ƽ����"},{"�ֶ���","������","������"}};
	private Spinner spin_prvi = null;
	private Spinner spin_city = null;
	private TextView spin_res = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_spin);
        spin_color = (Spinner) super.findViewById(R.id.spin_color);
        spin_stu = (Spinner) super.findViewById(R.id.spin_stu);
        spin_prvi = (Spinner) super.findViewById(R.id.spin_prvi);
        spin_city = (Spinner) super.findViewById(R.id.spin_city);
        spin_res = (TextView) super.findViewById(R.id.spin_res);
        spin_color.setPrompt("��ѡ��");
        spin_color.setAdapter(ArrayAdapter.createFromResource(this,R.array.spin_color,android.R.layout.simple_spinner_item)); 
        sDatas = new ArrayList<CharSequence>();
        sDatas.add("������");
        sDatas.add("��ѧ��");
        sDatas.add("�о���");
        sDatas.add("��ʿ��");
        sAd = new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,sDatas);
        sAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_stu.setPrompt("��ѡ��");
        spin_stu.setAdapter(sAd);
        spin_prvi.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> ad, View v, int index, long id) {
				sAd = new ArrayAdapter<CharSequence>(BSpinner.this,android.R.layout.simple_spinner_item,cDatas[index]);
				spin_city.setAdapter(sAd);
				prvi = ad.getItemAtPosition(index).toString();
			}
			@Override
			public void onNothingSelected(AdapterView<?> ad) {}
		});
        spin_city.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> ad, View v, int index, long id) {
				spin_res.setText(prvi+ad.getItemAtPosition(index).toString());
			}
			@Override
			public void onNothingSelected(AdapterView<?> ad) {}
		});
    }
}