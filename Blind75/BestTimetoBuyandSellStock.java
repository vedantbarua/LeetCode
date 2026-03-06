public class BestTimetoBuyandSellStock {
    //Question: You are given an array prices where prices[i] is the price of a given stock on the ith day. 
    // You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock. 
    // Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price; // Update the minimum price
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice; // Update the maximum profit
            }
        }

        return maxProfit;
    }
    public static void main(String[] args) {
        BestTimetoBuyandSellStock solution = new BestTimetoBuyandSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        int result = solution.maxProfit(prices);
        System.out.println("Maximum profit: " + result); // Output: Maximum profit: 5
    }
    
}