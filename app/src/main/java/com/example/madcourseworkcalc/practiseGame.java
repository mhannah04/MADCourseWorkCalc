package com.example.madcourseworkcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class practiseGame extends AppCompatActivity {

    public static EditText reqNum;
    private Button submitButton;
    private int[] numbers = new int[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practise_game);

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }

        reqNum = findViewById(R.id.numberEditText);

        submitButton = findViewById(R.id.submitButton);
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
    }
}
