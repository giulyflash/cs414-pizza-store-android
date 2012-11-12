package pizzastore.android;

import core.PizzaStoreFacadeInterface;
import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	
	protected static PizzaStoreFacadeInterface store;
	protected static String customerUsername;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        store = PizzaKioskActivity.getStore();
    }
    
    public static void setCustomer(String username){
    	BaseActivity.customerUsername = username;
    }
}
