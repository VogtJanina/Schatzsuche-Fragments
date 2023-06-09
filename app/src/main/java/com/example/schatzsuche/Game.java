package com.example.schatzsuche;

import android.util.Log;
import android.widget.ImageButton;

import com.example.schatzsuche.R;
import com.google.android.material.button.MaterialButton;

import java.time.LocalDateTime;
import java.util.Random;

public class Game {
    private int treasure;
    private int seaMonster;
    private static final int MAX_ISLANDS = 15;
    private int counter = 1;
    private int MAX_TRIES = 3;

    private ScoreItem scoreItem;

    //private MainActivity parent;

    public Game(){

    }

    public void hideTreasureAndSeamonster(){

        treasure = new Random().nextInt(MAX_ISLANDS-1)+1;
        counter = 1;
        scoreItem = null;
        do {
            seaMonster = new Random().nextInt(MAX_ISLANDS-1)+1;
        } while(treasure == seaMonster);
        Log.d("Game", "treasure = " + treasure);
        Log.d("Game", "seaMonster= " + seaMonster);
    }
    public ScoreItem checkForTreasureAndSeaMonster(ImageButton btn) {
        Log.d("checkForTreasureAndSeaMonster()", "btn.getId(): " + btn.getId());
        String treasureId = "island_" + treasure;
        Log.d("checkForTreasureAndSeaMonster()", "TreasureId: " + treasureId);
        String seaMonsterId = "island_" + seaMonster;
        Log.d("checkForTreasureAndSeaMonster()", "SeaMonsterId: " + seaMonsterId);

        if (btn.getAccessibilityPaneTitle().equals(treasureId)) {
            Log.d("checkForTreasure", "btn.getAccessibilityPaneTitle: " + btn.getAccessibilityPaneTitle());
            btn.setImageResource(R.mipmap.treasure);
            scoreItem = new ScoreItem(counter, LocalDateTime.now());
            //parent.setImageButtonState(false);
        } else if (btn.getAccessibilityPaneTitle().equals(seaMonsterId)) {
            Log.d("checkForSeaMonster", "btn.getAccessibilityPaneTitle: " + btn.getAccessibilityPaneTitle());
            btn.setImageResource(R.mipmap.seamonster);
            scoreItem = new ScoreItem(-1, LocalDateTime.now());
            //parent.setImageButtonState(false);
        } else {
            Log.d("checkForTreasure", "counter = " + counter);
            btn.setImageResource(R.mipmap.wave);
            if (counter < MAX_TRIES) {
                counter++;
            } else {
                scoreItem = new ScoreItem(4, LocalDateTime.now());
                //parent.setImageButtonState(false);
            }

        }
        return scoreItem;
    }

    public String toString(){
        return "treasure: " + treasure + " seamonster: " + seaMonster;
    }


}



