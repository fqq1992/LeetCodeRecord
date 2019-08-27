/**

Given a 32-bit signed integer, reverse digits of an integer.

Input: 120
Output: 21















 */



class Solution_0007 {

    fun reverse(x: Int): Int {
        if (x == 0) return 0
        var sign = if (x > 0) 1 else -1
        var xValue = Math.abs(x)
        var value = 0
        while (xValue >= 1) {
            if (value > Int.MAX_VALUE / 10) return 0
            value = xValue % 10 + value * 10
            xValue = (xValue / 10)
        }
        return if (value * sign < Int.MIN_VALUE) 0 else value * sign
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = Solution_0007()
            println(obj.reverse(1534236469))
            println(obj.reverse(1200))
            println(obj.reverse(-1200))
            println(obj.reverse(-4587))
        }
    }
}