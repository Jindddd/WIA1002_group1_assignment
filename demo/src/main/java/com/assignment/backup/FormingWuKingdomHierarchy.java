package com.assignment.backup;

import com.assignment.CharacterNode;

public class FormingWuKingdomHierarchy {
    public static void main(String[] args) {
        DataOfKingdom dataOfKingdom = new DataOfKingdom();
        CharacterNode<String> treeRoot = dataOfKingdom.getSet();
        // Implicitly utilizes the iterator provided by the CharacterNode<T> class
        for (CharacterNode<String> node : treeRoot) {
            String indent = createIndent(node.getLevel());
            System.out.println(indent + node);
        }

        /*// Define a comparator
        Comparable<String> searchCriteria = treeData -> {
            // Set the target name
            boolean nodeOk = treeData.equals("Xiao Qiao");
            // Return 0 if equals, else 1
            return nodeOk ? 0 : 1;
        };

        // Find the target node with the comparator
        CharacterNode<String> found = treeRoot.findTreeNode(searchCriteria);
        System.out.println("Found: " + found);*/
    }

    private static String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("   ");
        }
        return sb.toString();
    }
}