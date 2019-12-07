/**

有序整数数组

返回 target的 开始 结束下标






 */

class Solution_34 {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty() || nums[0] > target || nums[nums.size - 1] < target) return intArrayOf(-1, -1)

        var first = searchRangeFrom(nums, target, 0, false)

        if (first == -1)
            return return intArrayOf(-1, -1)
        else return intArrayOf(first, searchRangeFrom(nums, target, first, true))

    }

    private fun searchRangeFrom(nums: IntArray, target: Int, left: Int, isLast: Boolean): Int {
        var left = left
        var right = nums.size - 1
        var index = -1

        while (left <= right) {
            val mid = left + ((right - left) / 2)
            if (isLast) {
                if (nums[mid] <= target) left = mid + 1 else right = mid - 1
            } else {
                if (nums[mid] < target) left = mid + 1 else right = mid - 1
            }

            if (nums[mid] == target) index = mid
        }
        return index
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var obj = Solution_34()
            print(obj.searchRange(intArrayOf(5, 7, 7, 8, 8, 10), 8).toList().toString())
        }
    }
}