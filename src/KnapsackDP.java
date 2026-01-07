// dynamic programming solution
// for the 0/1 knapsack problem (crop selection under water constraints)
public class KnapsackDP {
    static int knapsack(Crop[] crops, int waterLimit) {
        int n = crops.length;     // no. of items to choose from

        // DP table (2D matrix)
        int[][] dp = new int[n + 1][waterLimit + 1];
        // dp[i][w]: max profit with i crops and water w

        // Loop through crops one by one (0/1 -> each crop considered once only)
        for (int i = 1; i <= n; i++) {
            // Loop through all possible water capacities
            for (int w = 1; w <= waterLimit; w++) {
                // amount of water required by the current crop
                int neededWater = crops[i - 1].waterRequired;
                int remainingWater = w - neededWater;   // water left if crop is chosen

                // if crop fits within available water
                if (neededWater <= w) {
                    // profits obtained
                    int currentProfit = crops[i - 1].expectedProfit;  // current crop
                    int oldProfit = dp[i - 1][remainingWater];  // best from old using water left
                    int take = currentProfit + oldProfit;  // total profit if current crop is chosen
                    int notTake = dp[i - 1][w];  // profit if crop is not chosen

                    dp[i][w] = Math.max(take, notTake);   // choose option with higher profit
                } else {
                    dp[i][w] = dp[i - 1][w];   // skip crop if it doesn't fit
                }
            }
        }

        // final answer: max profit using all crops within full water limit
        return dp[n][waterLimit];
    }
}