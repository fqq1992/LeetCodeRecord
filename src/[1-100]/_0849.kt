import kotlin.math.max

/**

到最近的人的最大距离

给你一个数组 seats 表示一排座位，其中 seats[i] = 1 代表有人坐在第 i 个座位上，seats[i] = 0 代表座位 i 上是空的（下标从 0 开始）。

至少有一个空座位，且至少有一人已经坐在座位上。

亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。

返回他到离他最近的人的最大距离。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/maximize-distance-to-closest-person
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


限制：
2 <= seats.length <= 2 * 104
seats[i] 为 0 或 1
至少有一个 空座位
至少有一个 座位上有人





思路 要么是两个1直接的最大距离 要么是从头到尾的最大距离。


 */
class Solution_0849 {
    fun maxDistToClosest(seats: IntArray): Int {
        var pre = -1 //上一个已坐位置，初始值为-1
        var maxdis = 0 //两个已坐位置间的最大长度

        seats.forEachIndexed { index, value ->
            if (value == 1) {
                //已坐
                if (pre == -1) {
                    maxdis = index * 2
                } else if (index - pre > 1) {
                    maxdis = max(maxdis, (index - pre - 1))
                }
                pre = index
            } else if (index == seats.size - 1) {
                maxdis = max(maxdis, 2 * (index - pre))
            }
        }

        return maxdis - maxdis / 2
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_0849().maxDistToClosest(intArrayOf(1, 0, 0, 0, 1, 0, 1)))
        }
    }
}