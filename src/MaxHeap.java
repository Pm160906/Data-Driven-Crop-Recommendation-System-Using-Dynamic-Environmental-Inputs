// max heap implementation using array
// crop with highest expected profit is always at index 0
public class MaxHeap {
    Crop[] heap = new Crop[20];   // array to store heap elements, size = 20.
    int size = 0;                 // current number of elements in the heap

    // insert a new crop into the heap
    void insert(Crop crop) {
        heap[size] = crop;   // place new crop at available position (bottom)
        int i = size;        // index
        size++;              // increase heap size

        // bubble up
        while (i > 0) {
            int parent = (i - 1) / 2;

            int parentProfit = heap[parent].expectedProfit;
            int currentProfit = heap[i].expectedProfit;

            if (parentProfit >= currentProfit)
                break;

            Crop temp = heap[i];
            heap[i] = heap[parent];
            heap[parent] = temp;

            i = parent;
        }
    }

    // removes & returns maximum element
    Crop extractMax() {
        if (size == 0) return null;    // heap = empty: null

        Crop max = heap[0];            // crop with maximum profit at root
        heap[0] = heap[size - 1];      // move last element to root
        size--;                        // reduce heap size

        heapify(0);                 // restore heap property starting at root
        return max;                   // return the removed maximum crop
    }

    // bubble down
    void heapify(int i) {      // fixes heap property starting from index i
        int largest = i;       // assume current index as largest
        int l = 2 * i + 1;     // left child index
        int r = 2 * i + 2;     // right child index

        int CurrentProfit = heap[largest].expectedProfit;

        // if left child exists, left > current
        if (l < size) {
            int leftChildProfit = heap[l].expectedProfit;
            if (leftChildProfit > CurrentProfit) {
                largest = l;
                CurrentProfit = leftChildProfit;
            }
        }

        // if right child exists, right > current
        if (r < size) {
            int rightChildProfit = heap[r].expectedProfit;
            if (rightChildProfit > CurrentProfit) {
                largest = r;
            }
        }

        // if current node is not the largest
        if (largest != i) {
            // swap current node with the largest child
            Crop temp = heap[i];
            heap[i] = heap[largest];
            heap[largest] = temp;

            // recursively heapify the affected subtree
            heapify(largest);
        }
    }
}