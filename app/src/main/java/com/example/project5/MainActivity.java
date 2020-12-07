package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main activity class to start up the main page of the app. Lets the user to
 * navigate through the list of museums in NYC. Clicking on a museum lets the
 * user to purchase tickets by opening up the second activity.
 *
 * @author Sophia Cho, Mohamed Moussa
 */
public class MainActivity extends AppCompatActivity {

    /**
     * onCreate method to execute the initialization of the activity
     *
     * @param savedInstanceState current instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showToast();
    }

    /**
     * Upon clicking on a museum listing, a second activity opens up and the
     * museum that was selected is passed through as an Extra object.
     *
     * @param view TextView that displays the museum
     */
    public void displayTickets(View view) {
        Intent createTicket = new Intent(this, TicketsActivity.class);
        TextView selectedMuseum = (TextView) view;
        createTicket.putExtra(Intent.EXTRA_TEXT, selectedMuseum.getText());
        startActivity(createTicket);
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
}