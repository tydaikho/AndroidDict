package lmc.lonely.file;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import lmc.lonely.R;
import lmc.lonely.SysConts;
import lmc.utils.OtherUtils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class FApp extends Activity implements OnClickListener {
	private String fName = null;
	private Button fil_create = null;
	private Button fil_read = null;
	private TextView fil_res = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.file_fil);
        fil_create = (Button) super.findViewById(R.id.fil_create);
        fil_read = (Button) super.findViewById(R.id.fil_read);
        fil_res = (TextView) super.findViewById(R.id.fil_res);
        fil_create.setOnClickListener(this);
        fil_read.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.fil_create){
			try{
				fName = OtherUtils.getLsh()+".txt";
				FileOutputStream fos = super.openFileOutput(fName,Activity.MODE_PRIVATE);
				PrintStream out = new PrintStream(fos);
		        out.println(SysConts.datao[0]);
		        out.println(SysConts.datao[1]);
		        out.println(SysConts.datao[2]);
		        out.close();
		        fos.close();
		        fil_res.setText("����Ӧ���ļ��ɹ�\n����:/data/data/"+super.getApplication().getPackageName()+"/files/"+fName);
			}catch(Exception e){
				fil_res.setText("����Ӧ���ļ�ʧ��");
				e.printStackTrace();
			}
		}else if(v.getId()==R.id.fil_read){
			try{
				FileInputStream fis = super.openFileInput(fName);
				Scanner sc = new Scanner(fis);
				StringBuffer sb = new StringBuffer();
		        while(sc.hasNext()){
		        	sb.append("--"+sc.next()+"\n");
		        }
		        sc.close();
		        fis.close();
		        fil_res.setText(sb.deleteCharAt(sb.length()-1).toString());
			}catch(Exception e){
				fil_res.setText("��ȡӦ���ļ�ʧ��!��ȷ��Ӧ���ļ��Ѵ���");
				e.printStackTrace();
			}
		}
	}
}