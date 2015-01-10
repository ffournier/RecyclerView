package com.android2ee.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author florian
 * Class MainAcitivty
 * Switch between SimpleActivity and Complex Activity
 */
public class MainActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/**
		 * Button Simple Sample
		 */
		Button simpleButton = (Button) findViewById(R.id.myButtonSimple);
		simpleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// run activity SimpleActivity
				Intent intent = new Intent(MainActivity.this, SimpleActivity.class);
				startActivity(intent);
				
			}
		});
		
		/**
		 * Button Complex Sample
		 */
		Button complexButton = (Button) findViewById(R.id.myButtonComplex);
		complexButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// run start ComplexActivity
				Intent intent = new Intent(MainActivity.this, ComplexActivity.class);
				startActivity(intent);
				
			}
		});
		
	}	
}
