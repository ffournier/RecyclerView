package com.android2ee.recyclerview;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android2ee.recyclerview.adapter.RecyclerComplexViewAdapter;
import com.android2ee.recyclerview.adapter.model.ImageModel;
import com.android2ee.recyclerview.itemanimator.FlipInAnimator;
import com.android2ee.recyclerview.itemdecoration.MyDividerItemDecoration;
import com.android2ee.recyclerview.layoutmanager.MyGridLayoutManager;
import com.android2ee.recyclerview.listener.RecyclerItemClickListener;
import com.android2ee.recyclerview.listener.RecyclerItemClickListener.OnItemClickListener;

/**
 * 
 * @author florian
 * Class ComplexActivity
 * Present a complex RecyclerView 
 * Grid with a header and footer
 * with an animation
 * with a custom adapter
 * 
 */
public class ComplexActivity extends ActionBarActivity implements OnItemClickListener {
	
	/**
	 * List of items
	 */
	List<ImageModel> items ;
	/**
	 * The RecyclerView Adapter
	 */
	RecyclerComplexViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complex);
		
		// create the recyclerView
		RecyclerView recyclerView =  (RecyclerView) findViewById(R.id.myListComplex);
		recyclerView.setHasFixedSize(false);
		// add new Decoration, provide the separator between View (row in this case)
		recyclerView.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL_LIST));
		// a Add the RecyclerItemClickListener, to intercept click on his child
		recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
		
		// fill the list
		items = new ArrayList<ImageModel>();
		for (int i = 1; i < 29 ; i++) {
			// create a new ImageModel
			ImageModel img = new ImageModel();
			img.setTitle("Image No " + i);
			int drawableResourceId = this.getResources().getIdentifier("image"+String.valueOf(i), "drawable", this.getPackageName());
			img.setResId(drawableResourceId);
			// add in list
			items.add(img);
			
		}
		
		// create the adapter with header and footer
		// header represent by R.layout.header and footer by  R.layout.footer
		adapter = new RecyclerComplexViewAdapter(ComplexActivity.this, items, R.layout.header,  R.layout.footer);   	
		// set the adapter
		recyclerView.setAdapter(adapter);
		// create my LayoutManager Custom
		MyGridLayoutManager layoutManager = new MyGridLayoutManager(this, 2, items);
		// set parameter of this layoutManager
		layoutManager.displayHeader(true);
		layoutManager.displayFooter(true);
		// set this LayoutManager in RecyclerView
		recyclerView.setLayoutManager(layoutManager);
		// create and set FlipInAnimator in RecyclerView
		recyclerView.setItemAnimator(new FlipInAnimator());
		
		// Link Button Add 
		Button buttonAdd = (Button) findViewById(R.id.myButtonComplexAdd);
		buttonAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// max and min random possible for item (1-28 images)
				int min = 1;
				int max = 28;

				// create a new item random
				Random r = new Random();
				int i = r.nextInt(max - min + 1) + min;
				// create new item ImageModel
				ImageModel img = new ImageModel();
				img.setTitle("Image No " + i);
				// get drawable by name image
				int drawableResourceId = getResources().getIdentifier("image"+String.valueOf(i), "drawable", getPackageName());
				img.setResId(drawableResourceId);
				Log.i(getClass().getCanonicalName(), "Add Image Position" + "image"+String.valueOf(i) + adapter.getItemCount());
				// add in list
				add(img, adapter.mItems.size());
			}
		});
		
	}
	
	/**
	 * Add new item in list
	 * @param item : the new item
	 * @param position : the position of new item (insert)
	 */
	public void add(ImageModel item, int position) {
		adapter.addItem(item, position);
	}
	 
	/**
	 * Remove item in list
	 * @param item : the item to remove
	 */
	public void remove(ImageModel item) {
		// find position of item in list
	    int position = adapter.mItems.indexOf(item);
	    // remove item
	    adapter.removeItem(position);
	}
	
	/**
	 * Remove item by position in list
	 * @param position : the position of item to remove
	 */
	public void remove(int position) {
		// remove item
		adapter.removeItem(position);
	}

	/**
	 * Intercept Click on Item
	 * @view : the view has clicked
	 * @position : the position of view in list
	 */
	@Override
	public void onItemClick(View view, int position) {
		Log.i(getClass().getCanonicalName(), "Id found : " + adapter.getItemId(position));
		// remove item
		remove(position);
	}

}
