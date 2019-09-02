import java.util.*


/**
17. Letter Combinations of a Phone Number

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].


9宫格数字键盘 输出所有组合。

 */


// 思路每多一个数字 之前的strArr 多对于的遍历。

class Solution_0017 {
    fun letterCombinations(digits: String): List<String> {
        var list = ArrayList<String>()
        if (digits.isEmpty()) return list

        val map = HashMap<Char, Array<String>>()
        map['2'] = arrayOf("a", "b", "c")
        map['3'] = arrayOf("d", "e", "f")
        map['4'] = arrayOf("g", "h", "i")
        map['5'] = arrayOf("j", "k", "l")
        map['6'] = arrayOf("m", "n", "o")
        map['7'] = arrayOf("p", "q", "r", "s")
        map['8'] = arrayOf("t", "u", "v")
        map['9'] = arrayOf("w", "x", "y", "z")

        val firstArr = map[digits[0]]
        for (s in firstArr!!) {
            list.add(s)
        }
        for (i in 1..(digits.length-1)) {
            val newList = ArrayList<String>()
            val c = digits[i]
            for (str in (map.get(c))!!) {
                for (inlist in list) newList.add(inlist + str)
            }
            list = newList
        }
        return list
    }
}