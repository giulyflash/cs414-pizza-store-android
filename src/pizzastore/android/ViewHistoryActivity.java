package pizzastore.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ViewHistoryActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_view_history, menu);
        return true;
    }
}
