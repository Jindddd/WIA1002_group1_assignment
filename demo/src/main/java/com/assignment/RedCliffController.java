package com.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Paint;

import java.util.Arrays;

public class RedCliffController extends ApplicationController {
    @FXML
    Label columnLabel;
    @FXML
    Label rowLabel;
    @FXML
    Label matrixLabel;
    @FXML
    TextField columnTextField;
    @FXML
    TextField rowTextField;
    @FXML
    TextArea matrixTextArea;
    @FXML
    Label clusterLabel;
    @FXML
    GridPane myGridPane;

    public void submit(ActionEvent event) {
        // Reset the Label text to black
        columnLabel.setTextFill(Paint.valueOf("black"));
        rowLabel.setTextFill(Paint.valueOf("black"));
        matrixLabel.setTextFill(Paint.valueOf("black"));
        // Clear the old GridPane
        myGridPane.getChildren().clear();

        try {
            // Check if the input format is correct
            if (validate()) {
                int column = Integer.parseInt(columnTextField.getText());
                int row = Integer.parseInt(rowTextField.getText());
                String matrixText = matrixTextArea.getText().replaceAll("\\s+","");
                StringBuilder sb = new StringBuilder();

                // Initialise the 2-D array
                int counter = 0;
                int[][] matrix = new int[column][row];
                for (int i = 0; i < column; i++) {
                    for (int j = 0; j < row; j++) {
                        // Remove all the white spaces
                        matrix[i][j] = Integer.parseInt(String.valueOf(matrixText.charAt(counter)));
                        counter++;
                    }
                }

                // Create a second array to verify visited or naot
                boolean[][] visited = new boolean[column][row];
                for (boolean[] r : visited) {
                    Arrays.fill(r, false);
                }

                // Depth First Search through the 2-D array
                int count = 0;
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if (matrix[i][j] == 1 && !visited[i][j]) {
                            count++;
                            dfs(matrix, visited, i, j);
                        }
                    }
                }

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        // Create a label to display the matrix
                        Label label = new Label(String.valueOf(matrix[i][j]));
                        // Set the label style to red if the value is 1
                        label.setStyle("-fx-text-fill: " + (matrix[i][j] == 1 ? "red;" : "black;") +
                                "-fx-font-weight: bold; " +
                                "-fx-font-size: 16px;");
                        // Add the label to the grid pane
                        myGridPane.add(label, j, i);
                        myGridPane.getColumnConstraints().add(new ColumnConstraints(10));
                        myGridPane.getRowConstraints().add(new RowConstraints(10));
                    }
                }

                sb.append("Cluster(s): ").append(count).append("\n");
                sb.append("Matrix:\n");
                clusterLabel.setText(sb.toString());
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("Enter only integer!!!");
            alert.showAndWait();
        } catch (StringIndexOutOfBoundsException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("Please enter a complete matrix based on the size you provide!");
            alert.showAndWait();
        }
    }

    boolean validate() {
        StringBuilder errors = new StringBuilder();

        // Confirm mandatory fields are filled out
        if (columnTextField.getText().trim().isEmpty()) {
            errors.append("Please enter a number for column\n");
            columnLabel.setTextFill(Paint.valueOf("red"));
        }
        if (rowTextField.getText().trim().isEmpty()) {
            errors.append("Please enter a number for row\n");
            rowLabel.setTextFill(Paint.valueOf("red"));
        }

        int column = Integer.parseInt(columnTextField.getText());
        int row = Integer.parseInt(rowTextField.getText());
        if (column != row) {
            errors.append("Please enter the same number for column and row\n");
            columnLabel.setTextFill(Paint.valueOf("red"));
            rowLabel.setTextFill(Paint.valueOf("red"));
        }

        if (matrixTextArea.getText().trim().isEmpty()) {
            errors.append("Please enter the matrix\n");
            matrixLabel.setTextFill(Paint.valueOf("red"));
        }

        if (matrixTextArea.getText().trim().isEmpty()) {
            errors.append("Please enter the matrix\n");
            matrixLabel.setTextFill(Paint.valueOf("red"));

            // If the matrixTextArea is not empty, check if the matrix is valid
            String matrix = matrixTextArea.getText().replaceAll("\\s+",""); // Remove all the white spaces
            // Counter to keep track of the matrix
            int counter = 0;
            // Create a test matrix to check if the matrix is valid
            int[][] testMatrix = new int[column][row];
            boolean hasError = false;

            try {
                for (int i = 0; i < column; i++) {
                    for (int j = 0; j < row; j++) {
                        testMatrix[i][j] = Integer.parseInt(matrix.substring(counter, counter + 1));
                        counter++;
                        if (testMatrix[i][j] != 0 && testMatrix[i][j] != 1) {
                            errors.append("Please enter a number between 0 or 1 for matrix\n");
                            matrixLabel.setTextFill(Paint.valueOf("red"));
                            hasError = true;
                            break;
                        }
                        if (hasError)
                            break;
                    }
                }
                // User entered matrix smaller than the size he provided
                if (counter < matrix.length()) {
                    errors.append("Please enter a correct matrix based on the size you provided!\n");
                    matrixLabel.setTextFill(Paint.valueOf("red"));
                }
            } catch (StringIndexOutOfBoundsException e) {
                errors.append("Please enter a complete matrix based on the size you provided!\n");
                matrixLabel.setTextFill(Paint.valueOf("red"));
            }
        }

        // If any missing information is found, show the error messages and return false
        if (errors.length() > 0) {
            printError(errors);
            return false;
        }

        // No errors
        return true;
    }

    private static void dfs(int[][] matrix, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] == 0 || visited[i][j]) {
            // If not valid then return to another dfs
            return;
        }
        visited[i][j] = true;
        // Using recursion
        dfs(matrix, visited, i - 1, j);
        dfs(matrix, visited, i + 1, j);
        dfs(matrix, visited, i, j - 1);
        dfs(matrix, visited, i, j + 1);
    }
}
