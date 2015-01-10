package com.android2ee.recyclerview.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.recyclerview.R;
import com.android2ee.recyclerview.adapter.model.ImageModel;

/**
 * 
 * @author florian
 * RecyclerComplexViewAdapter provides and adpater custom of RecyclerView.Adapter
 * which can display a footer and header on start and end of list
 * to add and remove item you must be use function addItem and removeItem provide by this class
 * 
 */
public class RecyclerComplexViewAdapter extends RecyclerView.Adapter<RecyclerComplexViewAdapter.ViewHolder> {

	// type Header View
	private static final int TYPE_HEADER = 0;
	// type Footer View
	private static final int TYPE_FOOTER = 1;
	// Type standard
	private static final int TYPE_ADAPTERVIEW = 2;

	// list items
	public List<ImageModel> mItems;
	// Context
	Context mContext;
	
	// Resource Id of HeaderView
	Integer mViewHeader;
	// Resource Id of FooterView
	Integer mViewFooter;
	
	/**
	 * Constructor RecyclerComplexViewAdapter
	 * @param context : the context
	 * @param objects : the list of items
	 */
	public RecyclerComplexViewAdapter(Context context,List<ImageModel> objects) {
		mContext = context;
		mItems = objects;
		
		// set null header and footer by default
		mViewHeader = null;
		mViewFooter = null;

	}
	
	/**
	 * Constructor RecyclerComplexViewAdapter
	 * @param context : the context
	 * @param objects : the list items
	 * @param viewHeaderId : the header resource id
	 * @param viewFooterId : the footer resource id
	 */
	public RecyclerComplexViewAdapter(Context context,List<ImageModel> objects, Integer viewHeaderId, Integer viewFooterId) {
		mContext = context;
		mItems = objects;
		
		mViewHeader = viewHeaderId;
		mViewFooter = viewFooterId;

	}
	
	
	/**
	 * 
	 * @author florian
	 * Class viewHolder
	 * Hold an imageView and textView
	 */
	static class ViewHolder extends RecyclerView.ViewHolder{
		// The imageView
		public  ImageView mImageView;
		// the TextView
		public  TextView mTextView;
		// the rootView
		public View rootView;
		
		/**
		 * Constructor
		 * @param itemView
		 */
		public ViewHolder(View itemView) {
			super(itemView);
			// set RootView
			rootView = itemView;
			// link imageView
			mImageView =(ImageView)itemView.findViewById(R.id.image);
			// link TextView
			mTextView =(TextView)itemView.findViewById(R.id.title);
		}
	}
	
	/**
	 * Test if a Header resource is present
	 * @return if a resource is present
	 */
	private boolean useHeader() {
		return mViewHeader != null;
	}
	
	/**
	 * Test if a Footer resource is present
	 * @return if a resource is present
	 */
	private boolean useFooter() {
		return mViewFooter != null;
	}

	/**
	 * Get the size of items in adapter
	 * @return the size of items in adapter
	 */
	@Override
	public int getItemCount() {
		// get the size of items
		int count = mItems.size();
		// if header uses add 1
		if (useHeader()) {
			count ++;
		}
		// if footer uses add 1
		if (useFooter()) {
			count++;
		}
		return count;
	}

	/**
	 * Bind View Holder with Items 
	 * @param holder: the view holder
	 * @param position : the current position
	 */
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		// test type of holder
		if (holder.getItemViewType() == TYPE_HEADER) {
			// nothing
			Log.i("TAG", "POSITION TYPE_HEADER " + position);
		} else if (holder.getItemViewType() == TYPE_FOOTER) {
			// nothing
			Log.i("TAG", "POSITION TYPE_FOOTER " + position);
        } else {
        	// save information in holder of item
        	Log.i("TAG", "POSITION" + position);
        	// get item  if header user shift the position by 1
    		ImageModel item = mItems.get(useHeader() ? position - 1 : position);	
    		holder.mImageView.setBackgroundResource(item.getResId());
            holder.mTextView.setText("Position " + position);
        }
    }


	/**
	 * Create View Holder by Type
	 * @param parent, the view parent
	 * @param viewType : the type of View
	 */
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		// get inflater
		LayoutInflater inflater =    
				(LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View convertView;
		// in function of type return the right viewHolder
		if (viewType == TYPE_HEADER  && useHeader()) {
			// Header
			convertView = inflater.inflate(mViewHeader, parent, false);
		} else if (viewType == TYPE_FOOTER && useFooter()) {
			// Footer
			convertView = inflater.inflate(mViewFooter, parent, false);
        } else {
        	// Standard
        	convertView = inflater.inflate(R.layout.item, parent, false);
        }
        return new ViewHolder(convertView);
	
	}
	
	
	/**
	 * Get type of view by position (HEADER, FOOTER, STANDARD)
	 * @param position : the position of item
	 */
	@Override
    public int getItemViewType(int position) {
		// test if header
        if (position == 0 && useHeader()) {
            return TYPE_HEADER;
        }
        // get the size of item in adapter
        int max = useHeader() ? mItems.size() + 1 : mItems.size();
        // test footer
        if (position == max && useFooter()) {
            return TYPE_FOOTER;
        }
        return TYPE_ADAPTERVIEW;
    }
	
	/**
	 * Remove item in list and notify adapter
	 * @param position : the position of item to remove
	 */
	public void removeItem(int position) {
		// if header or footer don't move it
		int max = useHeader() ? mItems.size() + 1 : mItems.size();
		if (useHeader() && position == 0) {
			return;
		}
		// if header or footer don't move it
		if (useFooter() && position == max) {
			return;
		}
		// notify the adapter
		notifyItemRemoved(position);
		// remove item, if header used shift position by one
		mItems.remove(useHeader() ? position -1 : position);
	}
	
	/**
	 * Add item in adapter
	 * @param item : the new item to add
	 * @param position : the position to add item
	 */
	public void addItem(ImageModel item, int position) {
		// notify the adapter, shift position by one if header used
		notifyItemInserted(useHeader() ? position + 1: position);
		// add the item
	    mItems.add(position, item);
	}
	
	

}
