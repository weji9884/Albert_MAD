package com.example.restaurants;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private Spinner cuisineSpinner;
    private Restaurant myRestaurant = new Restaurant();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cuisineSpinner = findViewById(R.id.spinner);
        button = findViewById(R.id.button);

        //create listener
        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findResaurant(v);
            }
        };

        // add listener to button
        button.setOnClickListener(onclick);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void findResaurant(View view){
        Integer cuisine = cuisineSpinner.getSelectedItemPosition();
        myRestaurant.setRestaurantName(cuisine);
        String suggestedRestaurant = myRestaurant.getRestaurantName();
        String suggestedRestaurantURL = myRestaurant.getRestaurantURL();

        Log.i("Restaurant suggested", suggestedRestaurant);
        Log.i("RestaurantURL suggested", suggestedRestaurantURL);

        Intent intent = new Intent(this, RestaurantActivity.class);
        intent.putExtra( "RestaurantName", suggestedRestaurant);
        intent.putExtra("RestaurantURL", suggestedRestaurantURL);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //get the ID of the item on the action bar that was clicked
        switch (item.getItemId()){
            case R.id.create_order:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
