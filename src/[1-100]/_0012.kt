/**
12. Integer to Roman


13. Roman to Integer 不在此体现。

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

Input: 3        Output: "III"
Input: 9        Output: "IX"
Input: 1994     Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

本质是进制换算。
Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 */


class Solution_0012 {
    fun intToRoman(num: Int): String {
        var nums = num
        val N = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        val M = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
        val sb = StringBuilder()
        for (i in 0..(N.size - 1)) {
            while (nums >= N[i]) {
                sb.append(M[i])
                nums -= N[i]
            }
        }
        return sb.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = Solution_0012()
            print(obj.intToRoman(3544))
        }
    }
}