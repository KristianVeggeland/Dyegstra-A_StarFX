package org.example.superfastline.container;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.example.superfastline.logic.AlgoType;
import org.example.superfastline.logic.Drawing;

public class BoxContainer extends BorderPane {

    Label title = new Label("");

    private int mapSize;

    AlgoType algoType;

    Drawing drawing;

    public BoxContainer(AlgoType a, int mS) {
        if(a == null) return;
        algoType = a;
        mapSize = mS;
        init();
    }

    private void init() {
        this.setMinWidth(ApplicationBox.WIDTH);
        this.setMinHeight(ApplicationBox.HEIGHT);
        if (algoType == AlgoType.DYKSTRA) {
            title.setText("Dyekstra");
        } else {
            title.setText("A*");
        }
        this.setTop(title);
        this.setCenter(new Map(mapSize));
    }

    public void flush() {
        this.setCenter(null);
    }


}
