package lmc.lonely.lay;
import lmc.lonely.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
public class LayFrame1 extends Activity implements OnClickListener {
	private ImageButton frame_next = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.lay_frame);
        frame_next = (ImageButton) super.findViewById(R.id.frame_next);
        frame_next.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.frame_next){
			this.startActivity(new Intent(this,LayFrame2.class));
		}
	}
}