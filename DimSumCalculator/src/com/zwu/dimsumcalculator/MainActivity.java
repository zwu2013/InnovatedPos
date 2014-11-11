package com.zwu.dimsumcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		  requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
//                                WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
	
		final Double[] prices = {2.00,2.85,3.50,3.95,4.95,5.50};
		final Integer[] componentIds = {R.id.subTotal1,R.id.subTotal2,R.id.subTotal3,R.id.subTotal4,R.id.subTotal5,R.id.subTotal6};
		final Integer[] groupIds = {R.id.radioGroup1,R.id.radioGroup2,R.id.radioGroup3,R.id.radioGroup4,R.id.radioGroup5,R.id.radioGroup6};
		for(int i =0; i<prices.length; i++){
			handleAction(prices[i],groupIds[i],componentIds[i]);
		}
		handleNewOrder();
		//new LoadViewTask().execute();
		
	}

	private void handleAction(final double prize, final int groupId, final int viewId) {
		final TextView prizeView = (TextView) findViewById(viewId);
		final RadioGroup radioGroup = (RadioGroup) findViewById(groupId);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				final double prevSubTotal = Double.parseDouble((String)prizeView.getText());
				final RadioButton tmp = (RadioButton) findViewById(checkedId);
				final int q = Integer.parseInt((String) tmp.getText());
				double total = q * prize;
				total = Math.round(total * 100.0) / 100.0;
				prizeView.setText(String.valueOf(total));
				prizeView.refreshDrawableState();
				updateTotalPrice(prevSubTotal,total);
			}
		});
		
	}
	
	private void updateTotalPrice(final double prevSelectedPrice, final double currSelectedPrice){
		final TextView totalPriceView = (TextView) findViewById(R.id.grandTotal);
		double currTotal = Double.parseDouble((String)totalPriceView.getText());
		currTotal = currTotal - prevSelectedPrice + currSelectedPrice;
		currTotal = Math.round(currTotal * 100.0) / 100.0;
		totalPriceView.setText(String.valueOf(currTotal));
		totalPriceView.refreshDrawableState();
	}
	
	private void handleNewOrder(){
		 final Button button = (Button) findViewById(R.id.newOrder);
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 final RadioGroup radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
            	 radioGroup1.check(R.id.group1Radio0);
            	 final RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);
            	 radioGroup2.check(R.id.group2Radio0);
            	 final RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.radioGroup3);
            	 radioGroup3.check(R.id.group3Radio0);
            	 final RadioGroup radioGroup4 = (RadioGroup) findViewById(R.id.radioGroup4);
            	 radioGroup4.check(R.id.group4Radio0);
            	 final RadioGroup radioGroup5 = (RadioGroup) findViewById(R.id.radioGroup5);
            	 radioGroup5.check(R.id.group5Radio0);
            	 final RadioGroup radioGroup6 = (RadioGroup) findViewById(R.id.radioGroup6);
            	 radioGroup6.check(R.id.group6Radio0);
             }
         });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	 // Using the following method, we will handle all screen swaps.
//    public boolean onTouchEvent(MotionEvent touchevent) {
//    	switch (touchevent.getAction()) {
//        
//        case MotionEvent.ACTION_DOWN: 
//        	lastX = touchevent.getX();
//            break;
//        case MotionEvent.ACTION_UP: 
//            float currentX = touchevent.getX();
//            
//            // Handling left to right screen swap.
//            if (lastX < currentX) {
//            	
//            	// If there aren't any other children, just break.
//                if (viewFlipper.getDisplayedChild() == 0)
//                	break;
//                
//                // Next screen comes in from left.
//                viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
//                // Current screen goes out from right. 
//                //viewFlipper.setOutAnimation(this, R.anim.slide_out_from_right);
//                
//                // Display next screen.
//                viewFlipper.showNext();
//             }
//                                     
//            // Handling right to left screen swap.
//             if (lastX > currentX) {
//            	 
//            	 // If there is a child (to the left), kust break.
//            	 if (viewFlipper.getDisplayedChild() == 1)
//            		 break;
//    
//            	 // Next screen comes in from right.
//            	 viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
//            	// Current screen goes out from left. 
//            	 //viewFlipper.setOutAnimation(this, R.anim.slide_out_from_left);
//                 
//            	// Display previous screen.
//                 viewFlipper.showPrevious();
//             }
//             break;
//    	 }
//         return false;
//    }
}
