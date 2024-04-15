package com.example.madcourseworkcalc;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

import java.util.ArrayList;


public class calculator extends AppCompatActivity {
//https://stackoverflow.com/questions/6645537/how-to-detect-the-swipe-left-or-right-in-android

    public ArrayList <Integer> lastInputLength = new ArrayList<Integer>();
    private TextView output;
    private TextView degOrRad;
    public boolean chngTrig = false;
    public boolean degOrRadBool = true;
    private SensorManager mSensorManager;
    private double accelerationValue;
    private double accelerationPrevious;
    private Sensor mAcceletometer;
    private float x1, x2;
    static final int MIN_DISTANCE=150;

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            accelerationValue = Math.sqrt((x*x+y*y+z*z));
            double change= Math.abs(accelerationValue-accelerationPrevious);
             if (change > 15)
             {
                 output.setText("");
             }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

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

    private void updateSum(String input, int inputLength)
    {
        lastInputLength.add(inputLength);

        String oldSum = output.getText().toString();

        if(oldSum.contains("Error")){
            oldSum="";
        }
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
            output.setText(answer);
            return;
        }

        String leftString;
        int decimalPoint = answer.indexOf(".");
        if (decimalPoint != -1) {
            leftString = answer.substring(0, decimalPoint);
        } else {
            leftString = answer.toString();
        }

        StringBuilder addCommas = new StringBuilder();
        int count =0;
        for (int i=leftString.length()-1;i>=0;i--){
            addCommas.insert(0,leftString.charAt(i));
            count++;
            if (count %3 ==0 && i !=0){
                addCommas.insert(0,",");
            }
        }

