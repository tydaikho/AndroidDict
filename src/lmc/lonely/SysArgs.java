package lmc.lonely;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.content.Context;
import android.os.Environment;
public class SysArgs {
	//SD��·��
	public static String SD = Environment.getExternalStorageDirectory().getAbsolutePath();
	//Ӧ����ݿ�
	public static String dbName = "lonely.db";
	//Ӧ�������ļ�
	private static String appSet = "lonely.properties";
	//Ӧ����Ŀ¼
	private static String appHome = SysArgs.SD+"/Lonely/";
	//Ӧ�ñ���ɫ
	private static int bgColor = 0xFFFFCCFF;
	//��ҳģʽ 0:Ĭ�� 1:��״
	private static int mode = 0;
	//�Ƿ���ʾ��ӭ��
	private static boolean isShow = true;
	//http����ͷ��
	private static String urlHeader = "http://192.168.1.6:8080/android/";
	public static String getAppHome() {
		return appHome;
	}
	public static void setAppHome(String appHome) {
		SysArgs.appHome = appHome;
	}
	public static int getBgColor() {
		return bgColor;
	}
	public static void setBgColor(int bgColor) {
		SysArgs.bgColor = bgColor;
	}
	public static int getMode() {
		return mode;
	}
	public static void setMode(int mode) {
		SysArgs.mode = mode;
	}
	public static boolean isShow() {
		return isShow;
	}
	public static void setShow(boolean isShow) {
		SysArgs.isShow = isShow;
	}
	public static String getUrlheader() {
		return urlHeader;
	}
	public static void setUrlHeader(String urlHeader) {
		SysArgs.urlHeader = urlHeader;
	}
	//��ȡhttp����URL
	public static String getUrlLogin() {
		return urlHeader+"andr/andrdeal!login.do";
	}
	public static String getUrlEntity() {
		return urlHeader+"andr/andrdeal!entity.do";
	}
	public static String getUrlJson() {
		return urlHeader+"andr/andrdeal!json.do";
	}
	public static String getUrlJarray() {
		return urlHeader+"andr/andrdeal!jarray.do";
	}
	public static String getUrlServlet() {
		return urlHeader+"servlet/servdeal";
	}
	public static String getUrlImage() {
		return urlHeader+"servlet/imgdeal";
	}
	public static void init(){
		appHome = SysArgs.SD+"/Lonely/";
		bgColor = 0xFFFFCCFF;
		mode = 0;
		isShow = true;
		urlHeader = "http://192.168.1.6:8080/android/";
	}
	public static boolean save(Context con){
		try{
			Properties props = new Properties();
			props.put("apphome",SysArgs.getAppHome());
			props.put("backgrounp",String.valueOf(SysArgs.getBgColor()));
			props.put("mode",String.valueOf(SysArgs.getMode()));
			props.put("isshow",String.valueOf(SysArgs.isShow()));
			props.put("urlheader",SysArgs.getUrlheader());
			FileOutputStream fos = con.openFileOutput(appSet,Context.MODE_WORLD_WRITEABLE);
			props.store(fos,appSet);
			fos.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public static void read(Context con){
		try{
			Properties props = new Properties();
			FileInputStream fis = con.openFileInput(appSet);
			props.load(fis);
			SysArgs.setAppHome(props.getProperty("apphome"));
			SysArgs.setBgColor(Integer.parseInt(props.getProperty("backgrounp")));
			SysArgs.setMode(Integer.parseInt(props.getProperty("mode")));
			SysArgs.setShow(Boolean.valueOf(props.getProperty("isshow")));
			SysArgs.setUrlHeader(props.getProperty("urlheader"));
			fis.close();
		}catch(Exception e){
			SysArgs.init();
			e.printStackTrace();
		}
	}
}