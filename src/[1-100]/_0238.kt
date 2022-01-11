/**
 * 给定数组，计算除自己以外所有数的乘积。
 *
 * 要求时间复杂度O（n） 且不得用除法。
 *
 */

class Solution_0238 {
    //思路1 当可以使用除法时。 全部乘 除以当前数，问题是需要针对包含0的单独处理。
    //思路2 增加两个数组 分别保存left(i) right(i)的乘积
    fun productExceptSelf(nums: IntArray): IntArray {

        val n = nums.size
        val leftArr = IntArray(n)
        val rightArr = IntArray(n)
        val result = IntArray(n)
        leftArr[0] = 1
        rightArr[0] = 1
        for (i in 1 until n) {
            leftArr[i] = leftArr[i - 1] * nums[i]
            rightArr[i] = rightArr[i - 1] * nums[n - i]
        }

        for (i in nums.indices) {
            result[i] = leftArr[i] * rightArr[n - 1 - i]
        }

        return result
    }
}