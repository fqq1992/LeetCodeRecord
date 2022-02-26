import kotlin.math.min

class Solution_6005 {
    fun minimumOperations(nums: IntArray): Int {
        var maxOdd = 0
        var maxOddValue = 0
        var maxEven = 0
        var maxEvenValue = 0
        val arrOdd = IntArray(100001)
        val arrEven = IntArray(100001)

        var preMaxOdd = 0
        var preMaxEven = 0

        nums.forEachIndexed { index, value ->

            if (index % 2 == 0) {
                arrEven[value]++
                //偶数
                if (arrEven[value] > maxEven) {
                    if (maxEvenValue != 0 && value != maxEvenValue) {
                        preMaxEven = maxEven
                    }
                    maxEven = arrEven[value]
                    maxEvenValue = value
                } else if (arrEven[value] > preMaxEven) {
                    preMaxEven = arrEven[value]
                }
            } else {
                arrOdd[value]++
                //奇数
                if (arrOdd[value] > maxOdd) {
                    if (maxOdd != 0 && value != maxOddValue) {
                        preMaxOdd = maxOdd
                    }
                    maxOdd = arrOdd[value]
                    maxOddValue = value
                } else if (arrOdd[value] > preMaxOdd) {
                    preMaxOdd = arrOdd[value]
                }
            }
        }

        if (maxOddValue != maxEvenValue)
            return nums.size - (maxEven + maxOdd)

        return Math.min(nums.size - (maxEven + preMaxOdd), nums.size - (maxOdd + preMaxEven))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            println(Solution_6005().minimumOperations(intArrayOf(2, 2, 2, 2)))
//            println(Solution_6005().minimumOperations(intArrayOf(2, 1, 2, 2, 2)))
//            println(Solution_6005().minimumOperations(intArrayOf(2, 1, 2, 2, 2, 2)))
            println(Solution_6005().minimumOperations(intArrayOf(2, 2, 2, 2, 2, 1, 2)))
            //[3,3,3,4,3,3,3]
        }
    }
}