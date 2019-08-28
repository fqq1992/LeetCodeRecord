/**

11. Container With Most Water

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.


Input: [1,8,6,2,5,4,8,3,7]
Output: 49


设定：数组最少2位

要求是 左右两个index的数值中最小的 ✖ 步长 = 最大。

思路，遍历 找到数组两边两个元素中较小的一个 X 步长 然后保存。
不记录下标的话 单次遍历



 提交测试后192ms, 围观其他答案基本思路一样 java实现只要3ms。


 本项目仅作为熟悉语言


    以上。



 */


class Solution_0011 {
    fun maxArea(height: IntArray): Int {
        var maxArea = 0
        var leftIndex = 0
        var rightIndex = height.size - 1

        while (leftIndex < rightIndex) {
            var curValue = Math.min(height[leftIndex], height[rightIndex]) * (rightIndex - leftIndex)
            maxArea = if (curValue > maxArea) curValue else maxArea
            if (height[leftIndex] <= height[rightIndex]) {
                leftIndex++
            } else {
                rightIndex--
            }
        }
        return maxArea
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var obj = Solution_0011()
            var i = intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)
            print(obj.maxArea(i))
        }
    }
}