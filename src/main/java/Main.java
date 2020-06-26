

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        Main tr = new Main();
        int i = 0;
        while(i < 10) {
            tr.insert(i);
        }
    }

    Node mRoot;

    public Main() {

    }

    public Main(int root) {
        mRoot = new Node(root);

    }

    private Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.mValue) {
            root.mLeft = insert(root.mLeft, key);
        } else {
            root.mRight = insert(root.mRight, key);
        }
        return balance(root);
    }

    public Node balance(Node p) {
        if(p == null)
            return null;
        fixHeight(p);
        if (bfactor(p) == 2) {
            if (bfactor(p.mRight) < 0) {
                p.mRight = rotateRight(p.mRight);
            }
            return rotateLeft(p);
        }
        if (bfactor(p) == -2) {
            if (bfactor(p.mLeft) > 0) {
                p.mLeft = rotateLeft(p.mLeft);
            }
            return rotateRight(p);
        }
        return p;
    }

    private Node rotateRight(Node p) {
        Node q = p.mLeft;
        p.mLeft = q.mRight;
        q.mRight = p;
        fixHeight(p);
        fixHeight(q);
        return q;
    }

    private Node rotateLeft(Node q) {
        Node p = q.mRight;
        q.mRight = p.mLeft;
        p.mLeft = q;
        fixHeight(q);
        fixHeight(p);
        return p;
    }

    private int height(Node p) {
        return p == null ? 0 : p.mHeight;
    }

    private int bfactor(Node p) {
        return height(p.mRight) - height(p.mLeft);
    }

    private void fixHeight(Node p) {
        int hl = height(p.mLeft);
        int hr = height(p.mRight);
        p.mHeight = (hl > hr ? hl : hr) + 1;
    }

    public void insert(@NotNull int... keys) {
        for (int value : keys) {
            mRoot = insert(mRoot, value);
        }

    }

    @Override
    public boolean equals(Object arg0) {
        if (this == arg0) {
            return true;
        }
        if (!(arg0 instanceof Main)) {
            return false;
        }
        Main another = (Main) arg0;
        return areTreesEqual(this.mRoot, another.mRoot);
    }

    private boolean areTreesEqual(Node root1, Node root2) {
        if (root1 == root2) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        return root1.mValue == root2.mValue && areTreesEqual(root1.mLeft, root2.mLeft) && areTreesEqual(root1.mRight, root2.mRight);
    }

    @Override
    public int hashCode() {
        if (mRoot == null) {
            return 0;
        }
        LinkedList<Node> nodes = new LinkedList<Main.Node>();
        nodes.add(mRoot);
        int res = 17;
        while (!nodes.isEmpty()) {
            Node head = nodes.remove();
            res = 31 * res + head.hashCode();
            if (head.mLeft != null) {
                nodes.addLast(head.mLeft);
            }
            if (head.mRight != null) {
                nodes.addLast(head.mRight);
            }
        }
        return res;
    }

    @Override
    public String toString() {
        if (mRoot == null) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        inOrderPrint(mRoot, builder);
        builder.setLength(builder.length() - 2);
        builder.append("]");
        return builder.toString();
    }

    private void inOrderPrint(Node root, StringBuilder builder) {
        if (root != null) {
            inOrderPrint(root.mLeft, builder);
            builder.append(root + ", ");
            inOrderPrint(root.mRight, builder);
        }
    }

    static class Node {

        Node mLeft;
        Node mRight;
        final int mValue;
        private int mHeight;

        private Node(int value) {
            mValue = value;
            mHeight = 1;
        }

        @Override
        public int hashCode() {
            int res = 17;
            res = 17 * res + mValue;
            return res;
        }

        @Override
        public String toString() {
            return Integer.toString(mValue);
        }
    }

}
