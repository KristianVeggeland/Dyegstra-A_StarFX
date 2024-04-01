package org.example.superfastline.logic;

import javafx.scene.shape.Line;
import org.example.superfastline.container.Map;
import org.example.superfastline.container.Walls.Box;

public class DeegDrawing  implements Drawing {
    boolean pointReached = false;
    Map map;
    Box[][] mapGrid;
    double innerS;
    private double startPointX, startPointY, endPointX, endPointY;
    private int startPosX, startPosY;

    public DeegDrawing(Map map) {
        super();
        this.map = map;
        this.mapGrid = map.getWalls();
        this.innerS = map.getInnerSize();
        this.startPointX = map.getStartCircleX();
        this.startPointY = map.getStartCircleY();
        this.endPointX = map.getEndCircleX();
        this.endPointY = map.getEndCircleY();
        draw();
    }

    @Override
    public void draw() {
        double a = 100;
        double b = 200;
        while (!pointReached) {
            System.out.println(map == null);
            Line line = new Line(10, 10,a, b);
            map.getChildren().add(line);
            a += 100;
        }
    }

    private void checkNeighbourBox() {

    }
    @Override
    public void erase() {

    }
}
