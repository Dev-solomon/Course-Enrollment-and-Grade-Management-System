import java.util.ArrayList; // Import the arrayList class 



// Programming Assignment Unit_4 --- Data Analysis Project
public class Stock { 

    // Method for calculating the average stock price
    public static float calculateAveragePrice(float[] stock_prices) {
        float total_sum = 0;
        float cAP;
        for (float price: stock_prices) {
            total_sum += price;
        }
        cAP = total_sum / stock_prices.length;
        return cAP;
    }

    // Method for finding the maximum stock price
    public static float findMaximumPrice(float[] stock_prices) {
        float maxPrice = stock_prices[0];
        for (int i = 0; i < stock_prices.length; i++) {
            if (stock_prices[i] > maxPrice) {
                maxPrice = stock_prices[i];
            }
        }
        return maxPrice;
    }

    // Method for determining the occurrence count of a specific price
    public static int countOccurrences(float[] stock_prices, float targetPrice) {
        int counter = 0;
        for (float stockPrice: stock_prices) {
            if (stockPrice == targetPrice) {
                counter += 1;
            }
        }
        return counter;
    }

    // Method for computing the cummulative sum of stock prices
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> stock_prices) {
        ArrayList<Float> cumulativeSum = new ArrayList<>();
        float total_sum = 0;
        for (float stock_price: stock_prices) {
            total_sum += stock_price;
            cumulativeSum.add(total_sum);
        }
        return cumulativeSum;
    }


    public static void main(String[] args) {  

        float[] stock_prices = { 12.6f, 33.40f, 20.81f, 9.02f, 11.45f, 52.1f, 12.6f, 74.0f, 12.6f, 4.22f };
        ArrayList<Float> stock_prices_list = new ArrayList<>();
        for (float price : stock_prices) {
            stock_prices_list.add(price);
        } 

        System.out.print("\n\n");
        System.out.println(" Stock Prices Sample - " + stock_prices_list);
        System.out.println("-------------------------------------------------------------");

        System.out.println("Average Price: " + calculateAveragePrice(stock_prices));
        System.out.println("Maximum Price : " + findMaximumPrice(stock_prices));
        System.out.println("Count Occurrences : " + countOccurrences(stock_prices, 12.6f));
        System.out.println("Cumulative sum : " + computeCumulativeSum(stock_prices_list));
        System.out.print("\n\n");
       
    } 
    
}