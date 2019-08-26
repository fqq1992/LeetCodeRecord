import java.util.*

/**
 *
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].


 */

/**
 * 思路首先是循环遍历；时间复查度较高；
 *
 * 考虑到数组元素唯一；当 a+b = target的时候，输出其 a/b的下标；
 *
 * 不妨单次遍历 values当key；下标为value;利用hashMap自身机制来获取。
 * 单次遍历多次寻找。
 *
 */

class Solution_0001 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val result = IntArray(2)
        val map = HashMap<Int, Int>()
        for (i in 0..nums.size) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i
                result[0] = (map[target - nums[i]])!!.toInt()
                return result
            }
            map.put(nums[i], i)
        }
        return result
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution_0001()
            val a = intArrayOf(2, 3, 67, 4)
            print(Arrays.toString(solution.twoSum(a, 6)))
        }
    }

}