package org.example.superfastline.logic;

import javafx.scene.layout.Pane;
import org.example.superfastline.logic.Drawing;

public class DeegDrawing extends Pane implements Drawing {

    int boxSize;

    int mapSize;


    public DeegDrawing(int boxSize, int mapSize) {
        this.boxSize = boxSize;
        this.mapSize = mapSize;


    }
    @Override
    public void draw() {

    }

    @Override
    public void erase() {

    }
}
