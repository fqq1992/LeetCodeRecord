/**
1510. 石子游戏 IV

Alice 和 Bob 两个人轮流玩一个游戏，Alice 先手。

一开始，有 n 个石子堆在一起。每个人轮流操作，正在操作的玩家可以从石子堆里拿走 任意 非零 平方数 个石子。

如果石子堆里没有石子了，则无法操作的玩家输掉游戏。

给你正整数 n ，且已知两个人都采取最优策略。如果 Alice 会赢得比赛，那么返回 True ，否则返回 False 。


输入：n = 1
输出：true
解释：Alice 拿走 1 个石子并赢得胜利，因为 Bob 无法进行任何操作。

输入：n = 2
输出：false
解释：Alice 只能拿走 1 个石子，然后 Bob 拿走最后一个石子并赢得胜利（2 -> 1 -> 0）。

输入：n = 17
输出：false
解释：如果 Bob 采取最优策略，Alice 无法赢得胜利。

提示：1 <= n <= 10^5

思路：
1、遍历
2、如果 存在f(i - k*k) == false 则f(i)肯定等于true 反之则对方赢。

 */
class Solution_1510 {
    fun winnerSquareGame(n: Int): Boolean {
        if (n == 1) {
            return true
        }
        val arr = BooleanArray(n + 1)

        arr[1] = true
        arr[2] = false

        for (i in 3..n) {
            var k = 1
            while ((i - k * k) >= 0) {
                if (!arr[i - k * k]) {
                    arr[i] = true
                    break
                }
                k++
            }
        }
        return arr[n]
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("----" + Solution_1510().winnerSquareGame(1))
            println("----" + Solution_1510().winnerSquareGame(2))
            println("----" + Solution_1510().winnerSquareGame(3))
            println("----" + Solution_1510().winnerSquareGame(7))
        }
    }
}
