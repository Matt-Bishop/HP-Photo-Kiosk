package hp.pk;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;


public class SelectList extends ListActivity
{
	// private ArrayAdapter mArrayAdapter;
	 private Context myContext;
	 private String [] list;
	 
	 private static final String [] testList = new String[]{ "TEST1","TEST2"};
	 public static String EXTRA_DEVICE_ADDRESS = "device_address";
	 public static String selDevice;
	 public SelectList()
	 {
		// System.out.println("Shouldn't be here");
	 }
	/* public void initSelectList(Context c, ArrayAdapter mArrayAdapter )
	 {
		 this.myContext = (Context)c;
		 System.out.println("HERE");
		 mArrayAdapter = mArrayAdapter;
		 list = new String [ mArrayAdapter.getCount() ];
		 System.out.println("array is of size"+mArrayAdapter.getCount());
		 for(int count = 0 ; count< mArrayAdapter.getCount();count++)
		 {
			 list[count] = (String) mArrayAdapter.getItem(count);
			 System.out.println("list is "+list[count]);
		 }
	 }*/
	 public void printList()
	 {
		 System.out.println("array is of size"+Connecting.mArrayAdapter.getCount());
		 list = new String [ Connecting.mArrayAdapter.getCount() ];
		 for(int count = 0 ; count < Connecting.mArrayAdapter.getCount() ; count++ )
		 {
			 System.out.println("Count is "+count);
			 System.out.println("Array Adapter is "+Connecting.mArrayAdapter.getItem(count));
			 list[count] = (String) Connecting.mArrayAdapter.getItem(count);
			 System.out.println("list is "+list[count]);
		 }
	 }
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 System.out.println("*/**/**/*/Inside Select List*/***/*/*/**/*");
		 if(Connecting.mArrayAdapter != null)
		 {
			// System.out.println("array is of size"+Connecting.mArrayAdapter.getCount());
			 printList();
		 }
		 else
			 System.out.println("Array is null");
		 
		 //printList();
		 System.out.println("Starting Layout");
		 // setContentView(R.layout.selectlist);
		 
		 setListAdapter(new ArrayAdapter<String>(this, R.layout.selectlist, list ) );
		 
		 ListView lv = getListView();
		 lv.setTextFilterEnabled(true);
		 
		 lv.setOnItemClickListener(
				 new OnItemClickListener()
				 {
					 public void onItemClick(AdapterView<?> parent, View view,int position,long id)
					 {
						 System.out.println("Selected "+((TextView) view).getText());
						 selDevice = (String) ((TextView) view).getText().toString();
						 selDevice = selDevice.substring(selDevice.length() - 17);
						 
						 System.out.println("SENDING "+selDevice+" TO activity");
						 Intent intent = new Intent();
				         intent.putExtra(EXTRA_DEVICE_ADDRESS, selDevice);
						 
						 setResult(Activity.RESULT_OK,intent);
						 finish();
					 }
				 } );
	 	}
	 }

