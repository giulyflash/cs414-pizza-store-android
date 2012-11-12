package pizzastore.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ReceiptActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
        TextView receipt = (TextView) findViewById(R.id.receipt);
        receipt.setText(store.getTransactionReciept(PlaceOrderActivity.getOrderId()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_receipt, menu);
        return true;
    }
}
