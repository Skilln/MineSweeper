package com.skilln.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.skilln.game.stage.field.MineField;

public class Cell extends Actor {

    private static final String TAG = "CELL";

    public static final int EMPTY = 0;
    public static final int MINE = 1;

    public static final float CELL_SIZE = 64;

    private int positionX;
    private int positionY;

    private int amountOfMines;

    private boolean checked = false;
    private boolean openedInChain = false;

    private int type;

    private MineField field;

    private BitmapFont font;

    //Debug
    private ShapeRenderer renderer;

    public Cell(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

        setBounds(positionX * CELL_SIZE, positionY * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        type = EMPTY;
        //Debug
        renderer = new ShapeRenderer();

        InputListener touchListener = new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

                if (type != MINE) {
                    field.openChain(Cell.this);
                }

                return super.touchDown(event, x, y, pointer, button);
            }
        };
        addListener(touchListener);

        font = new BitmapFont();

    }

    public void init(MineField field) {
        this.field = field;
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.end();
        renderer.setProjectionMatrix(batch.getProjectionMatrix());

        if (!checked) {
            renderer.setColor(Color.DARK_GRAY);
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.rect(positionX * CELL_SIZE, positionY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            renderer.end();
        } else {
            renderer.setColor(Color.GRAY);
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.rect(positionX * CELL_SIZE, positionY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            renderer.end();
        }

        renderer.setColor(Color.BLACK);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.rect(positionX * CELL_SIZE, positionY * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        renderer.end();

        batch.begin();

        if (checked && amountOfMines > 0) {

            font.draw(batch, Integer.toString(amountOfMines), getX() + CELL_SIZE / 2, getY() + CELL_SIZE / 2 );
        }
    }

    public int getAmountOfMines() {
        return amountOfMines;
    }

    public void setAmountOfMines(int amountOfMines) {
        this.amountOfMines = amountOfMines;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpenedInChain() {
        return openedInChain;
    }

    public void setOpenedInChain(boolean openedInChain) {
        this.openedInChain = openedInChain;
    }
}
