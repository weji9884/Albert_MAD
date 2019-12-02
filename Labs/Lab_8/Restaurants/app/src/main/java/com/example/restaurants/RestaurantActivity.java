package com.example.restaurants;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class RestaurantActivity extends AppCompatActivity {

    private String Restaurant;
    private String RestaurantURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                loadWebSite(view);
            }
        });

        Intent intent = getIntent();
        Restaurant = intent.getStringExtra("RestaurantName");
        RestaurantURL = intent.getStringExtra("RestaurantURL");
        Log.i( "restaurant received",  Restaurant);
        Log.i("url received", Restaurant);

        //update text view
        TextView messageView = findViewById(R.id.restaurantTextView);
        messageView.setText("You should check out " + Restaurant);

    }

    private void loadWebSite(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(RestaurantURL));
        startActivity(intent);
    }
}
