import java.util.*

/**

402. 移掉 K 位数字
给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。


示例 1 ：

输入：num = "1432219", k = 3
输出："1219"
解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
示例 2 ：

输入：num = "10200", k = 1
输出："200"
解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
示例 3 ：

输入：num = "10", k = 2
输出："0"
解释：从原数字移除所有的数字，剩余为空就是 0 。


提示：

1 <= k <= num.length <= 105
num 仅由若干位数字（0 - 9）组成
除了 0 本身之外，num 不含任何前导零


思路：单调栈。


 */

class Solution_0402 {
    fun removeKdigits(num: String, k: Int): String {
        if (num.length <= k) {
            return "0"
        }
        val stack = Stack<Int>()
        var index = 0

        val remain = num.length - k

        while (index < num.length) {
            var curValue = Integer.parseInt(num[index].toString())
            if (stack.isEmpty()) {
                stack.push(curValue)
            } else {
                while (stack.isNotEmpty() && stack.peek() > curValue && (stack.size + 1 + (num.length - 1 - index)) > remain) {
                    stack.pop()
                }
                stack.push(curValue)
            }
            index++
        }
        val strBuffer = StringBuffer(stack.size)
        var tempIndex = 0
        stack.forEach {
            if (strBuffer.isEmpty() && it == 0) {
            } else if (tempIndex >= remain) {
                return@forEach
            } else {
                strBuffer.append(it)
            }
            tempIndex++
        }

        if (strBuffer.isEmpty()){
            return "0"
        }
        return strBuffer.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            println(Solution_0402().removeKdigits("3151421", 3))
//            println(Solution_0402().removeKdigits("112", 1))
            println(Solution_0402().removeKdigits("10200", 1))
        }
    }
}