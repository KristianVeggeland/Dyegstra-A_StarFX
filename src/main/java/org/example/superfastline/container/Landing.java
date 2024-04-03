package org.example.superfastline.container;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.example.superfastline.logic.AlgoType;

public class Landing extends VBox {

    static final Label typeS = new Label("Select type: ");
    static final Label size = new Label("Select size: ");
    TextField sizeField = new TextField("");
    private RadioButton deegButton = new RadioButton(" BFS ");
    private RadioButton aStarButton = new RadioButton(" GreedySearch ");
    private AlgoType algoType = AlgoType.BFS;
    ApplicationBox parent;

    public Landing(ApplicationBox parent) {
        this.parent = parent;
        init();
    }

    private void init() {
        this.getChildren().add(typeS);

        ToggleGroup algoGroup = new ToggleGroup();
        deegButton.setSelected(true);
        deegButton.setToggleGroup(algoGroup);
        aStarButton.setToggleGroup(algoGroup);
        algoGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            RadioButton selectedButton = (RadioButton) newValue.getToggleGroup().getSelectedToggle();
            if (selectedButton == deegButton) {
                algoType = AlgoType.BFS;
                System.out.println(algoType);
            } else if (selectedButton == aStarButton) {
                algoType = AlgoType.GreedyS;
                System.out.println(algoType);
            }
        });
        HBox algoBox = new HBox();
        algoBox.getChildren().addAll(deegButton, aStarButton);
        this.getChildren().add(algoBox);

        this.getChildren().add(size);

        HBox sizeBox = new HBox();
        sizeBox.getChildren().addAll(sizeField);
        this.getChildren().add(sizeBox);

        Button startButton = new Button(" Try it ");
        startButton.setOnAction(e -> onStart());
        this.getChildren().add(startButton);
    }


    public void clean() {
        sizeField.setText("");
    }

    private void onStart() {
        parent.flush();
        if (sizeField.getText().isEmpty()) {
            sizeField.setText("give size");
            return;
        }
        if (sizeField.getText().matches("[a-zA-Z]+")) {
            sizeField.setText("give number");
            return;
        }
        if (Integer.parseInt(sizeField.getText()) == 0) {
            sizeField.setText("give size > 0");
            return;
        }
        parent.setBoxContainer(new BoxContainer(algoType, Integer.parseInt(sizeField.getText())));
        clean();
    }

}
