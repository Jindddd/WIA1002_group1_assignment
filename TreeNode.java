import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    private final General general;
    private final List<TreeNode> children;

    public TreeNode(General general) {
        this.general = general;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public General getGeneral() {
        return general;
    }
}
