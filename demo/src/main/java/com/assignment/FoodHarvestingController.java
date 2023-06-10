package com.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class FoodHarvestingController extends ApplicationController {
    @FXML
    Label noFoodNodeLabel;
    @FXML
    Label bestPathLabel;
    @FXML
    TextField noFoodNodeTextField;
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
    CubicCurve edge12;
    @FXML
    CubicCurve edge13;
    @FXML
    CubicCurve edge16;
    @FXML
    CubicCurve edge110;
    @FXML
    CubicCurve edge24;
    @FXML
    CubicCurve edge34;
    @FXML
    CubicCurve edge37;
    @FXML
    CubicCurve edge45;
    @FXML
    CubicCurve edge56;
    @FXML
    CubicCurve edge57;
    @FXML
    CubicCurve edge67;
    @FXML
    CubicCurve edge68;
    @FXML
    CubicCurve edge78;
    @FXML
    CubicCurve edge79;
    @FXML
    CubicCurve edge89;
    @FXML
    CubicCurve edge810;
    @FXML
    CubicCurve edge910;

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
    public void submit(ActionEvent event) {

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

        edge12.setStroke(Color.BLACK);
        edge13.setStroke(Color.BLACK);
        edge16.setStroke(Color.BLACK);
        edge110.setStroke(Color.BLACK);
        edge24.setStroke(Color.BLACK);
        edge34.setStroke(Color.BLACK);
        edge37.setStroke(Color.BLACK);
        edge45.setStroke(Color.BLACK);
        edge56.setStroke(Color.BLACK);
        edge57.setStroke(Color.BLACK);
        edge67.setStroke(Color.BLACK);
        edge68.setStroke(Color.BLACK);
        edge78.setStroke(Color.BLACK);
        edge79.setStroke(Color.BLACK);
        edge89.setStroke(Color.BLACK);
        edge810.setStroke(Color.BLACK);
        edge910.setStroke(Color.BLACK);

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Best path:\n");
            // Check if the input format is correct
            if (validate()) {
                int[][] graph = {
                        {0, 1, 1, 0, 0, 1, 0, 0, 0, 1},
                        {1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                        {1, 0, 0, 1, 0, 0, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 1, 1, 0, 0, 0},
                        {1, 0, 0, 0, 1, 0, 1, 1, 0, 0},
                        {0, 0, 1, 0, 1, 1, 0, 1, 1, 0},
                        {0, 0, 0, 0, 0, 1, 1, 0, 1, 1},
                        {0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 1, 1, 0}
                };

                int nodeWithoutFood = Integer.parseInt(noFoodNodeTextField.getText());
                List<Integer> visitedNodes = new ArrayList<>();

                int startNode = 1; // Start with Node 1
                int endNode;

                if (nodeWithoutFood != 10) {
                    endNode = 10;    // End with Node 10

                    List<Integer> path1 = findPath1(graph, startNode, endNode, nodeWithoutFood, visitedNodes);
                    // Node that must be included in the path
                    if (nodeWithoutFood == 4 || nodeWithoutFood == 6 || nodeWithoutFood == 7 || nodeWithoutFood == 8 )
                        sb.append("Node ").append(nodeWithoutFood).append(" must be included in the path to ensure the connectivity of all food nodes").append(".\n");

                    if (path1 != null) {
                        int lastNode = 0;
                        String edgeID = "";
                        sb.append("Path: ");
                        for (int node : path1) {
                            sb.append(node).append(" -> ");
                            edgeID =  "edge" + lastNode + node;
                            setNodeAndEdge(node, edgeID);
                            lastNode = node;
                        }
                        edgeID =  "edge" + lastNode + 1;
                        setNodeAndEdge(1, edgeID);
                        sb.append("1").append("\n");
                    }
                } else {
                    endNode = 2;

                    List<Integer> path2 = findPath2(graph, startNode, endNode, nodeWithoutFood, visitedNodes);

                    if (path2 != null) {
                        sb.append("Path: ");
                        int lastNode = 0;
                        String edgeID = "";
                        for (int node : path2) {
                            sb.append(node).append(" -> ");
                            edgeID =  "edge" + lastNode + node;
                            setNodeAndEdge(node, edgeID);
                            lastNode = node;
                        }
                        edgeID =  "edge" + lastNode + 1;
                        setNodeAndEdge(1, edgeID);
                        sb.append("1").append("\n");
                    }
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

    void setNodeAndEdge(int node, String edgeID) {
        if (node == 2) {
            node2.setFill(yellowGradient);
        } else if (node == 3) {
            node3.setFill(yellowGradient);
        } else if (node == 4) {
            node4.setFill(yellowGradient);
        } else if (node == 5) {
            node5.setFill(yellowGradient);
        } else if (node == 6) {
            node6.setFill(yellowGradient);
        } else if (node == 7) {
            node7.setFill(yellowGradient);
        } else if (node == 8) {
            node8.setFill(yellowGradient);
        } else if (node == 9) {
            node9.setFill(yellowGradient);
        } else if (node == 10) {
            node10.setFill(yellowGradient);
        }

        switch (edgeID) {
            case "edge12", "edge21" -> edge12.setStroke(Color.RED);
            case "edge13", "edge31" -> edge13.setStroke(Color.RED);
            case "edge16", "edge61" -> edge16.setStroke(Color.RED);
            case "edge110", "edge101" -> edge110.setStroke(Color.RED);
            case "edge24", "edge42" -> edge24.setStroke(Color.RED);
            case "edge34", "edge43" -> edge34.setStroke(Color.RED);
            case "edge37", "edge73" -> edge37.setStroke(Color.RED);
            case "edge45", "edge54" -> edge45.setStroke(Color.RED);
            case "edge56", "edge65" -> edge56.setStroke(Color.RED);
            case "edge57", "edge75" -> edge57.setStroke(Color.RED);
            case "edge67", "edge76" -> edge67.setStroke(Color.RED);
            case "edge68", "edge86" -> edge68.setStroke(Color.RED);
            case "edge78", "edge87" -> edge78.setStroke(Color.RED);
            case "edge79", "edge97" -> edge79.setStroke(Color.RED);
            case "edge89", "edge98" -> edge89.setStroke(Color.RED);
            case "edge810", "edge108" -> edge810.setStroke(Color.RED);
            case "edge910", "edge109" -> edge910.setStroke(Color.RED);
        }
    }

    boolean validate() {
        StringBuilder errors = new StringBuilder();

        // Confirm mandatory fields are filled out
        if (noFoodNodeTextField.getText().trim().isEmpty() || Integer.parseInt(noFoodNodeTextField.getText()) < 2 || Integer.parseInt(noFoodNodeTextField.getText()) > 10) {
            errors.append("Please enter available enemy base camp (2-10)\n");
            noFoodNodeLabel.setTextFill(Paint.valueOf("red"));
        }

        // If any missing information is found, show the error messages and return false
        if (errors.length() > 0) {
            printError(errors);
            return false;
        }

        // No errors
        return true;
    }

    private List<Integer> findPath1(int[][] graph, int currentNode, int endNode, int nodeWithoutFood, List<Integer> visitedNodes) {

        visitedNodes.add(currentNode);

        if (nodeWithoutFood == 4) {
            nodeWithoutFood = 0;
        }

        if (nodeWithoutFood == 6) {
            nodeWithoutFood = 0;
        }

        if (nodeWithoutFood == 8) {
            nodeWithoutFood = 0;
        }

        if (nodeWithoutFood == 7) {
            nodeWithoutFood = 0;
        }

        if (currentNode == endNode || visitedNodes.contains(nodeWithoutFood)) {
            List<Integer> path = new ArrayList<>();
            path.add(currentNode);
            return path;
        }

        for (int nextNode = 1; nextNode <= graph[currentNode - 1].length; nextNode++) {

            if (graph[currentNode - 1][nextNode - 1] == 1 && !visitedNodes.contains(nextNode) && nextNode != nodeWithoutFood) {

                graph[currentNode - 1][nextNode - 1] = 0;  // Mark the edge as visited
                graph[nextNode - 1][currentNode - 1] = 0;  // Mark the edge as visited

                if (!visitedNodes.contains(currentNode)) {
                    visitedNodes.add(currentNode);
                } // Add the next node to the visited nodes list

                List<Integer> path = findPath1(graph, nextNode, endNode, nodeWithoutFood, visitedNodes);
                if (path != null) {
                    path.add(0, currentNode);  // Add the current node to the beginning of the path
                    return path;
                }

                graph[currentNode - 1][nextNode - 1] = 1;  // Reset the visited edge
                graph[nextNode - 1][currentNode - 1] = 1;  // Reset the visited edge

                visitedNodes.remove(visitedNodes.size() - 1);  // Remove the last visited node if no path found
            }
        }

        visitedNodes.remove(Integer.valueOf(currentNode));  // Backtrack, remove the current node from the visited nodes list
        return null;
    }

    public static List<Integer> findPath2(int[][] graph, int startNode, int endNode, int nodeWithoutFood, List<Integer> visitedNodes) {
        int currentNode = startNode;

        if (currentNode == endNode) {
            List<Integer> path = new ArrayList<>();
            path.add(endNode);
            return path;
        }

        if (currentNode == nodeWithoutFood) {
            visitedNodes.add(currentNode);
        }

        for (int nextNode = 10; nextNode >= 1; nextNode--) {
            if (nextNode == nodeWithoutFood || visitedNodes.contains(nextNode) ) {
                continue;  // Skip the node without food or already visited nodes
            }

            if( currentNode == 1 && nextNode == 6 ){
                nextNode=3;
            }

            if (graph[currentNode - 1][nextNode - 1] == 1) {
                graph[currentNode - 1][nextNode - 1] = 0;  // Mark the edge as visited
                graph[nextNode - 1][currentNode - 1] = 0;  // Mark the edge as visited

                visitedNodes.add(nextNode);  // Add the next node to the visited nodes list

                List<Integer> path = findPath2(graph, nextNode, endNode, nodeWithoutFood, visitedNodes);
                if (path != null) {
                    path.add(0, startNode);  // Add the current node to the beginning of the path
                    return path;
                }

                graph[currentNode - 1][nextNode - 1] = 1;  // Reset the visited edge
                graph[nextNode - 1][currentNode - 1] = 1;  // Reset the visited edge

                visitedNodes.remove(visitedNodes.size() - 1);  // Remove the last visited node if no path found
            }
        }

        return null;  // No path found
    }
}
