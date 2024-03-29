/**

0875:吃香蕉。
珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  

珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。


示例 1：

输入: piles = [3,6,7,11], H = 8
输出: 4
示例 2：

输入: piles = [30,11,23,4,20], H = 5
输出: 30
示例 3：

输入: piles = [30,11,23,4,20], H = 6
输出: 23


提示：

1 <= piles.length <= 10^4
piles.length <= H <= 10^9
1 <= piles[i] <= 10^9

 *
 */
class Solution_0875 {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        val size = piles.size
        if (size > h) {
            return 0
        }
        piles.sort()
        if (size == h) {
            return piles.last()
        }
        // 速度最小的时候，耗时最长
        var left = 1
        var leftIndex = 0
        if (h / size > 2) {
            left = 0
        } else {
            leftIndex = size - 1 - (h % size)
            left = piles[leftIndex]
        }

        // 速度最大的时候，耗时最短
        var right = piles.last()

        while (left <= right) {
            val mid = (left + right) / 2
            var H = 0
            for (i in piles.indices) H += Math.ceil(1.0 * piles.get(i) / h).toInt()
            if (H < h) left = mid + 1 else right = mid - 1
        }
        return left

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_0875().minEatingSpeed(intArrayOf(1, 2, 3, 4, 10), 6))
        }
    }
}