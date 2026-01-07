public class HashTable {
    int SIZE = 10;
    LinkedList[] table;

    HashTable() {
        this.table = new LinkedList[this.SIZE];

        for(int i = 0; i < this.SIZE; ++i) {
            this.table[i] = new LinkedList();
        }

    }

    int hash(String key) {
        return Math.abs(key.hashCode()) % this.SIZE;
    }

    void insert(Crop crop) {
        int index = this.hash(crop.name);
        this.table[index].insert(crop);
    }

    void display() {
        for(int i = 0; i < this.SIZE; ++i) {
            for(LinkedList.Node temp = this.table[i].getHead(); temp != null; temp = temp.next) {
                System.out.println(temp.data.name);
            }
        }

    }

    public Crop[] getAllCrops(int totalCount) {
        Crop[] retrievedCrops = new Crop[totalCount];
        int k = 0;

        for(int i = 0; i < this.SIZE; ++i) {
            for(LinkedList.Node temp = this.table[i].getHead(); temp != null; temp = temp.next) {
                retrievedCrops[k++] = temp.data;
            }
        }

        return retrievedCrops;
    }
}
