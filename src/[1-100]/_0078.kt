/**

输出全部 子集

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [0]
Output: [[],[0]]


思路： 注意点 非连续子串。
BFS


 */

class Solution_0078 {
    fun subsets(nums: IntArray): List<List<Int>> {
        if (nums.isEmpty()) {
            return listOf()
        }

        val list = mutableListOf<MutableList<Int>>()


        nums.forEachIndexed { index, value ->
            val mutableList = mutableListOf<MutableList<Int>>()
            list.forEach {
                //                it.add(value)
                mutableList.add(it.toMutableList().also { list ->
                    list.add(value)
                })
            }
            list.addAll(mutableList)
            list.add(mutableListOf(value))
        }
        list.add(mutableListOf())
        return list
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_0078().subsets(intArrayOf(1, 2)))
        }
    }
}