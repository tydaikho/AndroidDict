package lmc.lonely.http;
import lmc.lonely.R;
import lmc.lonely.SysArgs;
import lmc.lonely.SysConts;
import lmc.utils.OtherUtils;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
public class HttpImgImpl extends Activity implements OnClickListener {
	private Button updown_dbyres = null;
	private Button updown_dbydata = null;
	private Button updown_upimg = null;
	private MainThreadHandler mainThreadHandler = new MainThreadHandler();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
			
			
        super.setContentView(R.layout.http_updown);
        Toast.makeText(this,"先安装运行"+SysConts.appName+"服务端并修改 设置->连接后台 的网址",Toast.LENGTH_SHORT).show();
        updown_dbyres = (Button) super.findViewById(R.id.updown_dbyres);
        updown_dbydata = (Button) super.findViewById(R.id.updown_dbydata);
        updown_upimg = (Button) super.findViewById(R.id.updown_upimg);
        updown_dbyres.setOnClickListener(this);
        updown_dbydata.setOnClickListener(this);
        updown_upimg.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.updown_dbyres){
			String url = SysArgs.getUrlheader()+"res/imgs/beauty.jpg";
			String path = SysArgs.getAppHome()+OtherUtils.getLsh()+".jpg";
			Toast.makeText(this,HttpImgFac.download(url,path)?"图片下载成功,存于\n"+path:"图片下载失败",Toast.LENGTH_SHORT).show();
		}else if(v.getId()==R.id.updown_dbydata){
			String url = SysArgs.getUrlImage()+"?type=download";
			String path = SysArgs.getAppHome()+OtherUtils.getLsh()+".jpg";
			Toast.makeText(this,HttpImgFac.download(url,path)?"图片下载成功,存于\n"+path:"图片下载失败",Toast.LENGTH_SHORT).show();
		}else if(v.getId()==R.id.updown_upimg){
			/*
			ArrayList<String>imgs = OtherUtils.getSdFile(SysArgs.SD,SysConts.fms);
			String imgToUpload = imgs.get(0);
			*/ 
			
			
			Thread thread = new Thread(new Runnable() {
				

				@Override
				public void run() {
					String imgToUpload = "/storage/emulated/0/Samsung/Image/001.JPG";
					String urlImage = SysArgs.getUrlImage();
					boolean isUploadOK = HttpImgFac.upload(urlImage, imgToUpload);
					
					Message msg = new Message();
					Bundle bundle = new Bundle();
					bundle.putBoolean("UploadStat", isUploadOK);
					msg.setData(bundle);
					HttpImgImpl.this.mainThreadHandler.sendMessage(msg);
				}
			});
			
			thread.start();
			
		}
	}
	
	
	class MainThreadHandler extends Handler{
			public MainThreadHandler(){
				
			}
			
			public MainThreadHandler(Looper L){
				super(L);
			}
		
		
			@Override
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				Bundle bundle = msg.getData();
				boolean uploadStat = bundle.getBoolean("UploadStat");
				Toast.makeText(HttpImgImpl.this,uploadStat?"图片上传成功":"图片不可读或上传失败",Toast.LENGTH_SHORT).show();
				
			};
		
	}


}