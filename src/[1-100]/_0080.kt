/**

80. 删除有序数组中的重复项 II

给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

说明：

为什么返回数值是整数，但输出的答案是数组呢？

请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

示例 1：

输入：nums = [1,1,1,2,2,3]
输出：5, nums = [1,1,2,2,3]
解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。

示例 2：

输入：nums = [0,0,1,1,1,1,2,3,3]
输出：7, nums = [0,0,1,1,2,3,3]
解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 

提示：

1 <= nums.length <= 3 * 104
-10^4 <= nums[i] <= 10^4
nums 已按升序排列



思路：
O（1） 循环 双指针，前指针用来标志摆放的位置，后指针用来前进。

 */

//  1112
class Solution_0080 {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty() || nums.size <= 2) {
            return nums.size
        }
        var realIndex = -1

        var curValue = Int.MIN_VALUE

        var duplicateCount = 0
        val s = "hello"
        s.forEachIndexed { index, c ->

        }
        nums.forEachIndexed { index, value ->
            if (curValue == value) {
                duplicateCount++
            } else {
                duplicateCount = 0
            }

            curValue = value
            if (duplicateCount >= 2) {

            } else {
                realIndex++
            }

            if (realIndex != index) {
                nums[realIndex] = nums[index]
            }
        }

        return realIndex + 1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_0080().removeDuplicates(intArrayOf(1, 1, 2)))
            println(Solution_0080().removeDuplicates(intArrayOf(1, 1, 1, 2)))
            println(Solution_0080().removeDuplicates(intArrayOf(1, 1, 2, 2, 2, 3)))
        }
    }
}