package se.kth.assigment.service;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import assigment.kth.se.activity.R;
import se.kth.assigment.model.Game;

/**
 * Created by Hichem Memmi on 2017-05-01.
 */

public class TennisGameService implements GameService, View.OnClickListener {

    private final View child;
    private Button resetButton;
    private TextView pointPlayer1;
    private TextView pointPlayer2;
    private TextView setPlayer1;
    private TextView setPlayer2;
    private TextView gamePlayer1;
    private TextView gamePlayer2;
    private Button makePointPlayer1;
    private Button makePointPlayer2;
    private final Context baseContext;
    private final Game game;

    public TennisGameService(final Context baseContext, final View child,final Game game) {
        this.child = child;
        this.baseContext=baseContext;
        this.game=game;
        initComponents();
    }

    private void initComponents() {
        resetButton = (Button) child.findViewById(R.id.reset);
        pointPlayer1 = (TextView) child.findViewById(R.id.pointPlayer1);
        pointPlayer2 = (TextView) child.findViewById(R.id.pointPlayer2);
        setPlayer1 = (TextView) child.findViewById(R.id.setPlayer1);
        setPlayer2 = (TextView) child.findViewById(R.id.setPlayer2);
        gamePlayer1 = (TextView) child.findViewById(R.id.gamePlayer1);
        gamePlayer2 = (TextView) child.findViewById(R.id.gamePlayer2);
        makePointPlayer1 = (Button) child.findViewById(R.id.goalTeam1);
        makePointPlayer2 = (Button) child.findViewById(R.id.goalTeam2);
    }

    @Override
    public void PlayHandler() {
        makePointPlayer1.setOnClickListener(this);
        makePointPlayer2.setOnClickListener(this);
        resetButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goalTeam1: {
                play(pointPlayer1, gamePlayer1, setPlayer1);
                break;
            }
            case R.id.goalTeam2: {
                play(pointPlayer2, gamePlayer2, setPlayer2);
                break;
            }
            case R.id.reset: {
                reset();
                break;
            }
        }
    }

    public void play(final TextView pointPlayer,final TextView gamePlayer,final TextView setPlayer) {

        if (Integer.valueOf(pointPlayer.getText().toString()) == 40) {
            pointPlayer.setText("0");
            if (Integer.valueOf(gamePlayer.getText().toString()) < 5) {
                gamePlayer.setText(String.valueOf(Integer.valueOf(gamePlayer.getText().toString()) + 1));
            } else {
                gamePlayer.setText("0");

                if (Integer.valueOf(setPlayer.getText().toString()) < 2) {
                    setPlayer.setText(String.valueOf(Integer.valueOf(setPlayer.getText().toString()) + 1));
                } else if (Integer.valueOf(setPlayer.getText().toString()) == 2) {
                    setPlayer.setText(String.valueOf(Integer.valueOf(setPlayer.getText().toString()) + 1));
                    this.makePointPlayer1.setEnabled(false);
                    this.makePointPlayer2.setEnabled(false);
                    updateScore();
                    Toast.makeText(baseContext,game.getScore1()>(game.getScore2())?"Player 1 won":"Player 2 won",Toast.LENGTH_LONG).show();
                }
            }
        } else {
            if (Integer.valueOf(pointPlayer.getText().toString()) < 30) {
                pointPlayer.setText(String.valueOf(Integer.valueOf(pointPlayer.getText().toString()) + 15));
            } else {
                pointPlayer.setText(String.valueOf(Integer.valueOf(pointPlayer.getText().toString()) + 10));
            }

        }
    }

    private void updateScore() {
        game.setScore1(Integer.valueOf(setPlayer1.getText().toString()));
        game.setScore2(Integer.valueOf(setPlayer2.getText().toString()));
    }

    private void reset() {
        this.makePointPlayer1.setEnabled(true);
        this.makePointPlayer2.setEnabled(true);
        pointPlayer1.setText("0");
        pointPlayer2.setText("0");
        setPlayer1.setText("0");
        setPlayer2.setText("0");
        gamePlayer1.setText("0");
        gamePlayer2.setText("0");
        updateScore();
    }
}
