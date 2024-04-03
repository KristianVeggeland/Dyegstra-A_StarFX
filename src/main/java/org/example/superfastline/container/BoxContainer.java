package org.example.superfastline.container;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.example.superfastline.logic.AlgoType;
import org.example.superfastline.logic.GreedyS;
import org.example.superfastline.logic.BFS;
import org.example.superfastline.logic.Drawing;

public class BoxContainer extends BorderPane {

    Label title = new Label("");
    Button start = new Button(" Run ");
    Button back = new Button(" Back ");
    private int mapSize;
    AlgoType algoType;
    Map map;
    Drawing drawing;
    boolean pointReached = false;
    boolean isRunning = false;

    Label steps = new Label(" Operations: N/A");
    Label stepsOfBestPath = new Label(" Steps of shortest path: N/A");
    VBox facts = new VBox();


    public BoxContainer(AlgoType a, int mS) {
        if(a == null) return;
        algoType = a;
        mapSize = mS +1;
        init();
    }

    private void init() {
        this.setWidth(750);
        this.setHeight(750);
        HBox titleBox = new HBox();
        titleBox.setSpacing(10);
        facts.getChildren().add(steps);
        facts.getChildren().add(stepsOfBestPath);
        this.setRight(facts);
        start.setOnAction(e -> {
            startAlgo();
        });
        back.setOnAction(e -> {
            isRunning = false;
            pointReached = false;
            ApplicationBox parent = (ApplicationBox) this.getParent();
            parent.goToLanding();
        });
        titleBox.getChildren().add(title);
        titleBox.getChildren().add(start);
        titleBox.getChildren().add(back);
        this.setTop(titleBox);
        this.map = new Map(mapSize, this);
        if (algoType == AlgoType.BFS) {
            title.setText("BFS");
            drawing = new BFS(this.map, this);
        } else {
            title.setText("GREEDYSEARCH");
            drawing = new GreedyS(this.map, this);
        }
        this.setCenter(map);
    }

    public void flush() {
        this.setCenter(null);
    }

    private void refresh() {
        System.out.println("Refresh");
        flush();
        this.setCenter(map);
    }

    private void startAlgo() {
        if (!pointReached) {
            drawing.draw();
        }
    }

    public void setPointReached(boolean pointReached) {
        this.isRunning = false;
        this.pointReached = pointReached;
    }

    public void setSteps(int steps) {
        this.steps.setText("Steps: " + steps);
    }

    public void setStepsOfBestPath(int steps) {
        this.stepsOfBestPath.setText("Steps of best path: " + steps);
    }

    public void updateFacts() {
        facts.getChildren().clear();
        facts.getChildren().add(steps);
        facts.getChildren().add(stepsOfBestPath);
        setRight(facts);
    }

}
