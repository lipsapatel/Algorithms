package Node;

import java.util.List;

public class NaryNode {

    public int data;
    public List<NaryNode> children;

    public NaryNode(int id) {
        this.data = id;
    }

    public NaryNode(int id, List<NaryNode> c) {
        this.data = id;
        this.children = c;
    }
}
