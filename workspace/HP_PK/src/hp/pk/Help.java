package hp.pk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


public class Help extends Activity
{
	private ImageButton cBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.helpscreen);
				
		cBtn = (ImageButton) findViewById(R.id.help_back);
		 
		this.cBtn.setOnClickListener(new Button.OnClickListener()
		{
		      public void onClick(View v)
		      {
		    		finish(); 
		      }
		});
	}
	
}
