package com.android2ee.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android2ee.recyclerview.adapter.RecyclerSimpleViewAdapter;
import com.android2ee.recyclerview.listener.RecyclerItemClickListener;
import com.android2ee.recyclerview.listener.RecyclerItemClickListener.OnItemClickListener;


/**
 * 
 * @author florian
 * Class SimpleActivity
 * Present a simple RecyclerView 
 * List simple without animation and decoration
 *
 */
public class SimpleActivity extends ActionBarActivity implements OnItemClickListener {
	
	/**
	 * List items
	 */
	List<String> items ;
	/**
	 * The Adapter of RecyclerView
	 */
	RecyclerSimpleViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);
		
		// fill the list items
		items = new ArrayList<String>();
        for (int i = 0; i < 6; i++) {
        	// new item
            items.add("test " + i);
        }
	        
        // create the recyclerView
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myListSimple);
		recyclerView.setHasFixedSize(true);
		// create the adapter with the layout android android.R.layout.simple_list_item_1
		adapter = new RecyclerSimpleViewAdapter(items, android.R.layout.simple_list_item_1);
		// set the adapter into the recyclerView
		recyclerView.setAdapter(adapter);
		// set the LinearLayoutManager in the recyclerView
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		// Add the RecyclerItemClickListener, to intercept click on his child
		recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
		// Button Add to add a new item in the list
		Button buttonAdd = (Button) findViewById(R.id.myButtonSimpleAdd);
		buttonAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// add a new item at the end of the list
				add("test " + adapter.getItemCount(), adapter.getItemCount());
			}
		});
	}
	
	/**
	 * Add new item , and notify to the adapter that item has been added
	 * @param item : the new item
	 * @param position : the position of the new item in the list
	 */
	public void add(String item, int position) {
		// add new item
	    items.add(position, item);
	    // noyify the adapter
	    adapter.notifyItemInserted(position);
	}
	 
	/**
	 * Remove item in the list and notify to the adapter that item has been removed
	 * @param item : the item to remove
	 */
	public void remove(String item) {
		// find the position of item in list
	    int position = items.indexOf(item);
	    // remove the item in list
	    items.remove(position);
	    // notify the adapter
	    adapter.notifyItemRemoved(position);
	}
	
	/**
	 * Remove item by position in the list and notify to the adapter that item has been removed
	 * @param position : the position in list to remove
	 */
	public void remove(int position) {
		// remove the item in list
	    items.remove(position);
	 // notify the adapter
	    adapter.notifyItemRemoved(position);
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
