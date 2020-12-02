package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView modernArt,brooklyn,museumofArt,naturalHistory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modernArt = findViewById(R.id.modernArt);
        brooklyn = findViewById(R.id.brooklyn);
        museumofArt = findViewById(R.id.museumOfArt);
        naturalHistory = findViewById(R.id.naturalHistory);
    }

    public void displayTickets(View view) {
    Intent createTicket = new Intent(this,TicketsActivity.class);
    TextView selectedMuseum = (TextView) view ;
    createTicket.putExtra(Intent.EXTRA_TEXT,selectedMuseum.getText());
    startActivity(createTicket);
    }
}