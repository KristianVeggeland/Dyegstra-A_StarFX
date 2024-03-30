package org.example.superfastline.container;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import org.example.superfastline.container.Walls.ClosedBox;
import org.example.superfastline.container.Walls.OpenBox;
import org.example.superfastline.container.Walls.Wall;

public class Map extends Pane {


        private int size;

        double innerS;
        private Wall[][] map;

        public Map(int size) {
            if (size  == 0) return;
            this.size = size;
            init();
        }

        private void init() {
            map = new Wall[size-1][size-1];
            this.setStyle("-fx-border-color: black");
            setInnerSize();
            generateMap();
        }

        private void setInnerSize() {
            BoxContainer boxContainer = (BoxContainer) this.getParent();
            innerS =  40;
        }


        private int randomNumb() {
            return (int) (Math.random() * 10);
        }

        private void generateMap()  {
            for (int row = 0; row  < map.length; row++) {
                for (int col = 0; col < map.length; col++) {
                    boolean isEdge = isEdge(row, col);
                    if (isEdge) {
                        map[row][col] = new ClosedBox(innerS, row*innerS, col*innerS);
                    }
                   else if (randomNumb() > 7) {
                        map[row][col] = new ClosedBox(innerS, row*innerS, col*innerS);
                    } else {
                        map[row][col] = new OpenBox(innerS, row*innerS, col*innerS);
                        double dotSize = innerS / 5;
                    }
                    this.getChildren().add(map[row][col]);
                }
            }
        }

        private boolean isEdge(int row, int col) {
            return row == 0 || col == 0 || row == map.length -1  || col == map.length-1 ;
        }
}
