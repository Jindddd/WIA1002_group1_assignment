package com.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import scala.util.parsing.combinator.testing.Str;

import java.util.*;

public class EnemyFortressController extends ApplicationController{
    @FXML
    Text text1;
    @FXML
    Text text2;
    @FXML
    Text text3;
    @FXML
    Text text4;
    @FXML
    Text text5;
    @FXML
    Text text6;
    @FXML
    Text text7;
    @FXML
    Text text8;
    @FXML
    Text text9;
    @FXML
    Text text10;
    @FXML
    Circle node1;
    @FXML
    Circle node2;
    @FXML
    Circle node3;
    @FXML
    Circle node4;
    @FXML
    Circle node5;
    @FXML
    Circle node6;
    @FXML
    Circle node7;
    @FXML
    Circle node8;
    @FXML
    Circle node9;
    @FXML
    Circle node10;
    @FXML
    Label enemyLabel;
    @FXML
    TextField enemyTextField;
    @FXML
    Label bestPathLabel;

    public void submit(ActionEvent event) {
        LinearGradient blueGradient = new LinearGradient(
                0.0, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, new Color(0.0585, 0.4634, 0.8421, 1.0)),
                new Stop(1.0, new Color(1.0, 1.0, 1.0, 1.0)));
        LinearGradient greenGradient = new LinearGradient(
                0.0, 0.0142, 0.7867, 0.6588, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, new Color(0.1306, 0.6447, 0.0627, 1.0)),
                new Stop(0.0067, new Color(0.0677, 0.3816, 0.0194, 1.0)),
                new Stop(0.6074, new Color(0.3108, 0.75, 0.2708, 1.0)),
                new Stop(1.0, new Color(0.5128, 0.8026, 0.5176, 1.0)));
        LinearGradient redGradient = new LinearGradient(
                0.0, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, new Color(1.0, 0.2549, 0.4235, 1.0)),
                new Stop(1.0, new Color(1.0, 0.2941, 0.1686, 1.0)));
        LinearGradient yellowGradient = new LinearGradient(
                0.0, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, new Color(1.0, 0.8784, 0.0, 1.0)),
                new Stop(1.0, new Color(0.4745, 0.6235, 0.0471, 1.0)));
        node1.setFill(greenGradient);
        node2.setFill(blueGradient);
        node3.setFill(blueGradient);
        node4.setFill(blueGradient);
        node5.setFill(blueGradient);
        node6.setFill(blueGradient);
        node7.setFill(blueGradient);
        node8.setFill(blueGradient);
        node9.setFill(blueGradient);
        node10.setFill(blueGradient);
        try {
            // Check if the input format is correct
            if (validate()) {
                int enemyBaseCamp = Integer.parseInt(enemyTextField.getText());
                Graph graph = new Graph(10);

                for (int i = 1; i <= 10; i++) {
                    graph.addNode(new Node(i));
                }

                graph.addEdge(1, 2);
                graph.addEdge(1, 3);
                graph.addEdge(1, 6);
                graph.addEdge(1, 10);
                graph.addEdge(2, 1);
                graph.addEdge(2, 4);
                graph.addEdge(3, 1);
                graph.addEdge(3, 4);
                graph.addEdge(3, 7);
                graph.addEdge(4, 2);
                graph.addEdge(4, 3);
                graph.addEdge(4, 5);
                graph.addEdge(5, 4);
                graph.addEdge(5, 6);
                graph.addEdge(5, 7);
                graph.addEdge(6, 1);
                graph.addEdge(6, 5);
                graph.addEdge(6, 7);
                graph.addEdge(6, 8);
                graph.addEdge(7, 5);
                graph.addEdge(7, 6);
                graph.addEdge(7, 8);
                graph.addEdge(7, 9);
                graph.addEdge(8, 6);
                graph.addEdge(8, 7);
                graph.addEdge(8, 9);
                graph.addEdge(8, 10);
                graph.addEdge(9, 7);
                graph.addEdge(9, 8);
                graph.addEdge(9, 10);
                graph.addEdge(10, 1);
                graph.addEdge(10, 8);
                graph.addEdge(10, 9);

                List<List<Integer>> shortestPaths = graph.bfs(1, enemyBaseCamp);
                StringBuilder sb = new StringBuilder();
                sb.append("Best path:\n");

                for (List<Integer> path : shortestPaths) {
                    for (int i = 0; i < path.size(); i++) {
                        LinearGradient pathNodeColor = yellowGradient;
                        if (i == path.size() - 1) {
                            pathNodeColor = redGradient;
                        }
                        sb.append(path.get(i));
                        if (path.get(i) == 2) {
                            node2.setFill(pathNodeColor);
                        } else if (path.get(i) == 3) {
                            node3.setFill(pathNodeColor);
                        } else if (path.get(i) == 4) {
                            node4.setFill(pathNodeColor);
                        } else if (path.get(i) == 5) {
                            node5.setFill(pathNodeColor);
                        } else if (path.get(i) == 6) {
                            node6.setFill(pathNodeColor);
                        } else if (path.get(i) == 7) {
                            node7.setFill(pathNodeColor);
                        } else if (path.get(i) == 8) {
                            node8.setFill(pathNodeColor);
                        } else if (path.get(i) == 9) {
                            node9.setFill(pathNodeColor);
                        } else if (path.get(i) == 10) {
                            node10.setFill(pathNodeColor);
                        }
                        if (i != path.size() - 1) {
                            sb.append(" -> ");
                        }
                    }
                    sb.append("\n");
                }
                bestPathLabel.setText(sb.toString());
            }


        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("Enter only integer!!!");
            alert.showAndWait();
        }
    }

    boolean validate() {
        StringBuilder errors = new StringBuilder();

        // Confirm mandatory fields are filled out
        if (enemyTextField.getText().trim().isEmpty() || Integer.parseInt(enemyTextField.getText()) < 2 || Integer.parseInt(enemyTextField.getText()) > 10) {
            errors.append("Please enter available enemy base camp (2-10)\n");
            enemyLabel.setTextFill(Paint.valueOf("red"));
        }


        // If any missing information is found, show the error messages and return false
        if (errors.length() > 0) {
            printError(errors);
            return false;
        }

        // No errors
        return true;
    }

    static class Node {
        int data;

        Node (int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }

    static class Graph {
        private ArrayList<Node> nodes;
        private int[][] matrix;

        public Graph(int size) {
            nodes = new ArrayList<>();
            matrix = new int[size][size];
        }

        public void addNode(Node node) {
            nodes.add(node);
        }

        public void addEdge(int src, int dst) {
            matrix[src - 1][dst - 1] = 1;
        }

        public List<List<Integer>> bfs(int src, int dst) {
            List<List<Integer>> allPaths = new ArrayList<>(); // List to store all the paths
            int shortestPathLength = Integer.MAX_VALUE;  // Variable to track the minimum path length
            Set<List<Integer>> visitedPaths = new HashSet<>();  // Set to keep track of visited paths

            for (int startNode = 1; startNode <= nodes.size(); startNode++) {
                Queue<List<Integer>> queue = new LinkedList<>();
                queue.offer(new ArrayList<>(List.of(src))); // Add the source node to the queue

                boolean[] visited = new boolean[nodes.size()];
                visited[startNode - 1] = true;

                while (!queue.isEmpty()) {
                    List<Integer> currentPath = queue.poll(); // Get the current path
                    int currentNode = currentPath.get(currentPath.size() - 1);

                    // If destination found
                    if (currentNode == dst) {
                        if (currentPath.size() < shortestPathLength) {
                            shortestPathLength = currentPath.size();  // Update the shortest path length
                            allPaths.clear();  // Clear the existing paths as we found a shorter path
                        }
                        // Add the path to the list of paths only if it is equal the size shortest path
                        if (currentPath.size() == shortestPathLength && !visitedPaths.contains(currentPath)) {
                            allPaths.add(currentPath);
                            visitedPaths.add(currentPath);
                        }
                        continue;
                    }

                    for (int i = 0; i < matrix[currentNode - 1].length; i++) {
                        if (matrix[currentNode - 1][i] == 1 && !visited[i]) {
                            List<Integer> newPath = new ArrayList<>(currentPath);
                            newPath.add(i + 1);
                            queue.offer(newPath);
                            visited[i] = true;
                        }
                    }
                }
            }
            return allPaths;
        }
    }
}
