package com.example.amit;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;

    int [][] winningState={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view)
    {
        ImageView counter=(ImageView) view;

        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter]==2 && gameActive==true)
        {
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                counter.animate().alpha(1).setDuration(2500);
                activePlayer = 1;


            } else {
                counter.setImageResource(R.drawable.zero);
                counter.animate().alpha(1).setDuration(2500);
                activePlayer = 0;

            }
            for (int[] winningState : winningState) {
                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2) {
                    //someone has won
                    gameActive=false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "X";
                    } else if(activePlayer==0) {
                        winner = "O";
                    }
                    Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                    TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner+" has won");
                    playAgainButton.setVisibility(view.VISIBLE);
                    winnerTextView.setVisibility(view.VISIBLE);
                }
                else{
                    boolean gameOver=true;
                    for(int counterstate:gameState){
                        if(counterstate==2)
                        {
                            gameOver=false;
                        }
                    }
                    if(gameOver){
                        gameActive=true;
                        String winner;
                        winner="Game Draw Play Again";
                        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
                        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
                        winnerTextView.setText(winner);
                        playAgainButton.setVisibility(view.VISIBLE);
                        winnerTextView.setVisibility(view.VISIBLE);
                    }
                }

            }
        }
    }
    public void playAgain(View view)
    {
        Button playAgainButton=(Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView=(TextView) findViewById(R.id.winnerTextView);
        playAgainButton.setVisibility(view.INVISIBLE);
        winnerTextView.setVisibility(view.INVISIBLE);
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++) {
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gameState.length;i++) {
            gameState[i]=2;
        }
        activePlayer=0;
        gameActive=true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}