package lmc.lonely.sys;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lmc.lonely.R;
import lmc.lonely.SysArgs;
import lmc.lonely.SysConts;
import lmc.utils.OtherUtils;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class SysApk extends Activity implements OnClickListener {
	private Button apk_install = null;
	private Button apk_uninstall = null;
	private TextView apk_info = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.sys_apk);
        apk_install = (Button) super.findViewById(R.id.apk_install);
        apk_uninstall = (Button) super.findViewById(R.id.apk_uninstall);
        apk_info = (TextView) super.findViewById(R.id.apk_info);
        apk_install.setOnClickListener(this);
        apk_uninstall.setOnClickListener(this);
		apk_info.setMovementMethod(ScrollingMovementMethod.getInstance());
		this.init();
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.apk_install){
			ArrayList<String>apks = OtherUtils.getSdFile(SysArgs.SD,new String[]{"lonely.apk"});
			if(apks.size()==0){
				Toast.makeText(this,SysConts.appName+"��װ��Lonely.apk������",Toast.LENGTH_SHORT).show();
				return;
			}
			Intent it = new Intent(Intent.ACTION_VIEW);
			it.setDataAndType(Uri.fromFile(new File(apks.get(0))),"application/vnd.android.package-archive");
			this.startActivity(it);
		}else if(v.getId()==R.id.apk_uninstall){
			Uri uri = Uri.parse("package:"+super.getApplication().getPackageName());
			this.startActivity(new Intent(Intent.ACTION_DELETE,uri));
		}
	}
	private void init(){
		List<PackageInfo>pgs = super.getPackageManager().getInstalledPackages(0);
        StringBuffer sb = new StringBuffer("");
        sb.append("--------��ǰϵͳ����--------\n");
        sb.append("��Ʒ�ͺ�:"+android.os.Build.MODEL+"\n");
        sb.append("�汾��:"+android.os.Build.VERSION.RELEASE+"\n");
        sb.append("�汾��:"+android.os.Build.VERSION.SDK_INT+"\n");
        sb.append("SDK�汾��:"+android.os.Build.VERSION.SDK+"\n\n");
        try{
            PackageInfo currInfo = super.getPackageManager().getPackageInfo(super.getPackageName(),0);
            sb.append("--------��ǰӦ����Ϣ---------\n");
            sb.append("Ӧ����:"+currInfo.applicationInfo.loadLabel(super.getPackageManager()).toString()+"\n");
			sb.append("����:"+currInfo.packageName+"\n");
			sb.append("�汾��:"+currInfo.versionCode+"\n");
			sb.append("�汾��:"+currInfo.versionName+"\n");
			sb.append("Logo���:"+currInfo.applicationInfo.loadIcon(super.getPackageManager()).getMinimumWidth()+"\n");
			sb.append("�Ƿ�ΪϵͳӦ��:"+((currInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0?"��\n\n":"��\n\n"));
		}catch(Exception e){
			e.printStackTrace();
		}
        sb.append("--------����Ӧ����Ϣ--------\n");
		for(int i=0;i<pgs.size();i++){
			PackageInfo info = pgs.get(i);
			sb.append("Ӧ����:"+info.applicationInfo.loadLabel(super.getPackageManager()).toString()+"\n");
			sb.append("����:"+info.packageName+"\n");
			sb.append("�汾��:"+info.versionCode+"\n");
			sb.append("�汾��:"+info.versionName+"\n");
			sb.append("Logo���:"+info.applicationInfo.loadIcon(super.getPackageManager()).getMinimumWidth()+"\n");
			sb.append("�Ƿ�ΪϵͳӦ��:"+((info.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0?"��\n\n":"��\n\n"));
		}
		sb.append("��"+pgs.size()+"��Ӧ��");
		apk_info.setText(sb.toString());
	}
}