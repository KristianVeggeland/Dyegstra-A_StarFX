package org.example.superfastline.logic;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class DeegMoves {
    int moves;
    private ArrayList<Line> lineList;

    public DeegMoves() {
        this.moves = 0;
        this.lineList = new ArrayList<>();
    }

    public void addMove(Line line) {
        this.lineList.add(line);
        moves++;
    }

    public void undoMove() {
        if (moves > 0) {
            lineList.remove(moves - 1);
            moves--;
        }
    }

    public double[] getTranslationStartingPoint( int index) {
        double x = lineList.get(index).getStartX();
        double y = lineList.get(index).getStartY();
        return new double[]{x, y};
    }
}
