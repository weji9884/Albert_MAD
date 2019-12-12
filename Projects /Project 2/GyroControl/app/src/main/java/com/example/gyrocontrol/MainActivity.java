package com.example.gyrocontrol;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Matrix;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
//  13 phrases from http://trianglesinspace.com/trending3000/#
    List<String> trending = Arrays.asList("SHRINKS ACUTE", "NUISANCE CHOICE", "BOSOM RELATED",
            "POLICIES BOBBY", "PRAIRIE LUNGS", "DOZING DEPRIVED", "PINCH ARCHIVAL", "ANGEL CAGE",
            "ANGEL CAGE", "AMBLE", "RIGHT MOLDED", "INSULT BUDGED", "HAGGLE DRIVER");
    TextView changeText;
    TextView originalText;
    Matrix matrix = new Matrix();
    Float scale = 30f;
    ScaleGestureDetector SGD;
    int Siz;
    int Ori;
    int Size;
    int Angle;


//  https://developer.android.com/guide/topics/sensors/sensors_motion.html#java
    private SensorManager sensorManager;
    private Sensor rotationSensor;
    private SensorEventListener rotationEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        rotationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        rotationEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);
                // Remap coordinate system
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix, SensorManager.AXIS_X, SensorManager.AXIS_Z, remappedRotationMatrix);
                // Convert to orientations
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);
                for(int i = 0; i < 3; i++) {
                    orientations[i] = (float)(Math.toDegrees(orientations[i]));
                }
                Ori = Math.round(-orientations[2]);   // need to get this reading to public for comparison
                getWindow().getDecorView().findViewById(R.id.change).setRotation(-orientations[2]); // rotate the textview responding to the gyro
                getWindow().getDecorView().findViewById(R.id.button).setRotation(orientations[2]); // rotate the buttonview responding to the gyro this is for fun
                if (Ori < Angle + 5 && Ori > Angle - 5 && Siz < Size + 2 && Size - 2 < Siz){ // comparing the reading
                    TextView pass = findViewById(R.id.messageb);
                    pass.setText("You passed the test!");
                    getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorRight));
                }else{
                    TextView pass = findViewById(R.id.messageb);
                    pass.setText("Keep working!");
                    getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.colorWrong));
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        TextView begin = findViewById(R.id.message);
        begin.setText("Rotate your phone and pinch to change the text size to match the orientation and the text size on the top.");
        changeText = findViewById(R.id.change);
        originalText = findViewById(R.id.original);
        SGD = new ScaleGestureDetector(this, new ScaleListener());
    }

    public void Test (View view){
        int randomNum = ThreadLocalRandom.current().nextInt(0, 13); // set random number to pick the phrase in the list of Trending
        int randomSize = ThreadLocalRandom.current().nextInt(10, 51); // set the random size for original
        int randomAngle = ThreadLocalRandom.current().nextInt(-180,181); // set the random orientation for original
        changeText.setText(trending.get(randomNum));
        originalText.setText(trending.get(randomNum));
        originalText.setTextSize(randomSize);
        getWindow().getDecorView().findViewById(R.id.original).setRotation(randomAngle);
        Angle = randomAngle;
        Size = randomSize;
        TextView pass = findViewById(R.id.message);
        pass.setText("Original angle: " + String.valueOf(Angle) +", original size: " + String.valueOf(Size) +
                "; your angle: " + String.valueOf(Ori) + ", your size: " + String.valueOf(Siz));
    }

    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scale = scale * detector.getScaleFactor();
//            scale = Math.max(1f, Math.min(scale, 5f));
//            matrix.setScale(scale, scale);
            changeText.setTextSize(scale); // need to get this reading public for comparison //
            Siz = Math.round(scale);
//            TextView value = findViewById(R.id.messageb);
//            value.setText(String.valueOf(Siz)); // test the value reading
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(rotationEventListener, rotationSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(rotationEventListener);
    }
}
