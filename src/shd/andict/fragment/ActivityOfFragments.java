package shd.andict.fragment;

import lmc.lonely.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.Display;

public class ActivityOfFragments extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shd_layout_fragment_main);
		
		Display display = getWindowManager().getDefaultDisplay();
		if (display.getWidth() > display.getHeight()) {  
            Fragment1 fragment1 = new Fragment1();  
            Fragment2 fragment2 = new Fragment2();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_activity, fragment1);
            fragmentTransaction.add(R.id.main_activity, fragment2);
            fragmentTransaction.commit();
        } else {  
            Fragment1 fragment1 = new Fragment1();  
            getFragmentManager().beginTransaction().replace(R.id.main_activity, fragment1).commit();  
        }  
	}


}
