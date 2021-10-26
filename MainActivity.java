package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int[] currentPositions = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    boolean gameActive = true;
    int player = 0;

    public void drop(View view) {
        int count = 0;
        ImageView image = (ImageView) view;
        Log.i("tag", image.getTag().toString());
        int tags = Integer.parseInt(image.getTag().toString());
        if (currentPositions[tags] == 2 && gameActive) {
            currentPositions[tags] = player;
            image.setTranslationY(-2500);
            if (player == 0) {
                image.setImageResource(R.drawable.yellow);
                player = 1;

            } else {
                image.setImageResource(R.drawable.red);
                player = 0;
            }
            image.animate().translationYBy(2500).rotationY(540).setDuration(500);
            for (int[] win : winningPositions) {
                if (currentPositions[win[0]] == currentPositions[win[1]] &&
                        currentPositions[win[1]] == currentPositions[win[2]] &&
                        currentPositions[win[0]] != 2) {
                    gameActive = false;
                    if (player == 0) {
                        Toast.makeText(this, "RED WINS!!!!", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(this, "YELLOW WINS!!!!", Toast.LENGTH_SHORT).show();

                    }
                    Button retry = (Button) findViewById(R.id.retryButton);
                    retry.setVisibility(View.VISIBLE);

                }

            }
        }
        for (int i = 0; i < currentPositions.length; i++) {
            if (currentPositions[i] != 2) {
                count += 1;
            }
        }


        if (count == 9)
        {
           Button retry = (Button) findViewById(R.id.retryButton);
           retry.setVisibility(View.VISIBLE);
        }
    }


    public void retryButton(View view)
    {
        Button retryButton = (Button) findViewById(R.id.retryButton);
        retryButton.setVisibility(View.INVISIBLE);
        androidx.gridlayout.widget.GridLayout myGridView = findViewById(R.id.gridLayout);
        for(int i = 0; i < myGridView.getChildCount(); i++)
        {
             ImageView image = (ImageView) myGridView.getChildAt(i);
             image.setImageDrawable(null);
        }
        for(int i = 0; i < 9; i++)
        {
            currentPositions[i] = 2;
        }
        player = 0;
        gameActive = true;
        Log.i("Info", "Button was pressed");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
