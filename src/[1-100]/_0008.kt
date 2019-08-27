/**
8. String to Integer (atoi)

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

Input: "42"
Output: 42

Input: "-4193 with words"
Output: -4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.

Input: "words and 987"
Output: 0


思路 第一反应trim();
但是基于实际考量 trim是对资源有浪费的；不如直接手撸“ ”。

初版思路分
1、null数据直接0
2、剔除前面的空格
3、找到第一个“+” “-”
4、遍历后续字符集判断是否为0-9；记录下标

很快写完了。出现了第一个问题，toInt()方法 异常；数太大了；
这是我选择直接toLong();继续异常 字符串可以无限长；
接着我考虑startIndex 跟 endIndex的位数； 这时候又出现了新的用例。

“   -0000000123”

至此上诉方案只得进化了。
4—pro: 循环遍历数字首位 之前的数字*10+char.





 */

class Solution_0008 {
    fun myAtoi(str: String): Int {
        if (str.isEmpty()) return 0
        var index = 0
        var sign = 1
        while (index < str.length) {
            if (str[index] == (' ')) {
                index++
            } else if (str[index] == ('+')) {
                index++
                sign = 1
                break
            } else if (str[index] == ('-')) {
                index++
                sign = -1
                break
            } else {
                break
            }
        }
        if (index >= str.length) {
            return 0
        }
        var x = 0

        do {
            if (str[index] in '0'..'9') {
                var j = (str[index].toInt() - '0'.toInt())
                if (x > Int.MAX_VALUE / 10 || (x == Int.MAX_VALUE / 10 && j >= Int.MAX_VALUE % 10))
                    return when (sign) {
                        1 -> Int.MAX_VALUE
                        else -> Int.MIN_VALUE
                    }
                x = x * 10 + str[index].toString().toInt()
                index++
            } else {
                break
            }
        } while (index < str.length)

        return x * sign
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var obj = Solution_0008()
//            println(obj.myAtoi("words and 987"))
//            println(obj.myAtoi("   -4193 with words"))
//            println(obj.myAtoi("   -42"))
//            println(obj.myAtoi("-91283472332"))
//            println(obj.myAtoi("2147483646"))
            println(obj.myAtoi("2147483648"))
//            println(obj.myAtoi("  -0000091283472332"))
        }
    }
}