package com.example.madcourseworkcalc;

import static java.util.Collections.swap;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
public class GamePT2 extends AppCompatActivity {

    private static final String SHAREDPREF_SET="StartTime";
    private static final String SHAREDPREF_TIME="Table";



    TextView name1, name2, name3, name4, name0;
    TextView number1, number2, number3, number4, number0;
    TextView time1, time2, time3, time4, time0;
    TextView date1, date2, date3, date4, date0;




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

        name0 = findViewById(R.id.name0);
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);
        name4 = findViewById(R.id.name4);

        number0 = findViewById(R.id.numb0);
        number1 = findViewById(R.id.numb1);
        number2 = findViewById(R.id.numb2);
        number3 = findViewById(R.id.numb3);
        number4 = findViewById(R.id.numb4);

        time0 = findViewById(R.id.time0);
        time1 = findViewById(R.id.time1);
        time2 = findViewById(R.id.time2);
        time3 = findViewById(R.id.time3);
        time4 = findViewById(R.id.time4);

        date0 = findViewById(R.id.date0);
        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
        date3 = findViewById(R.id.date3);
        date4 = findViewById(R.id.date4);




        for (int i = 0; i < 2; i++) {
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
                    } else {
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
                                Date c = Calendar.getInstance().getTime();
                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                String formattedDate = df.format(c);
                                if (TextUtils.isEmpty(userInput)) {
                                    Toast.makeText(GamePT2.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    for (int j = 0; j < 5; j++) {
                                        TextView textView = (TextView) findViewById(getResources().getIdentifier("name" + j, "id", getPackageName()));
                                        String textViewContents = textView.getText().toString();

                                        if (TextUtils.isEmpty(textViewContents)) {

                                            displaySavedPlayer(
                                                    findViewById(getResources().getIdentifier("name" + j, "id", getPackageName())),
                                                    findViewById(getResources().getIdentifier("numb" + j, "id", getPackageName())),
                                                    findViewById(getResources().getIdentifier("time" + j, "id", getPackageName())),
                                                    findViewById(getResources().getIdentifier("date" + j, "id", getPackageName())));

                                            ((TextView)findViewById(getResources().getIdentifier("name" + (j+1), "id", getPackageName()))).setText(String.valueOf(enterName.getText()));
                                            ((TextView)findViewById(getResources().getIdentifier("numb" + (j+1), "id", getPackageName()))).setText(String.valueOf(practiseGame.reqNum.getText()));
                                            ((TextView)findViewById(getResources().getIdentifier("time" + (j+1), "id", getPackageName()))).setText(String.valueOf(Timer.getText()));
                                            ((TextView)findViewById(getResources().getIdentifier("date" + (j+1), "id", getPackageName()))).setText(formattedDate);


                                            storeLastPlayerFromSharedPreference(
                                                    findViewById(getResources().getIdentifier("name" + (j+1), "id", getPackageName())),
                                                    findViewById(getResources().getIdentifier("numb" + (j+1), "id", getPackageName())),
                                                    findViewById(getResources().getIdentifier("time" + (j+1), "id", getPackageName())),
                                                    findViewById(getResources().getIdentifier("date" + (j+1), "id", getPackageName())));

                                            break;
                                        }
                                }
                                    scoresTable.setVisibility(View.VISIBLE);
                                    relativeLayout.setVisibility(View.GONE);
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

    private void displayCurrentName(TextView name, TextView number, TextView time, TextView date){
        // Get the array of player information
        String[] playerInfo = getLastPlayerFromSharedPreference();

        // Extract the name from the array (assuming it's at index 0)
        String nameText = playerInfo[0];
        String numberText = playerInfo[1];
        String timeText = playerInfo[2];
        String dateText = playerInfo[3];

        // Set the text of the provided TextView to the retrieved name
        name.setText(nameText);
        number.setText(numberText);
        time.setText(timeText);
        date.setText(dateText);
    }
    public void displaySavedPlayer(TextView name, TextView number, TextView time, TextView date){
        // Get the array of player information
        String[] playerInfo = getLastPlayerFromSharedPreference();

        // Extract the name from the array (assuming it's at index 0)
        String nameText = playerInfo[0];
        String numberText = playerInfo[1];
        String timeText = playerInfo[2];
        String dateText = playerInfo[3];

        // Set the text of the provided TextView to the retrieved name
        name.setText(nameText);
        number.setText(numberText);
        time.setText(timeText);
        date.setText(dateText);
    }

    private String[] getLastPlayerFromSharedPreference(){
        SharedPreferences prefs = getSharedPreferences("playerDetails", MODE_PRIVATE);
        String name = prefs.getString("name", "");
        String number = prefs.getString("number", "");
        String time = prefs.getString("time", "");
        String date = prefs.getString("date", "");

        // Create an array to hold all the information
        String[] playerInfo = {name, number, time, date};

        return playerInfo;
    }

    private void storeLastPlayerFromSharedPreference(TextView name, TextView number, TextView time, TextView date){
        String nameText = name.getText().toString();
        String numberText = number.getText().toString();
        String timeText = time.getText().toString();
        String dateText = date.getText().toString();

        SharedPreferences prefs = getSharedPreferences("playerDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("name", nameText);
        editor.putString("number", numberText);
        editor.putString("time", timeText);
        editor.putString("date", dateText);

        editor.commit();
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



