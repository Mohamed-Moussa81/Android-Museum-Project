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

/**
 * Second activity class to show ticket prices and image of the museum that was selected
 * in the previous main activity. User is allowed to click on the image to navigate to
 * the website of the museum, as well as the prices of different types of tickets.
 *
 * @author Sophia Cho, Mohamed Moussa
 */
public class TicketsActivity extends AppCompatActivity {
    private TextView title, adult, student, senior, TicketPrice, TaxPrice, TotalPrice;
    private ImageView displayImage;
    private String url;
    private int[] prices;
    private Spinner spinner, spinner2, spinner3;

    /**
     * onCreate method to execute the initialization of the activity. Variables used
     * in this class are instantiated in this method. Depending on the Extra value
     * passed in, displays the 2nd activity to match the museum information by using
     * setUpDisplay method.
     *
     * @param savedInstanceState current instance state
     */
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
        TicketPrice.setKeyListener(null);
        TotalPrice.setKeyListener(null);
        TaxPrice.setKeyListener(null);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        Intent intent = getIntent();
        String museum = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (getString(R.string.Moma).equals(museum)) {
            setUpDisplay(museum, R.string.momaURL, R.array.momaprice, R.drawable.moma);
        } else if (getString(R.string.amnh).equals(museum)) {
            setUpDisplay(museum, R.string.amnhURL, R.array.nhPrice, R.drawable.naturalhistory);
        } else if (getString(R.string.brooklyn).equals(museum)) {
            setUpDisplay(museum, R.string.brooklynURL, R.array.bkPrice, R.drawable.brooklyn);
        } else {
            setUpDisplay(museum, R.string.metURL, R.array.mmaPrice, R.drawable.metro);
        }

        showToast();
    }

    /**
     * A helper method to set up the display of the tickets activity. Uses the string value of
     * the museum passed in to find rest of its information. The image of the museum is set, as
     * well as the three prices and the name of the museum.
     *
     * @param museum       String of the museum name
     * @param urlId        ID of the url of the respective museum
     * @param priceArrayId ID of the price array of the respective museum
     * @param image        ID of the image of the respective museum
     */
    private void setUpDisplay(String museum, int urlId, int priceArrayId, int image) {
        displayImage.setImageResource(image);
        title.setText(museum);
        url = getString(urlId);
        prices = getResources().getIntArray(priceArrayId);
        adult.setText(String.format(Locale.US, "$%d", prices[0]));
        student.setText(String.format(Locale.US, "$%d", prices[1]));
        senior.setText(String.format(Locale.US, "$%d", prices[2]));
    }

    /**
     * onClick method for all images displayed on the ticket screen. The image leads to
     * the website of the respective museum.
     *
     * @param view the ImageView
     */
    public void goToWebsite(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    /**
     * Overriden onResume() method to show the Toast message upon returning to the 2nd
     * activity screen.
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
        Toast toast = Toast.makeText(getApplicationContext(), R.string.toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Executed upon the clicking of the submit button. Uses helper methods to calculate the
     * prices of the tickets. Once values are calculated, display the prices on the TextField.
     *
     * @param view the submit button
     */
    public void calculatePrice(View view) {
        int ticketPrice = calculateTickets();
        double taxPrice = calculateTax(ticketPrice);
        double totalPrice = calculateTotal(ticketPrice, taxPrice);

        TicketPrice.setText(String.format(Locale.US, "$%d", ticketPrice));
        TaxPrice.setText(String.format(Locale.US, "$%.2f", taxPrice));
        TotalPrice.setText(String.format(Locale.US, "$%.2f", totalPrice));
    }

    /**
     * Calculates the total price of selected tickets. This value does not include the
     * tax. The ticket price is received from the array of prices from integers.xml.
     *
     * @return the total price of selected tickets
     */
    protected int calculateTickets() {
        int adultNumber = Integer.parseInt(spinner.getSelectedItem().toString());
        int seniorNumber = Integer.parseInt(spinner2.getSelectedItem().toString());
        int studentNumber = Integer.parseInt(spinner3.getSelectedItem().toString());

        int adultPrice = prices[0];
        int seniorPrice = prices[1];
        int studentPrice = prices[2];

        return (adultNumber * adultPrice) + (seniorNumber * seniorPrice) + (studentNumber * studentPrice);
    }

    /**
     * Calculates the tax price by using NYC's sales tax of 8.875%.
     *
     * @param ticketPrice total price of selected tickets
     * @return the tax value of selected tickets
     */
    protected double calculateTax(int ticketPrice) {
        return ticketPrice * 0.08875;
    }

    /**
     * Calculates the total price by adding the initial ticket price and its tax.
     *
     * @param ticketPrice price of tickets selected
     * @param taxPrice    price of tax of above value
     * @return the total price of the tickets
     */
    protected double calculateTotal(int ticketPrice, double taxPrice) {
        return ticketPrice + taxPrice;
    }
}