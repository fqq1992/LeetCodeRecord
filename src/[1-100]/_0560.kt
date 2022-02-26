/**
找连续子数组和等于k
 */

//思路1 双指针
// 注意点：边界问题， 数组中可能存在0的情况 以及负数的情况，所以需要每次遍历。

//思路2 前缀和
class Solution_0560 {

    fun subarraySum(nums: IntArray, k: Int): Int {
        if (nums.isEmpty()) {
            return 0
        }
        if (nums.size == 1) {
            return if (k == nums[0]) 1 else 0
        }
        val sumsMap = mutableMapOf<Int, Int>()

        var sum = 0
        var res = 0
        //和为整数k
        nums.forEachIndexed { index, value ->
            sum += value
            if (sumsMap.containsKey(sum - k)) {
                res += sumsMap.get(sum - k)!!
            }
            sumsMap.put(sum, sumsMap.getOrDefault(sum, 0) + 1)
        }
        return res


        //前缀和 再遍历就超时啦
//        for (i in nums.indices) {
//            if (sumsMap[i] == k) {
//                res++
//            }
//            var startIndex = 0
//
//            while (startIndex < i) {
//                if (sumsMap[i]!! - sumsMap[startIndex]!! == k) {
//                    res++
//                }
//                startIndex++
//            }
//        }


    }


    fun subarraySum_v1(nums: IntArray, k: Int): Int {
        if (nums.isEmpty()) {
            return 0
        }
        if (nums.size == 1) {
            return if (k == nums[0]) 1 else 0
        }
        var startIndex = 0
        var endIndex = 1
        var res = 0

        var sums = 0

        while (startIndex < nums.size) {
            endIndex = startIndex + 1
            sums = 0

            sums += nums[startIndex]
            if (sums == k) {
                res++
            }
            while (endIndex < nums.size) {
                sums += nums[endIndex]
                if (sums == k) {
                    res++
                    //后面可能会出现0 的数字
//                        break
                }
                endIndex++
            }

            startIndex++
        }
        return res
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //[-1,-1,1]
            //0


            println(Solution_0560().subarraySum(intArrayOf(-1, -1, 1), 0))
            println(Solution_0560().subarraySum(intArrayOf(1, -1, 0), 0))
            println(Solution_0560().subarraySum(intArrayOf(0, 0), 0))

            //[28,54,7,-70,22,65,-6]
            //100

            println(Solution_0560().subarraySum(intArrayOf(28, 54, 7, -70, 22, 65, -6), 100))
        }
    }
}