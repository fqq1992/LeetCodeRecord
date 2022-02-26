import java.util.*

/**

给你一个由 n 个正整数组成的数组 nums 。

你可以对数组的任意元素执行任意次数的两类操作：

如果元素是 偶数 ，除以 2
例如，如果数组是 [1,2,3,4] ，那么你可以对最后一个元素执行此操作，使其变成 [1,2,3,2]
如果元素是 奇数 ，乘上 2
例如，如果数组是 [1,2,3,4] ，那么你可以对第一个元素执行此操作，使其变成 [2,2,3,4]
数组的 偏移量 是数组中任意两个元素之间的 最大差值 。

返回数组在执行某些操作之后可以拥有的 最小偏移量 。



示例 1：

输入：nums = [1,2,3,4]
输出：1
解释：你可以将数组转换为 [1,2,3,2]，然后转换成 [2,2,3,2]，偏移量是 3 - 2 = 1
示例 2：

输入：nums = [4,1,5,20,3]
输出：3
解释：两次操作后，你可以将数组转换为 [4,2,5,5,3]，偏移量是 5 - 2 = 3
示例 3：

输入：nums = [2,10,8]
输出：3


提示：

n == nums.length
2 <= n <= 100000
1 <= nums[i] <= 10^9

 */
class Solution_1675 {
    fun minimumDeviation(nums: IntArray): Int {
        //奇数
        var oddList = mutableListOf<Int>()
        //偶数
        var evenList = mutableListOf<Int>()

        val set = TreeSet<Int>(object : Comparator<Int> {
            override fun compare(p0: Int?, p1: Int?): Int {
                val temP0 = p0 ?: 0
                val temP1 = p1 ?: 0
                return when {
                    temP0 < temP1 -> {
                        1
                    }
                    temP0 == temP1 -> {
                        0
                    }
                    else -> {
                        -1
                    }
                }
            }
        })

        nums.forEach {
            if (it % 2 == 1) {
//                oddList.add(it)
                set.add(it * 2)
            } else {
                set.add(it)
            }
        }

        var minDiff = set.first() - set.last()

        while (set.first() % 2 == 0) {
            val temMax = set.first()
            set.remove(temMax)
            set.add(temMax / 2)

            if (minDiff > (set.first() - set.last())){
                minDiff = set.first() - set.last()
            }
        }

        return minDiff

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //25,23 ，26，18；

            //
            println(Solution_1675().minimumDeviation(intArrayOf(1, 2, 3, 4)))
            println(Solution_1675().minimumDeviation(intArrayOf(2, 10, 8)))
            println(Solution_1675().minimumDeviation(intArrayOf(23, 25, 18, 26)))
            println(Solution_1675().minimumDeviation(intArrayOf(4, 1, 5, 20, 3)))

        }
    }
}