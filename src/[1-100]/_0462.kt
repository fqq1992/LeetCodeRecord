/**

462. 最少移动次数使数组元素相等 II
给定一个非空整数数组，找到使所有数组元素相等所需的最小移动数，其中每次移动可将选定的一个元素加1或减1。 您可以假设数组的长度最多为10000。

输入:
[1,2,3]

输出:
2

说明：
只有两个动作是必要的（记得每一步仅可使其中一个元素加1或减1）：

[1,2,3]  =>  [2,2,3]  =>  [2,2,2]


思路：本质就是找中位数。

有序数组
【1 3 8 9 15】 [1,15] 步骤固定为14 [3,9]固定为6，[8]不动，
【1 3 8 9】   【1,9】固定为 8 【3,8】固定为5

求移动的次数。




 */

class Solution_0462 {
    fun minMoves2(nums: IntArray): Int {
        if (nums.isEmpty() || nums.size == 1) {
            return 0
        }
        nums.sort()
        var res = 0
        val n = nums.size
        for (i in 0 until (n / 2)) {
            res += nums[n - 1 - i] - nums[i]
        }
        return res
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("_____" + Solution_0462().minMoves2(intArrayOf(1, 10, 2, 9)))
        }
    }
}