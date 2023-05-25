package com.assignment;

public class HuaRongRoad {
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

    public static void main(String[] args) {
        pathMaze = new char[maze.length][maze[0].length];

        // Initialize path of maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                pathMaze[i][j] = Integer.toString(maze[i][j]).charAt(0);
            }
        }

        System.out.println("2D maze of Hua Rong Road");
        printPath();
        System.out.println();

        // insert the starting and ending points
        int[] start = {1, 0};
        int[] end = {9, 12};

        dfs(start, end);
        if (maze[end[0]][end[1]] == 3) {
            pathMaze[end[0]][end[1]] = '.';
        }
        System.out.println("Path of how Cao Cao might have escaped");
        printPath();
    }

    public static boolean dfs(int[] start, int[] end) {
        if (!isValid(start[0], start[1]))
            return false;
        if (maze[start[0]][start[1]] == 3)
            return true;

        // mark the cell as visited temporarily
        char temp = pathMaze[start[0]][start[1]];
        pathMaze[start[0]][start[1]] = '.';

        for (int[] dir : directions) {
            int newX = start[0] + dir[0];
            int newY = start[1] + dir[1];
            if (dfs(new int[]{newX, newY}, end)) {
                return true;
            }
        }
        // if the cell does not lead to the target, unmark it and backtrack
        pathMaze[start[0]][start[1]] = temp;
        return false;
    }

    public static boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x >= maze.length || y >= maze[0].length || maze[x][y] == 1 || pathMaze[x][y] == '.') {
            return false;
        }
        return true;
    }

    public static void printPath() {
        System.out.println("-----------------------------");
        for (int i = 0; i < pathMaze.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < pathMaze[i].length; j++) {
                System.out.print(pathMaze[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-----------------------------");
        System.out.println();
    }
}



