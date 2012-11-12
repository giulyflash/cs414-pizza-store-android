package pizzastore.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ViewHistoryActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        TextView points = (TextView) findViewById(R.id.points);
        points.setText("Points: " + store.getCustomerPointsByUserName(customerUsername));
        
        LinearLayout ordersLayout = (LinearLayout) findViewById(R.id.orders);
        for(int orderId : store.getCustomerOrderHistory(customerUsername)){
        	TextView orderReceipt = new TextView(this);
        	orderReceipt.setText(store.getTransactionReciept(orderId) + "\n");
        	ordersLayout.addView(orderReceipt);
        }
             
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_history, menu);
        return true;
    }
}
