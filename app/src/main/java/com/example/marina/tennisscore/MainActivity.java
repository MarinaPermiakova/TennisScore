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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Disable resetButton
        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setEnabled(false);
    }

    /**
     * Increase the score for Player 1 and show current result of game in TextViews.
     */

    public void plusPointOne(View view) {

        // Increase the score for Player 1
        scorePlayerOne++;

        // TextViews for current result of game
        TextView scoreView = (TextView) findViewById(R.id.score_player_one);
        TextView scoreViewTwo = (TextView) findViewById(R.id.score_player_two);

        // first point for Player 1
        if (scorePlayerOne == 1) scoreView.setText("15");

        // second point for Player 1
        if (scorePlayerOne == 2) scoreView.setText("30");

        // third point for Player 1
        if (scorePlayerOne == 3)

        // compare result with Player 2: 40 or deuce
        {
            if (scorePlayerOne == scorePlayerTwo) {
                scoreView.setText("De");
                scoreViewTwo.setText("De");
            } else scoreView.setText("40");
        }

        // forth point for Player 1
        if (scorePlayerOne >= 4) {

            // compare result with Player 2: deuce or Ad (advantage) or win
            if (scorePlayerOne == scorePlayerTwo) {
                scoreView.setText("De");
                scoreViewTwo.setText("De");
            }
            if ((scorePlayerTwo >= 3) & (scorePlayerOne - scorePlayerTwo == 1)) {
                scoreView.setText("Ad");
                scoreViewTwo.setText("");
            }
            if ((scorePlayerTwo <= 2) | (scorePlayerOne - scorePlayerTwo == 2)) {
                scoreView.setText("Game");
                scoreViewTwo.setText("");
                printWinnerText();
            }
        }
    }

    /**
     * Increase the score for Player 2 and show current result of game in TextViews.
     */

    public void plusPointTwo(View view) {

        // Increase the score for Player 1
        scorePlayerTwo++;

        // TextViews for current result of game
        TextView scoreView = (TextView) findViewById(R.id.score_player_one);
        TextView scoreViewTwo = (TextView) findViewById(R.id.score_player_two);

        // first point for Player 2
        if (scorePlayerTwo == 1) scoreViewTwo.setText("15");

        // second point for Player 2
        if (scorePlayerTwo == 2) scoreViewTwo.setText("30");

        // third point for Player 2
        if (scorePlayerTwo == 3) {

            // compare result with Player 2: 40 or deuce
            if (scorePlayerTwo == scorePlayerOne) {
                scoreView.setText("De");
                scoreViewTwo.setText("De");
            } else scoreViewTwo.setText("40");
        }

        // Fourth point for Player 2
        if (scorePlayerTwo >= 4) {

            // compare result with Player 1: deuce or Ad (advantage)
            if (scorePlayerOne == scorePlayerTwo) {
                scoreView.setText("De");
                scoreViewTwo.setText("De");
            }

            // compare result with Player 1: deuce or Ad (advantage) or win
            if ((scorePlayerOne >= 3) & (scorePlayerTwo - scorePlayerOne == 1)) {
                scoreViewTwo.setText("Ad");
                scoreView.setText("");
            }
            if ((scorePlayerOne <= 2) | (scorePlayerTwo - scorePlayerOne == 2)) {
                scoreViewTwo.setText("Game");
                scoreView.setText("");
                printWinnerText();
            }
        }
    }

    /**
     * Displays the result of game and score of the game.
     */

    public void printWinnerText() {
        TextView winnerView = (TextView) findViewById(R.id.win_view);
        if (scorePlayerOne > scorePlayerTwo){
            winnerView.setText("Player 1 win game!");
        }
        else winnerView.setText("Player 2 win game!");

       // Disable buttons for score and enable resetButton

        Button plusButtonFirst = (Button) findViewById(R.id.button_player_one);
        plusButtonFirst.setEnabled(false);

        Button plusButtonSecond = (Button) findViewById(R.id.button_player_two);
        plusButtonSecond.setEnabled(false);

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setEnabled(true);
    }

    /**
     *  reset variables, textViews and Buttons to initial state
     */

    public void resetClick(View view) {


        scorePlayerOne = 0;
        scorePlayerTwo = 0;

        TextView scoreViewFirst = (TextView) findViewById(R.id.score_player_one);
        scoreViewFirst.setText(R.string._0);

        TextView scoreViewSecond = (TextView) findViewById(R.id.score_player_two);
        scoreViewSecond.setText(R.string._0);

        TextView winnerView = (TextView) findViewById(R.id.win_view);
        winnerView.setText("");

        Button plusButtonFirst = (Button) findViewById(R.id.button_player_one);
        plusButtonFirst.setEnabled(true);

        Button plusButtonSecond = (Button) findViewById(R.id.button_player_two);
        plusButtonSecond.setEnabled(true);

        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setEnabled(false);

    }

}
