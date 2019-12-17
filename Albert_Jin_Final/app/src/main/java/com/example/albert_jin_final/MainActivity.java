package com.example.albert_jin_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private TextView build;
    private ImageView food;
    private String message;
    private Spinner location;
    private ToggleButton filling;
    private RadioGroup type;
    private Switch gluten;
    private CheckBox salsa;
    private CheckBox sour;
    private CheckBox cheese;
    private CheckBox guacamole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        location = findViewById(R.id.spinner);
        name = findViewById(R.id.editText);
        build = findViewById(R.id.message);
        food = findViewById(R.id.imageView);
        filling = findViewById(R.id.toggleButton);
        type = findViewById(R.id.radioGroup);
        gluten = findViewById(R.id.switch1);
        salsa = findViewById(R.id.checkBox1);
        sour = findViewById(R.id.checkBox2);
        cheese = findViewById(R.id.checkBox3);
        guacamole = findViewById(R.id.checkBox4);

        // check for recovering the instance state
        if (savedInstanceState !=null){
            message = savedInstanceState.getString("msg");
            int image_id = savedInstanceState.getInt("imageid");

            build.setText(message);
            food.setImageResource(image_id);
        }
    }

    public void BuildBurrito(View view) {
        boolean vom = filling.isChecked();
        String fillingName;
        if (vom == true) { // need to check
            fillingName = "meat";
        } else {
            fillingName = "veggie";
        }

        boolean gf = gluten.isChecked();
        String glutenFree = null;
        if (gf == true) {
            glutenFree = " in a corn tortilla ";
        }

        //check boxes
        Boolean sa = salsa.isChecked();
        Boolean so = sour.isChecked();
        Boolean ch = cheese.isChecked();
        Boolean gu = guacamole.isChecked();

        String topping = null;

        if (sa == true) {
            topping = topping + "salsa";
        }
        if (so == true){
            topping = topping + "sour cream";
        }
        if (ch == true){
            topping = topping + "cheese";
        }
        if (gu == true){
            topping = topping + "guacamole";
        }

        String nameValue = name.getText().toString();
        String typeName = null;
        String locate = null;
        String loc = String.valueOf(location.getSelectedItem());

        switch (loc) {
            case "The Hill":
                locate = "The Hill";
                break;
            case "29th Street":
                locate = "29th Street";
                break;
            case "Pearl Street":
                locate = "Pearl Street";
                break;
            default:
                locate = "";
        }

        int type_id = type.getCheckedRadioButtonId();
        if (type_id == -1) {
            Context context = getApplicationContext();
            CharSequence text = "Please select a type:";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            if (type_id == R.id.radioButton1) {
                typeName = "Burrito";
                food.setImageResource(R.drawable.burrito);
            } else { 
                typeName = "Taco";
                food.setImageResource(R.drawable.taco);
            }
        }

        message = nameValue + " wants a " + typeName + "with" + fillingName + glutenFree + " with " + topping + ". You should eat on " + locate + ".";
        build.setText(message);
    }

}
