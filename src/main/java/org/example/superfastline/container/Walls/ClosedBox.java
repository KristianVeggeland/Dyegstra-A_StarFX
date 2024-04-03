package org.example.superfastline.container.Boxes;

public class ClosedBox extends Box {
    public ClosedBox(double size, double posX, double posY, int row, int col) {
        super(size, posX, posY, row, col);
        this.setFill(javafx.scene.paint.Color.BLACK);
    }
}