        if (decimalPoint != -1) {
            addCommas.append(answer.substring(decimalPoint));
        }
        output.setText(addCommas);
    }


    public void num1Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("1",1);
        }
        else{
            updateSum("1",1);
        }
    }
    public void num2Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("2",1);
        }
        else{
            updateSum("2",1);
        }
    }
    public void num3Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("3",1);
        }
        else{
            updateSum("3",1);
        }
    }
    public void num4Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("4",1);
        }
        else{
            updateSum("4",1);
        }
    }
    public void num5Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("5",1);
        }
        else{
            updateSum("5",1);
        }
    }
    public void num6Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("6",1);
        }
        else{
            updateSum("6",1);
        }
    }
    public void num7Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("7",1);
        }
        else{
            updateSum("7",1);
        }
    }
    public void num8Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("8",1);
        }
        else{
            updateSum("8",1);
        }
    }
    public void num9Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("9",1);
        }
        else{
            updateSum("9",1);
        }
    }
    public void num0Pushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("0",1);
        }
        else{
            updateSum("0",1);
        }
    }
    public void plusPushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("+",1);
        }
        else{
            updateSum("+",1);
        }
    }
    public void subtractPushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("-",1);
        }
        else{
            updateSum("-",1);
        }
    }
    public void timesPushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("X",1);
        }
        else{
            updateSum("X",1);
        }
    }
    public void divPushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("÷",1);
        }
        else{
            updateSum("÷",1);
        }
    }
    public void opnBrktPushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum("(",1);
        }
        else{
            updateSum("(",1);
        }
    }
    public void clsBrktPushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum(")",1);
        }
        else{
            updateSum(")",1);
        }
    }
    public void mcPushed(View view) {}//TODO
    public void mrPushed(View view) {}//TODO
    public void mpPushed(View view) {}//TODO
    public void mmPushed(View view) {}//TODO
    public void acPushed(View view) {output.setText("");}
    public void pnPushed(View view) {}
    public void percentagePushed(View view) {updateSum("%",1);}
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
    public void XSquaredPushed(View view) {updateSum("²",1);}
    public void xCubedPushed(View view) {updateSum("³",1);}
    public void xToYPushed(View view) {updateSum("^",1);}
    public void eToXPushed(View view) {updateSum("^",1);}
    public void tenToXPushed(View view) {}//TODO
    public void oneOverXPushed(View view) {}//TODO
    public void sqrtXPushed(View view) {}//TODO
    public void cbrtXPushed(View view) {updateSum("∛",1);}//TODO
    public void yRtXPushed(View view) {}//TODO
    public void InPushed(View view) {}//TODO
    public void logPushed(View view) {}//TODO
    public void xFactPushed(View view) {}//TODO

    public void sinPushed(View view) {
        Button button_sin = findViewById(R.id.button_sin);

        if(output.getText().toString().contains("Error")) {
            output.setText("");
            if (button_sin.getText().toString().equals("sin")) {
                updateSum("sin(",4);
            } else {
                updateSum("sin⁻¹(",6);
            }
        }
        else {
            if (button_sin.getText().toString().equals("sin")) {
                updateSum("sin(",4);
            } else {
                updateSum("sin⁻¹(",6);
            }
        }
    }

    public void cosPushed(View view) {
        Button button_cos = findViewById(R.id.button_cos);

        if(output.getText().toString().contains("Error")) {
            output.setText("");
            if (button_cos.getText().toString().equals("cos")) {
                updateSum("cos(",4);
            } else {
                updateSum("cos⁻¹(",6);
            }
        }
        else {
            if (button_cos.getText().toString().equals("cos")) {
                updateSum("cos(",4);
            } else {
                updateSum("cos⁻¹(",6);
            }
        }
    }
    public void tanPushed(View view) {
        Button button_tan = findViewById(R.id.button_tan);

        if(output.getText().toString().contains("Error")) {
            output.setText("");
            if (button_tan.getText().toString().equals("tan")) {
                updateSum("tan(",4);
            } else {
                updateSum("tan⁻¹(",6);
            }
        }
        else {
            if (button_tan.getText().toString().equals("tan")) {
                updateSum("tan(",4);
            } else {
                updateSum("tan⁻¹(",6);
            }
        }
    }
    public void ePushed(View view) {}//TODO
    public void EEPushed(View view) {}///TODO
    public void RadPushed(View view) {

        Button Rad = findViewById(R.id.button_rad);

        if(!degOrRadBool)
        {
            degOrRadBool = true;
            degOrRad.setText("Deg");
            Rad.setText("Rad");
            mXparser.setDegreesMode();
        }
        else
        {
            degOrRadBool = false;
            degOrRad.setText("Rad");
            Rad.setText("Deg");
            mXparser.setRadiansMode();
        }

    }
    public void sinhPushed(View view) {
        Button button_sinh = findViewById(R.id.button_sinh);

        if(output.getText().toString().contains("Error")) {
            output.setText("");
            if (button_sinh.getText().toString().equals("sinh(")) {
                updateSum("sinh(",5);
            } else {
                updateSum("sinh⁻¹(",7);
            }
        }
        else {
            if (button_sinh.getText().toString().equals("sinh(")) {
                updateSum("sinh(",5);
            } else {
                updateSum("sinh⁻¹(",7);
            }
        }
    }
    public void coshPushed(View view) {
        Button button_cosh = findViewById(R.id.button_cosh);

        if(output.getText().toString().contains("Error")) {
            output.setText("");
            if (button_cosh.getText().toString().equals("cosh(")) {
                updateSum("cosh(",5);
            } else {
                updateSum("cosh⁻¹(",7);
            }
        }
        else {
            if (button_cosh.getText().toString().equals("cosh(")) {
                updateSum("cosh(",5);
            } else {
                updateSum("cosh⁻¹(",7);
            }
        }
    }
    public void tanhPushed(View view) {
        Button button_tanh = findViewById(R.id.button_tanh);

        if(output.getText().toString().contains("Error")) {
            output.setText("");
            if (button_tanh.getText().toString().equals("tanh(")) {
                updateSum("tanh(",5);
            } else {
                updateSum("tanh⁻¹(",7);
            }
        }
        else {
            if (button_tanh.getText().toString().equals("tanh(")) {
                updateSum("tanh(",5);
            } else {
                updateSum("tanh⁻¹(",7);
            }
        }
    }
    public void piPushed(View view) {}
    public void RandPushed(View view) {}
    public void decPushed(View view) {
        if(output.getText().toString().contains("Error")){
            output.setText("");
            updateSum(".",1);
        }
        else{
            updateSum(".",1);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent swipe) {
        switch (swipe.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = swipe.getX();
                break;

            case MotionEvent.ACTION_UP:
                x2 = swipe.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    String currentOutput = output.getText().toString();
                    String newText = currentOutput.replaceAll(",", "");


                    if (!currentOutput.isEmpty()) {
                        if(currentOutput.equals("Error")){
                            newText = newText.substring(0, newText.length() - 5);
                            newText = newText.substring(0, newText.length() - 5);
                        }else {
                            int index = lastInputLength.size()-1;
                            newText = newText.substring(0, newText.length() - (int)lastInputLength.get(index)); // Perform swipe action
                            lastInputLength.remove(index);
                        }

                    }

                    output.setText(newText);
                }
                break;
        }
        return super.onTouchEvent(swipe);
    }

    public void back_button(View view) {

        Intent back = new Intent(calculator.this, MainActivity.class);
        startActivity(back);

    }

}