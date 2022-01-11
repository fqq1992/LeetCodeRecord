class Solution_0121 {
    fun maxProfit(prices: IntArray): Int {
        if (prices.size == 1)
            return 0
        var result = 0
        var minValue = prices[0]

        for (i in 1 until prices.size) {
            if (prices[i] - minValue < 0) {
                minValue = prices[i]
            }

            if (prices[i] - minValue > result) {
                result = prices[i] - minValue
            }
        }
        return result
    }
}