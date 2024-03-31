package com.example.madcourseworkcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class GamePT2 extends AppCompatActivity {
    TextView Timer;
    EditText answerBox;
    Button startTimerButton;
    Button submitButton;
    boolean regenRandomNumb=true;
    private long elapsedTime = 0;
    int randomIndex;
    CountDownTimer countUpTimer;
    TextView questionBox;
    Random rand = new Random();

    ArrayList <Integer> numbers = new ArrayList<Integer>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pt2);

        Timer = findViewById(R.id.clock);
        questionBox = findViewById(R.id.questionBox);
        startTimerButton = findViewById(R.id.startButton);
        submitButton = findViewById(R.id.submitButton);
        answerBox = findViewById(R.id.answerBox);

        for (int i = 0; i < 12; i++) {
            numbers.add(i + 1);
        }

         if (regenRandomNumb)
         {
             randomIndex = rand.nextInt(numbers.size());
             regenRandomNumb = false;
         }

        startTimerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                startTimer();

                //Display multiplication questions
                //questionBox.setText(String.valueOf(getRandom(numbers)));

                questionBox.setText((String.valueOf(practiseGame.reqNum.getText()))+" X "+numbers.get(randomIndex));
                startTimerButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.VISIBLE);
            }
        });


        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if ((Integer.parseInt(String.valueOf(answerBox.getText()))) == (Integer.parseInt(String.valueOf(practiseGame.reqNum.getText())))*numbers.get(randomIndex))
                {
                    numbers.remove(randomIndex);
                    regenRandomNumb = true;
                    questionBox.setText((String.valueOf(practiseGame.reqNum.getText()))+" X "+numbers.get(randomIndex));
                }

            }
        });
    }

    private void startTimer() {
        countUpTimer = new CountDownTimer(Long.MAX_VALUE, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                elapsedTime += 10; // Incrementing by 10 milliseconds

                long mins = (elapsedTime / (1000 * 60)) % 60;
                long secs = (elapsedTime / 1000) % 60;
                long miliSecs = elapsedTime % 1000;

                String timer = String.format(Locale.getDefault(), "%d:%02d:%03d", mins, secs, miliSecs);
                Timer.setText(timer);
            }

            @Override
            public void onFinish() {
                // This won't happen as we are using Long.MAX_VALUE
            }
        }.start();
    }



}
