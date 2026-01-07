public class BacktrackingSimulator {
    static int bestProfit = 0;

    static void simulate(Crop[] crops, int index, int water, int profit) {
        if (water >= 0) {
            if (index == crops.length) {
                if (profit > bestProfit) {
                    bestProfit = profit;
                }

            } else {
                int maxPossibleProfit = profit;

                for(int i = index; i < crops.length; ++i) {
                    maxPossibleProfit += crops[i].expectedProfit;
                }

                if (maxPossibleProfit > bestProfit) {
                    simulate(crops, index + 1, water - crops[index].waterRequired, profit + crops[index].expectedProfit);
                    simulate(crops, index + 1, water, profit);
                }
            }
        }
    }
}