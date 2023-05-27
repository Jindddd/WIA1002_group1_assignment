package com.assignment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.List;

public class BorrowingArrowController extends ApplicationController {
    @FXML
    Label borrowingArrowLabel;
    @FXML
    Label frontLabel;
    @FXML
    Label leftLabel;
    @FXML
    Label rightLabel;
    @FXML
    Label backLabel;
    @FXML
    Label arrowLabel;
    @FXML
    Button submitButton;
    @FXML
    TextField frontTextField;
    @FXML
    TextField leftTextField;
    @FXML
    TextField rightTextField;
    @FXML
    TextField backTextField;
    @FXML
    TextField arrowTextField;

    public void submit(ActionEvent event) {
        List<Integer> arrowList = new ArrayList<>();
        boolean isInputValid = true;
        // Reset the label text to black
        frontLabel.setTextFill(Paint.valueOf("black"));
        leftLabel.setTextFill(Paint.valueOf("black"));
        rightLabel.setTextFill(Paint.valueOf("black"));
        backLabel.setTextFill(Paint.valueOf("black"));
        arrowLabel.setTextFill(Paint.valueOf("black"));
        try {
            String front = frontTextField.getText();
            String left = leftTextField.getText();
            String right = rightTextField.getText();
            String back = backTextField.getText();
            String arrows = arrowTextField.getText();
            // Check if the input format is correct
            if (validate()) {
                String[] arrowArr = arrows.split(",");
                int previousArrow = Integer.MAX_VALUE;
                for (String a : arrowArr) {
                    int currentArrow = Integer.parseInt(a);
                    // Check again if the arrows are in descending order
                    if (currentArrow > previousArrow) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Wrong Input");
                        alert.setContentText("Invalid input for arrows. Arrows must be in descending order.");
                        alert.showAndWait();
                        arrowLabel.setTextFill(Paint.valueOf("red"));
                        isInputValid = false;
                    }
                    previousArrow = currentArrow;
                    arrowList.add(currentArrow);
                }
                if (isInputValid) {
                    calculateBoatDirections(Integer.parseInt(front), Integer.parseInt(left), Integer.parseInt(right),
                            Integer.parseInt(back), arrowList);
                }
            }


        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Wrong Input");
            alert.setContentText("Enter only integer!!!");
            alert.showAndWait();
        }
    }

    public void calculateBoatDirections(int front, int left, int right, int back, List<Integer> arrowList) {
        List<String> boatDirections = new ArrayList<>();
        List<Integer> captured = new ArrayList<>();

        int newFront = front;
        int newLeft = left;
        int newRight = right;
        int newBack = back;

        for (int arrowCount : arrowList) {
            // Find direction that have maximum arrows
            String direction = findMaximum(newFront, newLeft, newRight, newBack);
            int capture = switch (direction) {
                case "front" -> (int) (arrowCount * (newFront / 100.0));
                case "left" -> (int) (arrowCount * (newLeft / 100.0));
                case "right" -> (int) (arrowCount * (newRight / 100.0));
                default ->  /* Back */ (int) (arrowCount * (newBack / 100.0));
            };

            // Add number of captured arrows
            captured.add(capture);
            // Add boat direction
            boatDirections.add(direction);

            // Update efficiency of the straw men left
            switch (direction) {
                case "front" ->
                        newFront = newFront > (front * 80 / 100) ? (front * 80 / 100) :
                                newFront > (front * 40 / 100) ? (front * 40 / 100) : 0;
                case "left" ->
                        newLeft = newLeft > (left * 80 / 100) ? (left * 80 / 100) :
                                newLeft > (left * 40 / 100) ? (left * 40 / 100) : 0;
                case "right" ->
                        newRight = newRight > (right * 80 / 100) ? (right * 80 / 100) :
                                newRight > (right * 40 / 100) ? (right * 40 / 100) : 0;
                case "back" ->
                        newBack = newBack > (back * 80 / 100) ? (back * 80 / 100) :
                                newBack > (back * 40 / 100) ? (back * 40 / 100) : 0;
            }
        }
        String text = "Boat direction: " + boatDirections + "\n" +
                "Arrow received: " + captured + "\n" +
                "Total: " + calculateTotalArrowsCaptured(captured);
        borrowingArrowLabel.setText(text);
    }

    public static String findMaximum(int front, int left, int right, int back) {
        int max = front;
        String bestDirection = "front";

        if (left > max) {
            max = left;
            bestDirection = "left";
        }
        if (right > max) {
            max = right;
            bestDirection = "right";
        }
        if (back > max) {
            max = back;
            bestDirection = "back";
        }

        return bestDirection;
    }


    public static int calculateTotalArrowsCaptured(List<Integer> captured) {
        int total = 0;
        for (int arrows : captured) {
            total += arrows;
        }
        return total;
    }

    public boolean validate() {
        StringBuilder errors = new StringBuilder();

        // Confirm mandatory fields are filled out
        if (frontTextField.getText().trim().isEmpty() || Integer.parseInt(frontTextField.getText()) < 0 || Integer.parseInt(frontTextField.getText()) > 100) {
            errors.append("Please enter a number between 0 - 100 for front straw man\n");
            frontLabel.setTextFill(Paint.valueOf("red"));
        }
        if (leftTextField.getText().trim().isEmpty() || Integer.parseInt(leftTextField.getText()) < 0 || Integer.parseInt(leftTextField.getText()) > 100) {
            errors.append("Please enter a number between 0 - 100 for left straw man\n");
            leftLabel.setTextFill(Paint.valueOf("red"));
        }
        if (rightTextField.getText().trim().isEmpty() || Integer.parseInt(rightTextField.getText()) < 0 || Integer.parseInt(rightTextField.getText()) > 100) {
            errors.append("Please enter a number between 0 - 100 for right straw man\n");
            rightLabel.setTextFill(Paint.valueOf("red"));
        }
        if (backTextField.getText().trim().isEmpty() || Integer.parseInt(backTextField.getText()) < 0 || Integer.parseInt(backTextField.getText()) > 100) {
            errors.append("Please enter a number between 0 - 100 for back straw man\n");
            backLabel.setTextFill(Paint.valueOf("red"));
        }
        if (arrowTextField.getText().trim().isEmpty()) {
            errors.append("Please enter the number of arrows\n");
            arrowLabel.setTextFill(Paint.valueOf("red"));
        }
        if (!arrowTextField.getText().matches("\\d+(,\\d+)*")) {
            errors.append("Invalid input. Arrows must be in the format: [2000,1500,1000,...]");
            arrowLabel.setTextFill(Paint.valueOf("red"));
        }

        // If any missing information is found, show the error messages and return false
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Warning");
            alert.setContentText(errors.toString());

            alert.showAndWait();
            return false;
        }

        // No errors
        return true;
    }

}