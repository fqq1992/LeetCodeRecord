/**

给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。

示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。

提示:

1 <= s.length, p.length <= 3 * 10^4
s 和 p 仅包含小写字母



 */
class Solution_0438 {

    fun findAnagrams(s: String, p: String): List<Int> {
        if (s.isEmpty() || p.isEmpty() || s.length < p.length) {
            return emptyList()
        }
        val intArray = IntArray(26)
        p.forEach {
            intArray[it - 'a']++
        }

        var diffSize = p.length
        var startIndex = 0
        var windowSize = p.length
        var endIndex = startIndex + windowSize

        val resultList = mutableListOf<Int>()

        while (endIndex <= s.length) {
            val tempArr = intArray.clone()
            var tempBreakIndex = -1

            s.substring(startIndex, endIndex).forEachIndexed { index, it ->
                if (tempArr[it - 'a'] == 0) {
                    tempBreakIndex = index + startIndex
                    return@forEachIndexed
                }
                tempArr[it - 'a']--
                if (tempArr[it - 'a'] >= 0) {
                    diffSize--
                }
            }

            if (diffSize == 0) {
                resultList.add(startIndex)
            }
            if (tempBreakIndex >= 0) {
                startIndex = tempBreakIndex + 1
            } else {
                startIndex++
            }
            endIndex = startIndex + windowSize
            diffSize = p.length
        }

        return resultList
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_0438().findAnagrams("cbaebabacd", "abc"))
//            println(Solution_0438().findAnagrams("abab", "ab"))
//            println(Solution_0438().findAnagrams("baa", "aa"))
        }
    }
}