/**
1288. 删除被覆盖区间
给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。

只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。

在完成所有删除操作后，请你返回列表中剩余区间的数目。



示例：

输入：intervals = [[1,4],[3,6],[2,8]]
输出：2
解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。


提示：​​​​​​

1 <= intervals.length <= 1000
0 <= intervals[i][0] < intervals[i][1] <= 10^5
对于所有的 i != j：intervals[i] != intervals[j]
 */
class Solution_1288 {
    fun removeCoveredIntervals(intervals: Array<IntArray>): Int {
        val list = intervals.sortedWith(compareBy({ it.first() }, { -it.last() }))

        var delCount = 0
        var startIndex = 0

        while (startIndex < list.size) {
            var endIndex = startIndex + 1

            while (endIndex < list.size) {
                if (list[endIndex].last() <= list[startIndex].last()) {
                    delCount++
                } else {
                    break
                }
                endIndex++
            }
            if (endIndex != startIndex + 1) {
                startIndex = endIndex - 1
            } else {
                startIndex++
            }
        }

        return list.size - delCount
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            println(Solution_1288().removeCoveredIntervals(arrayOf(intArrayOf(1, 4), intArrayOf(3, 6), intArrayOf(2, 8))))
//            println(Solution_1288().removeCoveredIntervals(arrayOf(intArrayOf(3, 10), intArrayOf(4, 10), intArrayOf(5, 11))))
            println(Solution_1288().removeCoveredIntervals(arrayOf(intArrayOf(1, 2), intArrayOf(1, 4), intArrayOf(3, 4))))
        }
    }
}