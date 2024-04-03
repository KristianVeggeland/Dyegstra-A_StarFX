package org.example.superfastline.container;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.example.superfastline.logic.AlgoType;

import java.util.List;

public class ApplicationBox extends BorderPane {

    static final Label title = new Label(" Pathfinding ");
    static final int HEIGHT = 800;
    static final int WIDTH = 800;

    Landing landing = new Landing(this);

    private BoxContainer boxContainer;


    public ApplicationBox() {
            init();
    }

    private void init() {
        this.setPrefSize(WIDTH, HEIGHT);
        Pane top = new Pane(title);
        top.setMinWidth(WIDTH);
        this.setTop(top);
        this.setCenter(landing);
    }

    public void setBoxContainer(BoxContainer boxContainer) {
        this.boxContainer = boxContainer;
        this.setCenter(null);
        this.setCenter(boxContainer);
    }

    public void flush() {
        this.setCenter(null);
    }

    public void goToLanding() {
        this.setCenter(landing);
    }
}
