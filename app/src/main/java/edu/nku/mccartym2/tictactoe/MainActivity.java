package edu.nku.mccartym2.tictactoe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
    private Button one;
    private Button two;
    private Button three;
    private Button four;
    private Button five;
    private Button six;
    private Button seven;
    private Button eight;
    private Button nine;
    private Button newGame;

    private TextView status;

    private int turn = 0; // turn 0 is "X" and turn 1 is "O"

    private int turnCounter = 0; // counts turns and when above 4, checks win condition

    private SharedPreferences savedValues;


    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status = (TextView) findViewById(R.id.status);
        status.setText("Player X's turn.");

        one = (Button) findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(one);
            }
        });

        two = (Button) findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(two);
            }
        });

        three = (Button) findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(three);
            }
        });

        four = (Button) findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(four);
            }
        });

        five = (Button) findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(five);
            }
        });

        six = (Button) findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(six);
            }
        });

        seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(seven);
            }
        });

        eight = (Button) findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(eight);
            }
        });

        nine = (Button) findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                takeTurn(nine);
            }
        });

        newGame = (Button) findViewById(R.id.newGame);
        newGame.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                newGame();
                status.setText("Player X's turn.");
            }
        });

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    @Override
    public void onPause()
    {
        SharedPreferences.Editor editor = savedValues.edit();


        editor.putString("oneText", one.getText().toString());
        editor.putString("twoText", two.getText().toString());
        editor.putString("threeText", three.getText().toString());
        editor.putString("fourText", four.getText().toString());
        editor.putString("fiveText", five.getText().toString());
        editor.putString("sixText", six.getText().toString());
        editor.putString("sevenText", seven.getText().toString());
        editor.putString("eightText", eight.getText().toString());
        editor.putString("nineText", nine.getText().toString());

        editor.putBoolean("oneStat", one.isEnabled());
        editor.putBoolean("twoStat", two.isEnabled());
        editor.putBoolean("threeStat", three.isEnabled());
        editor.putBoolean("fourStat", four.isEnabled());
        editor.putBoolean("fiveStat", five.isEnabled());
        editor.putBoolean("sixStat", six.isEnabled());
        editor.putBoolean("sevenStat", seven.isEnabled());
        editor.putBoolean("eightStat", eight.isEnabled());
        editor.putBoolean("nineStat", nine.isEnabled());

        editor.putInt("turn", turn);
        editor.putString("status", status.getText().toString());

        editor.apply();


        super.onPause();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        one.setText(savedValues.getString("oneText", ""));
        one.setEnabled(savedValues.getBoolean("oneStat", one.isEnabled()));

        two.setText(savedValues.getString("twoText", ""));
        two.setEnabled(savedValues.getBoolean("twoStat", two.isEnabled()));

        three.setText(savedValues.getString("threeText", ""));
        three.setEnabled(savedValues.getBoolean("threeStat", three.isEnabled()));

        four.setText(savedValues.getString("fourText", ""));
        four.setEnabled(savedValues.getBoolean("fourStat", four.isEnabled()));

        five.setText(savedValues.getString("fiveText", ""));
        five.setEnabled(savedValues.getBoolean("fiveStat", five.isEnabled()));

        six.setText(savedValues.getString("sixText", ""));
        six.setEnabled(savedValues.getBoolean("sixStat", six.isEnabled()));

        seven.setText(savedValues.getString("sevenText", ""));
        seven.setEnabled(savedValues.getBoolean("sevenStat", seven.isEnabled()));

        eight.setText(savedValues.getString("eightText", ""));
        eight.setEnabled(savedValues.getBoolean("eightStat", eight.isEnabled()));

        nine.setText(savedValues.getString("nineText", ""));
        nine.setEnabled(savedValues.getBoolean("nineStat", nine.isEnabled()));

        turn = savedValues.getInt("turn", turn);

        status.setText(savedValues.getString("status", ""));
    }

    public void newGame()
    {
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        four.setEnabled(true);
        five.setEnabled(true);
        six.setEnabled(true);
        seven.setEnabled(true);
        eight.setEnabled(true);
        nine.setEnabled(true);

        one.setText("");
        two.setText("");
        three.setText("");
        four.setText("");
        five.setText("");
        six.setText("");
        seven.setText("");
        eight.setText("");
        nine.setText("");

        turn = 0;
        turnCounter = 0;
    }

    public void checkWinner()
    {
        if(turnCounter >= 9)
        {
            status.setText("Draw!");
        }

        // Top Horizontal Row Win for X
        if(one.getText().toString().equals("X") && two.getText().toString().equals("X") && three.getText().toString().equals("X"))
        {
            status.setText("Player X wins!");
            disallowPlaying();
        }

        // Top Horizontal Row Win for O
        if(one.getText().toString().equals("O") && two.getText().toString().equals("O") && three.getText().toString().equals("O"))
        {
            status.setText("Player O wins!");
            disallowPlaying();
        }

        // Middle Horizontal Row Win for X
        if(four.getText().toString().equals("X") && five.getText().toString().equals("X") && six.getText().toString().equals("X"))
        {
            status.setText("Player X wins!");
            disallowPlaying();
        }

        // Middle Horizontal Row Win for O
        if(four.getText().toString().equals("O") && five.getText().toString().equals("O") && six.getText().toString().equals("O"))
        {
            status.setText("Player O wins!");
            disallowPlaying();
        }

        // Bottom Horizontal Row Win for X
        if(seven.getText().toString().equals("X") && eight.getText().toString().equals("X") && nine.getText().toString().equals("X"))
        {
            status.setText("Player X wins!");
            disallowPlaying();
        }

        // Bottom Horizontal Row Win for O
        if(seven.getText().toString().equals("O") && eight.getText().toString().equals("O") && nine.getText().toString().equals("O"))
        {
            status.setText("Player O wins!");
            disallowPlaying();
        }

        // Left Column Win for X
        if(one.getText().toString().equals("X") && four.getText().toString().equals("X") && seven.getText().toString().equals("X"))
        {
            status.setText("Player X wins!");
            disallowPlaying();
        }

        // Left Column Win for O
        if(one.getText().toString().equals("O") && four.getText().toString().equals("O") && seven.getText().toString().equals("O"))
        {
            status.setText("Player O wins!");
            disallowPlaying();
        }

        // Middle Column Win for X
        if(two.getText().toString().equals("X") && five.getText().toString().equals("X") && eight.getText().toString().equals("X"))
        {
            status.setText("Player X wins!");
            disallowPlaying();
        }

        // Middle Column Win for O
        if(two.getText().toString().equals("O") && five.getText().toString().equals("O") && eight.getText().toString().equals("O"))
        {
            status.setText("Player O wins!");
            disallowPlaying();
        }

        // Right Column Win for X
        if(three.getText().toString().equals("X") && six.getText().toString().equals("X") && nine.getText().toString().equals("X"))
        {
            status.setText("Player X wins!");
            disallowPlaying();
        }

        // Right Column Win for O
        if(three.getText().toString().equals("O") && six.getText().toString().equals("O") && nine.getText().toString().equals("O"))
        {
            status.setText("Player O wins!");
            disallowPlaying();
        }

        // Top-Left to Bottom-Right Diagonal Win for X
        if(one.getText().toString().equals("X") && five.getText().toString().equals("X") && nine.getText().toString().equals("X"))
        {
            status.setText("Player X wins!");
            disallowPlaying();
        }

        // Top-Left to Bottom-Right Diagonal Win for O
        if(one.getText().toString().equals("O") && five.getText().toString().equals("O") && nine.getText().toString().equals("O"))
        {
            status.setText("Player O wins!");
            disallowPlaying();
        }

        // Top-Right to Bottom-Left Diagonal Win for X
        if(three.getText().toString().equals("X") && five.getText().toString().equals("X") && seven.getText().toString().equals("X"))
        {
            status.setText("Player X wins!");
            disallowPlaying();
        }

        // Top-Right to Bottom-Left Diagonal Win for O
        if(three.getText().toString().equals("O") && five.getText().toString().equals("O") && seven.getText().toString().equals("O"))
        {
            status.setText("Player O wins!");
            disallowPlaying();
        }
    }

    // Stop player from playing after a win.
    // Forces a new game at that point.
    public void disallowPlaying()
    {
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
        six.setEnabled(false);
        seven.setEnabled(false);
        eight.setEnabled(false);
        nine.setEnabled(false);
    }

    // Logic for what happens when you press a button/cell.
    public void takeTurn(Button cell)
    {
        if (turn == 0)
        {
            cell.setText("X");
            turn = 1;
            cell.setEnabled(false);
            status.setText("Player O's turn.");
            turnCounter++;
            if(turnCounter >= 5)
            {
                checkWinner();
            }
        }
        else if (turn == 1)
        {
            cell.setText("O");
            turn = 0;
            cell.setEnabled(false);
            status.setText("Player X's turn.");
            turnCounter++;
            if(turnCounter >= 5)
            {
                checkWinner();
            }
        }
    }
}
