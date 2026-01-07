import java.io.PrintStream;

public class RecommendationEngine {
    Crop[] rawInput = new Crop[]{new Crop("Rice", 120, 90, 120), new Crop("Wheat", 60, 70, 110), new Crop("Maize", 50, 65, 100), new Crop("Sugarcane", 150, 120, 300)};

    void runAll(int waterLimit) {
        System.out.println("Initializing System: Storing data in Hash Table...");
        HashTable cropTable = new HashTable();

        for(Crop c : this.rawInput) {
            cropTable.insert(c);
        }

        Crop[] crops = cropTable.getAllCrops(this.rawInput.length);
        System.out.println("\n--- Efficiency Check: BST vs AVL (Worst Case) ---");
        Crop[] sortedCrops = new Crop[]{new Crop("Maize", 50, 65, 100), new Crop("Wheat", 60, 70, 110), new Crop("Rice", 120, 90, 120), new Crop("Sugarcane", 150, 120, 300)};
        BST bst = new BST();
        AVLTree avl = new AVLTree();

        for(Crop c : sortedCrops) {
            bst.insert(c);
            avl.root = avl.insert(avl.root, c);
        }

        int bstHeight = bst.height(bst.root);
        int avlHeight = avl.height(avl.root);
        System.out.println("Input Order: Maize(65) -> Wheat(70) -> Rice(90) -> Sugarcane(120)");
        System.out.println("BST Height (Skewed): " + bstHeight);
        System.out.println("AVL Height (Balanced): " + avlHeight);
        if (bstHeight > avlHeight) {
            System.out.println(">> PROOF: BST degraded to linked list; AVL remained balanced.");
        }

        System.out.println("\n--- AVL Tree (Sorted Crops) ---");
        AVLTree mainAvl = new AVLTree();

        for(Crop c : crops) {
            mainAvl.root = mainAvl.insert(mainAvl.root, c);
        }

        mainAvl.inorder(mainAvl.root);
        System.out.println("\n--- Max Heap (Top Crops) ---");
        MaxHeap heap = new MaxHeap();

        for(Crop c : crops) {
            heap.insert(c);
        }

        System.out.println("Best Crop: " + heap.extractMax().name);
        System.out.println("\n--- Knapsack (DP) ---");
        PrintStream var10000 = System.out;
        int var10001 = KnapsackDP.knapsack(crops, waterLimit);
        var10000.println("Max Profit: " + var10001);
        System.out.println("\n--- Greedy Selection ---");
        System.out.println("Quick Choice: " + GreedySelector.selectBest(crops).name);
        System.out.println("\n--- Backtracking Simulation ---");
        BacktrackingSimulator.simulate(crops, 0, waterLimit, 0);
        System.out.println("Best Profit (Backtracking): " + BacktrackingSimulator.bestProfit);
    }
}
