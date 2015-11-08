package android.gus.justg.us.tasker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create dummy list.
        final ArrayList<String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");

        // Create adapter.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);

        // Get listView and add adapter.
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // When the button is clicked, we want to show an "add new task" dialog!

                // This object will create our alert.
                // Note: the MainActivity in front of this is important: otherwise, this refers to the onClickListener!
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                // Fancy title.
                builder.setTitle("Add Task");

                // This will be the text box into which users will type their tasks.
                final EditText taskInput = new EditText(MainActivity.this);

                // Tell our builder to make our dialog display our new EditText!
                builder.setView(taskInput);

                ///////////////////////////////////
                // Here comes the complicated looking stuff! Don't worry!

                // Create an "add" button
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {

                    // This is what happens when our "add" button is clicked.
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Get the inputted string - our new task!
                        String task = taskInput.getText().toString();

                        // Add the new task to our task list.
                        list.add(task);
                    }
                });

                // Create a "cancel" button
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    // This is what happens when our cancel button is clicked.
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
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
        }

        return super.onOptionsItemSelected(item);
    }
}
