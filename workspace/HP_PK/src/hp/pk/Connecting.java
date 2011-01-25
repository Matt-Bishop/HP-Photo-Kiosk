package hp.pk;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Connecting extends Activity
{
	 private ImageButton cBtn;
	 @SuppressWarnings("unchecked")
	private ArrayAdapter mArrayAdapter;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.connecting);
		 
		 startActivity(new Intent(Connecting.this,iGallery.class));
		 
		 BluetoothAdapter bTooth = BluetoothAdapter.getDefaultAdapter();
		 if(bTooth == null)
		 {
			 System.out.println("Not Supported");
		 }
		 else
		 {
			 System.out.println("Bluetooth Supported");
			 if(!bTooth.isEnabled())
			 {
				 System.out.println("Bluetooth not Enabled");
				 Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				 startActivityForResult(i, 1);
			 }
			 bTooth.startDiscovery();
			
			 final BroadcastReceiver mReceiver = new BroadcastReceiver() 
			 {
			     @SuppressWarnings("unchecked")
				public void onReceive(Context context, Intent intent) 
			     {
			         String action = intent.getAction();
			        
			         if (BluetoothDevice.ACTION_FOUND.equals(action)) 
			         {
			             BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
			             mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
			         }
			     }
			 };
			 // Register the BroadcastReceiver
			 IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
			 registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
		 }
		 
		 		 
		 cBtn = (ImageButton) findViewById(R.id.widget30);
		 
		 this.cBtn.setOnClickListener(new Button.OnClickListener()
		 {
		     public void onClick(View v)
		     {
		    		finish(); 
		     }
		 });
	 }
	 
	 protected void OnActivityResult(int requestCode, int resultCode, Intent data)
	 {
		 super.onActivityResult(requestCode, resultCode, data);
	     
	     if (resultCode == RESULT_OK)
	         System.out.println("Bluetooth Sucessfully Enabled");
	     else
	    	 System.out.println("Bluetooth Not Enabled");
	 }
	 
	
}
