package com.android2ee.recyclerview.adapter.model;

/**
 * 
 * @author florian
 * Class ImageModel
 * POJO for ComplexRecyclerView
 */
public class ImageModel {

	/**
	 * Title Text
	 */
	String mTitle;
	/**
	 * Resource Id of image
	 */
	int mResId;
	
	/**
	 * Get Res Id
	 * @return mResId
	 */
	public int getResId() {
		return mResId;
	}

	/**
	 * Set Res Id
	 * @param resId : the new resource Id
	 */
	public void setResId(int resId) {
		mResId = resId;
	}

	/**
	 * Get Title
	 * @return mTitle
	 */
	public String getTitle() {
		return mTitle;
	}

	/**
	 * Set Title
	 * @param title ; the new title
	 */
	public void setTitle(String title) {
		mTitle = title;
	}
	
	
}
