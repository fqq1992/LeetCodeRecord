/**
14 Longest Common Prefix

Input: ["flower","flow","flight"]
Output: "fl"


要求共同前缀。

 *
 */
class Solution_0014 {
    fun longestCommonPrefix(strs: Array<String>): String {
        if (strs.isEmpty()) return ""
        var cPrefix = strs[0]
        for (i in 1..(strs.size - 1)) {
            while (strs[i].indexOf(cPrefix) != 0) {
                cPrefix = cPrefix.substring(0, cPrefix.length - 1)
                if (cPrefix.isEmpty()) return ""
            }
        }
        return cPrefix
    }
}