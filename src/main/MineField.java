package main;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MineField {

    private int width;
    private int height;

    private Score gameScore;

    private LinkedList<Cell> cells = new LinkedList<>();

    public MineField(int width, int height) {
        this.width = width;
        this.height = height;

        gameScore = new Score();
    }

    public void create() {
       for (int y = 0 ; y < height; y++) {
           for (int x = 0; x < width; x++) {
               Cell cell = new Cell(x, y);
               cell.setType(Cell.EMPTY);

               cells.push(cell);
           }
       }

       for (int i = 0; i < 10; i++) {
           int rand = (int)(Math.random() * cells.size());

           cells.get(rand).setType(Cell.MINE);
       }

       onCreated();
    }
    public void update() {
        if (gameScore.isAllFinded()) {
            JOptionPane.showMessageDialog(null, "YAY! You are stay alive!", "WIN", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    public void render(Graphics g) {
        for (Cell cell : cells) {
            cell.render(g);
        }
    }

    private void onCreated() {
        for (Cell cell : cells) {
           List<Cell> neigs = getNeighbords(cell);

            int amountOfMines = 0;

            for (Cell neig : neigs) {
                if (neig != null && neig.getType() == Cell.MINE) {
                    amountOfMines++;
                }
            }

            cell.setAmountOfMines(amountOfMines);
        }
    }

    public void onFlag(Cell cell) {
       if (cell.getType() == Cell.MINE) {
           gameScore.findMine();
           cell.onFlag();
       }
    }

    public void onClick(Cell cell) {
        cell.onClick();

        if (cell.getAmountOfMines() == 0) {
            List<Cell> neigs = getNeighbords(cell);

            for (Cell neig : neigs) {
                if (neig != null) {
                    neig.onClick();
                }
            }

            chainCheck(cell);
        }
    }

    private void chainCheck(Cell cell)
    {
        List<Cell> neighbords = getNeighbords(cell);

        for (Cell neig : neighbords) {
            if (neig != null && neig.getAmountOfMines() == 0 && !neig.getChecked()) {
                chainCheck(neig);
            }
        }
    }

    public Cell findIn(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
    }

    public List<Cell> getNeighbords(Cell cell) {
        LinkedList<Cell> neig = new LinkedList<>();

        neig.push(findIn(cell.getX() + 1, cell.getY()));
        neig.push(findIn(cell.getX() + 1, cell.getY() + 1));
        neig.push(findIn(cell.getX() + 1, cell.getY() - 1));
        neig.push(findIn(cell.getX() - 1, cell.getY()));
        neig.push(findIn(cell.getX() - 1, cell.getY() + 1));
        neig.push(findIn(cell.getX() - 1, cell.getY()- 1));
        neig.push(findIn(cell.getX(), cell.getY() + 1));
        neig.push(findIn(cell.getX(), cell.getY() - 1));

        return neig;
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }
}
