package hp.pk;

import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Uploading extends Activity
{
	private ImageButton cBtn;
	
	private BluetoothAdapter mBluetoothAdapter = null;
	  BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(SelectList.selDevice);
	  BluetoothSocket tmp = null;
	  final BluetoothSocket mmSocket = null;
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.uploading);
		 	 
		 cBtn = (ImageButton) findViewById(R.id.up_cancel);
		 
		 try {
             tmp = device.createRfcommSocketToServiceRecord(Connecting.MY_UUID);
             tmp.connect();
             System.out.println("Connected to" +tmp.getRemoteDevice());
             OutputStream temp = tmp.getOutputStream();
            // temp.
             
             
         } catch (IOException e) {
             System.out.println("CAN't Connect"+e);
         }
        // mmSocket = tmp;
		 
		 
		 this.cBtn.setOnClickListener(new Button.OnClickListener()
		 {
		     public void onClick(View v)
		     {
		    		finish(); 
		     }
		 });
	 }
	 
}
	 