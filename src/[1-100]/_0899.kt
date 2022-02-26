/**
 *
0899
给出了一个由小写字母组成的字符串 S。然后，我们可以进行任意次数的移动。

在每次移动中，我们选择前 K 个字母中的一个（从左侧开始），将其从原位置移除，并放置在字符串的末尾。

返回我们在任意次数的移动之后可以拥有的按字典顺序排列的最小字符串。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/orderly-queue
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

输入：S = "cba", K = 1
输出："acb"
解释：
在第一步中，我们将第一个字符（“c”）移动到最后，获得字符串 “bac”。
在第二步中，我们将第一个字符（“b”）移动到最后，获得最终结果 “acb”。

输入：S = "baaca", K = 3
输出："aaabc"
解释：
在第一步中，我们将第一个字符（“b”）移动到最后，获得字符串 “aacab”。
在第二步中，我们将第三个字符（“c”）移动到最后，获得最终结果 “aaabc”。


思路: 脑筋急转弯。

只要k>1 意味这前后可以自由交换顺序 且不限制次数的话 可以组成最小字符串。
针对性考虑 k=1的情况， 这时候需要遍历起止位置结束位置，查询最小值，返回。

 */
class Solution_0899 {
    fun orderlyQueue(s: String, k: Int): String {
        if (s.length == 1 || s.isEmpty()) {
            return s
        }
        if (k > 1) {
            val arr = s.toCharArray()
            arr.sort()
            return String(arr)
        }
        var minString = s
        for (i in 1 until s.length) {
            val tem = s.substring(i) + s.substring(0, i)
            if (tem < minString) {
                minString = tem
            }
        }
        return minString
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("___" + Solution_0899().orderlyQueue("cba", 1))
            println("___" + Solution_0899().orderlyQueue("baaca", 3))
        }
    }
}