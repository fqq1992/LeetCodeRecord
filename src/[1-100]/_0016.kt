import java.util.*


/**
16. 3Sum Closest
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.
假设只有一个答案。





Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

 */
class Solution_0016 {
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        //参考0015.
        //1。排序。
        Arrays.sort(nums)

        var min_diff = Int.MAX_VALUE

        for (i in 0..(nums.size - 2)) {
            var p = i + 1
            var q = nums.size - 1
            while (p < q) {
                var diff = nums[p] + nums[q] + nums[i] - target
                if (diff == 0) return target + 0
                min_diff = if (Math.abs(diff) < Math.abs(min_diff)) diff else min_diff
                if (diff > 0) q-- else p++
            }
        }

        return min_diff + target
    }
}