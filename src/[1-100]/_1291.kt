/**
我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。

请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。

示例 1：

输出：low = 100, high = 300
输出：[123,234]
示例 2：

输出：low = 1000, high = 13000
输出：[1234,2345,3456,4567,5678,6789,12345]

提示：

10 <= low <= high <= 10^9
 */
class Solution_1291 {
    fun sequentialDigits(low: Int, high: Int): List<Int> {
        val lowSize = low.toString().length
        val list = mutableListOf<Int>()
        if (lowSize == 9 && low > 123456789) {
            return emptyList()
        } else if (high < 12) {
            return emptyList()
        }
        val allString = "123456789"

        var curSize = lowSize
        var startIndex = low / Math.pow(10.0, lowSize.toDouble() - 1).toInt() - 1

        if (startIndex + curSize >= 9) {
            startIndex = 0
            curSize++
        }

        var curValue = allString.substring(startIndex, startIndex + curSize).toInt()

        while (curValue < high) {
            if (curValue > low) {
                list.add(curValue)
            }
            if (startIndex + curSize >= 9) {
                startIndex = 0
                curSize++
            } else {
                startIndex++
            }
            if (startIndex + curSize <= 9) {
                curValue = allString.substring(startIndex, startIndex + curSize).toInt()
            } else {
                break
            }
        }
        return list
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_1291().sequentialDigits(372591426,841554424))
            println(Solution_1291().sequentialDigits(1000, 13000))
            println(Solution_1291().sequentialDigits(1000, 3000))
            println(Solution_1291().sequentialDigits(300, 1000))
            println(Solution_1291().sequentialDigits(58, 155))
            println(Solution_1291().sequentialDigits(8511, 23553))
        }
    }
}