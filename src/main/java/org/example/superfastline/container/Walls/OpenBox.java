package org.example.superfastline.container.Walls;

import javafx.scene.paint.Color;

public class OpenBox extends Box {
    public OpenBox(double size, double posX, double posY) {
        super(size, posX, posY);
        this.setFill(Color.TRANSPARENT);
        this.setStroke(Color.GRAY);

    }
}
