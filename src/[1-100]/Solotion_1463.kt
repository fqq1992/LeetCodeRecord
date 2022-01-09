import java.util.*
import kotlin.collections.HashMap


/**
 * 采摘樱桃。
 *
 * 思路：通过回溯遍历出所有答案，统计最大值。
 *  dfs
 *
 *  自上而下 递归耗时较大。
 *  for循环一遍出。
 *
 *
 */
class Solution_1463 {

    fun cherryPickup(grid: Array<IntArray>): Int {
        val startTime = System.currentTimeMillis()
        if (grid.size == 1) {
            return when {
                grid.first().size > 1 -> {
                    grid.first()[0] + grid.first().last()
                }
                grid.first().size == 1 -> {
                    grid.first()[0]
                }
                else -> {
                    0
                }
            }
        } else {
            var robot = intArrayOf(0, 0, grid.first().size - 1)

            findMax(grid, robot, grid.first()[0] + grid.first().last())

            return maxResult
        }
    }

    val delta = arrayOf(intArrayOf(-1, -1), intArrayOf(-1, 0), intArrayOf(-1, 1), intArrayOf(0, -1), intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(1, -1), intArrayOf(1, 0), intArrayOf(1, 1))
    var maxResult = 0
    val helper: HashMap<String, Int> = HashMap()

    private fun findMax(grid: Array<IntArray>, robot: IntArray, proAllValues: Int) {
        val i = robot.first() + 1
        if (i >= grid.size) {
            if (proAllValues > maxResult) {
                maxResult = proAllValues
            }
            return
        }

        delta.forEachIndexed { _, ints ->
            val curIntArray = grid[i]
            val robotIndex1 = robot[1] + ints[0]
            val robotIndex2 = robot[2] + ints[1]

            if (robotIndex1 >= 0 && robotIndex1 < curIntArray.size && robotIndex2 >= 0 && robotIndex2 < curIntArray.size && robotIndex1 != robotIndex2 && robotIndex1 < robotIndex2) {
                val curValue = curIntArray[robotIndex1] + curIntArray[robotIndex2]
                val all = proAllValues + curValue

//                println("i = $i  curIntArray=${curIntArray.toList()}  robotIndex1=$robotIndex1  robotIndex2=$robotIndex2   preloadAll=$all")

                findMax(grid, intArrayOf(i, robotIndex1, robotIndex2), all)
            }
        }
    }

    fun cherryPickup1(grid: Array<IntArray>): Int {
        val row = grid.size
        val m = grid.first().size
        val f = Array(row) { Array(m) { IntArray(m) } }
        f[0][0][m - 1] = grid.first().first() + grid[0].last()
        for (i in 1 until row) {

            val curIntArray = grid[i]
            //左边机器人
            for (robotIndex1 in 0..Math.min(i, m - 1)) {
                //右边机器人
                for (robotIndex2 in m - 1 downTo Math.max(0, m - i - 1)) {
                    if (robotIndex1 < robotIndex2) {
                        // 当前层的某个位置下，机器人可能从上一层的九种状态转移而来，取最大值
                        var max = 0
                        delta.forEachIndexed { index, ints ->
                            val preIndex1 = robotIndex1 + ints[0]
                            val preIndex2 = robotIndex2 + ints[1]
                            if (preIndex1 >= 0 && preIndex1 < curIntArray.size && preIndex2 >= 0 && preIndex2 < curIntArray.size && preIndex1 != preIndex2 && preIndex1 < preIndex2) {
                                if (f[i - 1][preIndex1][preIndex2] != 0) {
                                    if (f[i - 1][preIndex1][preIndex2] > max) {
                                        max = Math.max(max, f[i - 1][preIndex1][preIndex2])
                                    }
                                }
                            }
                        }
                        f[i][robotIndex1][robotIndex2] = max + curIntArray[robotIndex1] + curIntArray[robotIndex2]
                    }
                }
            }
        }
        var res = 0;
        // 比较最底层，所有情况的最大值
        for (index in 0 until m) {
            for (index1 in 0 until m) {
                res = res.coerceAtLeast(f[row - 1][index][index1])
            }
        }
        return res
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
            // intArrayOf(3,1,1), intArrayOf(2,5,1), intArrayOf(1,5,5), intArrayOf(2,1,1)
//            val args: Array<IntArray> = arrayOf(intArrayOf(3, 1, 1), intArrayOf(2, 5, 1), intArrayOf(1, 5, 5), intArrayOf(2, 1, 1))
            // [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
//            val args: Array<IntArray> = arrayOf(intArrayOf(1, 0, 0, 0, 0, 0, 1), intArrayOf(2, 0, 0, 0, 0, 3, 0), intArrayOf(2, 0, 9, 0, 0, 0, 0), intArrayOf(0, 3, 0, 5, 4, 0, 0), intArrayOf(1, 0, 2, 3, 0, 0, 6))


            // [[8,8,10,9,1,7],[8,8,1,8,4,7],[8,6,10,3,7,7],[3,0,9,3,2,7],[6,8,9,4,2,5],[1,1,5,8,8,1],[5,6,5,2,9,9],[4,4,6,2,5,4],[4,4,5,3,1,6],[9,2,2,1,9,3]]
            // [[4,7,5,0,10,8,6],[7,8,1,4,3,8,8],[3,2,9,3,4,8,10],[5,4,1,9,9,8,8],[3,6,8,0,10,4,5],[1,9,1,9,5,7,5],[1,4,9,2,5,4,3],[8,9,6,9,10,2,7],[3,2,1,0,3,1,6],[4,2,2,3,8,0,1]]

            val args: Array<IntArray> = arrayOf(intArrayOf(8, 8, 10, 9, 1, 7), intArrayOf(8, 8, 1, 8, 4, 7), intArrayOf(8, 6, 10, 3, 7, 7), intArrayOf(3, 0, 9, 3, 2, 7), intArrayOf(6, 8, 9, 4, 2, 5), intArrayOf(1, 1, 5, 8, 8, 1), intArrayOf(5, 6, 5, 2, 9, 9), intArrayOf(4, 4, 6, 2, 5, 4), intArrayOf(4, 4, 5, 3, 1, 6), intArrayOf(9, 2, 2, 1, 9, 3))


            //[[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
//            val args: Array<IntArray> = arrayOf(intArrayOf(3, 1, 1), intArrayOf(2, 5, 1))

            var start1 = System.currentTimeMillis()
            println(Solution_1463().cherryPickup1(args))
            var costTime = System.currentTimeMillis() - start1
            println("costTime1=$costTime")

            val start2 = System.currentTimeMillis()
            println(Solution_1463().cherryPickup(args))
            val costTime1 = System.currentTimeMillis() - start2
            println("costTime=$costTime1")

            val start3 = System.currentTimeMillis()
            println(Solution_kotlin().cherryPickup(args))
            val costTime2 = System.currentTimeMillis() - start3
            println("costTime=$costTime2")
        }
    }

}

