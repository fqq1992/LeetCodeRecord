import javax.print.attribute.IntegerSyntax

/**
525. 连续数组

给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。

 

示例 1:

输入: nums = [0,1]
输出: 2
说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
示例 2:

输入: nums = [0,1,0]
输出: 2
说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 

提示：

1 <= nums.length <= 105
nums[i] 不是 0 就是 1



思路：

穷举、前缀和、哈希表

1、穷举的话

for（val i in nums.length）{
left + right 遍历
}
O(N^2)

2、前缀和。
sums(i)
子序列可以由 sums(i) - sums(j)得出。
举例：[0,1,0] sums[0] = 0 sums[1] = 1 sums[2] = 1
0-1 = sums[1] 1-2 = sums[2]-sums[0]
3、前缀和+哈希表


 */

class Solution_0525 {
    fun findMaxLength(nums: IntArray): Int {
        var result = 0;
        val hashMap = mutableMapOf<Int, Int>()

        hashMap[0] = -1
        var sumPrefix = 0

        nums.forEachIndexed { index, value ->
            var temValue = value
            if (value == 0) {
                temValue = -1
            }
            sumPrefix += temValue

            // 哈希表存储每个前缀和第一次出现时末尾元素的下标
            // 其中putIfAbsent方法只有当key不存在哈希表中时，才会存储value
            hashMap.putIfAbsent(sumPrefix, index)
            // right - hash.get(prefix)为符合新问题2中的条件的子数组的长度
            result = Math.max(result, index - hashMap[sumPrefix]!!);
        }

        return result
    }
}