package pizzastore.android;

import core.CentralTracker;
import core.PizzaStoreFacade;
import core.PizzaStoreFacadeInterface;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PizzaKioskActivity extends Activity {
	
	private static PizzaStoreFacadeInterface store;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //TODO: figure out how to move this to onclick method for button
        final Button button = (Button) findViewById(R.id.button1);
        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(context, NewUserActivity.class);
                startActivity(intent); 
            }
        });
        
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	BaseActivity.setCustomer(((EditText) findViewById(R.id.editText1)).getText().toString());
            	Intent intent = new Intent(context, ExistingUserMainActivity.class);
                startActivity(intent); 
            }
        });
        
        store = new PizzaStoreFacade(new CentralTracker(), true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public static PizzaStoreFacadeInterface getStore(){
    	return store;
    }


}
