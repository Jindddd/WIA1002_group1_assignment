package com.assignment;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class HuaRongRoadController extends ApplicationController {
    @FXML
    Button findButton;
    @FXML
    GridPane mazeGridPane;
    Color entranceColor = new Color(0.7725, 0.4824, 0.3412, 1.0);
    Color obstructedPathColor = new Color(0.1451, 0.0863, 0.0196, 1.0);
    Color exitColor = new Color(0.6118, 0.6863, 0.7176, 1.0);
    Color escapePathColor = new Color(0.9686, 0.8588, 0.6549, 1.0);
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] maze = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {2, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    /*  0: Path
        1: Obstacle
        2: Starting point
        3: Ending point*/
    static char[][] pathMaze;

    public void find() {
        pathMaze = new char[maze.length][maze[0].length];

        // Initialize path of maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                pathMaze[i][j] = Integer.toString(maze[i][j]).charAt(0);
            }
        }

        // insert the starting and ending points
        int[] start = {1, 0};
        int[] end = {9, 12};

        animatePathFinding(start, end);
    }

    public void animatePathFinding(int[] start, int[] end) {
        Timeline timeline = new Timeline();
        final Duration duration = Duration.seconds(1); // Duration of each animation step

        animateCell(timeline, start, duration); // Animate the starting point
        boolean pathFound = animateDFS(timeline, start, end, duration); // Animate the path finding process
        if (!pathFound) {
            System.out.println("Path not found!"); // Print message if path cannot be found
        }
        timeline.play(); // Start the animation
    }

    public boolean animateDFS(Timeline timeline, int[] start, int[] end, Duration duration) {
        if (!isValid(start[0], start[1]))
            return false;
        if (maze[start[0]][start[1]] == 3)
            return true;

        // mark the cell as visited temporarily
        char temp = pathMaze[start[0]][start[1]];
        pathMaze[start[0]][start[1]] = '.';
        animateCell(timeline, new int[]{start[0], start[1]}, duration);

        for (int[] dir : directions) {
            int newX = start[0] + dir[0];
            int newY = start[1] + dir[1];
            if (animateDFS(timeline, new int[]{newX, newY}, end, duration)) {
                animateCell(timeline, new int[]{newX, newY}, duration); // Animate the path
                return true;
            }
        }

        // if the cell does not lead to the target, unmark it and backtrack
        pathMaze[start[0]][start[1]] = temp;
        animateCell(timeline, new int[]{start[0], start[1]}, duration);
        return false;
    }

    public void animateCell(Timeline timeline, int[] cell, Duration duration) {
        Rectangle rectangle = new Rectangle(40, 40);
        rectangle.setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.3));
        rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
        rectangle.setStrokeLineJoin(StrokeLineJoin.ROUND);
        rectangle.setStrokeWidth(0.5);
        rectangle.setSmooth(true);
        rectangle.setStrokeType(StrokeType.INSIDE);

        char value = pathMaze[cell[0]][cell[1]];
        rectangle.setFill(value == '3' ? exitColor :
                          value == '2' ? entranceColor :
                          value == '.' ? escapePathColor :
                          value == '1' ? obstructedPathColor : Color.WHITE);

        // Animate the opacity of the cell to create a fade-in effect
        rectangle.setOpacity(0);
        KeyFrame fadeFrame = new KeyFrame(duration, "opacity", new KeyValue(rectangle.opacityProperty(), 1));
        timeline.getKeyFrames().add(fadeFrame);

        mazeGridPane.add(rectangle, cell[1], cell[0]);
    }

    public static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] != 1 && pathMaze[x][y] != '.';
    }
}
