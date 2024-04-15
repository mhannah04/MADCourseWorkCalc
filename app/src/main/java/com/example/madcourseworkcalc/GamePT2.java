package com.example.madcourseworkcalc;

import static java.util.Collections.swap;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GamePT2 extends AppCompatActivity {

    private static final String SHAREDPREF_SET="StartTime";
    private static final String SHAREDPREF_TIME="Table";



    TextView name1, name2, name3, name4, name0;
    TextView number1, number2, number3, number4, number0;
    TextView time1, time2, time3, time4, time0;
    TextView date1, date2, date3, date4, date0;


    private boolean finalQuestionPassed =false;

    TextView Timer;
    ImageView imageView3, closeButton2;
    ProgressBar progressBar;
    private boolean isPaused = false;
    private long pausedTime = 0;
    EditText answerBox;
    Button startTimerButton, back_button;

    RelativeLayout relativeLayout2;

    Button submitButton;
    TableLayout scoresTable;
    private long elapsedTime = 0;
    int progressBarInt =0;

    RelativeLayout relativeLayout;
    EditText enterName;
    Button submitNameButton;
    ImageButton closeLayout;

    boolean toastWorkAround = false;
    CountDownTimer countDownTimer;
    CountDownTimer countUpTimer;
    TextView questionBox;

    ArrayList <Integer> numbers = new ArrayList<Integer>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pt2);

        back_button = findViewById(R.id.back_page);
        relativeLayout2 = findViewById(R.id.relativeLayout2);
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

        closeButton2 = findViewById(R.id.closeButton2);
        submitButton.setText("Submit");




        for (int i = 0; i < 12; i++) {
            numbers.add(i + 1);
        }


        shuffleList(numbers);

        back_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent back = new Intent(GamePT2.this, practiseGame.class);
                startActivity(back);
            }

        });
        startTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                startTimerButton.setVisibility(View.GONE);
                submitButton.setVisibility(View.VISIBLE);
                answerBox.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                questionBox.setText((practiseGame.reqNum.getText())+" X "+numbers.get(0));
            }

        });


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(submitButton.getText().equals(getString(R.string.SubmitText2))){submitButton.setText(getString(R.string.SubmitText1));}




                if (!numbers.isEmpty()) {
                    if (answerBox.getText().toString().length() == 0) {
                        Toast.makeText(GamePT2.this, "No value entered!", Toast.LENGTH_SHORT).show();
                    } else if ((Integer.parseInt(String.valueOf(answerBox.getText()))) == (Integer.parseInt(String.valueOf(practiseGame.reqNum.getText()))) * numbers.get(0)) {
                        numbers.remove(0);


                        if (numbers.isEmpty()) {
                            YouWin();
                            return;
                        }
                        questionBox.setText((practiseGame.reqNum.getText())+" X "+numbers.get(0));
                        progressBarInt++;
                        progressBar.setProgress(progressBarInt);
                        answerBox.setText("");



                    } else {
                        Log.w("myApp", numbers.toString());

                        penaltyTimer();
                        answerBox.setText("");
                    }
                }
            }
        });
        answerBox.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {

                    if(submitButton.getText().equals(getString(R.string.SubmitText2))){submitButton.setText(getString(R.string.SubmitText1));}

                    if (!numbers.isEmpty()) {
                        if (answerBox.getText().toString().length() == 0) {
                            Toast.makeText(GamePT2.this, "No value entered!", Toast.LENGTH_SHORT).show();
                            return true;

                        } else if ((Integer.parseInt(String.valueOf(answerBox.getText()))) == (Integer.parseInt(String.valueOf(practiseGame.reqNum.getText()))) * numbers.get(0)) {
                            numbers.remove(0);

                            if (numbers.isEmpty()) {
                                YouWin();
                                return true;
                            }
                            questionBox.setText((practiseGame.reqNum.getText())+" X "+numbers.get(0));
                            progressBarInt++;
                            progressBar.setProgress(progressBarInt);

//                            answerBox.setText("");


                            // Show the keyboard


                            answerBox.setShowSoftInputOnFocus(true);


                        } else {
                            Log.w("myApp", numbers.toString());

                            penaltyTimer();
                            answerBox.setText("");
                        }
                    }

                }

                return false;
            }
        });
    }

    public void YouWin(){
        questionBox.setText("You win!");
        progressBar.setProgress(progressBarInt + 1);
        pauseTimer();
        answerBox.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        answerBox.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(answerBox.getWindowToken(), 0);

        progressBar.setVisibility(View.GONE);
        closeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(answerBox.getWindowToken(), 0);

                    List<Player> players = getPlayersFromSharedPreference();
                    if (players.size() < 5) {

                        Player newPlayer = new Player(userInput, String.valueOf(practiseGame.reqNum.getText()), String.valueOf(Timer.getText()), formattedDate);


                        players.add(newPlayer);

                        Collections.sort(players, new SortTimes());


                        storePlayersFromSharedPreference(players);

                        updateTable(players);

                        relativeLayout2.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.GONE);
                        Timer.setVisibility(View.GONE);

                    } else {
                        Player newPlayer = new Player(userInput, String.valueOf(practiseGame.reqNum.getText()), String.valueOf(Timer.getText()), formattedDate);
                        Player incomingPlayer = players.get(4);
                        SortTimes Compare = new SortTimes();

                        if (Compare.compare(newPlayer, incomingPlayer) < 0) {
                            players.set(4, newPlayer);
                            Collections.sort(players, new SortTimes());

                            storePlayersFromSharedPreference(players);
                            updateTable(players);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.GONE);
                            Timer.setVisibility(View.GONE);
                        } else if (Compare.compare(newPlayer, incomingPlayer) == 0) {
                            updateTable(players);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.GONE);
                            Timer.setVisibility(View.GONE);
                            Toast.makeText(GamePT2.this, "So close! Times are exact tie!", Toast.LENGTH_SHORT).show();
                        } else {
                            updateTable(players);
                            relativeLayout2.setVisibility(View.VISIBLE);
                            relativeLayout.setVisibility(View.GONE);
                            Timer.setVisibility(View.GONE);
                            Toast.makeText(GamePT2.this, "Sorry. Too slow!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }
        });

        closeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout2.setVisibility(View.GONE);
                submitButton.setText(getString(R.string.SubmitText2));
                submitButton.setVisibility(View.VISIBLE);
                Timer.setVisibility(View.VISIBLE);
            }

        });


        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                relativeLayout2.setVisibility(View.VISIBLE);
                submitButton.setText(getString(R.string.SubmitText2));
                submitButton.setVisibility(View.GONE);
                Timer.setVisibility(View.GONE);
            }


        });
    }

    private void startTimer() {
        countUpTimer = new CountDownTimer(Long.MAX_VALUE, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                elapsedTime += 10;

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
        countDownTimer = new CountDownTimer(5000, 1000) {
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
        }.start();
    }

    private void storePlayersFromSharedPreference(List<Player> players) {
        SharedPreferences prefs = getSharedPreferences("playerDetails", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            String keyPrefix = "player_" + i + "_";
            editor.putString(keyPrefix + "name", player.getName());
            editor.putString(keyPrefix + "number", player.getNumber());
            editor.putString(keyPrefix + "time", player.getTime());
            editor.putString(keyPrefix + "date", player.getDate());
        }

        editor.apply();
    }

    private List<Player> getPlayersFromSharedPreference() {
        SharedPreferences prefs = getSharedPreferences("playerDetails", MODE_PRIVATE);
        List<Player> players = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String keyPrefix = "player_" + i + "_";
            String name = prefs.getString(keyPrefix + "name", "");
            String number = prefs.getString(keyPrefix + "number", "");
            String time = prefs.getString(keyPrefix + "time", "");
            String date = prefs.getString(keyPrefix + "date", "");

            if (!name.isEmpty()) {
                Player player = new Player(name, number, time, date);
                players.add(player);
            }
        }

        return players;
    }


    private void pauseTimer() {
        isPaused = true;
        pausedTime = elapsedTime;
        countUpTimer.cancel();
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

    private void updateTable(List<Player> players) {

        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            TextView nameTextView = findViewById(getResources().getIdentifier("name" + i, "id", getPackageName()));
            TextView numberTextView = findViewById(getResources().getIdentifier("numb" + i, "id", getPackageName()));
            TextView timeTextView = findViewById(getResources().getIdentifier("time" + i, "id", getPackageName()));
            TextView dateTextView = findViewById(getResources().getIdentifier("date" + i, "id", getPackageName()));

            nameTextView.setText(player.getName());
            numberTextView.setText(player.getNumber());
            timeTextView.setText(player.getTime());
            dateTextView.setText(player.getDate());
        }
    }

    private void openKeyboard() {
        // Request focus for the EditText field
        answerBox.requestFocus();

        // Show the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(answerBox, InputMethodManager.SHOW_IMPLICIT);
    }


}