internal class Solution_sample {
    fun cherryPickup(grid: Array<IntArray>): Int {
        val m = grid.size
        val n: Int = grid[0].size
        var f = Array(n) { IntArray(n) }
        var g = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            Arrays.fill(f[i], -1)
            Arrays.fill(g[i], -1)
        }
        f[0][n - 1] = grid[0][0] + grid[0][n - 1]
        for (i in 1 until m) {
            for (j1 in 0 until n) {
                for (j2 in 0 until n) {
                    var best = -1
                    for (dj1 in j1 - 1..j1 + 1) {
                        for (dj2 in j2 - 1..j2 + 1) {
                            if (dj1 >= 0 && dj1 < n && dj2 >= 0 && dj2 < n && f[dj1][dj2] != -1) {
                                best = Math.max(best, f[dj1][dj2] + if (j1 == j2) grid[i][j1] else grid[i][j1] + grid[i][j2])
                            }
                        }
                    }
                    g[j1][j2] = best
                }
            }
            val temp = f
            f = g
            g = temp
        }
        var ans = 0
        for (j1 in 0 until n) {
            for (j2 in 0 until n) {
                ans = Math.max(ans, f[j1][j2])
            }
        }
        return ans
    }
}


class Solution_kotlin {
    fun cherryPickup(grid: Array<IntArray>): Int {
        var h = grid.size
        var w = grid[0].size
        var tempY = intArrayOf(-1, 0, 1)
        var dp = Array(h, { Array(w, { IntArray(w, { -1 }) }) })
        dp[0][0][w - 1] = grid[0][0]
        if (w - 1 != 0) {
            dp[0][0][w - 1] = grid[0][0] + grid[0][w - 1]
        }
        for (i in (0 until h - 1)) {
            for (x in (0 until w)) {
                for (y in (0 until w)) {
                    if (dp[i][x][y] != -1) {
                        for (z1 in (0 until 3)) {
                            var xx = x + tempY[z1]
                            if (xx > w - 1 || xx < 0) continue
                            for (z2 in (0 until 3)) {
                                var yy = y + tempY[z2]
                                if (yy > w - 1 || yy < 0) continue
                                var t = grid[i + 1][xx]
                                if (xx != yy) t += grid[i + 1][yy]
                                dp[i + 1][xx][yy] = Math.max(dp[i + 1][xx][yy], dp[i][x][y] + t)
                            }
                        }
                    }
                }
            }
        }
        var max = 0
        for (r in dp[h - 1]) {
            for (rr in r) {
                max = Math.max(rr, max)
            }
        }
        return max
    }
}
