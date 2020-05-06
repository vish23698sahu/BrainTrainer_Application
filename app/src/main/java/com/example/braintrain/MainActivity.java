package com.example.braintrain;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int correctAnswerLoc;

    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;

    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;

    ConstraintLayout afterStart;

    int score = 0;
    int totalQuestion = 0;

    public void playAgain(View view){

        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);

        score = 0;
        totalQuestion = 0;
        timerTextView.setText("30");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestion));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText(" ");

        new CountDownTimer(20100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                resultTextView.setVisibility(View.INVISIBLE);
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                playAgainButton.setVisibility(View.VISIBLE);
                sumTextView.setVisibility(View.INVISIBLE);
                timerTextView.setVisibility(View.INVISIBLE);


//                Intent intent = new Intent(getApplicationContext(), ScoreActivity.class);
//                startActivity(intent);
            }
        }.start();
    }

    public void answer(View view){
        if (Integer.toString(correctAnswerLoc).equals(view.getTag().toString())){
            resultTextView.setText("Correct..!");
            score++;
        }
        else{
            resultTextView.setText("Wrong...");
        }
        totalQuestion++;

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestion));
        newQuestion();
    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        afterStart.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));
        correctAnswerLoc = random.nextInt(4);

        answers.clear();

        for (int i=0 ; i<4 ; i++) {
            if (i == correctAnswerLoc) {
                answers.add(a + b);
            } else {
                int wronganswer = random.nextInt(41);
                while (wronganswer == a + b) {
                    wronganswer = random.nextInt(41);
                }
                answers.add(wronganswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);

        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        sumTextView = findViewById(R.id.sumTextView);
        timerTextView = findViewById(R.id.timerTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);

        afterStart = findViewById(R.id.afterStart);

        goButton.setVisibility(View.VISIBLE);
        afterStart.setVisibility(View.INVISIBLE);
    }
}