package cse_4322_13.enb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import java.sql.SQLException;
import java.util.ArrayList;

import cse_4322_13.enb.Database.Event;
import cse_4322_13.enb.Database.EventsDataSource;

public class MainActivity extends AppCompatActivity {

    private EventsDataSource dataSource;
    private int position;
    Button createEventAdButton;
    ListView eventListView;
    EditText editText;
    ArrayList<Event> listOfEvents;
    ArrayAdapter<Event> eventListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataSource = new EventsDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // sample
        eventListView =(ListView) findViewById(R.id.myListView);
//        editText = (EditText) findViewById(R.id.listContent);
        listOfEvents = dataSource.getAllEvents();
        eventListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfEvents);
        eventListView.setAdapter(eventListAdapter);

        createEventAdButton = (Button) findViewById(R.id.createButton);

        setupListViewListener();

//        eventListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String eventPicked = "Selected " +
//                        String.valueOf(parent.getItemAtPosition(position));
//
//                Toast.makeText(MainActivity.this,eventPicked,Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void setupListViewListener() {

        eventListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        position = pos;
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("DELETE");
                        alert.setMessage("Are you sure to delete record");
                        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dataSource.deleteEvents(listOfEvents.get(position).getID());
                                listOfEvents.remove(position);
                                eventListAdapter.notifyDataSetChanged();
                                dialog.dismiss();

                            }
                        });
                        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });

                        alert.show();
                        return true;
                    }

                }
        );
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

    public void createEventAddFunction(View view) {

        Intent go = new Intent(this, AddEvent.class);
        int Result = 1;
        startActivityForResult(go, Result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, data.getStringExtra("description"), Toast.LENGTH_SHORT).show();
        if (resultCode == 1) {
            Event event = dataSource.createEvents(data.getStringExtra("name"),
                    data.getStringExtra("description"),
                    data.getStringExtra("date"),
                    data.getStringExtra("location"),
                    data.getStringExtra("clubName"),
                    data.getStringExtra("startTime"),
                    data.getStringExtra("endTime"));
            listOfEvents.add(event);
            eventListAdapter.notifyDataSetChanged();
        }
    }
}