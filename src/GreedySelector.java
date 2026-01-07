// chooses one best locally optimal crop; doesn't choose the overall best
public class GreedySelector {
    static Crop selectBest(Crop[] crops) {
        Crop best = crops[0];   // assume first crop as best

        for (Crop c : crops) {
            // current profit per unit water
            double current_profitPerWater = (double) c.expectedProfit / c.waterRequired;
            // best profit so far
            double bestProfit = (double) best.expectedProfit / best.waterRequired;

            // if current crop is more optimal
            if (current_profitPerWater > bestProfit)  {
                // update current crop as best
                best = c;
            }
        }
        // return crop that gives highest profit per unit water
        return best;
    }
}