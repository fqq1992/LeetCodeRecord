import java.util.*

/**
84. 柱状图中最大的矩形

给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

输入：heights = [2,1,5,6,2,3]
输出：10
解释：最大的矩形为图中红色区域，面积为 10

输入： heights = [2,4]
输出： 4


提示：

1 <= heights.length <=105
0 <= heights[i] <= 104


思路：

1 暴力算，

2 左侧边界确认 栈先进先出。


 */
class Solution_0084 {

    private fun getValues(heights: IntArray, stack: Stack<Int>) = if (stack.peek() < 0) 0 else heights[stack.peek()]

    fun largestRectangleArea(heights: IntArray): Int {

        val stack: Stack<Int> = Stack()
        stack.push(-1)
        var maxValue = 0


        heights.forEachIndexed { index, values ->
            while (stack.size > 1 && values < getValues(heights, stack)) {
                val tem = heights[stack.pop()]
                val curMaxValue = (index - stack.peek() - 1) * tem
                if (curMaxValue > maxValue) {
                    maxValue = curMaxValue
                }
            }
            stack.push(index)
        }
        while (stack.size > 1) {
            val tem = heights[stack.pop()]
            val curMaxValue = (heights.size - stack.peek() - 1) * tem
            if (curMaxValue > maxValue) {
                maxValue = curMaxValue
            }
        }

        return maxValue
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            println("-----${Solution_0084().largestRectangleArea(intArrayOf(2, 1, 5, 6, 2, 3))}")
            println("-----${Solution_0084().largestRectangleArea(intArrayOf(2, 1, 1, 2))}")
//            println("-----${Solution_0084().largestRectangleArea(intArrayOf(1, 0, 0, 1, 0, 1, 0, 0, 0, 1))}")
//            println("-----${Solution_0084().largestRectangleArea(intArrayOf(5, 7, 8, 1, 1, 4, 4, 6, 5, 0, 2))}")
        }
    }
}