/**
 * 454. 四数相加 II
 *
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
输出：2
解释：
两个元组如下：
1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0

输入：nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
输出：1


  提示：

n == nums1.length
n == nums2.length
n == nums3.length
n == nums4.length
1 <= n <= 200
-228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228



 *
 *
 */
class Solution_0454 {
    fun fourSumCount(nums1: IntArray, nums2: IntArray, nums3: IntArray, nums4: IntArray): Int {
        val map: MutableMap<Int, Int> = mutableMapOf()

        var temp = 0
        var res = 0


        //统计两个数组中的元素之和，同时统计出现的次数，放入map

        for (i in nums1) {
            for (j in nums2) {
                temp = i + j
                if (map.containsKey(temp)) {
                    map[temp] = map[temp]!!.plus(1)
                } else {
                    map[temp] = 1
                }

            }
        }


        //统计剩余的两个元素的和，在map中找是否存在相加为0的情况，同时记录次数
        for (i in nums3) {
            for (j in nums4) {
                temp = i + j
                if (map.containsKey(0 - temp)) {
                    res += map.get(0 - temp)!!
                }

            }
        }

        return res
    }
}
