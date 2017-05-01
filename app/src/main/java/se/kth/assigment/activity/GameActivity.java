package se.kth.assigment.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Vector;

import assigment.kth.se.activity.R;
import se.kth.assigment.model.Basketball;
import se.kth.assigment.model.Football;
import se.kth.assigment.model.Game;
import se.kth.assigment.model.Tennis;
import se.kth.assigment.service.BasketBallGameService;
import se.kth.assigment.service.FootballGameService;
import se.kth.assigment.service.GameService;
import se.kth.assigment.service.TennisGameService;

/**
 * Created by Hichem Memmi on 2017-04-30.
 */
public class GameActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Game game;
    private LinearLayout ll;
    private int resource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        actions();
    }

    private void init() {
        spinner = (Spinner) findViewById(R.id.spinner);
        ll = (LinearLayout) findViewById(R.id.gamePlay);
        Vector<Game> gameVector = new Vector<>();
        gameVector.add(new Game());
        gameVector.add(new Football());
        gameVector.add(new Basketball());
        gameVector.add(new Tennis());
        ArrayAdapter<Game> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gameVector);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void actions() {

        spinner.setOnItemSelectedListener(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        ll.removeAllViews();
        ll.refreshDrawableState();
        game =  (Game) spinner.getItemAtPosition(spinner.getSelectedItemPosition());
        Toast.makeText(this, game.toString(), Toast.LENGTH_LONG).show();
        if (game instanceof Football) {
            footBallBoard();
        }
        if (game instanceof Basketball) {
            basketBallBoard();

        } else if (game instanceof Tennis) {
            tennisBoard();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private View getView(int resource) {
        View child = getLayoutInflater().inflate(resource, null);
        ll.addView(child);
        return child;
    }

    private void footBallBoard() {
        resource = R.layout.footbal;
        View child = getView(resource);


        GameService footballGameService = new FootballGameService(getBaseContext(),child,game);
        play(footballGameService);

    }

    private void basketBallBoard() {
        resource = R.layout.basketball;
        View child = getView(resource);
        GameService basketBallGameService = new BasketBallGameService(getBaseContext(),child,game);
        basketBallGameService.PlayHandler();
    }

    private void tennisBoard() {
        resource = R.layout.tennis;
        View child = getView(resource);
        GameService tennisGameService = new TennisGameService(getBaseContext(),child,game);
        play(tennisGameService);

    }

    private void play(GameService gameService) {
        gameService.PlayHandler();
    }


}
