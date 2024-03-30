package org.example.superfastline.container;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Map extends Pane {


        private int size;

        double innerS;
        private Rectangle[][] map;

        public Map(int size) {
            if (size  == 0) return;
            this.size = size;
            init();
        }

        private void init() {
            map = new Rectangle[size-1][size-1];

            for (int row = 0; row < Math.sqrt(size); row++) {
                for (int col = 0; col < Math.sqrt(size); col++) {
                    Rectangle innerBox = new Rectangle(col * innerS, row * innerS, innerS, innerS);
                    innerBox.setFill(Color.TRANSPARENT);
                    innerBox.setStroke(Color.BLUE);
                    this.getChildren().add(innerBox);

                    // Create dot
                    double dotSize = innerS / 5;
                    Circle dot = new Circle(col * innerS + innerS / 2, row *   innerS + innerS / 2, dotSize / 2, Color.RED);
                    this.getChildren().add(dot);
                }
            }
        }

        private void setInnerSize() {
            BoxContainer boxContainer = (BoxContainer) this.getParent();
            innerS = 20;


        }
}
