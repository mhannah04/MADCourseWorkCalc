package com.example.madcourseworkcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * practise game class used to allow the player to input their chosen times table
 * suitable error handling put into place
 */
public class practiseGame extends AppCompatActivity {

    public static EditText reqNum;
    Button back_button;

    private Button submitButton;
    private int[] numbers = new int[12];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practise_game);
        back_button = findViewById(R.id.back_page);
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }




        reqNum = findViewById(R.id.numberEditText);

        submitButton = findViewById(R.id.submitButton);


        reqNum.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    Log.d("MyApp","Enter key input");
                    String userInput = reqNum.getText().toString();
                    if (TextUtils.isEmpty(userInput)) {
                        Toast.makeText(practiseGame.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                    } else {
                        int userNumber = Integer.parseInt(userInput);
                        boolean found = false;
                        for (int number : numbers) {
                            if (number == userNumber) {
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            Log.d("MyApp","Success");
                            Intent startGame = new Intent(practiseGame.this, GamePT2.class);
                            startActivity(startGame);
                        } else {
                            Toast.makeText(practiseGame.this, "Ensure number is between 1-12", Toast.LENGTH_SHORT).show();

                        }
                        return false;
                    }}


                return false;
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userInput = reqNum.getText().toString();
                if (TextUtils.isEmpty(userInput)) {
                    Toast.makeText(practiseGame.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                } else {
                    int userNumber = Integer.parseInt(userInput);
                    boolean found = false;
                    for (int number : numbers) {
                        if (number == userNumber) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        Intent startGame = new Intent(practiseGame.this, GamePT2.class);
                        startActivity(startGame);
                    } else {
                        Toast.makeText(practiseGame.this, "Ensure number is between 1-12", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent back = new Intent(practiseGame.this, MainActivity.class);
                startActivity(back);
            }
        });
    }
}