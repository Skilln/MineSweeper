package main;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input extends MouseAdapter {

    private MineField field;

    public Input(MineField field) {
        this.field = field;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        int x = e.getX() / Cell.CELL_SIZE;
        int y = e.getY() / Cell.CELL_SIZE;


        Cell touched = field.findIn(x, y);

        if (touched != null) {
            if (SwingUtilities.isLeftMouseButton(e)) {
                if (touched.getType() == Cell.MINE) {
                    JOptionPane.showMessageDialog(null, "BAM! You lost!", "Ooops!", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } else {
                    field.onClick(touched);
                }
            } else {
                field.onFlag(touched);
            }


        }

    }
}
