package org.example.superfastline.container.Walls;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Box extends Rectangle {
    private int row,col;
    boolean visited = false;
    Box previous;
    int totalCost = 0;


    public Box(double size, double posX, double posY, int row, int col) {
        super(posX, posY,size, size);
        this.row = row;
        this.col = col;
    }

    public void setVisited(boolean bob) {
        if (bob) {
            this.visited = true;
            this.setFill(Color.BLANCHEDALMOND);
        }
    }

    public void setPrevious(Box neighbour) {
        previous = neighbour;
    }

    public Box getPrevious() {
        return previous;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String toString () {
        return "Box at row: " + row + " col: " + col;
    }
}
