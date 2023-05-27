package com.assignment;

import java.util.*;

public class RedCliffOnFire {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter column: ");
        int column = sc.nextInt();
        System.out.print("Enter row: ");
        int row = sc.nextInt();
        System.out.println("Enter matrix: ");
        int[][] matrix = new int[column][row];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        // Create a second array to verify visited or naot
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
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

        System.out.println("Matrix:");
        for (int[] r : matrix) {
            System.out.println(Arrays.toString(r).replace("[", "").replace("]","").replace(",",""));
        }
        System.out.println(count + " cluster(s)");
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