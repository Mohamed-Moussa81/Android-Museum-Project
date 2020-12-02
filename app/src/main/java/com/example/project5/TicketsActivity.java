package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TicketsActivity extends AppCompatActivity {
private TextView title,adult,student,senior;
private ImageView displayImage;
private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tickets);
        displayImage = findViewById(R.id.imageView);
        title = findViewById(R.id.title);
        adult = findViewById(R.id.adult);
        student = findViewById(R.id.student);
        senior = findViewById(R.id.senior);
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

        Toast toast = Toast.makeText(getApplicationContext(),R.string.toastMessage,Toast.LENGTH_SHORT);
        toast.show();
    }

    private void setUpDisplay(String museum, int urlId,int priceArrayId, int image){
        //adult,student,senior
        displayImage.setImageResource(image);
        title.setText(museum);
        url = getString(urlId);
        int[] prices = getResources().getIntArray(priceArrayId);
        adult.setText(String.valueOf(prices[0]));
        student.setText(String.valueOf(prices[1]));
        senior.setText(String.valueOf(prices[2]));

    }

    public void goToWebsite(View view) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
    //shows message when we navigate back to page
    @Override
    protected void onResume() {
        super.onResume();
        Toast toast = Toast.makeText(getApplicationContext(),R.string.toastMessage,Toast.LENGTH_SHORT);
        toast.show();
    }
    //onclick for Submit button
    public void calculatePrice(View view) {

    }
}