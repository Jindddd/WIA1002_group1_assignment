package com.assignment;

class DataOfKingdom {
    public static CharacterNode<String> getSet() {
        CharacterNode<String> root = new CharacterNode<>("Sun Quan", "Emperor", "Cavalry", 98, 96, 72, 77, 95);
        {
            CharacterNode<String> node0 = root.addChild("Zhang Zhao", "Chief", "Management", "Archer", 80, 22, 89, 99, 60);
            {
                CharacterNode<String> node00 = node0.addChild("Xu Sheng","General", "Archer", 78, 90, 72, 40, 94);
                CharacterNode<String> node01 = node0.addChild("Zhu Ge Jin", "General", "Archer", 61, 63, 88, 82, 71);
                CharacterNode<String> node02 = node0.addChild("Lu Su", "General", "Infantry",87, 43, 94, 88, 53);
                CharacterNode<String> node03 = node0.addChild("Tai Shi Ci", "General", "Cavalry", 91, 96, 43, 33, 97);
            }

            CharacterNode<String> node1 = root.addChild("Zhou Yu", "Chief", "Military", "Cavalry", 86, 80, 97, 80, 90);
            {
                CharacterNode<String> node10 = node1.addChild("Xiao Qiao", "General", "Infantry",52, 42, 89, 77, 34);
                CharacterNode<String> node11 = node1.addChild("Da Qiao", "General", "Cavalry", 62, 39, 90, 62, 41);
                CharacterNode<String> node12 = node1.addChild("Zhou Tai", "General", "Infantry", 89, 92, 72, 43, 99);
                CharacterNode<String> node13 = node1.addChild("Gan Ning", "General", "Archer",92 , 98, 45, 23, 97);
                CharacterNode<String> node14 = node1.addChild("Lu Meng", "General", "Cavalry", 77, 70, 93, 83, 88);
                CharacterNode<String> node15 = node1.addChild("Huang Gai", "General", "Infantry", 98, 83, 72, 42, 89);
            }
        }
        return root;
    }
}