package hp.pk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

public class Connecting extends Activity
{
	private SelectList sList;
	private BluetoothAdapter mBluetoothAdapter = null;
	// Intent request codes
    private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;
    public static final UUID MY_UUID = UUID.fromString("426e5474-bfbe-460f-a352-b11748142278");
	 private ImageButton cBtn;
	 @SuppressWarnings("unchecked")
	public static ArrayAdapter mArrayAdapter =null;

	 
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.connecting);
		 
		 mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		 cBtn = (ImageButton) findViewById(R.id.widget30);
		 
		 if(mBluetoothAdapter == null)
		 {
			 System.out.println("Bluetooth Not Supported");
			 finish();
		 }
		 
		 this.cBtn.setOnClickListener(new Button.OnClickListener()
		 {
		     public void onClick(View v)
		     {
		    		finish(); 
		     }
		 });
	 }
	 @Override
	    public void onStart() {
	        super.onStart();
	        System.out.println("**************** ");
	        
	        //System.out.println("**************** "+mBluetoothAdapter.getName()+" ***********************");
	        
	      if (!mBluetoothAdapter.isEnabled()) {
	            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
	      }
	      else
	      {
	    	  if(mArrayAdapter == null)
	    	  {
	    		  
	    	  
			      mBluetoothAdapter.startDiscovery();
			      mArrayAdapter = new ArrayAdapter(this, 0);
					 final BroadcastReceiver mReceiver = new BroadcastReceiver() 
					 {
					     @SuppressWarnings("unchecked")
						public void onReceive(Context context, Intent intent) 
					     {
					    	 System.out.println("***************");
					         String action = intent.getAction();
					        
					         if (BluetoothDevice.ACTION_FOUND.equals(action)) 
					         {
					        	 System.out.println("&&&&&&&Action Found&&&&&&&");
					             BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					             System.out.println("I found "+ device.getName() + " Adding to arayAdapter");
					             mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
					            
					         }
					         else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
					         {
					        	 System.out.println("Discovery Finished");
					        	 temp();
					         }
					         System.out.println("Array Adapter Is "+mArrayAdapter.getCount());
					         System.out.println("Array has "+mArrayAdapter.getItem(0));
					         
					        
					     }
					     
					 };
					 
					 // Register the BroadcastReceiver
					 IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
					 registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
					 filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
				     this.registerReceiver(mReceiver, filter);
					 
					 
					// mBluetoothAdapter.cancelDiscovery();
	    	  }
	    	  else
	    	  {
	    		  if(SelectList.selDevice != null)
	    		  {
	    		  //FIX LATER (We don't actually want to connect in this spot anyways.......connect part works tho)
	    		 System.out.println("Forcing Connect to "+SelectList.selDevice+" -- Something's wrong here, I should have returned to result Activity  *AL-WELL*");
	    		
	              // Get a BluetoothSocket for a connection with the
	              // given BluetoothDevice
	              
	    		  	startActivity(new Intent(Connecting.this,iGallery.class));
	    		  }
	    		  else
	    			  System.out.println("CRAAAAAASSSSSSSHHHHHHHH");
	    	  }
	      }
	    }
	 private void temp()
	 {
		 System.out.println("!!!!!!!!!!!!!! I'm Done Here");
		 System.out.println("Preparing new Activity ");
		 //startActivity(new Intent( Connecting.this ,SelectList.class ) );
		 //sList.initSelectList(Connecting.this, mArrayAdapter);
		 
		 Intent i =  new Intent(this, SelectList.class);
		 
		 startActivityForResult(i, REQUEST_CONNECT_DEVICE);
		 
		/* BluetoothDevice device;
		 if(SelectList.selDevice != null)
		 {
			 System.out.println("GET REMOTE DEVICE");
			 device = mBluetoothAdapter.getRemoteDevice(SelectList.selDevice);
		 }*/
		 
	 }
	 protected void OnActivityResult(int requestCode, int resultCode, Intent data)
	 {
		 System.out.println("&*&*(&(*&( ONACTIVITY CALLED");
		 super.onActivityResult(requestCode, resultCode, data);
	     switch(requestCode)
	     {
	     	case REQUEST_CONNECT_DEVICE:
	     		//This case is broken I think?
	     		if (resultCode == Activity.RESULT_OK) {
	                // Get the device MAC address
	     			System.out.println("Request Connect Done::::::::;;");
	             //   String address = data.getExtras()
	             //                        .getString(SelectList.EXTRA_DEVICE_ADDRESS);
	                // Get the BLuetoothDevice object
	     			//if(SelectList.selDevice != null)
	     			//	BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(SelectList.selDevice);
	                // Attempt to connect to the device
	     		}      
	     		return;
	     	case REQUEST_ENABLE_BT:
		     	if (resultCode == Activity.RESULT_OK)
		     		System.out.println("Bluetooth Sucessfully Enabled");
		     	else
		     	{
		     		System.out.println("Bluetooth Not Enabled");
		     		finish();
		     	}
		     	return;
		     
	     
	     }
	    
	 }
	 
	
}
