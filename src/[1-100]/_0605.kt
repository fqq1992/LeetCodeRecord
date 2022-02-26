class Solution_0605 {
    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var lastPlotsIndex = 0
        var count = 0
        flowerbed.forEachIndexed { index, value ->
            if (index < lastPlotsIndex) {
                return@forEachIndexed
            }
            if (value == 0) {
                val pre = if (index - 1 < 0) 0 else flowerbed[index - 1]
                val next = if (index + 1 >= flowerbed.size) 0 else flowerbed[index + 1]

                if (pre == 0 && next == 0) {
                    count++
                    lastPlotsIndex = index + 2
                }
            }

        }
        return count >= n
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_0605().canPlaceFlowers(intArrayOf(1, 0, 0, 0, 0, 1), 2))
        }
    }
}