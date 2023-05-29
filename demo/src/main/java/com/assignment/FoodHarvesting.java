import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class FoodHarvesting {

    public static void main(String[] args) {

        List<Integer> nodesWithoutFood = new ArrayList<>();
        List<Integer> maxNodesWithoutFood=new ArrayList<>();
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

        System.out.println("Node(s) without food: " + nodesWithoutFood);
        List<Integer> visitedNodes = new ArrayList<>();

        int startNode1 = 1;  // Starting node is Node 1
        int endNode1 = 10;    // Ending node is also Node 1

        List<Integer> path1 = findPath1(graph, startNode1, endNode1, nodesWithoutFood, visitedNodes);
        int num,cho;

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
            System.out.println("\n-->No path found.\n");
            System.out.print("Enter number of nodes that you don't want to be in the path[("+nodesWithoutFood.size()+")]-->");
            num=sc.nextInt();
            int k=0;
            System.out.println("\nEnter num of node(s) that will not be in the path:"+num);
            while(maxNodesWithoutFood.size()<num){
                System.out.println("Choose either of those:"+nodesWithoutFood);
                System.out.print(k+1+")");
                cho=sc.nextInt();
                maxNodesWithoutFood.add(cho);
                k++;
            }
            List<Integer> path2=findPathWithFood(graph,startNode1,endNode1, visitedNodes,maxNodesWithoutFood);
            System.out.println("\nUpdated node(s) that will not be in the path :"+maxNodesWithoutFood);
            System.out.println("\nCreating new path...");
            assert path2 != null;
            for (int node : path2) {
                System.out.print(node + "-> ");
            }
            System.out.println("1");
            System.out.print("\nVisited nodes: " + visitedNodes);
        }
    }
    private static List<Integer> findPath1(int[][] graph, int currentNode, int endNode, List<Integer> nodesWithoutFood, List<Integer> visitedNodes) {
        visitedNodes.add(currentNode);

        if (currentNode == endNode || new HashSet<>(visitedNodes).containsAll(nodesWithoutFood)) {
            List<Integer> path = new ArrayList<>();
            path.add(currentNode);
            return path;
        }

        for (int nextNode = 1; nextNode <= graph[currentNode - 1].length; nextNode++) {
            if (graph[currentNode - 1][nextNode - 1] == 1 && !visitedNodes.contains(nextNode) && !isNodeWithoutFood(nodesWithoutFood, nextNode)) {
                graph[currentNode - 1][nextNode - 1] = 0;  // Mark the edge as visited
                graph[nextNode - 1][currentNode - 1] = 0;  // Mark the edge as visited

                if(!visitedNodes.contains(currentNode)) {
                    visitedNodes.add(nextNode);
                }// Add the next node to the visited nodes list

                List<Integer> path = findPath1(graph, nextNode, endNode, nodesWithoutFood, visitedNodes);
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

    private static boolean isNodeWithoutFood (List < Integer > nodesWithoutFood,int node){
        return nodesWithoutFood.contains(node);
    }

    private static List<Integer> findPathWithFood(int[][] graph, int currentNode, int endNode, List<Integer> visitedNodes, List<Integer>maxNodesWithoutFood) {
        if (!visitedNodes.contains(currentNode)){
            visitedNodes.add(currentNode);
        }

        if (currentNode == endNode) {
            List<Integer> path = new ArrayList<>();
            path.add(currentNode);
            return path;
        }

        for (int nextNode = 1; nextNode <= graph[currentNode - 1].length; nextNode++) {
            if (graph[currentNode - 1][nextNode - 1] == 1 && !visitedNodes.contains(nextNode) && !maxNodesWithoutFood.contains(currentNode)) {
                if(maxNodesWithoutFood.contains(10)) {
                    endNode=6;
                    graph[currentNode - 1][nextNode - 1] = 0;  // Mark the edge as visited
                    graph[nextNode - 1][currentNode - 1] = 0;  // Mark the edge as visited
                }

                if(!visitedNodes.contains(currentNode))
                    visitedNodes.add(nextNode);  // Add the next node to the visited nodes list

                List<Integer> path = findPathWithFood(graph, nextNode, endNode, visitedNodes, maxNodesWithoutFood);
                if (path != null) {
                    path.add(0, currentNode);  // Add the current node to the beginning of the path
                    return path;
                }

                graph[currentNode - 1][nextNode - 1] = 1;  // Reset the visited edge
                graph[nextNode - 1][currentNode - 1] = 1;  // Reset the visited edge
            }
        }

        visitedNodes.remove(Integer.valueOf(currentNode));  // Backtrack, remove the current node from the visited nodes list
        return null;
    }

}