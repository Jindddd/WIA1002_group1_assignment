package com.assignment;

import java.util.ArrayList;

public class DataOfKingdom {
    ArrayList<CharacterNode<String>> generalList = new ArrayList<>();
    public DataOfKingdom() {
        CharacterNode<String> root = new CharacterNode<>("Sun Quan", "Emperor", "Cavalry", 98, 96, 72, 77, 95);
        CharacterNode<String> node0 =new CharacterNode<>("Zhang Zhao", "Chief", "Management", "Archer", 80, 22, 89, 99, 60);
        CharacterNode<String> node1 =new CharacterNode<>("Zhou Yu", "Chief", "Military", "Cavalry", 86, 80, 97, 80, 90);
        generalList.add(new CharacterNode<String>("Xu Sheng", "General","Archer", 78, 90, 72, 40, 94));
        generalList.add(new CharacterNode<>("Zhu Ge Jin", "General", "Archer", 61, 63, 88, 82, 71));
        generalList.add(new CharacterNode<>("Lu Su", "General", "Infantry",87, 43, 94, 88, 53));
        generalList.add(new CharacterNode<>("Tai Shi Ci", "General", "Cavalry", 91, 96, 43, 33, 97));
        generalList.add(new CharacterNode<>("Xiao Qiao", "General", "Infantry",52, 42, 89, 77, 34));
        generalList.add(new CharacterNode<>("Da Qiao", "General", "Cavalry", 62, 39, 90, 62, 41));
        generalList.add(new CharacterNode<>("Zhou Tai", "General", "Infantry", 89, 92, 72, 43, 99));
        generalList.add(new CharacterNode<>("Gan Ning", "General", "Archer",92 , 98, 45, 23, 97));
        generalList.add(new CharacterNode<>("Lu Meng", "General", "Cavalry", 77, 70, 93, 83, 88));
        generalList.add(new CharacterNode<>("Huang Gai", "General", "Infantry", 98, 83, 72, 42, 89));
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