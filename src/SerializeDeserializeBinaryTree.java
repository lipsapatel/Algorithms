import Node.BinaryTreeNode;

/**
 * Serialize and Deserialize Binary Tree
 Hard

 Serialization is the process of converting a data structure or object into a sequence of bits so
 that it can be stored in a file or memory buffer, or transmitted across a network connection link to be
 reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary tree.
 There is no restriction on how your serialization/deserialization algorithm should work.
 You just need to ensure that a binary tree can be serialized to a string and
 this string can be deserialized to the original tree structure.

 Example:

 You may serialize the following tree:

 1
 / \
 2   3
 / \
 4   5

 as "[1,2,3,null,null,4,5]"

 Clarification: The above format is the same as how LeetCode serializes a binary tree.
 You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

 Note: Do not use class member/global/static variables to store states.
 Your serialize and deserialize algorithms should be stateless.

 Approach:
 1) Create comma separated string with markers for null.
 2) Use global variable to store index and deserialize

 Time Complexity: O(n) to serialize
 Space Compexity: O(n) for array

 Approach

 Append * for internal nodes
 Markers only for internal nodes

 */
public class SerializeDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(BinaryTreeNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    public String serialize1(BinaryTreeNode root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        serializeHelper1(sb, root);
        return sb.toString();
    }

    public void serializeHelper1(StringBuilder sb, BinaryTreeNode root) {
        if (root == null) {
            sb.append("n,");
            return;
        }

        sb.append(root.data);

        //Leaf
        if (root.left == null && root.right == null) {
            sb.append(",");
        } else {
            //Internal
            sb.append("*,");

            serializeHelper1(sb, root.left);
            serializeHelper1(sb, root.right);
        }
    }

    public void serializeHelper(BinaryTreeNode root, StringBuilder sb) {

        if (root == null) {

            //Append marker -1
            sb.append("-1,");
            return;
        }

        sb.append(root.data + ",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserialize(String data) {

        if (data.equals("")) {
            return null;
        }

        String[] values = data.split(",");

        BinaryTreeNode root = deserializeHelper(values);
        return root;
    }

    int index = 0;
    public BinaryTreeNode deserializeHelper(String[] values) {

        if (index >= values.length || values[index].equals("-1")) {
            index++;
            return null;
        }

        int val = Integer.parseInt(values[index]);
        index++;

        BinaryTreeNode node = new BinaryTreeNode(val);
        node.left = deserializeHelper(values);
        node.right = deserializeHelper(values);
        return node;
    }
    public BinaryTreeNode deserialize1(String data) {
        if (data.equals("")) {
            return null;
        }

        String[] values = data.split(",");
        return deserializeHelper1(values);
    }

    //int index = 0;
    public BinaryTreeNode deserializeHelper1(String[] values) {

        if (index >= values.length || values[index].equals("n")) {
            index++;
            return null;
        }

        String val = values[index];
        index++;



        if (val.charAt(val.length() - 1) == '*') {

            //has child
            val = val.substring(0, val.length() - 1);
            int data = Integer.parseInt(val);
            BinaryTreeNode node = new BinaryTreeNode(data);

            node.left = deserializeHelper1(values);
            node.right = deserializeHelper1(values);
            return node;
        } else {
            //leaf
            BinaryTreeNode node = new BinaryTreeNode(Integer.parseInt(val));
            return node;
        }
    }
}
