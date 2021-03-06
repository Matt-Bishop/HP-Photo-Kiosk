package hp.pk;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class HP_PK extends Activity 
{
		  
		   @Override
		   public void onCreate(Bundle savedInstanceState) 
		   {
		      super.onCreate(savedInstanceState);
		      setContentView(R.layout.splash);
		      /*Splash needs its on Thread in-case we want to do things here someday*/
		      Thread splashThread = new Thread() 
		      {
		         @Override
		         public void run()
		         {
		            try
		            {
		               int waited = 0;
		               while (waited < 2000)
		               {
		                  sleep(100);
		                  waited += 100;
		               }
		            }
		            catch (InterruptedException e) 
		            {
		               /* Catch an Exception but I'm Lazy so its blank and I mean come'on what could really happen here */
		            } 
		            finally 
		            {
		               /* 'DING!', Splash is done, start Activity */
		            	startActivity(new Intent(HP_PK.this, MainMenu.class));
		            	finish();
		              }
		         }
		      };
		     splashThread.start();
		 }
}



