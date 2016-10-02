package cse_4322_13.enb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button createEventAdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sample
        String[] listOfEvents = {"Lecture at NH 100", "Career Fair", "Recruiting members"};

        ListAdapter eventListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listOfEvents);

        ListView eventListView = (ListView) findViewById(R.id.myListView);

        eventListView.setAdapter(eventListAdapter);

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String eventPicked = "Selected " +
                        String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(MainActivity.this,eventPicked,Toast.LENGTH_SHORT).show();
            }
        });

        Button createButton = (Button) findViewById(R.id.createButton);

        createEventAdButton = (Button) findViewById(R.id.createButton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.exit_app){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void createEventAdFunction(View view) {
        /*
         * to be created
         */
    }
}
