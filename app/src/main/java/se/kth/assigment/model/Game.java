package se.kth.assigment.model;

import android.widget.Adapter;

/**
 * Created by Hichem Memmi on 2017-04-30.
 */

public class Game {
    private Integer score1;
    private Integer score2;


    public Integer getScore1() {
        return score1;
    }

    public void setScore1(Integer scoreTeam1) {
        this.score1 = scoreTeam1;
    }

    public Integer getScore2() {
        return score2;
    }

    public void setScore2(Integer scoreTeam2) {
        this.score2 = scoreTeam2;
    }

    @Override
    public String toString() {
        if((this instanceof Football)||(this instanceof Basketball)||(this instanceof Tennis)){
            return this.getClass().getSimpleName();
        }
        return "Please choose a game";
    }
}
