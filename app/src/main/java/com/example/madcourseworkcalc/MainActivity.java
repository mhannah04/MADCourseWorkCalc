package com.example.madcourseworkcalc;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
/**
 * Main activity class used to move from activity to activity
 * Buttons to go to either the calculator section of the game section
 */



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button HomeButton = (Button) findViewById(R.id.homeButton);
        Button HomeButton3 = (Button) findViewById(R.id.toGameButton);

        HomeButton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent Calculator = new Intent(MainActivity.this, calculator.class);
                startActivity(Calculator);
            }
        });



        HomeButton3.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent Game = new Intent(MainActivity.this, practiseGame.class);
                startActivity(Game);
            }
        });

    }
}