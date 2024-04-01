package com.example.madcourseworkcalc;

import static java.util.Collections.swap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class GamePT2 extends AppCompatActivity {
    TextView Timer;
    ImageView imageView3;
    ProgressBar progressBar;
    private boolean isPaused = false;
    private long pausedTime = 0;
    EditText answerBox;
    Button startTimerButton;
    Button submitButton;
    TableLayout scoresTable;
    private long elapsedTime = 0;
    int progressBarInt =0;

    CountDownTimer countDownTimer;
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
        progressBar = findViewById(R.id.progressBar);
        imageView3 = findViewById(R.id.imageView3);
        scoresTable = findViewById(R.id.scoresTable);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

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
                answerBox.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
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
                            progressBarInt++;
                            questionBox.setText((String.valueOf(practiseGame.reqNum.getText()))+" X "+numbers.get(0));
                            progressBar.setProgress(progressBarInt);

                        }
                        else{
                            penaltyTimer();
                        }
                    }
                    else {
                        questionBox.setText("You win!");
                        progressBar.setProgress(progressBarInt+1);
                        pauseTimer();

                        submitButton.setVisibility(View.GONE);
                        answerBox.setVisibility(View.GONE);
                        scoresTable.setVisibility(View.VISIBLE);

                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(answerBox.getWindowToken(), 0);

                    }
                }

        });
    }


    public static void hideKeyboard(GamePT2 activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(GamePT2.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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

    private void penaltyTimer() {
        countDownTimer = new CountDownTimer(5000, 1000) { // Change 10000 to 5000
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = (millisUntilFinished / 1000) % 60;
                imageView3.setVisibility(View.VISIBLE);
                submitButton.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                answerBox.setVisibility(View.GONE);
                questionBox.setVisibility(View.GONE);
                Timer.bringToFront();
            }

            @Override
            public void onFinish() {
                imageView3.setVisibility(View.GONE);
                submitButton.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                answerBox.setVisibility(View.VISIBLE);
                questionBox.setVisibility(View.VISIBLE);
            }
        }.start(); // Start the timer immediately after it's created
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



