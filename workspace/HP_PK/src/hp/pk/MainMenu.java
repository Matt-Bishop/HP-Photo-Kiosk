package hp.pk;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
		this.setContentView(R.layout.mainscreen);
		      
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

