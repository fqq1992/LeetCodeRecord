/**
字符串映射。

Input: pattern = "abba", s = "dog cat cat dog"
Output: true

Input: pattern = "abba", s = "dog cat cat fish"
Output: false


 */
class Solution_0290 {
    fun wordPattern(pattern: String, s: String): Boolean {
        if (pattern.isNullOrEmpty() && s.isNullOrEmpty()) {
            return true
        }
        val strList = s.split(" ")
        if (pattern.length != strList.size) {
            return false
        }

        //记录字符第一次出现的位置
        val patternMap = hashMapOf<Char, Int>()
        val strMap = hashMapOf<String, Int>()

        pattern.forEachIndexed { index, c ->
            if (patternMap.containsKey(c)) {
                if (!strMap.containsKey(strList[index]) || strMap.get(strList[index]) != patternMap[c]) {
                    return false
                }
            } else {
                if (strMap.containsKey(strList[index])) {
                    return false
                }
                patternMap[c] = index
                strMap[strList[index]] = index
            }
        }

        return true
    }
}