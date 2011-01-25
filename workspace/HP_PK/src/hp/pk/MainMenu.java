package hp.pk;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import android.content.Intent;

public class MainMenu extends Activity
{
	private ImageButton cBtn;
	private ImageButton hBtn;
	private ImageButton eBtn;
	   
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
			  
		super.onCreate(savedInstanceState);
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay(); 
		int orientation = display.getOrientation();  
		if(orientation == 0 )
			this.setContentView(R.layout.mainscreen);
		else
			this.setContentView(R.layout.mainscreen_l);
		cBtn = (ImageButton) this.findViewById(R.id.connect);
					      
		cBtn.setOnClickListener(new Button.OnClickListener()
		{
		    	  public void onClick(View v)
		    	  {
		    		 startActivity(new Intent(MainMenu.this,Connecting.class)); 
		    	  }
		});
		
		hBtn = (ImageButton) this.findViewById(R.id.help);
		
		hBtn.setOnClickListener(new Button.OnClickListener()
		{
		    	 public void onClick(View v)
		    	 {
		    		 startActivity(new Intent(MainMenu.this,Help.class)); 
		    	 }
		});
		
		eBtn = (ImageButton) this.findViewById(R.id.exit);
		
		eBtn.setOnClickListener(new Button.OnClickListener()
		{
		    	  public void onClick(View v)
		    	  {
		    		 finish(); 
		    	  }
		});  
		
	}
		   
}

