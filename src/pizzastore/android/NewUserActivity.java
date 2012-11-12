package pizzastore.android;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUserActivity extends BaseActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        
        //TODO: figure out how to move this to onclick method for button
        final Button button = (Button) findViewById(R.id.button1);
        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	String username = ((EditText) findViewById(R.id.editText2)).getText().toString(),
            			name = ((EditText) findViewById(R.id.EditText01)).getText().toString(),
            			street = ((EditText) findViewById(R.id.editText1)).getText().toString(),
            			cityState = ((EditText) findViewById(R.id.editText3)).getText().toString(),
            			zip = ((EditText) findViewById(R.id.editText3)).getText().toString();
            	setCustomer(username);
            	store.createCustomer(name, street, cityState, zip, username);
            	Intent intent = new Intent(context, ExistingUserMainActivity.class);
                startActivity(intent); 
            }
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_new_user, menu);
        return true;
    }
    

}
