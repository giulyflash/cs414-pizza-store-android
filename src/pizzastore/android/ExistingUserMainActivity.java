package pizzastore.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExistingUserMainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        
        
        //TODO: figure out how to move this to onclick method for button
        final Button button = (Button) findViewById(R.id.button1);
        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(context, PlaceOrderActivity.class);
                startActivity(intent); 
            }
        });
        
        //TODO: figure out how to move this to onclick method for button
        final Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(context, ViewHistoryActivity.class);
                startActivity(intent); 
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user_main, menu);
        return true;
    }
}
