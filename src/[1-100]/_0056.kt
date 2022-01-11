/**
 * TODO 合并间隔
 *
 * 【start，end】
 *   Input：  [[1,3],[2,6],[8,10],[15,18]]
 *    Output: [[1,6],[8,10],[15,18]]
 *
 *
 * Constraints:
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 10000
 *
 */

class Solution_0056 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        if (intervals.size == 1) {
            return intervals
        }
        val arr = IntArray(10001)
        val except = mutableListOf<Int>()
        intervals.forEach {
            if (it[0] == it[1]) {
                except.add(it[0])
            } else {
                arr[it[0]] += 1
                arr[it[1]] -= 1
            }
        }

        var res = 0
        var startIndex = -1
        var endIndex = -1
        val array = arrayListOf<IntArray>()
        arr.forEachIndexed { index, it ->

            if (res == 0 && startIndex < 0 && it > 0) {
                startIndex = index
            }

            if (except.contains(index) && startIndex < 0) {
                array.add(intArrayOf(index, index))
                return@forEachIndexed
            }

            res += it
            if (startIndex >= 0 && res == 0) {
                endIndex = index
                array.add(intArrayOf(startIndex, endIndex))
                //重置
                startIndex = -1
                endIndex = -1
                res = 0
            }
        }
        return array.toTypedArray()
    }
}