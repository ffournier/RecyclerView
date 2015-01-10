package com.android2ee.recyclerview.layoutmanager;

import java.util.List;

import com.android2ee.recyclerview.adapter.model.ImageModel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

/**
 * 
 * @author florian
 * Class MyGridLayoutManager
 * Provides a way to display Header and Footer in a GridLayoutManager, and take all row or column
 *
 */
public class MyGridLayoutManager extends GridLayoutManager {
	
	// useHeader
	private boolean mUseHeader;
	// useFOoter
	private boolean mUseFooter;
	// spanCount of the Grid
	private int mSpanCount;
	// list items
	private List<ImageModel> mObjects;
	
	/**
	 * New SpanSizeLookup, to treat Header and Footer size
	 */
	public SpanSizeLookup mySpanSizeLookup = new SpanSizeLookup() {
		
		/**
		 * Get Span Size of the position
		 * @return the size of item
		 */
		@Override
		public int getSpanSize(int position) {
			int max = mUseHeader ? mObjects.size() + 1 : mObjects.size();
			// if header
			if (position == 0 && mUseHeader) {
				// return all spanCount
				return mSpanCount;
			// if footer
			} else if (position == max && mUseFooter) {
				// return all spanCount
				return mSpanCount;
			}
			// standard return 1
			return 1;
		}
	};

	/**
	 * Constructor MyGridLayoutManager
	 * @param context : the context
	 * @param spanCount : the spanCount of Grid
	 * @param objects : the list Items
	 */
	public MyGridLayoutManager(Context context, int spanCount, List<ImageModel> objects) {
		super(context, spanCount);
		mUseHeader = false;
		mUseFooter = false;
		mSpanCount = spanCount;
		mObjects = objects;
		// set mySpanSizeLookup to treat header and footer
		setSpanSizeLookup(mySpanSizeLookup);
	}
	
	/**
	 * Display Header, if an header has displayed in adapter
	 * @param value, true of false
	 */
	public void displayHeader(boolean value) {
		mUseHeader = value;
	}
	
	/**
	 * Display Footer, if an footer has displayed in adapter
	 * @param value, true of false
	 */
	public void displayFooter(boolean value) {
		mUseFooter = value;
	}
	
	

}
