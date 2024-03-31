package org.example.superfastline.logic;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.example.superfastline.container.Map;
import org.example.superfastline.container.Walls.Wall;
import org.example.superfastline.logic.Drawing;

public class DeegDrawing  implements Drawing {

    boolean pointReached = false;

    Map map;
    Wall[][] mapGrid;

    double innerS;

    double startX, startY, endX, endY;
    public DeegDrawing(Map map) {
        super();
        this.map = map;
        this.mapGrid = map.getWalls();
        this.innerS = map.getInnerSize();
        this.startX = map.getStartCircleX();
        this.startY = map.getStartCircleY();
        this.endX = map.getEndCircleX();
        this.endY = map.getEndCircleY();
    }

    @Override
    public void draw() {

    }
    @Override
    public void erase() {

    }
}
