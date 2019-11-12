package com.example.cars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private TextView carSelection;
    private ToggleButton toggle;
    private Spinner Types;
    private RadioGroup cost;
    private CheckBox americanCheckBox;
    private CheckBox europeanCheckBox;
    private CheckBox asianCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get views
        carSelection = findViewById(R.id.carTextView);
        toggle = findViewById(R.id.toggleButton);
        Types = findViewById(R.id.spinner);
        cost = findViewById(R.id.radioGroup);
        americanCheckBox = findViewById(R.id.checkBox1);
        europeanCheckBox = findViewById(R.id.checkBox2);
        asianCheckBox = findViewById(R.id.checkBox3);

//        // check for recovering the instance state
//        if (savedInstanceState !=null){
//            message = savedInstanceState.getString("msg");
//            findCar.setText(message);
//        }
//
//        String message;
//        message = "You can get a " + perfectCar + " !";
    }

    public void findCar(View view) {
        //toggle button
        boolean location = toggle.isChecked();

        //spinner
        String carType = String.valueOf(Types.getSelectedItem());

        //radio buttons
        int cost_id = cost.getCheckedRadioButtonId();

        //check boxes
        Boolean American = americanCheckBox.isChecked();
        Boolean European = europeanCheckBox.isChecked();
        Boolean Asian = asianCheckBox.isChecked();

        //pick car
        String perfectCar;

        //check radio buttons
        if (cost_id == -1) {
            //toast
            Context context = getApplicationContext();
            CharSequence text = "Please select a cost level";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if (location) { //terrestrial
                if (cost_id == R.id.radioButton1) { //cheapest option
                    perfectCar = "Honda";
                } if (cost_id == R.id.radioButton2){
                    switch (carType) {
                        case "Sedan":
                            perfectCar = "Alfa Romeo";
                            break;
                        case "MPV":
                            perfectCar = "Buick";
                            break;
                        case "SUV":
                            perfectCar = "Toyota";
                            break;
                        case "Sports":
                            perfectCar = "BMW";
                            break;
                        case "Space":
                            perfectCar = "None";
                            break;
                        default:
                            perfectCar = "Alfa Romeo";
                    }
                } else {
                    switch (carType) {
                        case "Sedan":
                            perfectCar = "Bentley";
                            break;
                        case "MPV":
                            perfectCar = "Mercedes Benz";
                            break;
                        case "SUV":
                            perfectCar = "Lexus";
                            break;
                        case "Sports":
                            perfectCar = "Koenigsegg";
                            break;
                        case "Space":
                            perfectCar = "None";
                            break;
                        default:
                            perfectCar = "Bentley";
                    }
                }
            } else { //extraterrestrial
                if (cost_id == R.id.radioButton3) {
                    if (American) {
                        switch (carType) {
                            case "Sedan":
                                perfectCar = "None";
                                break;
                            case "MPV":
                                perfectCar = "None";
                                break;
                            case "SUV":
                                perfectCar = "None";
                                break;
                            case "Sports":
                                perfectCar = "None";
                                break;
                            case "Space":
                                perfectCar = "Lunar Roving Vehicle";
                                break;
                            default:
                                perfectCar = "Lunar Roving Vehicle";
                        }
                    } else {
                        perfectCar = "None";
                    }
                } else {
                    perfectCar = "None";
                }
            }

            //text view
            carSelection.setText("You can get a " + perfectCar + " !");
        }
    }
    // invoked when the activity may be temporarily destroyed, save the instance state here
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState); // to prevent the rotation pauses the app and lose the value
//        outState.putString("msg", message);
//        outState.putInt("imageid", R.drawable.ghost);
//    }
}
