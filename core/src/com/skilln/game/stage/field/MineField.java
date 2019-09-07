package com.skilln.game.stage.field;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.skilln.game.actors.Cell;

public class MineField {

    public static final String TAG = "MINE_FIELD";

    private int width;
    private int height;

    private int mineAmount;

    private Array<Cell> cells;

    public MineField(int width, int height, int mineAmount) {
        this.width = width;
        this.height = height;
        this.mineAmount = mineAmount;

        cells = new Array<Cell>();
    }

    public void create() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = new Cell(x, y);
                cell.init(this);
                cells.add(cell);
            }
        }

        for (int i = 0; i < mineAmount; i++) {
            int random = (int) (Math.random() * cells.size);

            Cell cell = cells.get(random);

            if (cell.getType() == Cell.MINE) {
                i--;
                continue;
            }

            cell.setType(Cell.MINE);
        }
        onCreate();
    }

    private void onCreate() {
        for (int i = 0; i < cells.size; i++) {
            int mineAmount = 0;
            Array<Cell> neighs = getNeighbors(cells.get(i));

            for (Cell neigh : neighs) {
                if (neigh != null && neigh.getType() == Cell.MINE) {
                    mineAmount++;
                }
            }

            cells.get(i).setAmountOfMines(mineAmount);
        }
    }

    private Cell findIn(int x, int y) {
        for (Cell cell : cells) {

            if (cell.getPositionX() == x && cell.getPositionY() == y) {

                return cell;
            }
        }

        return null;
    }

    public Array<Cell> getNeighbors(Cell cell) {
        Array<Cell> neighbors = new Array<Cell>();

        neighbors.add(findIn(cell.getPositionX() + 1, cell.getPositionY() - 1));
        neighbors.add(findIn(cell.getPositionX() + 1, cell.getPositionY() + 1));
        neighbors.add(findIn(cell.getPositionX() + 1, cell.getPositionY()));
        neighbors.add(findIn(cell.getPositionX() - 1, cell.getPositionY() - 1));
        neighbors.add(findIn(cell.getPositionX() - 1, cell.getPositionY() + 1));
        neighbors.add(findIn(cell.getPositionX() - 1, cell.getPositionY()));
        neighbors.add(findIn(cell.getPositionX(), cell.getPositionY() + 1));
        neighbors.add(findIn(cell.getPositionX(), cell.getPositionY() - 1));

        return neighbors;

    }

    public void openChain(Cell cell) {
        Array<Cell> canOpen = new Array<Cell>();
        canOpen.add(cell);

        while (canOpen.size != 0) {
            Cell opened = canOpen.pop();

            opened.setChecked(true);
            opened.setOpenedInChain(true);

            if (opened.getAmountOfMines() != 0) {
                return;
            }

            Array<Cell> neighs = getNeighbors(opened);

            for (Cell neigh : neighs) {
                if (neigh != null) {
                    neigh.setChecked(true);
                    if (neigh.getAmountOfMines() == 0 && !neigh.isOpenedInChain()) {
                        canOpen.add(neigh);
                    }
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMineAmount() {
        return mineAmount;
    }

    public Array<Cell> getCells() {
        return cells;
    }
}
