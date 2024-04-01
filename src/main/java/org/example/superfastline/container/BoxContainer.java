package org.example.superfastline.container;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.superfastline.logic.AlgoType;
import org.example.superfastline.logic.DeegDrawing;
import org.example.superfastline.logic.Drawing;

public class BoxContainer extends BorderPane {

    Label title = new Label("");

    Button start = new Button("Start");

    private int mapSize;

    AlgoType algoType;
    Map map;
    Drawing drawing;
    boolean pointReached = false;
    boolean isRunning = false;

    public BoxContainer(AlgoType a, int mS) {
        if(a == null) return;
        algoType = a;
        mapSize = mS +1;
        init();
    }

    private void init() {
        this.setMinWidth(ApplicationBox.WIDTH);
        this.setMinHeight(ApplicationBox.HEIGHT);
        HBox titleBox = new HBox();
        titleBox.getChildren().add(title);
        titleBox.getChildren().add(start);
        this.setTop(titleBox);
        this.map = new Map(mapSize, this);
        if (algoType == AlgoType.DYKSTRA) {
            title.setText("Dyekstra");
            drawing = new DeegDrawing(this.map);
        } else {
            title.setText("A*");
        }
        this.setCenter(map);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2.0), event -> {
      //     if (isRunning) {
                if (!pointReached) {
                    refresh();
                }
        //   }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void flush() {
        this.setCenter(null);
    }

    private void refresh() {
        System.out.println("Refresh");
        drawing = new DeegDrawing(this.map);
        this.setCenter(null);
        this.setCenter(map);
    }

    public double getInnerSize() {
        return ApplicationBox.WIDTH ;
    }

    public void setPointReached(boolean pointReached) {
        this.pointReached = pointReached;
    }
}
