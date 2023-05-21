package com.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BorrowingArrows {

    private static final int MIN_STRAWMEN_COUNT = 0;
    private static final int MAX_STRAWMEN_COUNT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of straw men for the directions.");
        System.out.println("Number of straw men in one direction must be 0-100.\n");
        int frontStrawMen = getValidInput(scanner, "Front : ");
        int leftStrawMen = getValidInput(scanner, "Left  : ");
        int rightStrawMen = getValidInput(scanner, "Right : ");
        int backStrawMen = getValidInput(scanner, "Back  : ");
        System.out.println("-------------------------------------------------------------------");

        List<Integer> arrowList = getArrowList(scanner);
        System.out.println("-------------------------------------------------------------------");

        calculateBoatDirections(frontStrawMen, leftStrawMen, rightStrawMen, backStrawMen, arrowList);
    }

    private static int getValidInput(Scanner scanner, String message) {
        int input;

        while (true) {
            System.out.print(message);
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= MIN_STRAWMEN_COUNT && input <= MAX_STRAWMEN_COUNT) {
                    break;
                } else {
                    System.out.println("Invalid input. The number must be 0-100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer value.");
            }
        }
        return input;
    }

    private static List<Integer> getArrowList(Scanner scanner) {
        List<Integer> arrowList = new ArrayList<>();
        String arrowShot;

        System.out.println("Enter the number of arrows shot in each wave.");
        System.out.println("The number of arrows shot should be in decreasing order.");
        System.out.println("Please enter the arrows in the format: [2000,1500,1000,...]");

        while (true) {
            System.out.print("\nArrows shot: ");
            arrowShot = scanner.nextLine();

            // Regex of the format [2000,1500, ...]
            if (arrowShot.matches("\\[\\d+(,\\d+)*\\]")) {
                String[] arrowArr = arrowShot.substring(1, arrowShot.length() - 1).split(",");
                try {
                    int previousArrow = Integer.MAX_VALUE;
                    for (String arrow : arrowArr) {
                        int currentArrow = Integer.parseInt(arrow);
                        if (currentArrow > previousArrow) {
                            throw new IllegalArgumentException("Invalid input. Arrows must be in descending order.");
                        }
                        previousArrow = currentArrow;
                        arrowList.add(currentArrow);
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer value.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter the arrows in decreasing order.");
                    arrowList.clear();
                }
            } else {
                System.out.println("Invalid input. Arrows must be in the format: [2000,1500,1000,...]");
            }
        }

        return arrowList;
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

    public static void calculateBoatDirections(int front, int left, int right, int back, List<Integer> arrowList) {
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

        System.out.println("Boat direction: " + boatDirections);
        System.out.println("Arrow received: " + captured);
        System.out.println("Total: " + calculateTotalArrowsCaptured(captured));
    }

    public static int calculateTotalArrowsCaptured(List<Integer> captured) {
        int total = 0;
        for (int arrows : captured) {
            total += arrows;
        }
        return total;
    }
}
