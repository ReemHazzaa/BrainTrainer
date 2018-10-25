package com.example.reem.braintrainer;


import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton, button0, button1,button2, button3, playAgainButton;
    TextView sumTextView, scoreTextView, timerTextView, resultTextView;
    ConstraintLayout gameLayout;


    ArrayList<Integer> answers = new ArrayList<>();
    int locationOfCorrectAnswer;

    //for the score txt view
    int numberOfQuestions =0;
    int score = 0;

    public void playAgain(View view){
        // reset game
        score =0;
        numberOfQuestions =0;
        //answers.clear();

        // Enable buttons
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);

                // Disable all buttons in the gridLayout
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void newQuestion(){
        // constructing the problem for the user to solve
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        int correctAnswer = a + b;

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(correctAnswer);
            }else{
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == correctAnswer){
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }



    public void chooseAnswer(View view){
        String clickedButtonTagNumber = view.getTag().toString();
        if (clickedButtonTagNumber.equals(Integer.toString(locationOfCorrectAnswer))){
            resultTextView.setVisibility(View.VISIBLE);
            resultTextView.setText("Correct :)");
            score ++;  // increase the score by one each time the user gets a correct ans.
        }else{
            resultTextView.setText("Wrong Answer :(");
            resultTextView.setVisibility(View.VISIBLE);
        }
        numberOfQuestions++; // increase no of Q's answered.
        // update the score textView
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();


    }

    // make the GO button disappear
    public void go(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.timerTextView));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        sumTextView = findViewById(R.id.sumTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        resultTextView = findViewById(R.id.resultTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        playAgainButton = findViewById(R.id.playAgainButton);

        gameLayout = findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
