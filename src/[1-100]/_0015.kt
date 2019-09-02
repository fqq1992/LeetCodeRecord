import java.util.*


/**
15. 3Sum
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
[-1, 0, 1],
[-1, -1, 2]
]

给一个数组 得出 三个求和=0的集合。

 */
class Solution_0015 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val result = ArrayList<List<Int>>()
        if (nums.size < 3) return result
        //1、排序。 解决不得出现重复答案的问题。
        Arrays.sort(nums)
        //2、遍历。
        var i = 0
        while (i + 2 < nums.size) {
            if (i > 0 && nums[i] == nums[i - 1]) {              // 过滤重复答案。
                i++
                continue
            }
            var j = i + 1
            var k = nums.size - 1
            val target = -nums[i]
            while (j < k) {
                when {
                    target == nums[j] + nums[k] -> {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]))
                        j++
                        k--
                        while (j < k && nums[j] == nums[j - 1]) j++  // skip same result
                        while (j < k && nums[k] == nums[k + 1]) k--  // skip same result
                    }
                    nums[j] + nums[k] > target -> k--
                    else -> j++
                }

            }
            i++
        }
        return result
    }
}