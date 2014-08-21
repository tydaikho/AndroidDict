package shd.andict.fragment;

import lmc.lonely.R;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment2 extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.shd_layout_fragment_fragment2, container, false);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Button button = (Button)getActivity().findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//////////////////////////
				//通过Activity获取另一个Fragment的信息
				TextView textView = (TextView)getActivity().findViewById(R.id.fragment1_text);
				Log.w("Fragment2", textView.getText().toString());
				Toast.makeText(getActivity(), textView.getText(), Toast.LENGTH_LONG).show();
			}
		});
	}
}
