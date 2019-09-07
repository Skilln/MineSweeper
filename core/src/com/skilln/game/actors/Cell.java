package com.skilln.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Cell extends Actor {

    public static final int EMPTY = 0;
    public static final int MINE = 1;

    private int amountOfMines;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }


}
