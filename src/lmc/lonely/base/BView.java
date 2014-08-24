package lmc.lonely.base;
import lmc.lonely.R;
import android.app.Activity;
import android.os.Bundle;
public class BView extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.base_view);
    }
}