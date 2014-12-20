package com.android2ee.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.android2ee.recyclerview.RecyclerItemClickListener.OnItemClickListener;

public class MainActivity extends ActionBarActivity implements OnItemClickListener {
	
	List<ViewModel> items ;
	RecyclerViewAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		items = new ArrayList<ViewModel>();
        for (int i = 0; i < 6; i++) {
            items.add(new ViewModel("first text", "second text", 
            		(i % 2 == 0) ? android.R.drawable.btn_star_big_on : android.R.drawable.btn_star_big_off));
        }
	        
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mylist);
		recyclerView.setHasFixedSize(true);
		adapter = new RecyclerViewAdapter(items, R.layout.mylayout);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
		
		Button button = (Button) findViewById(R.id.mybutton);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				ViewModel model = new ViewModel("new first text", "new second text", 
						(adapter.getItemCount() % 2 == 0)  ? android.R.drawable.arrow_up_float : android.R.drawable.arrow_down_float);
				add(model, adapter.getItemCount());
			}
		});
	}
	
	
	public void add(ViewModel item, int position) {
	    items.add(position, item);
	    adapter.notifyItemInserted(position);
	}
	 
	public void remove(ViewModel item) {
	    int position = items.indexOf(item);
	    items.remove(position);
	    adapter.notifyItemRemoved(position);
	}
	
	public void remove(int position) {
	    items.remove(position);
	    adapter.notifyItemRemoved(position);
	}


	@Override
	public void onItemClick(View view, int position) {
		Log.i(getClass().getCanonicalName(), "Id found : " + adapter.getItemId(position));
		remove(position);
	}

}
