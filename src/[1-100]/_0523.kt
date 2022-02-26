/**

思路1，两重遍历扫描过去 o(nxn)
思路2，用map 扫码数字出现的个数

 */
class Solution_0523 {

    fun findPairs(nums: IntArray, k: Int): Int {
        val map = mutableMapOf<Int, Int>()

        nums.forEach {
            map.put(it, map.getOrDefault(it, 0) + 1)
        }

        var res = 0

        map.forEach { (key, value) ->
            if (k == 0) {
                if (value > 1) {
                    res++
                }
            } else {
                if (map.containsKey(k + key)) {
                    res++
                }

            }
        }
        return res
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //[3,1,4,1,5]
            //2
            println(Solution_0523().findPairs(intArrayOf(3, 1, 4, 1, 5), 2))

            //[1,2,4,4,3,3,0,9,2,3]
            //3

            println(Solution_0523().findPairs(intArrayOf(1, 2, 4, 4, 3, 3, 0, 9, 2, 3), 3))
        }
    }


    fun findPairs1(nums: IntArray, k: Int): Int {
        var startIndex = 0
        val set = mutableSetOf<String>()

        while (startIndex < nums.size) {
            var endIndex = startIndex + 1
            while (endIndex < nums.size) {
                if (Math.abs(nums[startIndex] - nums[endIndex]) == k) {
                    set.add("${nums[startIndex]}_" + nums[endIndex])
                    set.add("${nums[endIndex]}_" + nums[startIndex])
                }
                endIndex++
            }
            startIndex++
        }
        if (k == 0) {
            return set.size
        }

        return set.size / 2
    }
}