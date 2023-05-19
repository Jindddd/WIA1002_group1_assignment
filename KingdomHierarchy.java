public class KingdomHierarchy {
    private final TreeNode root; //create root node

    public KingdomHierarchy(General emperor) {
        this.root = new TreeNode(emperor);
    } //the emperor is the root of the tree

    //add Generals
    public void addGeneral(General general) {
        TreeNode departmentNode;
        if (general.getIntelligence() > general.getStrength()) {
            departmentNode = getManagementDepartmentNode();
        } else {
            departmentNode = getMilitaryDepartmentNode();
        }
        TreeNode generalNode = new TreeNode(general);
        departmentNode.addChild(generalNode);
    }

    public TreeNode getManagementDepartmentNode() {
        return root.getChildren().get(1);
    }

    public TreeNode getMilitaryDepartmentNode() {
        return root.getChildren().get(0);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void printHierarchy(TreeNode node, String indent) {
        General general = node.getGeneral();
        System.out.println(indent + "- " + general.getName() + " (Intelligence: " + general.getIntelligence() + ", Strength: " + general.getStrength() + ")");
        for (TreeNode child : node.getChildren()) {
            printHierarchy(child, indent + "  ");
        }
    }

}
