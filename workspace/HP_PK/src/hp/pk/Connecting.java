package hp.pk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Connecting extends Activity
{
	 private ImageButton cBtn;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.connecting);
		 
		 startActivity(new Intent(Connecting.this,iGallery.class));
		 
		 cBtn = (ImageButton) findViewById(R.id.widget30);
		 
		 this.cBtn.setOnClickListener(new Button.OnClickListener()
		 {
		     public void onClick(View v)
		     {
		    		finish(); 
		     }
		 });
	 }
	 
}