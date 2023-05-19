public class Main {
    public static void main(String[]args) {
        KingdomHierarchy hierarchy = new KingdomHierarchy(new General("Emperor Sun Quan", 72, 96));
        TreeNode militaryDepartment = new TreeNode(new General("Zhou Yu", 89, 22));
        TreeNode managementDepartment = new TreeNode(new General("Zhang Zhao", 97, 80));

        hierarchy.getRoot().addChild(militaryDepartment);
        hierarchy.getRoot().addChild(managementDepartment);

        hierarchy.addGeneral(new General("Xu Sheng", 72, 90));
        hierarchy.addGeneral(new General("Zhu Ge Jin", 88, 63));
        hierarchy.addGeneral(new General("Lu Su", 84, 43));
        hierarchy.addGeneral(new General("Tai Shi Ci", 43, 96));
        hierarchy.addGeneral(new General("Xiao Qiao", 89, 42));
        hierarchy.addGeneral(new General("Da Qiao", 80, 39));
        hierarchy.addGeneral(new General("Zhou Tai", 72, 92));
        hierarchy.addGeneral(new General("Gan Ning", 45, 98));
        hierarchy.addGeneral(new General("Lu Meng", 93, 70));
        hierarchy.addGeneral(new General("Huang Gai", 72, 83));

        hierarchy.printHierarchy(hierarchy.getRoot(), "");
    }
}
