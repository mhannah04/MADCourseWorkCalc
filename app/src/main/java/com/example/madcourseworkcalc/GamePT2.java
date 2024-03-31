package com.example.madcourseworkcalc;

import static java.util.Collections.swap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class GamePT2 extends AppCompatActivity {
    TextView Timer;
    private boolean isPaused = false;
    private long pausedTime = 0;
    EditText answerBox;
    Button startTimerButton;
    Button submitButton;
    private long elapsedTime = 0;

    CountDownTimer countUpTimer;
    TextView questionBox;

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

        shuffleList(numbers);

        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                startTimerButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.VISIBLE);
                questionBox.setText((String.valueOf(practiseGame.reqNum.getText()))+" X "+numbers.get(0));
            }

        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (numbers.size() >1)
                    {
                        if (answerBox.getText().toString().trim().length() ==0)
                        {
                            Toast.makeText(GamePT2.this, "No value entered!", Toast.LENGTH_SHORT).show();
                        }
                        else if ((Integer.parseInt(String.valueOf(answerBox.getText()))) == (Integer.parseInt(String.valueOf(practiseGame.reqNum.getText())))*numbers.get(0))
                        {
                            numbers.remove(0);
                            questionBox.setText((String.valueOf(practiseGame.reqNum.getText()))+" X "+numbers.get(0));
                        }
                        else{
                            Toast.makeText(GamePT2.this, "test", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(GamePT2.this, "LIST EMPTY", Toast.LENGTH_SHORT).show();
                        questionBox.setText("game over");
                        pauseTimer();
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
            pauseTimer();
            }
        }.start();
    }

    private void pauseTimer() {
        isPaused = true;
        pausedTime = elapsedTime;
        countUpTimer.cancel(); // Cancel the current timer
    }

    public static void shuffleList(ArrayList<Integer> a) {
        int n = a.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            swap(a, i, change);
        }
    }
}
