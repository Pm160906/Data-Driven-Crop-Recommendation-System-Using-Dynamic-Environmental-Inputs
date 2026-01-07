public class BST {
    Node root;

    void insert(Crop crop) {
        this.root = this.insertRec(this.root, crop);
    }

    Node insertRec(Node root, Crop crop) {
        if (root == null) {
            return new Node(crop);
        } else {
            if (crop.expectedProfit < root.crop.expectedProfit) {
                root.left = this.insertRec(root.left, crop);
            } else {
                root.right = this.insertRec(root.right, crop);
            }

            return root;
        }
    }

    int height(Node node) {
        if (node == null) {
            return 0;
        } else {
            int lHeight = this.height(node.left);
            int rHeight = this.height(node.right);
            return Math.max(lHeight, rHeight) + 1;
        }
    }

    void inorder(Node root) {
        if (root != null) {
            this.inorder(root.left);
            System.out.println(root.crop.name + " Profit: " + root.crop.expectedProfit);
            this.inorder(root.right);
        }

    }

    class Node {
        Crop crop;
        Node left;
        Node right;

        Node(Crop crop) {
            this.crop = crop;
        }
    }
}
