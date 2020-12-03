package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TicketsActivity extends AppCompatActivity {
    private TextView title, adult, student, senior, TicketPrice, TaxPrice, TotalPrice;
    private ImageView displayImage;
    private String url;
    private int[] prices;
    private Spinner spinner, spinner2, spinner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickets);
        displayImage = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        adult = findViewById(R.id.adult);
        student = findViewById(R.id.student);
        senior = findViewById(R.id.senior);

        TicketPrice = findViewById(R.id.TicketPrice);
        TaxPrice = findViewById(R.id.TaxValue);
        TotalPrice = findViewById(R.id.TotalPrice);

        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        Intent intent = getIntent();
        String museum = intent.getStringExtra(Intent.EXTRA_TEXT);
       if(getString(R.string.Moma).equals(museum)){
           setUpDisplay(museum,R.string.momaURL,R.array.momaprice,R.drawable.moma);
       }
      else if(getString(R.string.amnh).equals(museum)){
           setUpDisplay(museum,R.string.amnhURL,R.array.nhPrice,R.drawable.naturalhistory);
       }
       else if(getString(R.string.brooklyn).equals(museum)){
           setUpDisplay(museum,R.string.brooklynURL,R.array.bkPrice,R.drawable.brooklyn);
       }
       else {
           setUpDisplay(museum,R.string.metURL,R.array.mmaPrice,R.drawable.metro);
       }

        showToast();
    }

    private void setUpDisplay(String museum, int urlId,int priceArrayId, int image){
        //adult,student,senior
        displayImage.setImageResource(image);
        title.setText(museum);
        url = getString(urlId);
        prices = getResources().getIntArray(priceArrayId);
        adult.setText(String.format(Locale.US, "$%d", prices[0]));
        student.setText(String.format(Locale.US, "$%d", prices[1]));
        senior.setText(String.format(Locale.US, "$%d", prices[2]));

    }

    public void goToWebsite(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
    //shows message when we navigate back to page
    @Override
    protected void onResume() {
        super.onResume();
        showToast();
    } //onclick for Submit button

    protected void showToast() {
        Toast toast = Toast.makeText(getApplicationContext(), R.string.toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void calculatePrice(View view) {
        int ticketPrice = calculateTickets();
        double taxPrice = calculateTax(ticketPrice);
        double totalPrice = calculateTotal(ticketPrice, taxPrice);

        TicketPrice.setText(String.format(Locale.US, "$%d", ticketPrice));
        TaxPrice.setText(String.format(Locale.US, "$%.2f", taxPrice));
        TotalPrice.setText(String.format(Locale.US, "$%.2f", totalPrice));
    }

    protected int calculateTickets() {
        int adultNumber = Integer.parseInt(spinner.getSelectedItem().toString());
        int seniorNumber = Integer.parseInt(spinner2.getSelectedItem().toString());
        int studentNumber = Integer.parseInt(spinner3.getSelectedItem().toString());

        int adultPrice = prices[0];
        int seniorPrice = prices[1];
        int studentPrice = prices[2];

        return (adultNumber * adultPrice) + (seniorNumber * seniorPrice) + (studentNumber * studentPrice);
    }

    protected double calculateTax(int ticketPrice) {
        return ticketPrice * 0.06625;
    }

    protected double calculateTotal(int ticketPrice, double taxPrice) {
        return ticketPrice + taxPrice;
    }
}