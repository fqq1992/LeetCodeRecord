import java.util.*

/**
22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
"((()))",
"(()())",
"(())()",
"()(())",
"()()()"
]





 */

class Solution {
    fun generateParenthesis(n: Int): List<String> {
        var list = ArrayList<String>()

        generateParenthesis(list, "", 0, 0, n)

        return list
    }

    fun generateParenthesis(lists: ArrayList<String>, string: String, open: Int, close: Int, max: Int) {
        if (string.length == max * 2) {
            lists.add(string)
            return
        }
        if (open < max)
            generateParenthesis(lists, string+"(", open + 1, close, max)
        if (close < open)
            generateParenthesis(lists, string + ")", open, close + 1, max)
    }
}