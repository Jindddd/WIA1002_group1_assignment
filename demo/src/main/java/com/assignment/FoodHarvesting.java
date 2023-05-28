import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodHarvesting {

    public static void main(String[] args) {

        List<Integer> nodesWithoutFood = new ArrayList<>();

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

        System.out.print("Insert node without food (Enter -1 to stop): ");
        Scanner sc = new Scanner(System.in);
        int nodeWithoutFood = sc.nextInt();

        while (nodeWithoutFood >= 1 && nodeWithoutFood <= 10) {
            nodesWithoutFood.add(nodeWithoutFood);  // Nodes without food
            System.out.print("Insert node without food (Enter -1 to stop): ");
            nodeWithoutFood = sc.nextInt();
        }

        int startNode = 1;  // Starting node is Node 1
        // Ending node is also Node 1

        System.out.println("Node(s) without food: " + nodesWithoutFood);
        List<Integer> visitedNodes = new ArrayList<>();
        List<List<Integer>> pathUsed = new ArrayList<>();
        findPath(graph, startNode, nodesWithoutFood, visitedNodes, pathUsed);

        System.out.println();
        System.out.println("List of all path possible:");
        System.out.println("#Ignoring redundancy and order of nodes\n#Not cover all nodes\n");
        int i;
        System.out.println("Num           Possible ways");
        for (i = 0; i < pathUsed.size(); i++) {

            System.out.printf("%d%-9s", i + 1, ".");
            for (int j = 0; j < pathUsed.get(i).size(); j++) {
                System.out.print(pathUsed.get(i).get(j) + "->");

            }
            System.out.print("\b\b");
            System.out.println();
        }
        System.out.println("\nTotal possibilities:"+i);

        int startNode1 = 1;  // Starting node is Node 1
        int endNode1 = 10;    // Ending node is also Node 1

        visitedNodes = new ArrayList<>();
        List<Integer> path1 = findPath1(graph, startNode1, endNode1, nodesWithoutFood, visitedNodes);
        if (path1 != null) {
            System.out.println("\nThe Most Efficient Path:");
            System.out.println("1)Covering maximum possible nodes");
            System.out.println("2)No nodes visited twice");
            System.out.println("3)from small to big node");
            System.out.println();
            for (int node : path1) {
                System.out.print(node + "-> ");
            }
            System.out.print("1\n");
            System.out.println("\nVisited nodes: " + visitedNodes);
        } else {
            System.out.println("No path found.");
        }
    }

    //most efficient path
    private static List<Integer> findPath1(int[][] graph, int currentNode, int endNode, List<Integer> nodesWithoutFood, List<Integer> visitedNodes) {
        visitedNodes.add(currentNode);

        if (currentNode == endNode) {
            List<Integer> path = new ArrayList<>();
            path.add(currentNode);
            return path;
        }

        for (int nextNode = 1; nextNode <= graph[currentNode - 1].length; nextNode++) {
            if (graph[currentNode - 1][nextNode - 1] == 1 && !isNodeWithoutFood(nodesWithoutFood, nextNode) && !visitedNodes.contains(nextNode)) {
                graph[currentNode - 1][nextNode - 1] = 0;  // Mark the edge as visited
                graph[nextNode - 1][currentNode - 1] = 0;  // Mark the edge as visited

                List<Integer> path = findPath1(graph, nextNode, endNode, nodesWithoutFood, visitedNodes);
                if (path != null) {
                    path.add(0, currentNode);  // Add the current node to the beginning of the path
                    return path;
                }

                graph[currentNode - 1][nextNode - 1] = 1;  // Reset the visited edge
                graph[nextNode - 1][currentNode - 1] = 1;  // Reset the visited edge
            }
        }

        visitedNodes.remove(visitedNodes.size() - 1);  // Backtrack, remove the current node from the visited nodes list
        return null;
    }

    private static boolean isNodeWithoutFood(List<Integer> nodesWithoutFood, int node) {
        return nodesWithoutFood.contains(node);
    }

    //all possibilities
    private static void findPath(int[][] graph, int currentNode, List<Integer> nodesWithoutFood, List<Integer> visitedNodes, List<List<Integer>> pathUsed) {
        if (visitedNodes.contains(1) && currentNode == 1 ){
            visitedNodes.add(currentNode);
            List<Integer> temp = new ArrayList<>(visitedNodes);
            pathUsed.add(temp);
            visitedNodes.remove(visitedNodes.size() - 1);
            return;
        } else {
            visitedNodes.add(currentNode);
        }

        for (int nextNode = 1; nextNode <= graph[currentNode - 1].length; nextNode++) {
            if (graph[currentNode - 1][nextNode - 1] == 1 && !nodesWithoutFood.contains(nextNode)) {// && !visitedNodes.contains(nextNode) ) {
                graph[currentNode - 1][nextNode - 1] = 0;  // Mark the edge as visited
                graph[nextNode - 1][currentNode - 1] = 0;  // Mark the edge as visited

                findPath(graph, nextNode, nodesWithoutFood, visitedNodes, pathUsed);

                graph[currentNode - 1][nextNode - 1] = 1;  // Reset the visited edge
                graph[nextNode - 1][currentNode - 1] = 1;  // Reset the visited edge

            }
        }
        visitedNodes.remove(visitedNodes.size() - 1);
    }


}