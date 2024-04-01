package org.example.superfastline.container.Walls;

import javafx.scene.paint.Color;

public class OpenBox extends Box {
    public OpenBox(double size, double posX, double posY, int row, int col) {
        super(size, posX, posY, row, col);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.GRAY);

    }
}
