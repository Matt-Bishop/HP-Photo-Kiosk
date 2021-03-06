package hp.pk;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter
{
	private String[] selectedIMGs;
	private Context myContext;
	
	public ImageAdapter(Context c,String[] IMGs)
	{
		this.myContext=(Context) c;
		/*Sync array memory pointer locations*/ 
		selectedIMGs= IMGs;
	}
		
	@Override
	public int getCount()
	{
		return selectedIMGs.length;
	}

	@Override
	public Object getItem(int position)
	{
		return position;
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	public float getScale(boolean focused, int offset)
	{
		return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset)));
	}
	 
	public View getView(int position, View convertView, ViewGroup parent) 
	{
	    ImageView i = new ImageView(this.myContext);
	         
	    BitmapFactory.Options options = new BitmapFactory.Options();
	    /*Scale Options to reduce memory usage for thumbnail preview*/
	    options.inSampleSize = 2;
	    options.inPurgeable=true;
	    options.inScaled=true;
	        
	    Bitmap myBitmap = BitmapFactory.decodeFile(selectedIMGs[position],options);
	         
	 // System.out.println("Bitmap Grabbed ="+ myBitmap);
	         
	    i.setImageBitmap(myBitmap);
	    i.setScaleType(ImageView.ScaleType.FIT_XY);
	    i.setLayoutParams(new Gallery.LayoutParams(150, 150));
	         
	    return i;
     }


}
