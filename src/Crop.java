public class Crop {
    String name;
    int waterRequired;
    int expectedProfit;
    int growthDays;

    Crop(String name, int waterRequired, int expectedProfit, int growthDays) {
        this.name = name;
        this.waterRequired = waterRequired;
        this.expectedProfit = expectedProfit;
        this.growthDays = growthDays;
    }
}
