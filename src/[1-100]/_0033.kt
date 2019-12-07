/**

Search in Rotated Sorted Array

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).


Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

阶段性有序数组 边界查找。


 */


class Solution_33 {
    fun search(nums: IntArray, target: Int): Int {
        if (nums == null || nums.isEmpty()) return -1

        var left = 0
        var right = nums.size - 1


        while (left <= right) {
            if (left == right) return if (nums[left] == target) left else -1

            var middle = (left + right) / 2

            if (left == middle) {
                if (nums[left] == target) {
                    return left
                }
            }

            if (right == middle) {
                if (nums[right] == target) {
                    return right
                }
            }
            //左边有序
            if (nums[middle] > nums[right]) {
                // target 在左边。
                if (target <= nums[middle] && target >= nums[left]) {
//                    right = middle
                    right = if (middle == right) --right else middle
                } else {
                    left = middle + 1
                }
            } else { //右边有序

                // target 在右边。
                if (nums[middle] <= target && nums[right] >= target) {
                    left = if (middle == left) ++left else middle
                } else {
                    right = middle - 1
                }
            }

        }
        if (left > right)
            return -1
        if (left == right) return if (nums[left] == target) left else -1
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var obj = Solution_33()
            print(obj.search(intArrayOf(1, 3), 1))
        }
    }
}