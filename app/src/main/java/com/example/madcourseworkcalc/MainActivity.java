package com.example.madcourseworkcalc;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button HomeButton = (Button) findViewById(R.id.homeButton);
        Button HomeButton2 = (Button) findViewById(R.id.homeButton2);
        Button HomeButton3 = (Button) findViewById(R.id.thirdButton);

        HomeButton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent Calculator = new Intent(MainActivity.this, calculator.class);
                startActivity(Calculator);
            }
        });



        HomeButton2.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Intent Converter = new Intent(MainActivity.this, converter.class);
                startActivity(Converter);
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