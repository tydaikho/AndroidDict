package shd.andict.file;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import lmc.lonely.R;
import lmc.lonely.SysArgs;
import lmc.lonely.SysConts;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
public class FOper extends Activity {
	private String fName = "lonely.txt";
	private TextView oper_msg = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.file_oper);
        oper_msg = (TextView) super.findViewById(R.id.oper_msg);
        StringBuffer sb = new StringBuffer();
        sb.append("�Ƿ�װSD��:"+Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)+"\n");
        sb.append("SD�����·��:"+Environment.getExternalStorageDirectory().getAbsolutePath()+"\n");
        File app = new File(SysArgs.getAppHome());
        sb.append("Ӧ��Ŀ¼���·��:"+app.getPath()+"\n");
        sb.append("Ӧ��Ŀ¼���·��:"+app.getAbsolutePath()+"\n");
        sb.append("Ӧ��˽��Ŀ¼·��:"+super.getFilesDir()+"\n");
        try{
			FileOutputStream fos = super.openFileOutput(fName,Activity.MODE_APPEND);
			fos.write((System.currentTimeMillis()+SysConts.appName+"��ӭ��\n").getBytes());
			fos.flush();
			fos.close();
			sb.append("����Ӧ���ļ��ɹ�,����"+super.getFilesDir()+"/lonely.txt\n");
		}catch(Exception e){
			e.printStackTrace();
			sb.append("����Ӧ���ļ�ʧ��\n");
		}
        try{
			FileInputStream fis = super.openFileInput(fName);
			ByteArrayOutputStream bais = new ByteArrayOutputStream();
			byte[]data = new byte[1024];
			int len = 0;
			while((len=fis.read(data))!=-1){
				bais.write(data,0,len);
			}
			sb.append("��ȡӦ���ļ��ɹ�\n");
			sb.append(bais.toString());
			bais.close();
			fis.close();
		}catch(Exception e){
			e.printStackTrace();
			sb.append("��ȡӦ���ļ�ʧ��");
		}
        oper_msg.setText(sb.toString());
        oper_msg.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}