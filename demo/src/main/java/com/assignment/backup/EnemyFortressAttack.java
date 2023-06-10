package com.assignment.backup;

import java.util.*;

public class EnemyFortressAttack {
    static class Node {
        int data;

        Node(int data) {
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
            System.out.println("Number of routes: " + allPaths.size());
            System.out.println("Shortest path length: " + shortestPathLength);
            return allPaths;
        }

        public void print() {
            System.out.print("  ");
            for (Node node : nodes) {
                System.out.print(node.getData() + " ");
            }
            System.out.println();

            for (int i = 0; i < matrix.length; i++) {
                System.out.print(nodes.get(i).getData() + " ");
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

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

        System.out.println("Map of battlefield : ");
        System.out.println();
        graph.print();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the base camp for the enemy base camp: ");
        int input = sc.nextInt();

        List<List<Integer>> shortestPaths = graph.bfs(1, input);

        System.out.println("Best path:");

        for (List<Integer> path : shortestPaths) {
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i != path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}