import java.util.*


/**

421 数组中异或的最大值。

给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。

进阶：你可以在 O(n) 的时间解决这个问题吗？

示例 1：

输入：nums = [3,10,5,25,2,8]
输出：28
解释：最大运算结果是 5 XOR 25 = 28.

示例 5：

输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
输出：127

提示：

1 <= nums.length <= 2 * 10000
0 <= nums[i] <= 2^31 - 1

 思路

1、排序后 拿最高位数字跟其他位分别做异或 获取较大的值。时间复杂度 n~n^2 跟具体数据有关系

2、
 二进制下 异或总计位数 30位。 我们从高位开始判断是否可以存在当前位数为1的可能性 不存在则为0 向下继续循环




 */
class Solution_0421 {
    fun findMaximumXOR(nums: IntArray): Int {
        // 最高位的二进制位编号为 30
        var curValue = 0
        for (i in 30 downTo 0) {
            //获取最高位的情况，
            val seen: HashSet<Int> = HashSet()
            nums.forEach {
                seen.add(it shr i)
            }
            val next = curValue * 2 + 1
            var found = false

            nums.forEach {
                if (seen.contains(next xor  (it shr i))) {
                    found = true
                    return@forEach
                }
            }

            if (found) {
                curValue = next
            } else {
                curValue = next - 1
            }
        }
        return curValue
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            3,10,5,25,2,8
            println("-----" + Solution_0421().findMaximumXOR(intArrayOf(3, 10, 5, 25, 2, 8)))
            println("-----" + Solution_0421().findMaximumXOR(intArrayOf(14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70)))
        }
    }
}