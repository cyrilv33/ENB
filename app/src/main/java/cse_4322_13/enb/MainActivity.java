package cse_4322_13.enb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button createEventAdButton;
    ListView eventListView;
    EditText editText;
    ArrayList<String> listOfEvents;
    ArrayAdapter<String> eventListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // sample
        eventListView =(ListView) findViewById(R.id.myListView);
        editText = (EditText) findViewById(R.id.listContent);
        listOfEvents = new ArrayList<>();
        eventListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfEvents);
        eventListView.setAdapter(eventListAdapter);

        createEventAdButton = (Button) findViewById(R.id.createButton);
        createEventAdButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String text = editText.getText().toString();

                listOfEvents.add(text);

                eventListAdapter.notifyDataSetChanged();
            }
        });

        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String eventPicked = "Selected " +
                        String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(MainActivity.this,eventPicked,Toast.LENGTH_SHORT).show();
            }
        });
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