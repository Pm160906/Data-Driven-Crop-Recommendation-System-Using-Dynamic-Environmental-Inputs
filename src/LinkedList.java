public class LinkedList {
    Node head;

    void insert(Crop crop) {
        Node newNode = new Node(crop);
        newNode.next = this.head;
        this.head = newNode;
    }

    Node getHead() {
        return this.head;
    }

    class Node {
        Crop data;
        Node next;

        Node(Crop data) {
            this.data = data;
            this.next = null;
        }
    }
}