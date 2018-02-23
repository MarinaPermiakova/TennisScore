package com.example.marina.tennisscore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Tracks the game score for Players
    int scorePlayerOne = 0;
    int scorePlayerTwo = 0;

    // Tracks the set score for Players
    int scoreSetPlayerOne = 0;
    int scoreSetPlayerTwo = 0;

    // Tracks the match score for Players
    int scoreMatchPlayerOne = 0;
    int scoreMatchPlayerTwo = 0;

    int acePlayerOne = 0;
    int acePlayerTwo = 0;

    // Shows the score for Players
    TextView textViewForScoreOne;
    TextView textViewForScoreTwo;

    TextView textViewForGameOne;
    TextView textViewForGameTwo;

    TextView textViewForSetOne;
    TextView textViewForSetTwo;

    TextView textViewForAceCountOne;
    TextView textViewForAceCountTwo;

    // Button to plus points for Players
    Button plusButtonOne;
    Button plusButtonTwo;

    // Button to plus aces for Players
    Button plusAceForPlayerOne;
    Button plusAceForPlayerTwo;

    // Buttons which enabled/disabled depends on tennis game phase
    Button nextGameButton;
    Button nextSetButton;
    Button resetButton;

    String stringForGameOne = "Player 1:";
    String stringForGameTwo = "Player 2:";

    String stringForSetOne = "Player 1:";
    String stringForSetTwo = "Player 2:";


    static final String STATE_SCOREPLAYER1 = "scorePlayerFirst";
    static final String STATE_SCOREPLAYER2 = "scorePlayerSecond";

    static final String STATE_ACEPLAYER1 ="acePlayerOne";
    static final String STATE_ACEPLAYER2 ="acePlayerTwo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Disable Buttons for new game score, for set score and for reset
        nextGameButton = findViewById(R.id.game_button);
        nextGameButton.setEnabled(false);
        nextSetButton = findViewById(R.id.set_button);
        nextSetButton.setEnabled(false);

        resetButton = findViewById(R.id.reset_button);
        resetButton.setEnabled(false);

        textViewForScoreOne = findViewById(R.id.score_player_one);
        textViewForScoreTwo = findViewById(R.id.score_player_two);

        textViewForSetOne = findViewById(R.id.t_v_set_one);
        textViewForSetTwo = findViewById(R.id.t_v_set_two);

        textViewForGameOne = findViewById(R.id.t_v_player_one);
        textViewForGameTwo = findViewById(R.id.t_v_player_two);

        textViewForAceCountOne = findViewById(R.id.ace_player_one);
        textViewForAceCountTwo = findViewById(R.id.ace_player_two);

        plusButtonOne = findViewById(R.id.button_player_one);
        plusButtonTwo = findViewById(R.id.button_player_two);

        plusAceForPlayerOne = findViewById(R.id.button_plus_ace_one);
        plusAceForPlayerTwo = findViewById(R.id.button_plus_ace_two);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the player's current score state
        savedInstanceState.putInt(STATE_SCOREPLAYER1, scorePlayerOne);
        savedInstanceState.putInt(STATE_SCOREPLAYER2, scorePlayerTwo);

        savedInstanceState.putInt(STATE_ACEPLAYER1, acePlayerOne);
        savedInstanceState.putInt(STATE_ACEPLAYER2, acePlayerTwo);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        scorePlayerOne = savedInstanceState.getInt(STATE_SCOREPLAYER1);
        scorePlayerTwo = savedInstanceState.getInt(STATE_SCOREPLAYER2);
        acePlayerOne = savedInstanceState.getInt(STATE_ACEPLAYER1);
        acePlayerTwo = savedInstanceState.getInt(STATE_ACEPLAYER2);

        scorePointForPlayerOne(scorePlayerOne);
        scorePointForPlayerTwo(scorePlayerTwo);
        printAceForPlayerOne(acePlayerOne);
        printAceForPlayerTwo(acePlayerTwo);
    }

    /**
     * Increase the score for Player 1.
     */
    public void plusPointForPlayerOne(View view) {
        scorePlayerOne++;
        scorePointForPlayerOne(scorePlayerOne);
    }

    /**
     * Score and show current result of game in textViewForScoreOne and textViewForScoreTwo.
     */
    public int scorePointForPlayerOne(int Int) {

        // first point for Player 1
        if (scorePlayerOne == 1) textViewForScoreOne.setText("15");

        // second point for Player 1
        if (scorePlayerOne == 2) textViewForScoreOne.setText("30");

        // third point for Player 1
        if (scorePlayerOne == 3)

        // compare result with Player 2: 40 or deuce
        {
            if (scorePlayerOne == scorePlayerTwo) {
                textViewForScoreOne.setText("De");
                textViewForScoreTwo.setText("De");
            } else textViewForScoreOne.setText("40");
        }

        // forth point for Player 1
        if (scorePlayerOne >= 4) {

            // compare result with Player 2: deuce or Ad (advantage) or win
            if (scorePlayerOne == scorePlayerTwo) {
                textViewForScoreOne.setText("De");
                textViewForScoreTwo.setText("De");
            }
            if ((scorePlayerTwo >= 3) & (scorePlayerOne - scorePlayerTwo == 1)) {
                textViewForScoreOne.setText("Ad");
                textViewForScoreTwo.setText("");
            }
            if ((scorePlayerTwo <= 2) | (scorePlayerOne - scorePlayerTwo == 2)) {
                textViewForScoreOne.setText("game");
                textViewForScoreTwo.setText("");

                // Print result of game on screen
                stringForGameOne = stringForGameOne + scorePlayerOne + "  ";
                textViewForGameOne.setText(stringForGameOne);
                stringForGameTwo = stringForGameTwo + scorePlayerTwo + "  ";
                textViewForGameTwo.setText(stringForGameTwo);

                nextGameButton.setEnabled(true);
                plusButtonOne.setEnabled(false);
                plusButtonTwo.setEnabled(false);

                // count sets Player 1 won
                scoreSetPlayerOne++;
                if (scoreSetPlayerOne >= 6) {
                    compareSetScore(scoreSetPlayerOne, scoreSetPlayerTwo);
                }
            }
        }
        return (scorePlayerOne);
    }

    /**
     * Increase the score for Player 2
     */
    public void plusPointForPlayerTwo(View view) {
        scorePlayerTwo++;
        scorePointForPlayerTwo(scorePlayerTwo);
    }


    /**
     * Score and show current result of game in textViewForScoreOne and textViewForScoreTwo.
     */
    public int scorePointForPlayerTwo(int Int) {
        // first point for Player 2
        if (scorePlayerTwo == 1) {
            textViewForScoreTwo.setText("15");
        }

        // second point for Player 2
        if (scorePlayerTwo == 2) textViewForScoreTwo.setText("30");

        // third point for Player 2
        if (scorePlayerTwo == 3) {

            // compare result with Player 2: 40 or deuce
            if (scorePlayerTwo == scorePlayerOne) {
                textViewForScoreOne.setText("De");
                textViewForScoreTwo.setText("De");
            } else textViewForScoreTwo.setText("40");
        }

        // Fourth point for Player 2
        if (scorePlayerTwo >= 4) {

            // compare result with Player 1: deuce or Ad (advantage)
            if (scorePlayerOne == scorePlayerTwo) {
                textViewForScoreOne.setText("De");
                textViewForScoreTwo.setText("De");
            }

            // compare result with Player 1: deuce or Ad (advantage) or win
            if ((scorePlayerOne >= 3) & (scorePlayerTwo - scorePlayerOne == 1)) {
                textViewForScoreTwo.setText("Ad");
                textViewForScoreOne.setText("");
            }
            if ((scorePlayerOne <= 2) | (scorePlayerTwo - scorePlayerOne == 2)) {
                textViewForScoreTwo.setText("game");
                textViewForScoreOne.setText("");

                // print current results of game on screen
                stringForGameOne = stringForGameOne + scorePlayerOne + "  ";
                textViewForGameOne.setText(stringForGameOne);
                stringForGameTwo = stringForGameTwo + scorePlayerTwo + "  ";
                textViewForGameTwo.setText(stringForGameTwo);

                nextGameButton.setEnabled(true);
                plusButtonOne.setEnabled(false);
                plusButtonTwo.setEnabled(false);

                // count games Player 2 won and compare results with Player 1
                scoreSetPlayerTwo++;
                if (scoreSetPlayerTwo >= 6) {
                    compareSetScore(scoreSetPlayerOne, scoreSetPlayerTwo);
                }
            }
        }
        return (scorePlayerTwo);
    }

    // compare results of Players in set and count sets Players won
    public void compareSetScore(int q, int w) {
        if (scoreSetPlayerOne - scoreSetPlayerTwo >= 2) {
            textViewForScoreOne.setText("set");
            textViewForScoreTwo.setText("");

            scoreMatchPlayerOne++;
            if (scoreMatchPlayerOne == 2) {
                textViewForScoreOne.setText("WIN");
                textViewForScoreTwo.setText("");

                stringForSetOne =  stringForSetOne + " " + scoreSetPlayerOne;
                textViewForSetOne.setText(stringForSetOne);

                stringForSetTwo = stringForSetTwo + " " + scoreSetPlayerTwo;
                textViewForSetTwo.setText(stringForSetTwo);

                nextSetButton.setEnabled(false);
                nextGameButton.setEnabled(false);
                resetButton.setEnabled(true);

                return;
            }

            plusButtonOne.setEnabled(false);
            plusButtonTwo.setEnabled(false);
            nextGameButton.setEnabled(false);
            nextSetButton.setEnabled(true);
            resetButton.setEnabled(true);
        }
        if (scoreSetPlayerTwo - scoreSetPlayerOne >= 2) {
            textViewForScoreTwo.setText("set");
            textViewForScoreOne.setText("");

            scoreMatchPlayerTwo++;
            if (scoreMatchPlayerTwo == 2) {
                textViewForScoreOne.setText("");
                textViewForScoreTwo.setText("WIN");

                stringForSetOne = stringForSetOne + " " + scoreSetPlayerOne;
                textViewForSetOne.setText(stringForSetOne);

                stringForSetTwo = stringForSetTwo + " " + scoreSetPlayerTwo;
                textViewForSetTwo.setText(stringForSetTwo);

                nextSetButton.setEnabled(false);
                nextGameButton.setEnabled(false);
                resetButton.setEnabled(true);

                return;
            }

            plusButtonOne.setEnabled(false);
            plusButtonTwo.setEnabled(false);
            nextGameButton.setEnabled(false);
            nextSetButton.setEnabled(true);
            resetButton.setEnabled(true);
        }
    }

    // start next game
    public void gameClick (View view) {
        scorePlayerOne = 0;
        scorePlayerTwo = 0;

        textViewForScoreOne.setText("0");
        textViewForScoreTwo.setText("0");

        plusButtonOne.setEnabled(true);
        plusButtonTwo.setEnabled(true);
        nextGameButton.setEnabled(false);

    }

    // start next set
    public void setClick(View view) {
        scorePlayerOne = 0;
        scorePlayerTwo = 0;

        stringForGameOne = getString(R.string.player_1);
        stringForGameTwo = getString(R.string.player_2);

        textViewForGameOne.setText(stringForGameOne);
        textViewForGameTwo.setText(stringForGameTwo);

        stringForSetOne = stringForSetOne + " " + scoreSetPlayerOne;
        textViewForSetOne.setText(stringForSetOne);

        stringForSetTwo = stringForSetTwo + " " + scoreSetPlayerTwo;
        textViewForSetTwo.setText(stringForSetTwo);

        scoreSetPlayerOne = 0;
        scoreSetPlayerTwo = 0;

        textViewForScoreOne.setText(R.string._0);
        textViewForScoreTwo.setText(R.string._0);

        plusButtonOne.setEnabled(true);
        plusButtonTwo.setEnabled(true);
        nextSetButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

    // count aces Player 1 made
    public void plusAceForPlayerOne (View view){
        acePlayerOne++;
        printAceForPlayerOne(acePlayerOne);
    }

    // print aces Player 1 made
    public void printAceForPlayerOne (int Int){
        textViewForAceCountOne.setText(String.valueOf(Int));
    }

    // count aces Player 2 made
    public void plusAceForPlayerTwo (View view){
        acePlayerTwo++;
        printAceForPlayerTwo(acePlayerTwo);
    }

    // print aces Player 2 made
    public void printAceForPlayerTwo (int Int){
        textViewForAceCountTwo.setText(String.valueOf(Int));
    }

    /**
     * reset variables, textViews and Buttons to initial state
     */
    public void resetClick(View view) {
        scorePlayerOne = 0;
        scorePlayerTwo = 0;

        scoreSetPlayerOne = 0;
        scoreSetPlayerTwo = 0;

        scoreMatchPlayerOne = 0;
        scoreMatchPlayerTwo = 0;

        acePlayerOne = 0;
        acePlayerTwo = 0;

        stringForGameOne = getString(R.string.player_1);
        stringForGameTwo = getString(R.string.player_2);
        stringForSetOne = getString(R.string.player_1);
        stringForSetTwo = getString(R.string.player_2);

        textViewForGameOne.setText("");
        textViewForGameTwo.setText("");

        textViewForSetOne.setText("");
        textViewForSetTwo.setText("");

        textViewForScoreOne.setText(R.string._0);
        textViewForScoreTwo.setText(R.string._0);

        textViewForAceCountOne.setText(R.string._0);
        textViewForAceCountTwo.setText(R.string._0);

        plusButtonOne.setEnabled(true);
        plusButtonTwo.setEnabled(true);
        nextSetButton.setEnabled(false);
        nextGameButton.setEnabled(false);
        resetButton.setEnabled(false);
    }

}
