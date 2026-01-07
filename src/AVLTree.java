// AVL (Adelson-Velsky and Landis) tree - self balancing bst
public class AVLTree {
    static class Node {
        Crop crop;             // stores crop
        Node left, right;      // child nodes
        int height;            // height of node (-1, 0, 1)≤

        Node(Crop crop) {
            this.crop = crop;  // store crop in node
            height = 1;        // new node = leaf node, height of leaf node = 1
        }
    }

    Node root;
    // return height
    int height(Node n) {
        // node = null: height = 0
        return n == null ? 0 : n.height;
    }

    int balance(Node n) {
        // balance factor = height(left subtree) - height(right subtree)
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    // performs right rotation [used in LL & LR cases]
    Node rightRotate(Node y) {
        Node x = y.left;     // x = new root
        Node t = x.right;    // temporary subtree

        // perform rotation
        x.right = y; y.left = t;

        // update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;   // return new root after rotation
    }

    // performs right rotation [used in LL & RL cases]
    Node leftRotate(Node x) {
        Node y = x.right;     // y becomes the new root
        Node t = y.left;      // temporary subtree

        // perform rotation
        y.left = x; x.right = t;

        // update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;   // return new node after rotation
    }

    // recursive insert, similar to bst
    Node insert(Node node, Crop crop) {
        // tree/ subtree = empty: create new node
        if (node == null)
            return new Node(crop);

        // bst insertion logic based on expected profit
        int profitNew = crop.expectedProfit; int profitOld = node.crop.expectedProfit;

        if (profitNew < profitOld)
            node.left = insert(node.left, crop);
        else
            node.right = insert(node.right, crop);

        // update height of current node
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = balance(node);


        // rotation cases
        // 1. LL [Left-Left] (left subtree of left child)
        if (balance > 1 && profitNew < node.left.crop.expectedProfit)
            return rightRotate(node);

        // 2. RR [Right-Right] (right subtree of right child)
        if (balance < -1 && profitNew > node.right.crop.expectedProfit)
            return leftRotate(node);

        // 3. LR [Left-Right] (right subtree of left child)
        if (balance > 1 && profitNew > node.left.crop.expectedProfit) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 4. RL [Right-Left] (left subtree of right child)
        if (balance < -1 && profitNew < node.right.crop.expectedProfit) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // if no rotation needed: return unchanged node
        return node;
    }

    // inorder traversal: sorts by profit (left - root - right)
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.crop.name + " Profit: " + node.crop.expectedProfit);
            inorder(node.right);
        }
    }
}
