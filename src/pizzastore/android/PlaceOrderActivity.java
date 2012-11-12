package pizzastore.android;

import java.util.ArrayList;

import core.MenuItemType;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

public class PlaceOrderActivity extends BaseActivity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        
        LinearLayout couponLayout = (LinearLayout) findViewById(R.id.linearLayout3);
        for(int couponId : store.getCustomerCouponIds(customerUsername)){
        	RadioButton couponButton = new RadioButton(this);
        	couponButton.setText("Free " + store.getMenuItemName(couponId) + ": " + store.getMenuItemDescription(couponId));
        	couponButton.setId(couponId);
        	couponLayout.addView(couponButton);
        }
              
        TextView descriptionTextview = (TextView) findViewById(R.id.descriptionTextview);
        descriptionTextview.setText(store.getDailySpecialName() + ": " + store.getDailySpecialDescription() + "   " + store.getDailySpecialPrice());
        
        
        LinearLayout menuLayout = (LinearLayout) findViewById(R.id.menuLayout);
        ArrayList<Integer> menuItems = store.getMenuItems();
        for(int itemId : menuItems){
        	TextView itemDescription = new TextView(this);
        	itemDescription.setText(store.getMenuItemName(itemId) + ": " + store.getMenuItemDescription(itemId) + "  " + store.getMenuItemPrice(itemId));
        	menuLayout.addView(itemDescription);
        	
        	EditText itemQuantity = new EditText(this);
        	itemQuantity.setInputType(InputType.TYPE_CLASS_NUMBER);
        	itemQuantity.setId(itemId);
        	menuLayout.addView(itemQuantity);
        }
        
        final Button button2 = (Button) findViewById(R.id.button1);
        final Context context = this;
        button2.setOnClickListener(new View.OnClickListener() {
        	
            public void onClick(View v) {
            		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
            				context);
             
            			// set dialog message
            			alertDialogBuilder
            				.setMessage("Are you sure?")
            				.setCancelable(false)
            				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
									submitOrder();
								}
							  })
            				.setNegativeButton("No",new DialogInterface.OnClickListener() {
            					public void onClick(DialogInterface dialog,int id) {
            						// if this button is clicked, just close
            						// the dialog box and do nothing
            						dialog.cancel();
            					}
            				});
             
            				// create alert dialog
            				AlertDialog alertDialog = alertDialogBuilder.create();
             
            				// show it
            				alertDialog.show();
            	}
            });
    }
    
   
    private void submitOrder(){
    	//TODO: submit order
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_place_order, menu);
        return true;
    }
}
