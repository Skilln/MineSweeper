package com.skilln.game.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.skilln.game.actors.Cell;
import com.skilln.game.stage.field.MineField;

public class GameStage extends Stage {

    private static final String TAG = "GAME_STAGE";

    private MineField mineField;

    public GameStage(Viewport viewport, Batch batch) {
        super(viewport, batch);
    }

    public void start(int width, int height, int mineAmount) {
        mineField = new MineField(width, height, mineAmount);

        mineField.create();

        Array<Cell> cells = mineField.getCells();

        for (Cell cell : cells) {
            addActor(cell);

        }
    }

    @Override
    public void draw() {
        super.draw();

    }
}
