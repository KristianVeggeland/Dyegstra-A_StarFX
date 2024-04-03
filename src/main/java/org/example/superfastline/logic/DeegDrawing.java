package org.example.superfastline.logic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.superfastline.container.BoxContainer;
import org.example.superfastline.container.Map;
import org.example.superfastline.container.Boxes.Box;
import org.example.superfastline.container.Boxes.ClosedBox;
import org.example.superfastline.logic.Drawing;

import java.util.*;

public class DeegDrawing implements Drawing {

    private Map map;
    private Box[][] mapGrid;
    private int startPointX, startPointY, endPointX, endPointY;
    private HashMap<Box, List<Box>> allPaths = new HashMap<>();
    private List<Box> successfulPath = new ArrayList<>();

    private int boxesVisited = 0;

    BoxContainer boxContainer;

    public DeegDrawing(Map map, BoxContainer boxContainer) {

        super();
        System.out.println("DeegDrawing constructor called");
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
        System.out.println("Drawing Deeg's algorithm...");
        System.out.println("Starting Dijkstra's algorithm...");
        colorStartAndEndPoints();
        findShortestPath();
        visualizePath();
    }

    private void colorStartAndEndPoints() {
        mapGrid[startPointX][startPointY].setFill(Color.BLUE);
        mapGrid[endPointX][endPointY].setFill(Color.RED);
    }

    private void findShortestPath() {
        // Initialize queue for BFS
        Queue<Box> queue = new LinkedList<>();
        Box startBox = mapGrid[startPointX][startPointY]; // Corrected order for start point
        startBox.setVisited(true);
        queue.add(startBox);
        List<Box> initialPath = new ArrayList<>();
        initialPath.add(startBox);
        allPaths.put(startBox, initialPath);

        while (!queue.isEmpty()) {
            System.out.println("Queue size: " + queue.size());
            Box current = queue.poll();
            int x = current.getRow();
            int y = current.getCol();
            // Check if we've reached the end point
            if (x == endPointX && y == endPointY) {
                System.out.println("Reached end point: (" + x + ", " + y + ")");
                reconstructSuccessfulPath(current);
                //System.out.println("Boxes visited: " + boxesVisited);
                boxContainer.setSteps(boxesVisited);
                boxContainer.updateFacts();
                boxContainer.setPointReached(true);
                break;
            }

            // Check neighbors
            List<Box> neighbors = getNeighbors(current);
            for (Box neighbor : neighbors) {
                if (!neighbor.isVisited()) {
                    neighbor.setFill(Color.YELLOW);
                    neighbor.setVisited(true);
                    boxesVisited++;
                    neighbor.setPrevious(current);
                    queue.add(neighbor);
                    List<Box> path = new ArrayList<>(allPaths.getOrDefault(current, new ArrayList<>()));
                    path.add(neighbor);
                    allPaths.put(neighbor, path);
                }
            }
        }
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
          //  System.out.println("box: " + n +" contains: "  + box.toString());
            box.setFill(Color.GREEN);
        }
    }

    @Override
    public void erase() {

    }
}
