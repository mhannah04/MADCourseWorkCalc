package com.example.madcourseworkcalc;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEventListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.hardware.SensorManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

import java.util.List;

public class calculator extends AppCompatActivity {
//https://stackoverflow.com/questions/6645537/how-to-detect-the-swipe-left-or-right-in-android
    private TextView output;

    private TextView degOrRad;
    public boolean chngTrig = false;
    public boolean degOrRadBool = true;
    private SensorManager mSensorManager;

    private double accelerationValue;
    private double accelerationPrevious;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            accelerationValue = Math.sqrt((x*x+y*y+z*z));
            double change= Math.abs(accelerationValue-accelerationPrevious);
            accelerationPrevious = accelerationValue;



             if (change > 0.1)
             {
                 output.setText("");
             }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    private Sensor mAcceletometer;

    private float x1, x2;

    static final int MIN_DISTANCE=150;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        output = findViewById(R.id.textView);
        degOrRad = findViewById(R.id.degRad);
        mXparser.setDegreesMode();

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAcceletometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(sensorEventListener, mAcceletometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(sensorEventListener);
    }



    private void updateSum(String input)
    {
        String oldSum = output.getText().toString().replaceAll(",","");
        StringBuilder formattedSum = new StringBuilder();

        int length = oldSum.length() + input.length();
        for (int i = 0; i < length; i++) {

            if (i < oldSum.length()) {
                formattedSum.append(oldSum.charAt(i));
            } else {
                formattedSum.append(input.charAt(i - oldSum.length()));
            }
        }



        output.setText(formattedSum.toString());
    }


    public void equPushed(View view) {
        String sum = output.getText().toString();
        sum = sum.replaceAll("X", "*"); // Replacing 'X' with '*'
        sum = sum.replaceAll("÷", "/"); // Replacing '÷' with '/'
        sum = sum.replaceAll("cosh⁻¹", "acosh");
        sum = sum.replaceAll("tanh⁻¹", "atanh");
        sum = sum.replaceAll("sinh⁻¹", "asinh");
        sum = sum.replaceAll("÷", "/");
        sum = sum.replaceAll("÷", "/");



        Expression  expression = new Expression(sum);


        String answer = String.valueOf(expression.calculate());

        if (answer.toString().equals("NaN"))
        {
            answer = new String("Error");
        }

        output.setText(answer);

    }



    public void num1Pushed(View view) {
        updateSum("1");
    }
    public void num2Pushed(View view) {
        updateSum("2");
    }
    public void num3Pushed(View view) {
        updateSum("3");
    }
    public void num4Pushed(View view) {
        updateSum("4");
    }
    public void num5Pushed(View view) {
        updateSum("5");
    }
    public void num6Pushed(View view) {
        updateSum("6");
    }
    public void num7Pushed(View view) {
        updateSum("7");
    }
    public void num8Pushed(View view) {
        updateSum("8");
    }
    public void num9Pushed(View view) {
        updateSum("9");
    }
    public void num0Pushed(View view) {
        updateSum("0");
    }
    public void plusPushed(View view) { updateSum("+");}
    public void subtractPushed(View view) { updateSum("-");}

