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

public class BasketBallGameService implements GameService, View.OnClickListener {
    private final View child;
    private final Context baseContext;
    private Button resetButton;
    private TextView resTeam1;
    private TextView resTeam2;
    private Button goalTeam1OnePt;
    private Button goalTeam2OnePt;
    private Button goalTeam1TwoPt;
    private Button goalTeam2TwoPt;
    private Button goalTeam1ThreePt;
    private Button goalTeam2ThreePt;
    private final Game game;
    private TextView end;


    public BasketBallGameService(final Context baseContext, final View child, final Game game) {
        this.child = child;
        this.baseContext=baseContext;
        this.game=game;
        initComponent();
    }

    private void initComponent() {
        goalTeam1OnePt = (Button) child.findViewById(R.id.goalTeam1OnePt);
        goalTeam2OnePt = (Button) child.findViewById(R.id.goalTeam2OnePt);
        goalTeam1TwoPt = (Button) child.findViewById(R.id.goalTeam1TwoPt);
        goalTeam2TwoPt = (Button) child.findViewById(R.id.goalTeam2TwoPt);
        goalTeam1ThreePt = (Button) child.findViewById(R.id.goalTeam1ThreePt);
        goalTeam2ThreePt = (Button) child.findViewById(R.id.goalTeam2ThreePt);
        resetButton = (Button) child.findViewById(R.id.reset);
        resTeam1 = (TextView) child.findViewById(R.id.resTeam1);
        resTeam2 = (TextView) child.findViewById(R.id.resTeam2);
        end=(TextView) child.findViewById(R.id.end);
    }

    @Override
    public void PlayHandler() {
        resetButton.setOnClickListener(this);
        goalTeam1OnePt.setOnClickListener(this);
        goalTeam2OnePt.setOnClickListener(this);
        goalTeam1TwoPt.setOnClickListener(this);
        goalTeam2TwoPt.setOnClickListener(this);
        goalTeam1ThreePt.setOnClickListener(this);
        goalTeam2ThreePt.setOnClickListener(this);
        end.setOnClickListener(this);
    }

    public void scoreOnePoint(TextView restTeam) {
        int goals = Integer.valueOf(restTeam.getText().toString()) + 1;
        restTeam.setText(String.valueOf(goals));
        updateScore();
    }

    public void scoreTwoPoint(TextView resTeam) {
        int goals = Integer.valueOf(resTeam.getText().toString()) + 2;
        resTeam.setText(String.valueOf(goals));
        updateScore();
    }

    public void scoreThreePoint(TextView resTeam) {
        int goals = Integer.valueOf(resTeam.getText().toString()) + 3;
        resTeam.setText(String.valueOf(goals));
        updateScore();
    }

    public void reset() {
        resTeam1.setText("0");
        resTeam2.setText("0");
        updateScore();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goalTeam1OnePt: {
                scoreOnePoint(resTeam1);
                break;
            }
            case R.id.goalTeam2OnePt: {
                scoreOnePoint(resTeam2);
                break;
            }
            case R.id.goalTeam1TwoPt: {
                scoreTwoPoint(resTeam1);
                break;
            }
            case R.id.goalTeam2TwoPt: {
                scoreTwoPoint(resTeam2);
                break;
            }
            case R.id.goalTeam1ThreePt: {
                scoreThreePoint(resTeam1);
                break;
            }
            case R.id.goalTeam2ThreePt: {
                scoreThreePoint(resTeam2);
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

    private void updateScore(){
        game.setScore1(Integer.valueOf(resTeam1.getText().toString()));
        game.setScore2(Integer.valueOf(resTeam2.getText().toString()));
    }

    private void end() {
        Toast.makeText(baseContext,game.getScore1()>(game.getScore2())?"Team 1 won":(game.getScore1()<game.getScore2())?"Team 2 won":"equals",Toast.LENGTH_LONG).show();
    }
}
