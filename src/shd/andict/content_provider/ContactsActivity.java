package shd.andict.content_provider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import lmc.lonely.R;


import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.PhoneLookup;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactsActivity extends Activity
{
	Context mContext = null;

	
	/**获取Phone表字段**/
	private static final String[] PHONES_PROJECTION = new String[]{
		Phone.DISPLAY_NAME,
		Phone.NUMBER,
		Phone.PHOTO_ID,
		Phone.CONTACT_ID
	};
	
	/**Phone表 Index**/
    /**联系人显示名称**/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    
    /**电话号码**/
    private static final int PHONES_NUMBER_INDEX = 1;
    
    /**头像ID**/
    private static final int PHONES_PHOTO_ID_INDEX = 2;
   
    /**联系人的ID**/
    private static final int PHONES_CONTACT_ID_INDEX = 3;
    
    /**存储Contact数据的Lists**/
    List<String> mContactsName = new ArrayList<String>();
    List<String> mContactsNumber  = new ArrayList<String>();
    List<Bitmap> mContactsPhoto = new ArrayList<Bitmap>();
	
    /*
     * 获取手机通信录的内容
     * */
	public void getPhoneContacts() {
		//取得ContentResolver对象
		ContentResolver cr = getContentResolver();
			//获取电话本中开始第一项的光标
			Cursor phoneContactCursor = cr.query(Phone.CONTENT_URI, PHONES_PROJECTION, null,null,null);
			
			if(phoneContactCursor!=null){
				//移动向下一光标
				while(phoneContactCursor.moveToNext())
				{
					//取得联系人名字
					String contactName = phoneContactCursor.getString(PHONES_DISPLAY_NAME_INDEX);
					//取得电话号码
					String number = phoneContactCursor.getString(PHONES_NUMBER_INDEX);
					//头像ID
					Long photoId = phoneContactCursor.getLong(PHONES_PHOTO_ID_INDEX);
					//联系人ID
					Long contactId = phoneContactCursor.getLong(PHONES_CONTACT_ID_INDEX);
					
					/*获取联系人头像Bitmap*/
					Bitmap contactBitmap = null;
					//photoId大于0表示联系人有头像，如果没有则给一个默认头像
					if(photoId>0){
						//获取联系人头像
						Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
						InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(cr, uri);
						contactBitmap = BitmapFactory.decodeStream(inputStream);
					}
					else {
						//给予默认头像，需要编译前要手动添加
						contactBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ico_logo);
					}
					
					//存储到全局变量
					mContactsName.add(contactName);
					mContactsNumber.add(number);
					mContactsPhoto.add(contactBitmap);

				}
			}
			
			phoneContactCursor.close();

	}
    
	public void onCreate(Bundle savedInstanceState)
	{
		mContext = this;
		//TextView tv = new TextView(this);
		String string = "";		
		ListView ls = new ListView(mContext);
		
		super.onCreate(savedInstanceState);	
        
        getPhoneContacts();
        
		//得到ContentResolver对象
        ContentResolver cr = getContentResolver();  
        try {
	        //取得电话本中开始一项的光标
	        //Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	        Cursor cursor = cr.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
	        //向下移动一下光标
	        while(cursor.moveToNext()) 
	        { 
	        	/*
	        	//取得联系人名字
	        	int nameFieldColumnIndex = cursor.getColumnIndex(PhoneLookup.DISPLAY_NAME);     
	        	String contactName = cursor.getString(nameFieldColumnIndex); 
	        	*/
	        	String contactName = cursor.getString(PHONES_DISPLAY_NAME_INDEX);
	        	
	        	//取得电话号码
	        	//int numberFieldColumnIndex = cursor.getColumnIndex(PhoneLookup.NUMBER);//这个index是错误的。
	        	String number = cursor.getString(PHONES_NUMBER_INDEX);

	        	string += (contactName+":"+number+"\n");
	        }
	        cursor.close();
		} catch (Exception e) {
			Log.w("getContacts","Exception e");
		}
        

		//设置TextView显示的内容
		//tv.setText(string);
		//显示到屏幕
		//setContentView(tv);
        
        ListAdapter adapter = new ContactListAdapter(mContext);
        ls.setAdapter(adapter);
        setContentView(ls);
	}

	class ContactListAdapter extends BaseAdapter{

		public ContactListAdapter(Context context){
			mContext = context;
		}
		
		@Override
		public int getCount() {
			return mContactsName.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
		    ImageView iamge = null;
		    TextView title = null;
		    TextView text = null;
		    if (convertView == null) {
				convertView = LayoutInflater.from(mContext).inflate(
					R.layout.colorlist, null);
		    }
			iamge = (ImageView) convertView.findViewById(R.id.color_image);
			title = (TextView) convertView.findViewById(R.id.color_title);
			text = (TextView) convertView.findViewById(R.id.color_text);
		    //绘制联系人名称
		    title.setText(mContactsName.get(position));
		    //绘制联系人号码
		    text.setText(mContactsNumber.get(position));
		    //绘制联系人头像
		    iamge.setImageBitmap(mContactsPhoto.get(position));
		    return convertView;
		}
		
	}
}
