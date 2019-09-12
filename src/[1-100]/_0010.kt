/**

10. Regular Expression Matching

'.' Matches any single character.
'*' Matches zero or more of the preceding element.


Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false


基本就是自己用代码实现一下正则表达式的匹配规则。

首先我们卖个机灵
提交一手 return s.matches(Regex(p))。会发现 竟然通过了。


下面我们来想一下具体怎么写：
第一步 我首先想到的是遍历A的同时 在B中寻找b的子集，不存在则返回false 存在则继续截取A(i+1);
这样则需要同时记录AB的循环下标。。尝试了一下 写着有点乱。

写的过程中赶脚递归可能写起来更顺手，那么问题来了 递归的话 怎么判断
isMatch(s.substring(0, s.length - 1), p) && {新条件}
新条件则需要对比之后的位数。那么问题来了，需要记录下来，匹配到第一位char的index.












 */


class Solution_0010 {
    fun isMatch(s: String, p: String): Boolean {
        if (p.isEmpty()) return s.isEmpty()

        // 1、判断第一位是否匹配。
        val firstMatch = !s.isEmpty() && (p[0] === s[0] || p[0] === '.')

        // 如果p第二位是*。 要么s,跟正则第三位以后的匹配；要么，首字母匹配 且同时 s的从第二个字母开始也匹配
        return if (p.length >= 2 && p[1] == '*') {
            isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p))
        } else {
            //反之 则要求 首字母相同,且
            firstMatch && isMatch(s.substring(1), p.substring(1))
        }



        if (s.length == 1) {
            return p.contains(s) || p.contains(".")
        } else {
            if (isMatch(s.substring(0, s.length - 1), p)) {
                return false
            } else {
                if (s[s.length - 1].equals(s.length - 2)) {

                } else {
                    return false
                }
            }

        }
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var obj = Solution_0010()
            print(obj.isMatch("ad", "acd"))
        }
    }
}