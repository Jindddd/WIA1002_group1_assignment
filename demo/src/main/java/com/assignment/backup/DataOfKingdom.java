package com.assignment.backup;

import com.assignment.CharacterNode;

import java.util.ArrayList;

public class DataOfKingdom {
    ArrayList<CharacterNode<String>> generalList = new ArrayList<>();
    public DataOfKingdom() {
        CharacterNode<String> root = new CharacterNode<>("Sun Quan", "Emperor", "Cavalry", 98, 96, 72, 77, 95);
        CharacterNode<String> node0 =new CharacterNode<>("Zhang Zhao", "Chief", "Management", "Archer", 80, 22, 89, 99, 60);
        CharacterNode<String> node1 =new CharacterNode<>("Zhou Yu", "Chief", "Military", "Cavalry", 86, 80, 97, 80, 90);

    }
    public CharacterNode<String> getSet() {
        CharacterNode<String> root = new CharacterNode<>("Sun Quan", "Emperor", "Cavalry", 98, 96, 72, 77, 95);
        CharacterNode<String> node0 = root.addChild("Zhang Zhao", "Chief", "Management", "Archer", 80, 22, 89, 99, 60);
        CharacterNode<String> node1 = root.addChild("Zhou Yu", "Chief", "Military", "Cavalry", 86, 80, 97, 80, 90);

        for (CharacterNode<String> node : generalList) {
            if (node.getIntelligence() > node.getStrength()) {
                // node0 is Chief of Management
                node0.addChild(node);
            } else {
                // node1 is Chief of Military
                node1.addChild(node);
            }
        }
        return root;
    }

    public ArrayList<CharacterNode<String>> getGeneralList() {
        return generalList;
    }
}