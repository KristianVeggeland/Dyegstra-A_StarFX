package org.example.superfastline.container;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.superfastline.container.Walls.ClosedBox;
import org.example.superfastline.container.Walls.OpenBox;
import org.example.superfastline.container.Walls.Box;

import java.util.Random;


public class Map extends Pane {
    private BoxContainer parent ;
    private int size;
    private Circle startCircle, endCircle;
    private double innerS;
    private Box[][] map;
    private int startPosX, startPosY, endPosX, endPosY;

    public Map(int size, BoxContainer parent) {
        if (size  == 0) return;
        this.size = size;
        this.parent = parent;
        init();

    }

    private void init() {
        map = new Box[size-1][size-1];
        this.setStyle("-fx-border-color: black");
        setInnerSize();
        generateMap();
        randomStartAndEndPoint();
    }

    private void setInnerSize() {
        innerS =  (double) parent.getWidth() / size;
    }

    private int randomNumb() {

        return (int) (Math.random() * 10);
        }
        private void randomStartAndEndPoint () {
             Random random = new Random();
             int startRow, startCol;
             do {
                 startRow = random.nextInt(size-1);
                 startCol = random.nextInt(size-1);
                 startPosX = startRow;
                 startPosY = startCol;
             } while (isClosedBox(startRow, startCol) || (startRow > size-1 || startCol > size-1));
             double startX = (startCol * innerS) + (innerS / 2);
             double startY = (startRow * innerS) + (innerS / 2);

             // Generate random end circle coordinates not at the edges
            int endRow, endCol;
            do {
                endRow = random.nextInt(size - 1);
                endCol = random.nextInt(size - 1);
                endPosX = endRow;
                endPosY = endCol;
            } while ( isClosedBox(endRow, endCol) ||  (endRow > size-1 || endCol > size-1) || (endRow == startRow && endCol == startCol));
            double endX = (endCol *innerS)  + innerS / 2;
            double endY = (endRow * innerS) + (innerS / 2);

            // Create start circle (green)
            startCircle = new Circle(startY, startX, innerS / 4, Color.BLUE);
            this.getChildren().add(startCircle);

            // Create end circle (red)
            endCircle = new Circle(endY, endX, innerS / 4, Color.RED);
            this.getChildren().add(endCircle);
        }

        private void generateMap()  {
            for (int row = 0; row  < map.length; row++) {
                for (int col = 0; col < map.length; col++) {
                    boolean isEdge = isEdge(row, col);
                    if (isEdge) {
                        map[row][col] = new ClosedBox(innerS, row*innerS, col*innerS, row, col);
                    }
                   else if (randomNumb() > 7) {
                        map[row][col] = new ClosedBox(innerS, row*innerS, col*innerS, row, col);
                    } else {
                        map[row][col] = new OpenBox(innerS, row*innerS, col*innerS, row, col);
                    }
                    this.getChildren().add(map[row][col]);
                }
            }
        }

        private boolean isEdge(int row, int col) {
            return row == 0 || col == 0 || row == map.length -1  || col == map.length-1;
        }

        private boolean isClosedBox(int row , int col) {
            if (map[row][col] instanceof ClosedBox) {
                return true;
            }
            return false;
        }

        public Box[][] getWalls() {
            return map;
        }
        public double getInnerSize() {
            return innerS;
        }

        public int getStartPosX() {
            return startPosX;
        }

        public int getStartPosY() {
            return startPosY;
        }

        public int getEndPosX() {
            return endPosX;
        }

        public int getEndPosY() {
            return endPosY;
        }
}
