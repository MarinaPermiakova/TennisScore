package com.example.marina.tennisscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Tracks the score for Player 1
    int scorePlayerOne = 0;

    // Tracks the score for Player 2
    int scorePlayerTwo = 0;

    // Show the score for Players
    TextView scoreViewFirst;
    TextView scoreViewSecond;

    // Plus points for Players
    Button plusButtonFirst;
    Button plusButtonSecond;

    Button resetButton;



    static final String STATE_SCOREPLAYER1 = "scorePlayerFirst";
    static final String STATE_SCOREPLAYER2 = "scorePlayerSecond";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Disable resetButton
        resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(false);

        scoreViewFirst = findViewById(R.id.score_player_one);
        scoreViewSecond = findViewById(R.id.score_player_two);

        plusButtonFirst = findViewById(R.id.button_player_one);
        plusButtonSecond = findViewById(R.id.button_player_two);


    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the player's current score state
        savedInstanceState.putInt(STATE_SCOREPLAYER1, scorePlayerOne);
        savedInstanceState.putInt(STATE_SCOREPLAYER2, scorePlayerTwo);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        scorePlayerOne = savedInstanceState.getInt(STATE_SCOREPLAYER1);
        scorePlayerTwo = savedInstanceState.getInt(STATE_SCOREPLAYER2);

        score1(scorePlayerOne);
        score2(scorePlayerTwo);
    }


    /**
     * Increase the score for Player 1.
     */
    public void plusPointOne(View view) {
        scorePlayerOne++;
        score1(scorePlayerOne);
    }

    /**
     * Show current result of game in TextViews.
     */
    public int score1(int Int) {

        // first point for Player 1
        if (scorePlayerOne == 1) scoreViewFirst.setText("15");

        // second point for Player 1
        if (scorePlayerOne == 2) scoreViewFirst.setText("30");

        // third point for Player 1
        if (scorePlayerOne == 3)

        // compare result with Player 2: 40 or deuce
        {
            if (scorePlayerOne == scorePlayerTwo) {
                scoreViewFirst.setText("De");
                scoreViewSecond.setText("De");
            } else scoreViewFirst.setText("40");
        }

        // forth point for Player 1
        if (scorePlayerOne >= 4) {

            // compare result with Player 2: deuce or Ad (advantage) or win
            if (scorePlayerOne == scorePlayerTwo) {
                scoreViewFirst.setText("De");
                scoreViewSecond.setText("De");
            }
            if ((scorePlayerTwo >= 3) & (scorePlayerOne - scorePlayerTwo == 1)) {
                scoreViewFirst.setText("Ad");
                scoreViewSecond.setText("");
            }
            if ((scorePlayerTwo <= 2) | (scorePlayerOne - scorePlayerTwo == 2)) {
                scoreViewFirst.setText("WIN");
                scoreViewSecond.setText("");
                plusButtonFirst.setEnabled(false);
                plusButtonSecond.setEnabled(false);
                resetButton.setEnabled(true);
            }
        }
        return (scorePlayerOne);
    }


    /**
     * Increase the score for Player 2 and show current result of game in TextViews.
     */
    public void plusPointTwo(View view) {
        scorePlayerTwo++;
        score2(scorePlayerTwo);
    }


    /**
     * show current result of game in TextViews.
     */
    public int score2(int Int) {
        // first point for Player 2
        if (scorePlayerTwo == 1) scoreViewSecond.setText("15");

        // second point for Player 2
        if (scorePlayerTwo == 2) scoreViewSecond.setText("30");

        // third point for Player 2
        if (scorePlayerTwo == 3) {

            // compare result with Player 2: 40 or deuce
            if (scorePlayerTwo == scorePlayerOne) {
                scoreViewFirst.setText("De");
                scoreViewSecond.setText("De");
            } else scoreViewSecond.setText("40");
        }

        // Fourth point for Player 2
        if (scorePlayerTwo >= 4) {

            // compare result with Player 1: deuce or Ad (advantage)
            if (scorePlayerOne == scorePlayerTwo) {
                scoreViewFirst.setText("De");
                scoreViewSecond.setText("De");
            }

            // compare result with Player 1: deuce or Ad (advantage) or win
            if ((scorePlayerOne >= 3) & (scorePlayerTwo - scorePlayerOne == 1)) {
                scoreViewSecond.setText("Ad");
                scoreViewFirst.setText("");
            }
            if ((scorePlayerOne <= 2) | (scorePlayerTwo - scorePlayerOne == 2)) {
                scoreViewSecond.setText("WIN");
                scoreViewFirst.setText("");
                plusButtonFirst.setEnabled(false);
                plusButtonSecond.setEnabled(false);
                resetButton.setEnabled(true);
            }
        }
        return (scorePlayerTwo);
    }

     /**
     * reset variables, textViews and Buttons to initial state
     */
    public void resetClick(View view) {

        scorePlayerOne = 0;
        scorePlayerTwo = 0;

        scoreViewFirst.setText(R.string._0);
        scoreViewSecond.setText(R.string._0);
        plusButtonFirst.setEnabled(true);
        plusButtonSecond.setEnabled(true);
        resetButton.setEnabled(false);

    }

}
