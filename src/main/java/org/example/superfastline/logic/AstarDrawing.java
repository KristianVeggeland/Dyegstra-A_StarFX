package org.example.superfastline.logic;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import org.example.superfastline.container.BoxContainer;
import org.example.superfastline.container.Map;
import org.example.superfastline.container.Boxes.Box;
import org.example.superfastline.container.Boxes.ClosedBox;
import org.example.superfastline.logic.Drawing;

import java.util.*;

public class AstarDrawing extends Pane implements Drawing {
    private Map map;
    private Box[][] mapGrid;
    private int startPointX, startPointY, endPointX, endPointY;
    private HashMap<Box, List<Box>> allPaths = new HashMap<>();
    private List<Box> successfulPath = new ArrayList<>();

    private int boxesVisited = 0;

    BoxContainer boxContainer;

    public AstarDrawing(Map map, BoxContainer boxContainer) {
        if (boxContainer == null) throw new IllegalArgumentException("BoxContainer cannot be null");
        this.boxContainer = boxContainer;
        this.map = map;
        this.mapGrid = map.getWalls();
        this.startPointX = map.getStartPosX();
        this.startPointY = map.getStartPosY();
        this.endPointX = map.getEndPosX();
        this.endPointY = map.getEndPosY();
    }

    @Override
    public void draw() {
        System.out.println("Drawing A* algorithm...");
        System.out.println("Starting A* algorithm...");
        colorStartAndEndPoints();
        findShortestPath();
        visualizePath();
    }

    private void colorStartAndEndPoints() {
        mapGrid[startPointX][startPointY].setFill(Color.BLUE);
        mapGrid[endPointX][endPointY].setFill(Color.RED);
    }

    private void findShortestPath() {
        // Initialize priority queue for A*
        PriorityQueue<Box> queue = new PriorityQueue<>(Comparator.comparingInt(a -> heuristic(a)));
        Box startBox = mapGrid[startPointX][startPointY];
        queue.add(startBox);
        allPaths.put(startBox, new ArrayList<>());
        while (!queue.isEmpty()) {
            Box current = queue.poll();
            current.setVisited(true);
            if (current.getRow() == endPointX && current.getCol() == endPointY) {
                reconstructSuccessfulPath(current);
                //System.out.println("Boxes visited: " + boxesVisited);
                boxContainer.setSteps(boxesVisited);
                boxContainer.updateFacts();
                boxContainer.setPointReached(true);
                break;
            }
            List<Box> neighbors = getNeighbors(current);
            for (Box neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    allPaths.put(neighbor, new ArrayList<>(allPaths.get(current)));
                    allPaths.get(neighbor).add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

    }

    private int heuristic(Box a) {
        return Math.abs(a.getRow() - endPointX) + Math.abs(a.getCol() - endPointY);
    }

    private void reconstructSuccessfulPath(Box current) {
        successfulPath = allPaths.get(current);
        boxContainer.setStepsOfBestPath(successfulPath.size() - 1);
        System.out.println("Successful path: " + successfulPath);
    }

    private List<Box> getNeighbors(Box current) {
        List<Box> neighbors = new ArrayList<>();
        int x = current.getCol();
        int y = current.getRow();

        // Check if the neighboring boxes are within bounds and are not walls
        if (x - 1 >= 0 && !(mapGrid[y][x - 1] instanceof ClosedBox)) {
            neighbors.add(mapGrid[y][x - 1]); // Left
        }
        if (x + 1 < mapGrid[0].length && !(mapGrid[y][x + 1] instanceof ClosedBox)) {
            neighbors.add(mapGrid[y][x + 1]); // Right
        }
        if (y - 1 >= 0 && !(mapGrid[y - 1][x] instanceof ClosedBox)) {
            neighbors.add(mapGrid[y - 1][x]); // Up
        }
        if (y + 1 < mapGrid.length && !(mapGrid[y + 1][x] instanceof ClosedBox)) {
            neighbors.add(mapGrid[y + 1][x]); // Down
        }
        return neighbors;
    }

    private void visualizePath() {
        // Color the boxes belonging to the successful path in green
        for (Box box : successfulPath) {
            box.setFill(Color.GREEN);
        }
    }

    @Override
    public void erase() {
        // Clear any visuals if needed
    }
}
