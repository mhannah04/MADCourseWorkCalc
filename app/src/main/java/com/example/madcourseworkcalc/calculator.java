package com.example.madcourseworkcalc;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

import java.util.ArrayList;


/**
 * Calculator class used to use calculator activity
 * This class contains all code required to run calculator activity
 * Code uses Math Parser library for help in regard to dealing with mathematical expressions
 * Code also makes use of a movement sensor, on the event of the user shaking the phone enough
 * the screen will clear
 * Additionally, if the user swipes the screen then the furthest digit on the right will be removed
 */




public class calculator extends AppCompatActivity {
//https://stackoverflow.com/questions/6645537/how-to-detect-the-swipe-left-or-right-in-android

    public ArrayList <Integer> lastInputLength = new ArrayList<Integer>();
    public String savedNum;
    private char[] validChars = {'1','2','3','4','5','6','7','8','9','0', '.'};
    public boolean invalid =false;
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
                 lastInputLength.clear();
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

        if(oldSum.contains("Error")|| oldSum.contains("Infinity")){
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
        lastInputLength.clear();
        String sum = output.getText().toString();
        sum = sum.replaceAll("X", "*"); // Replacing 'X' with '*'
        sum = sum.replaceAll("÷", "/"); // Replacing '÷' with '/'
        sum = sum.replaceAll("₂", "2");
        sum = sum.replaceAll("cosh⁻¹", "acosh");
        sum = sum.replaceAll("tanh⁻¹", "atanh");
        sum = sum.replaceAll("sinh⁻¹", "asinh");
        sum = sum.replaceAll("÷", "/");
        sum = sum.replaceAll("log₁₀\\(","log10(");



        Expression  expression = new Expression(sum);

        String answer = String.valueOf(expression.calculate());

        if (answer.toString().equals("NaN"))
        {
            answer = new String("Error");
            output.setText(answer);
            return;
        }
        if (answer.toString().equals("Infinity"))
        {
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
        updateSum("8",1);

    }
    public void num9Pushed(View view) {
            updateSum("9",1);
    }
    public void num0Pushed(View view) {
        updateSum("0",1);
    }
    public void plusPushed(View view) {

        updateSum("+",1);
    }
    public void subtractPushed(View view) {
        updateSum("-",1);
    }
    public void timesPushed(View view) {
        updateSum("X",1);
    }
    public void divPushed(View view) {
        updateSum("÷",1);
    }
    public void opnBrktPushed(View view) {
        updateSum("(",1);
    }
    public void clsBrktPushed(View view) {
        updateSum(")",1);

    }
    public void mcPushed(View view) {
        savedNum="";
    }
    public void mrPushed(View view) {updateSum(savedNum,1);}
    public void mpPushed(View view) {

        invalid=false;
        for (int i=0; i< (output.getText().toString().length());i++){
            char current = output.getText().toString().charAt(i);
            if (!inArray(current, validChars)){
                invalid =true;
                Toast.makeText(calculator.this, "Press equals before saving number.", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        if (!invalid){
            savedNum = output.getText().toString();
        }

    }
    public void mmPushed(View view) {updateSum(("-"+savedNum),2);}
    public void acPushed(View view) {output.setText("");}
    public void pnPushed(View view) {
        String currentSum = output.getText().toString();
        StringBuilder stringBuilder = new StringBuilder(currentSum);

        if (stringBuilder.charAt(0) != '-') {
            // Remove the '-' sign from the beginning
            stringBuilder.insert(0, "-");
        } else {
            // Add a '+' sign at the beginning

            stringBuilder.deleteCharAt(0);
        }

        currentSum = stringBuilder.toString();

        updateSum("Error",1);
        updateSum(currentSum,1);

    }
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



            sin.setText("sin⁻¹");
            sinh.setText("sinh⁻¹");
            cos.setText("cos⁻¹");
            cosh.setText("cosh⁻¹");
            tan.setText("tan⁻¹");
            tanh.setText("tanh⁻¹");

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



            sin.setText("sin");
            sinh.setText("sinh");
            cos.setText("cos");
            cosh.setText("cosh");
            tan.setText("tan");
            tanh.setText("tanh");

        }
    }
    public void XSquaredPushed(View view) {updateSum("²",1);}
    public void xCubedPushed(View view) {updateSum("³",1);}
    public void xToYPushed(View view) {updateSum("^",1);}
    public void eToXPushed(View view) {updateSum("e^",2);}
    public void tenToXPushed(View view) {updateSum("10^(",4);}
    public void oneOverXPushed(View view) {updateSum("1÷(",3);}
    public void sqrtXPushed(View view) {updateSum("√",1);}
    public void cbrtXPushed(View view) {updateSum("∛",1);}
    public void twoToXPushed(View view) {updateSum("2^(",3);}
    public void InPushed(View view) {
        updateSum("In(",3);
    }
    public void logPushed(View view) {updateSum("log₁₀(",6);}
    public void xFactPushed(View view) {updateSum("!",1);}

    public void sinPushed(View view) {
        Button button_sin = findViewById(R.id.button_sin);
        if (button_sin.getText().toString().equals("sin")) {
            updateSum("sin(",4);
        } else {
            updateSum("sin⁻¹(",6);
        }
    }

    public void cosPushed(View view) {
        Button button_cos = findViewById(R.id.button_cos);


        if (button_cos.getText().toString().equals("cos")) {
            updateSum("cos(",4);
        } else {
            updateSum("cos⁻¹(",6);
        }

    }
    public void tanPushed(View view) {
        Button button_tan = findViewById(R.id.button_tan);

        if (button_tan.getText().toString().equals("tan")) {
            updateSum("tan(",4);
        } else {
            updateSum("tan⁻¹(",6);
        }
    }
    public void ePushed(View view) {updateSum("e",1);}
    public void log2Pushed(View view) {updateSum("log₂(",5);}
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


        if (button_sinh.getText().toString().equals("sinh(")) {
            updateSum("sinh(",5);
        } else {
            updateSum("sinh⁻¹(",7);
        }

    }
    public void coshPushed(View view) {
        Button button_cosh = findViewById(R.id.button_cosh);

        if (button_cosh.getText().toString().equals("cosh(")) {
            updateSum("cosh(",5);
        } else {
            updateSum("cosh⁻¹(",7);
        }

    }
    public void tanhPushed(View view) {
        Button button_tanh = findViewById(R.id.button_tanh);

        if (button_tanh.getText().toString().equals("tanh(")) {
            updateSum("tanh(",5);
        } else {
            updateSum("tanh⁻¹(",7);
        }

    }
    public void piPushed(View view) {updateSum("π",1);}
    public void RandPushed(View view) {

        double Rand = 0 + Math.random() * (1-0);

        String randToString = String.valueOf(Rand);

        updateSum(randToString,1);
    }
    public void decPushed(View view) {
            updateSum(".",1);
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
                        }else if (currentOutput.equals("Infinity")){
                            newText = newText.substring(0,newText.length()-8);
                        }
                        else if(!lastInputLength.isEmpty()){
                            int index = lastInputLength.size()-1;
                            newText = newText.substring(0, newText.length() - (int)lastInputLength.get(index)); // Perform swipe action
                            lastInputLength.remove(index);
                        }
                        else {
                            newText = newText.substring(0, newText.length() - 1);
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


    public static boolean inArray(char currentChar, char[] charArray){
        for (char c : charArray){
            if (currentChar == c){
                return true;
            }
        }
        return false;
    }

}