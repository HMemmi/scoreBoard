package se.kth.assigment.service;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import assigment.kth.se.activity.R;
import se.kth.assigment.model.Game;

/**
 * Created by Hichem Memmi on 2017-04-30.
 */

public class FootballGameService implements GameService, View.OnClickListener {

    private final View child;
    private final Context baseContext;
    private final Game game;
    private Button resetButton;
    private TextView resTeam1;
    private TextView resTeam2;
    private Button goalTeam1;
    private Button goalTeam2;
    private TextView end;

    public FootballGameService(final Context baseContext, final View child, final Game game) {
        this.child = child;
        this.baseContext = baseContext;
        this.game = game;
        initComponent();
    }

    private void initComponent() {
        goalTeam1 = (Button) child.findViewById(R.id.goalTeam1);
        goalTeam2 = (Button) child.findViewById(R.id.goalTeam2);
        resetButton = (Button) child.findViewById(R.id.reset);
        resTeam1 = (TextView) child.findViewById(R.id.resTeam1);
        resTeam2 = (TextView) child.findViewById(R.id.resTeam2);
        end = (TextView) child.findViewById(R.id.end);
    }

    public void PlayHandler() {
        resetButton.setOnClickListener(this);
        goalTeam1.setOnClickListener(this);
        goalTeam2.setOnClickListener(this);
        end.setOnClickListener(this);
    }

    private void scoreGoal(TextView resTeam) {
        Integer goals = Integer.valueOf(resTeam.getText().toString()) + 1;
        resTeam.setText(String.valueOf(goals));
        updateScore();
    }

    private void reset() {
        resTeam1.setText("0");
        resTeam2.setText("0");
        updateScore();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goalTeam1: {
                scoreGoal(resTeam1);
                break;
            }
            case R.id.goalTeam2: {
                scoreGoal(resTeam2);
                break;
            }
            case R.id.reset: {
                reset();
                break;
            }
            case R.id.end: {
                end();
                break;
            }
        }
    }

    private void updateScore() {
        game.setScore1(Integer.valueOf(resTeam1.getText().toString()));
        game.setScore2(Integer.valueOf(resTeam2.getText().toString()));
    }

    private void end() {
        Toast.makeText(baseContext,game.getScore1()>(game.getScore2())?"Team 1 won":(game.getScore1()<game.getScore2())?"Team 2 won":"equals",Toast.LENGTH_LONG).show();
    }
}
