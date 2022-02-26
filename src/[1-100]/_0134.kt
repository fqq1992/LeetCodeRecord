/**
在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。

你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。

如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。

说明: 

如果题目有解，该答案即为唯一答案。
输入数组均为非空数组，且长度相同。
输入数组中的元素均为非负数。

 */

class Solution_0134 {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        val n = gas.size

        var sum = 0
        for (j in 0 until n) sum += gas.get(j) - cost.get(j)

        if (sum < 0) {
            return -1
        }

        var i = 0
        while (i < n) {
            if (gas[i] < cost[i]) {
                i++
                continue
            }
            var j = i
            var remain: Int = gas[i]

            //当前剩下的油是否可以到达下一个点。
            while (remain - cost[j] >= 0) {
                //再往下一个点
                remain = remain - cost[j] + gas[(j + 1) % n]
                j = (j + 1) % n
                if (j == i) {
                    return i
                }
            }
            i = j + 1
        }
        //不可以
        return -1
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_0134().canCompleteCircuit(intArrayOf(1, 2, 3, 4, 5), intArrayOf(3, 4, 5, 1, 2)))
        }
    }
}