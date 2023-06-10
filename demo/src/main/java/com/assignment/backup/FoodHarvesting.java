package com.assignment.backup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodHarvesting {

    public static void main(String[] args) {

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

        System.out.print("Insert node without food [2-10]: ");
        Scanner sc = new Scanner(System.in);
        int nodeWithoutFood = sc.nextInt();

        List<Integer> visitedNodes = new ArrayList<>();

        int startNode;
        int endNode;

        startNode = 1;  // Start with Node 1

        if (nodeWithoutFood != 10) {
            endNode = 10;    // End with Node 10

            List<Integer> path1 = findPath1(graph, startNode, endNode, nodeWithoutFood, visitedNodes);

            if (path1 != null) {
                System.out.print("Path: ");
                for (int node : path1) {
                    System.out.print(node + " -> ");
                }
                System.out.println("1");
                System.out.println("Visited nodes: " + visitedNodes);
            }
        } else {
            endNode = 2;

            List<Integer> path2 = findPath2(graph, startNode, endNode, nodeWithoutFood, visitedNodes);

            if (path2 != null) {
                System.out.print("Path: ");
                for (int node : path2) {
                    System.out.print(node + " -> ");
                }
                System.out.println("1");
                System.out.println("Visited nodes: " + visitedNodes);
            }
        }
    }

    private static List<Integer> findPath1(int[][] graph, int currentNode, int endNode, int nodeWithoutFood, List<Integer> visitedNodes) {

        visitedNodes.add(currentNode);

        if (nodeWithoutFood == 4) {
            System.out.print("Node " + nodeWithoutFood + " must be included in the path to ensure the connectivity of all food nodes.\n");
            nodeWithoutFood = 0;
        }

        if (nodeWithoutFood == 6) {
            System.out.print("Node " + nodeWithoutFood + " must be included in the path to ensure the connectivity of all food nodes.\n");
            nodeWithoutFood = 0;
        }

        if (nodeWithoutFood == 8) {
            System.out.print("Node " + nodeWithoutFood + " must be included in the path to ensure the connectivity of all food nodes.\n");
            nodeWithoutFood = 0;
        }

        if (nodeWithoutFood == 7) {
            System.out.print("Node " + nodeWithoutFood + " must be included in the path to ensure the connectivity of all food nodes.\n");
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
