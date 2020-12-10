package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main activity class to start up the main page of the app. Lets the user to
 * navigate through the list of museums in NYC. Clicking on a museum lets the
 * user to purchase tickets by opening up the second activity.
 *
 * @author Sophia Cho, Mohamed Moussa
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listview;

    /**
     * onCreate method to execute the initialization of the activity. Sets up the listview
     * of the museums as well.
     *
     * @param savedInstanceState current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listview);
        String[] stringList = getResources().getStringArray(R.array.ListOfMuseums);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
        showToast();
    }

    /**
     * Overriden onResume() method to show the Toast message upon returning to the main
     * screen.
     */
    @Override
    protected void onResume() {
        super.onResume();
        showToast();
    }

    /**
     * A helper method to show the Toast message.
     */
    protected void showToast() {
        Toast toast = Toast.makeText(getApplicationContext(), R.string.mainToastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Overridden method to set up the action when a museum is clicked. Upon clicking on a museum
     * listing, a second activity opens up and the museum that was selected is passed through
     * as an Extra object.
     *
     * @param parent   parent adapterview
     * @param view     listing of a museum
     * @param position position of the listing
     * @param id       id arg
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent createTicket = new Intent(this, TicketsActivity.class);
        TextView selectedMuseum = (TextView) view;
        createTicket.putExtra(Intent.EXTRA_TEXT, selectedMuseum.getText());
        startActivity(createTicket);
    }
}
