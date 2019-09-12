/**

20. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

1.Open brackets must be closed by the same type of brackets.
2.Open brackets must be closed in the correct order.

判断是否闭合。采用入栈出栈的逻辑


 */


class Solution_20 {
    fun isValid(s: String): Boolean {
        if (s.isEmpty()) return true
        else if (s.length % 2 != 0) return false

        var str = ""

        for (i in 0..(s.length - 1)) {
            when (s[i]) {
                '(', '[', '{' -> str += (s[i])
                ')' -> {
                    if (!str.isEmpty() && str.last() == '(') {
                        str = str.dropLast(1)
                    } else {
                        return false
                    }
                }
                ']' -> {
                    if (!str.isEmpty() && str.last() == '[') {
                        str = str.dropLast(1)
                    } else {
                        return false
                    }
                }
                '}' -> {
                    if (!str.isEmpty() && str.last() == '{') {
                        str = str.dropLast(1)
                    } else {
                        return false
                    }
                }
            }
        }
        return str.isEmpty()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var obj = Solution_20()
            print(obj.isValid("(){}"))
            print("()".dropLast(1))
            print("(".dropLast(1))
        }
    }
}