    public void timesPushed(View view) {updateSum("X");}
    public void divPushed(View view) { updateSum("÷");}
    public void opnBrktPushed(View view) {updateSum("(");}
    public void clsBrktPushed(View view) {updateSum(")");}
    public void mcPushed(View view) {}
    public void mrPushed(View view) {}
    public void mpPushed(View view) {}
    public void mmPushed(View view) {}
    public void acPushed(View view) {output.setText("");}
    public void pnPushed(View view) {}
    public void percentagePushed(View view) {updateSum("%");}
    public void TndPushed(View view)
    {
        if (!chngTrig)
        {
            chngTrig=true;

            Button sin = findViewById(R.id.button_sin);
            Button sinh = findViewById(R.id.button_sinh);
            Button cos = findViewById(R.id.button_cos);
            Button cosh = findViewById(R.id.button_cosh);
            Button tan = findViewById(R.id.button_tan);
            Button tanh = findViewById(R.id.button_tanh);
            Button tenX = findViewById(R.id.button_10x);
            Button log10 = findViewById(R.id.button_log10);
            Button eX = findViewById(R.id.button_ex);
            Button In = findViewById(R.id.button_In);

            sin.setText("sin⁻¹");
            sinh.setText("sinh⁻¹");
            cos.setText("cos⁻¹");
            cosh.setText("cosh⁻¹");
            tan.setText("tan⁻¹");
            tanh.setText("tanh⁻¹");
            tenX.setText("2ˣ");
            log10.setText("log₂");
            eX.setText("yˣ");
            In.setText("logᵧ");
        }
        else
        {
            chngTrig=false;

            Button sin = findViewById(R.id.button_sin);
            Button sinh = findViewById(R.id.button_sinh);
            Button cos = findViewById(R.id.button_cos);
            Button cosh = findViewById(R.id.button_cosh);
            Button tan = findViewById(R.id.button_tan);
            Button tanh = findViewById(R.id.button_tanh);
            Button tenX = findViewById(R.id.button_10x);
            Button log10 = findViewById(R.id.button_log10);
            Button eX = findViewById(R.id.button_ex);
            Button In = findViewById(R.id.button_In);

            sin.setText("sin");
            sinh.setText("sinh");
            cos.setText("cos");
            cosh.setText("cosh");
            tan.setText("tan");
            tanh.setText("tanh");
            tenX.setText("10ˣ");
            log10.setText("log₁₀");
            eX.setText("eˣ");
            In.setText("In");
        }
    }
    public void XSquaredPushed(View view) {updateSum("²");}
    public void xCubedPushed(View view) {updateSum("³");}
    public void xToYPushed(View view) {updateSum("^");}
    public void eToXPushed(View view) {updateSum("^");}
    public void tenToXPushed(View view) {}
    public void oneOverXPushed(View view) {}
    public void sqrtXPushed(View view) {}
    public void cbrtXPushed(View view) {}
    public void yRtXPushed(View view) {}
    public void InPushed(View view) {}
    public void logPushed(View view) {}
    public void xFactPushed(View view) {}

    public void sinPushed(View view) {
        Button button_sin = findViewById(R.id.button_sin);

        if (button_sin.getText().toString().equals("sin")) {
            updateSum("sin");
        } else {
            updateSum("sin⁻¹");
        }
    }
    public void cosPushed(View view) {
        Button button_cos = findViewById(R.id.button_cos);

        if (button_cos.getText().toString().equals("cos")) {
            updateSum("cos");
        } else {
            updateSum("cos⁻¹");
        }
    }
    public void tanPushed(View view) {
        Button button_tan = findViewById(R.id.button_tan);

        if (button_tan.getText().toString().equals("tan")) {
            updateSum("tan");
        } else {
            updateSum("tan⁻¹");
        }
    }
    public void ePushed(View view) {}
    public void EEPushed(View view) {}
    public void RadPushed(View view) {

        Button Rad = findViewById(R.id.button_rad);

        if(!degOrRadBool)
        {
            degOrRadBool = true;
            degOrRad.setText("Deg");
            Rad.setText("Deg");
            mXparser.setDegreesMode();
        }
        else
        {
            degOrRadBool = false;
            degOrRad.setText("Rad");
            Rad.setText("Rad");
            mXparser.setRadiansMode();
        }


    }
    public void sinhPushed(View view) {
        Button button_sinh = findViewById(R.id.button_sinh);

        if (button_sinh.getText().toString().equals("sinh")) {
            updateSum("sinh");
        } else {
            updateSum("sinh⁻¹");
        }
    }
    public void coshPushed(View view) {
        Button button_cosh = findViewById(R.id.button_cosh);

        if (button_cosh.getText().toString().equals("cosh")) {
            updateSum("cosh");
        } else {
            updateSum("cosh⁻¹");
        }
    }
    public void tanhPushed(View view) {
        Button button_tanh = findViewById(R.id.button_tanh);

        if (button_tanh.getText().toString().equals("tanh")) {
            updateSum("tanh");
        } else {
            updateSum("tanh⁻¹");
        }
    }
    public void piPushed(View view) {}
    public void RandPushed(View view) {}

    public void decPushed(View view){ updateSum(".");}


    @Override
    public boolean onTouchEvent(MotionEvent swipe){
        switch (swipe.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = swipe.getX();
                break;

            case MotionEvent.ACTION_UP:
                x2 = swipe.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    String currentOutput = output.getText().toString();
                    String newText = currentOutput.replaceAll(",", ""); // Remove commas
                    if (!currentOutput.isEmpty()){
                        newText = newText.substring(0, newText.length() - 1); // Perform swipe action
                    }
                    // Reformat the number with commas
                    StringBuilder formattedSum = new StringBuilder();
                    int length = newText.length();
                    for (int i = 0; i < length; i++) {
                        if (i > 0 && (length - i) % 3 == 0) {
                            formattedSum.append(",");
                        }
                        formattedSum.append(newText.charAt(i));
                    }
                    output.setText(formattedSum.toString());
                }
                break;
        }
        return super.onTouchEvent(swipe);
    }

}