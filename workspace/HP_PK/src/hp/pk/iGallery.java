package hp.pk;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;

public class iGallery extends Activity
{
	private ImageButton upBtn;
	private ImageButton cBtn;
	private ImageButton addBtn;
	private Uri chosenImageUri;
	private Gallery gallery;
	private ImageAdapter ImgAdp;
	private int picNum = -1;
	/*Decide On Initial Array length or use something  dynamic 
	 * (probably would be better than my resize/copy), memory
	 * may become a problem with either solution*/
	private String[] selectedIMGs = new String[30];
	
	 @Override
	 public void onCreate(Bundle savedInstanceState)
	 {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.gallery);
		
		 upBtn = (ImageButton) findViewById(R.id.kisokupload);
		 
		 this.upBtn.setOnClickListener(new Button.OnClickListener()
		 {
		     public void onClick(View v)
		     {
		    	 startActivity(new Intent(iGallery.this,Uploading.class));
		     }
		 });
		 
		 cBtn = (ImageButton) findViewById(R.id.uploadcancel);
		 
		 this.cBtn.setOnClickListener(new Button.OnClickListener()
		 {
		     public void onClick(View v)
		     {
		    	 /*Return User to Menu with all data Cleared*/
		    	 Intent i = new Intent(iGallery.this,MainMenu.class);
		    	 i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
		    	 startActivity(i);
		     }
		 });
		 
		 addBtn = (ImageButton) findViewById(R.id.add);
		 
		 this.addBtn.setOnClickListener(new Button.OnClickListener()
		 {
		     public void onClick(View v)
		     {
		    	 	picNum++;
		    	 	Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
		    	 	photoPickerIntent.setType("image/*");
		    	 	startActivityForResult(photoPickerIntent, 1);
		      	 	 
			   		gallery = (Gallery) findViewById(R.id.igallery);
			   		ImgAdp = new ImageAdapter(iGallery.this,selectedIMGs);
			   		gallery.setAdapter(ImgAdp);
			   				   		
		      }
	     });
		 
	 }
	 
	 protected void onActivityResult(int requestCode, int resultCode, Intent data)
	 {
		 
	     super.onActivityResult(requestCode, resultCode, data);
	     
	     if (resultCode == RESULT_OK)
	         chosenImageUri = data.getData();
	     else
	    	 System.out.println("Result Failed");
	     if(chosenImageUri == null)
			System.out.println("No Image Found");
	     else
	     {
			System.out.println("Image Found");
			
			if(picNum>selectedIMGs.length-1)
				selectedIMGs= (String[]) resizeArray(selectedIMGs,10);
			
			selectedIMGs[picNum]= data.getData().getPath().toString();
			Cursor c = managedQuery(chosenImageUri,null,null,null,null);
			if (c!=null && c.moveToFirst())
			{ 
                String column1Value = c.getString(1); 
            //    System.out.println("Display name"+column1Value);
                selectedIMGs[picNum]=column1Value;
			} 
			
		//  System.out.println("Path to Image = "+data.getData().getPath().toString());
		//  System.out.println("Array Length is "+selectedIMGs.length);
			
	     }
	     
	 //  System.out.println("You have "+picNum+" Selected");
	     /*for(int count=0;count<selectedIMGs.length;count++)
	     {
			System.out.println("Images Selected are : "+selectedIMGs[count]);
	     }*/
	     
	     /*Important to refresh preview gallery*/
	     ImgAdp.notifyDataSetChanged();
	 }
	 
	@SuppressWarnings("unchecked")
	private static Object resizeArray (Object oldArray, int nSize)
    {
		  int oSize = java.lang.reflect.Array.getLength(oldArray);
		  Class elementType = oldArray.getClass().getComponentType();
		  Object newArray = java.lang.reflect.Array.newInstance(elementType,nSize);
		  int preserveLength = Math.min(oSize,nSize);
		  if (preserveLength > 0)
			  System.arraycopy (oldArray,0,newArray,0,preserveLength);
		  return newArray; 
	}
	 
}




