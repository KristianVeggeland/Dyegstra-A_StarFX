package org.example.superfastline.container.Walls;

import javafx.scene.shape.Rectangle;

public abstract class Box extends Rectangle {
    double size;
    double posX;
    double posY;

    boolean traversable;

    public Box(double size, double posX, double posY) {
        super(posX, posY,size, size);
    }
}
