package com.example.madcourseworkcalc;

import static java.util.Collections.swap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.text.DecimalFormat;

public class GamePT2 extends AppCompatActivity {

    TextView name1, name2, name3, name4, name5;
    TextView number1, number2, number3, number4, number5;
    TextView time1, time2, time3, time4, time5;
    TextView date1, date2, date3, date4, date5;




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

    RelativeLayout relativeLayout;
    EditText enterName;
    Button submitNameButton;
    ImageButton closeLayout;

    CountDownTimer countDownTimer;
    CountDownTimer countUpTimer;
    TextView questionBox;

    String[][] matrix = {{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}};

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
        relativeLayout = findViewById(R.id.submitName);
        enterName = findViewById(R.id.enterName);
        submitNameButton = findViewById(R.id.submitNameButton);
        closeLayout = findViewById(R.id.imageButton);

        name1 = findViewById(R.id.nameOne);
        name2 = findViewById(R.id.nameTwo);
        name3 = findViewById(R.id.nameThree);
        name4 = findViewById(R.id.nameFour);
        name5 = findViewById(R.id.nameFive);

        number1 = findViewById(R.id.numbOne);
        number2 = findViewById(R.id.numbTwo);
        number3 = findViewById(R.id.numbThree);
        number4 = findViewById(R.id.numbFour);
        number5 = findViewById(R.id.numbFive);

        time1 = findViewById(R.id.timeOne);
        time2 = findViewById(R.id.timeTwo);
        time3 = findViewById(R.id.timeThree);
        time4 = findViewById(R.id.timeFour);
        time5 = findViewById(R.id.timeFive);

        date1 = findViewById(R.id.dateOne);
        date2 = findViewById(R.id.dateTwo);
        date3 = findViewById(R.id.dateThree);
        date4 = findViewById(R.id.dateFour);
        date5 = findViewById(R.id.dateFive);




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
                        relativeLayout.setVisibility(View.VISIBLE);

                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(answerBox.getWindowToken(), 0);

                        progressBar.setVisibility(View.GONE);
                        closeLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v){
                                relativeLayout.setVisibility(View.GONE);
                                submitButton.setVisibility(View.VISIBLE);
                                answerBox.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });

                        submitNameButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String userInput = enterName.getText().toString();

                                if (TextUtils.isEmpty(userInput)) {
                                    Toast.makeText(GamePT2.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    relativeLayout.setVisibility(View.GONE);
                                    scoresTable.setVisibility(View.VISIBLE);
                                    for (int i=0; i<matrix.length; ++i) {
                                        for (int j=0; j<matrix[i].length; ++j) {
                                            matrix[i][j] = String.valueOf(i);
                                            for(int k=0; k<5; k++) {
                                                if (name1.getText()!="testing") {
                                                    name1.setText(enterName.getText());
                                                    number1.setText((String.valueOf(practiseGame.reqNum.getText())));
                                                    time1.setText(Timer.getText());
                                                    Date c = Calendar.getInstance().getTime();
                                                    SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
                                                    String formattedDate = df.format(c);
                                                    date1.setText(formattedDate);

                                                }
                                            }
                                        }
                                    }
                                }

                            }

                        });

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



