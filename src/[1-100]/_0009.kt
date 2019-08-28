/**
9. Palindrome Number

Example 1:
Input: 121
Output: true

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.


Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.


思路plan A ：
1、排除负数
2、个位数无敌
3、遍历





 */
class Solution_0009 {
    fun isPalindrome(x: Int): Boolean {
        when {
            x < 0 -> return false
            x < 10 -> return true
            else -> {
                var xStr = x.toString()
                for (i in 0..(xStr.length / 2 - 1)) {
                    if (xStr[i] != xStr[xStr.length - i - 1])
                        return false
                }
                return true
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val obj = Solution_0009()
            print(obj.isPalindrome(123221))
        }
    }
}