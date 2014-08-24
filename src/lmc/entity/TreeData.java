package lmc.entity;
import java.util.ArrayList;

import shd.andict.file.FApp;
import shd.andict.file.FJson;
import shd.andict.file.FLkFiles;
import shd.andict.file.FMp3;
import shd.andict.file.FOper;
import shd.andict.file.FProp;
import shd.andict.file.FRaw;
import shd.andict.file.FSd;
import shd.andict.file.FShalay;
import shd.andict.file.FShare;
import shd.andict.file.FXml;

import lmc.lonely.Help;
import lmc.lonely.Main;
import lmc.lonely.base.BAnimation1;
import lmc.lonely.base.BAnimation2;
import lmc.lonely.base.BAnimation3;
import lmc.lonely.base.BAnimation4;
import lmc.lonely.base.BAnimation5;
import lmc.lonely.base.BButton;
import lmc.lonely.base.BCheckBox1;
import lmc.lonely.base.BCheckBox2;
import lmc.lonely.base.BClock1;
import lmc.lonely.base.BClock2;
import lmc.lonely.base.BColor;
import lmc.lonely.base.BDialog;
import lmc.lonely.base.BEditText;
import lmc.lonely.base.BGalley1;
import lmc.lonely.base.BGalley2;
import lmc.lonely.base.BGalley3;
import lmc.lonely.base.BGalley4;
import lmc.lonely.base.BGalley5;
import lmc.lonely.base.BGalley6;
import lmc.lonely.base.BGalley7;
import lmc.lonely.base.BGalley8;
import lmc.lonely.base.BListView1;
import lmc.lonely.base.BListView2;
import lmc.lonely.base.BListView3;
import lmc.lonely.base.BListView4;
import lmc.lonely.base.BListView5;
import lmc.lonely.base.BListView6;
import lmc.lonely.base.BListView7;
import lmc.lonely.base.BMenu1;
import lmc.lonely.base.BMenu2;
import lmc.lonely.base.BMenu3;
import lmc.lonely.base.BPopup;
import lmc.lonely.base.BRadioButton;
import lmc.lonely.base.BSeekBar;
import lmc.lonely.base.BSpinner;
import lmc.lonely.base.BTabHost1;
import lmc.lonely.base.BTabHost2;
import lmc.lonely.base.BTabHost3;
import lmc.lonely.base.BTextView;
import lmc.lonely.base.BTouch;
import lmc.lonely.base.BView;
import lmc.lonely.db.DbParDo;
import lmc.lonely.db.DbSqliDo;
import lmc.lonely.http.HttpApacheImpl;
import lmc.lonely.http.HttpImgImpl;
import lmc.lonely.http.HttpNetImpl;
import lmc.lonely.lay.LayAbs;
import lmc.lonely.lay.LayComx;
import lmc.lonely.lay.LayFrame1;
import lmc.lonely.lay.LayFrame2;
import lmc.lonely.lay.LayLine1;
import lmc.lonely.lay.LayLine2;
import lmc.lonely.lay.LayRelat1;
import lmc.lonely.lay.LayRelat2;
import lmc.lonely.lay.LayTab1;
import lmc.lonely.lay.LayTab2;
import lmc.lonely.sys.SysApk;
import lmc.lonely.sys.SysBdc;
import lmc.lonely.sys.SysChgbg;
import lmc.lonely.sys.SysHsqp;
import lmc.lonely.sys.SysServ;
import lmc.lonely.sys.SysXtcxMain;
import lmc.lonely.thread.ThAjaxCheck;
import lmc.lonely.thread.ThAjaxDown;
import lmc.lonely.thread.ThBall;
import lmc.lonely.thread.ThDMain;
import lmc.lonely.thread.ThFjxc;
import lmc.lonely.thread.ThJumpMain;
import lmc.lonely.thread.ThMgr;
import lmc.lonely.thread.ThNhd;
import android.content.Context;
import android.content.Intent;
public class TreeData {
	private Context con = null;
	private ArrayList<TreeEle>nodes = null;
	private TreeEle id00 = null;
	private TreeEle id01 = null;
	private TreeEle id02 = null;
	private TreeEle id03 = null;
	private TreeEle id04 = null;
	private TreeEle id05 = null;
	private TreeEle id06 = null;
	private TreeEle id07 = null;
	private TreeEle id08 = null;
	public TreeData(Context con) {
		this.con = con;
		this.init();
	}
	private void init(){
		nodes = new ArrayList<TreeEle>();
		id00 = new TreeEle("00","返回默认模式",this.getIntent(Main.class));
		nodes.add(id00);
		id01 = new TreeEle("01","基本组件",true);
		TreeEle id0101 = new TreeEle("0101","Animation",true);
		id0101.addChild(new TreeEle("010101","Animation1",this.getIntent(BAnimation1.class)));
		id0101.addChild(new TreeEle("010102","Animation2",this.getIntent(BAnimation2.class)));
		id0101.addChild(new TreeEle("010103","Animation3",this.getIntent(BAnimation3.class)));
		id0101.addChild(new TreeEle("010104","Animation4",this.getIntent(BAnimation4.class)));
		id0101.addChild(new TreeEle("010105","Animation5",this.getIntent(BAnimation5.class)));
		id01.addChild(id0101);
		id01.addChild(new TreeEle("0102","Button",this.getIntent(BButton.class)));
		TreeEle id0103 = new TreeEle("0103","CheckBox",true);
		id0103.addChild(new TreeEle("010301","CheckBox1",this.getIntent(BCheckBox1.class)));
		id0103.addChild(new TreeEle("010302","CheckBox2",this.getIntent(BCheckBox2.class)));
		id01.addChild(id0103);
		TreeEle id0104 = new TreeEle("0104","Clock",true);
		id0104.addChild(new TreeEle("010401","Clock1",this.getIntent(BClock1.class)));
		id0104.addChild(new TreeEle("010402","Clock2",this.getIntent(BClock2.class)));
		id01.addChild(id0104);
		id01.addChild(new TreeEle("0105","Color",this.getIntent(BColor.class)));
		id01.addChild(new TreeEle("0106","Dialog",this.getIntent(BDialog.class)));
		id01.addChild(new TreeEle("0107","EditText",this.getIntent(BEditText.class)));
		TreeEle id0108 = new TreeEle("0108","Gallery",true);
		id0108.addChild(new TreeEle("010801","Gallery1",this.getIntent(BGalley1.class)));
		id0108.addChild(new TreeEle("010802","Gallery2",this.getIntent(BGalley2.class)));
		id0108.addChild(new TreeEle("010803","Gallery3",this.getIntent(BGalley3.class)));
		id0108.addChild(new TreeEle("010804","Gallery4",this.getIntent(BGalley4.class)));
		id0108.addChild(new TreeEle("010805","Gallery5",this.getIntent(BGalley5.class)));
		id0108.addChild(new TreeEle("010806","Gallery6",this.getIntent(BGalley6.class)));
		id0108.addChild(new TreeEle("010807","Gallery7",this.getIntent(BGalley7.class)));
		id0108.addChild(new TreeEle("010808","Gallery8",this.getIntent(BGalley8.class)));
		id01.addChild(id0108);
		TreeEle id0109 = new TreeEle("0109","ListView",true);
		id0109.addChild(new TreeEle("010901","ListView1",this.getIntent(BListView1.class)));
		id0109.addChild(new TreeEle("010902","ListView2",this.getIntent(BListView2.class)));
		id0109.addChild(new TreeEle("010903","ListView3",this.getIntent(BListView3.class)));
		id0109.addChild(new TreeEle("010904","ListView4",this.getIntent(BListView4.class)));
		id0109.addChild(new TreeEle("010905","ListView5",this.getIntent(BListView5.class)));
		id0109.addChild(new TreeEle("010906","ListView6",this.getIntent(BListView6.class)));
		id0109.addChild(new TreeEle("010907","ListView7",this.getIntent(BListView7.class)));
		id01.addChild(id0109);
		TreeEle id0110 = new TreeEle("0110","Menu",true);
		id0110.addChild(new TreeEle("011001","Menu1",this.getIntent(BMenu1.class)));
		id0110.addChild(new TreeEle("011002","Menu2",this.getIntent(BMenu2.class)));
		id0110.addChild(new TreeEle("011003","Menu3",this.getIntent(BMenu3.class)));
		id01.addChild(id0110);
		id01.addChild(new TreeEle("0111","Popup",this.getIntent(BPopup.class)));
		id01.addChild(new TreeEle("0112","Radio",this.getIntent(BRadioButton.class)));
		id01.addChild(new TreeEle("0113","SeekBar",this.getIntent(BSeekBar.class)));
		id01.addChild(new TreeEle("0114","Spinner",this.getIntent(BSpinner.class)));
		TreeEle id0115 = new TreeEle("0115","TabHost",true);
		id0115.addChild(new TreeEle("011501","TabHost1",this.getIntent(BTabHost1.class)));
		id0115.addChild(new TreeEle("011502","TabHost2",this.getIntent(BTabHost2.class)));
		id0115.addChild(new TreeEle("011503","TabHost3",this.getIntent(BTabHost3.class)));
		id01.addChild(id0115);
		id01.addChild(new TreeEle("0116","TextView",this.getIntent(BTextView.class)));
		id01.addChild(new TreeEle("0117","Touch",this.getIntent(BTouch.class)));
		id01.addChild(new TreeEle("0118","View",this.getIntent(BView.class)));
		nodes.add(id01);
		id02 = new TreeEle("02","视图布局",true);
		id02.addChild(new TreeEle("0201","绝对布局",this.getIntent(LayAbs.class)));
		id02.addChild(new TreeEle("0202","复杂布局",this.getIntent(LayComx.class)));
		TreeEle id0203 = new TreeEle("0203","框架布局",true);
		id0203.addChild(new TreeEle("020301","框架布局1",this.getIntent(LayFrame1.class)));
		id0203.addChild(new TreeEle("020302","框架布局2",this.getIntent(LayFrame2.class)));
		id02.addChild(id0203);
		TreeEle id0204 = new TreeEle("0204","线性布局",true);
		id0204.addChild(new TreeEle("020401","线性布局1",this.getIntent(LayLine1.class)));
		id0204.addChild(new TreeEle("020402","线性布局2",this.getIntent(LayLine2.class)));
		id02.addChild(id0204);
		TreeEle id0205 = new TreeEle("0205","相对布局",true);
		id0205.addChild(new TreeEle("020501","相对布局1",this.getIntent(LayRelat1.class)));
		id0205.addChild(new TreeEle("020502","相对布局2",this.getIntent(LayRelat2.class)));
		id02.addChild(id0205);
		TreeEle id0206 = new TreeEle("0206","表格布局",true);
		id0206.addChild(new TreeEle("020601","表格布局1",this.getIntent(LayTab1.class)));
		id0206.addChild(new TreeEle("020602","表格布局2",this.getIntent(LayTab2.class)));
		id02.addChild(id0206);
		nodes.add(id02);
		id03 = new TreeEle("03","文件操作",true);
		id03.addChild(new TreeEle("0301","属性文件",this.getIntent(FProp.class)));
		id03.addChild(new TreeEle("0302","共享文件",this.getIntent(FShare.class)));
		id03.addChild(new TreeEle("0303","共享布局",this.getIntent(FShalay.class)));
		id03.addChild(new TreeEle("0304","RAW",this.getIntent(FRaw.class)));
		id03.addChild(new TreeEle("0305","应用文件",this.getIntent(FApp.class)));
		id03.addChild(new TreeEle("0306","本地文件",this.getIntent(FSd.class)));
		id03.addChild(new TreeEle("0307","XML",this.getIntent(FXml.class)));
		id03.addChild(new TreeEle("0308","JSON",this.getIntent(FJson.class)));
		id03.addChild(new TreeEle("0309","浏览文件",this.getIntent(FLkFiles.class)));
		id03.addChild(new TreeEle("0310","MP3播放",this.getIntent(FMp3.class)));
		id03.addChild(new TreeEle("0311","文件操作",this.getIntent(FOper.class)));
		nodes.add(id03);
		id04 = new TreeEle("04","数据库",true);
		id04.addChild(new TreeEle("0401","SQLite",this.getIntent(DbSqliDo.class)));
		id04.addChild(new TreeEle("0402","继承实现",this.getIntent(DbParDo.class)));
		nodes.add(id04);
		id05 = new TreeEle("05","多线程与跳转",true);
		id05.addChild(new TreeEle("0501","数据传递",this.getIntent(ThDMain.class)));
		id05.addChild(new TreeEle("0502","霓虹灯",this.getIntent(ThNhd.class)));
		id05.addChild(new TreeEle("0503","滚动小球",this.getIntent(ThBall.class)));
		id05.addChild(new TreeEle("0504","异步下载",this.getIntent(ThAjaxDown.class)));
		id05.addChild(new TreeEle("0505","异步登录",this.getIntent(ThAjaxCheck.class)));
		id05.addChild(new TreeEle("0506","程序跳转",this.getIntent(ThJumpMain.class)));
		id05.addChild(new TreeEle("0507","附加线程",this.getIntent(ThFjxc.class)));
		id05.addChild(new TreeEle("0508","性能管理",this.getIntent(ThMgr.class)));
		nodes.add(id05);
		id06 = new TreeEle("06","系统服务",true);
		id06.addChild(new TreeEle("0601","更改背景",this.getIntent(SysChgbg.class)));
		id06.addChild(new TreeEle("0602","横竖切屏",this.getIntent(SysHsqp.class)));
		id06.addChild(new TreeEle("0603","系统程序",this.getIntent(SysXtcxMain.class)));
		id06.addChild(new TreeEle("0604","安装卸载",this.getIntent(SysApk.class)));
		id06.addChild(new TreeEle("0605","广播机制",this.getIntent(SysBdc.class)));
		id06.addChild(new TreeEle("0606","应用服务",this.getIntent(SysServ.class)));
		nodes.add(id06);
		id07 = new TreeEle("07","网络",true);
		id07.addChild(new TreeEle("0701","Apache",this.getIntent(HttpApacheImpl.class)));
		id07.addChild(new TreeEle("0702","上传下载",this.getIntent(HttpImgImpl.class)));
		id07.addChild(new TreeEle("0703","Net接口",this.getIntent(HttpNetImpl.class)));
		nodes.add(id07);
		id08 = new TreeEle("08","赞助开发者",this.getIntent(Help.class));
		nodes.add(id08);
	}
	private Intent getIntent(Class<?>c){
		Intent it=new Intent();
		it.setClass(con,c);
		return it;
	}
	public ArrayList<TreeEle>getDataSource(){
		if(nodes==null||nodes.size()<=0){
			this.init();
		}
		return nodes;
	}
}