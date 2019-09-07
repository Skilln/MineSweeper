package main;

import java.awt.*;

public class Cell {

    public static final int EMPTY = 0;
    public static final int MINE = 1;

    public static final int CELL_SIZE = 50;

    private int x;
    private int y;

    private int type;
    private int amountOfMines = 0;

    private Boolean checked = false;
    private Boolean flagged = false;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void render(Graphics g) {
        if (checked) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            g.setColor(Color.BLACK);
            g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            if (type != MINE)
                g.drawString(amountOfMines + "", x * CELL_SIZE + CELL_SIZE / 2,  y * CELL_SIZE + CELL_SIZE / 2);
        } else {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        }

        g.setColor(Color.BLACK);
        g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        if (flagged) {
            g.setColor(Color.ORANGE);
            g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    public void onClick() {
        checked = true;
    }

    public void onFlag() {
        flagged = !flagged;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int state) {
        this.type = state;
    }

    public void setAmountOfMines(int amountOfMines) {
        this.amountOfMines = amountOfMines;
    }

    public int getAmountOfMines() {
        return amountOfMines;
    }

    public Boolean getChecked() {
        return checked;
    }
}
