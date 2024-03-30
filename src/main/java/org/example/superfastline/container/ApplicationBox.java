package org.example.superfastline.container;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.example.superfastline.logic.AlgoType;

import java.util.List;

public class ApplicationBox extends BorderPane {

    static final Label title = new Label("Super Fast Line");
    static final int HEIGHT = 800;
    static final int WIDTH = 600;

    final Landing landing = new Landing();


    public ApplicationBox() {
            init();
    }

    private void init() {
        this.setPrefSize(WIDTH, HEIGHT);
        this.setTop(title);
        //this.setCenter(landing);
        this.setCenter(new BoxContainer(AlgoType.A_STAR, 8));
    }

    public void flush() {
        this.setCenter(null);
    }


}
