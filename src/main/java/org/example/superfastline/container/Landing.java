package org.example.superfastline.container;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.example.superfastline.logic.AlgoType;

public class Landing extends VBox {



    static final Label typeS = new Label("Select type: ");
    static final Label size = new Label("Select size: ");


    TextField sizeField = new TextField("");

    private RadioButton deegButton = new RadioButton("Dyegstra");
    private RadioButton aStarButton = new RadioButton("A*");

    private AlgoType algoType;

    public Landing() {
        init();
    }

    private void init() {
        this.getChildren().add(typeS);

        ToggleGroup algoGroup = new ToggleGroup();
        deegButton.setSelected(true);
        deegButton.setToggleGroup(algoGroup);
        aStarButton.setToggleGroup(algoGroup);
        algoGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (algoGroup.getSelectedToggle().toString() == "Dyegstra") {
               algoType = AlgoType.DYKSTRA;
            }
            if (algoGroup.getSelectedToggle().toString() == "A*") {
                algoType = AlgoType.A_STAR;
            }
        });
        HBox algoBox = new HBox();
        algoBox.getChildren().addAll(deegButton, aStarButton);
        this.getChildren().add(algoBox);

        this.getChildren().add(size);

        HBox sizeBox = new HBox();
        sizeBox.getChildren().addAll(sizeField);
        this.getChildren().add(sizeBox);

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> onStart());
        this.getChildren().add(startButton);
    }


    public void clean() {
        this.getChildren().removeAll(this.getChildren());
        init();
    }

    private void onStart() {
        ApplicationBox parent = (ApplicationBox) this.getParent();
        parent.flush();
        parent.setCenter(new BoxContainer(algoType, Integer.parseInt(sizeField.getText())));
    }

}
