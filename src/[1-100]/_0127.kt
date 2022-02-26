/**
127. 单词接龙
字典 wordList 中从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：

每一对相邻的单词只差一个字母。
 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
sk == endWord
给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0 。


示例 1：

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
输出：5
解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
示例 2：

输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
输出：0
解释：endWord "cog" 不在字典中，所以无法进行转换。


提示：

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord、endWord 和 wordList[i] 由小写英文字母组成
beginWord != endWord
wordList 中的所有字符串 互不相同


思路：
1、遍历wordlist 改为有向图，
2、BFS


 */

class Solution_0127 {
    var minSize = 0
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
        if (!wordList.toSet().contains(endWord)) {
            return 0
        }

        val digraphMap = mutableMapOf<Int, MutableSet<Int>>()

        val startList = mutableListOf<Int>()
        var endIndex = -1

        wordList.forEachIndexed { index, s ->
            if (s == endWord) {
                endIndex = index
            }
            if (checkMatch(beginWord, s)) {
                startList.add(index)
            }

            var startIndex = index + 1

            val set = mutableSetOf<Int>()
            while (startIndex < (wordList.size)) {
                if (checkMatch(s, wordList[startIndex])) {
                    set.add(startIndex)
                    digraphMap[startIndex] = digraphMap.getOrDefault(startIndex, mutableSetOf()).also {
                        it.add(index)
                    }
                }
                startIndex++
            }
            digraphMap.put(index, digraphMap.getOrDefault(index, mutableSetOf()).also {
                it.addAll(set)
            })
        }

        if (startList.isEmpty()) {
            return 0
        }
        minSize = wordList.size + 2


        //BFS

//        if (startList.contains(endIndex)) {
//            return 2
//        }

        bfs(endIndex, digraphMap, startList.toMutableSet(), 1)
//        startList.forEach {
//            bfs(it, endIndex, digraphMap, mutableSetOf(it))
//        }

        if (minSize == wordList.size + 2) {
            return 0
        }
        return minSize
    }

    fun dfs(startIndex: Int, endIndex: Int, digraphMap: MutableMap<Int, MutableSet<Int>>, curSet: MutableSet<Int>) {
        if (startIndex == endIndex) {
            curSet.add(endIndex)
            if (curSet.size < minSize) {
                minSize = curSet.size
            }
            return
        }
        digraphMap[startIndex]!!.forEach {
            if (!curSet.contains(it) && curSet.size < minSize) {
                curSet.add(it)
                dfs(it, endIndex, digraphMap, curSet)
                curSet.remove(it)
            }
        }
    }


    private fun bfs(endIndex: Int, digraphMap: MutableMap<Int, MutableSet<Int>>, curSet: MutableSet<Int>, curIndex: Int) {
        if (curIndex >= minSize) {
            return
        }

        val set = mutableSetOf<Int>()
        curSet.forEach {
            if (it == endIndex) {
                if (minSize > curIndex + 1) {
                    minSize = curIndex + 1
                }
            } else {
                set.addAll(digraphMap[it]!!)
            }
        }
        bfs(endIndex, digraphMap, set, curIndex + 1)
    }


    //注意点 必须只一个字符的差异
    fun checkMatch(strA: String, strB: String): Boolean {
        var diffSize = 0

        strA.forEachIndexed { index, c ->
            if (c != strB[index]) {
                diffSize++
            }
            if (diffSize > 1) {
                return false
            }
        }

        if (diffSize == 1) {
            return true
        }
        return false
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            //"hit"
            //"cog"
            //["hot","dot","dog","lot","log","cog"]

            println(Solution_0127().ladderLength("hit", "cog", listOf("hot", "dot", "dog", "lot", "log", "cog")))
            println(Solution_0127().ladderLength("a", "c", listOf("a", "b", "c")))
            println(Solution_0127().ladderLength("lost", "miss", listOf("most", "mist", "miss", "lost", "fist", "fish")))
            println(Solution_0127().ladderLength("leet", "code", listOf("lest", "leet", "lose", "code", "lode", "robe", "lost")))
//
            println(Solution_0127().ladderLength("qa", "sq", listOf("si", "go", "se", "cm", "so", "ph", "mt", "db", "mb", "sb", "kr", "ln", "tm", "le", "av", "sm", "ar", "ci", "ca", "br", "ti", "ba", "to", "ra", "fa", "yo", "ow", "sn", "ya", "cr", "po", "fe", "ho", "ma", "re", "or", "rn", "au", "ur", "rh", "sr", "tc", "lt", "lo", "as", "fr", "nb", "yb", "if", "pb", "ge", "th", "pm", "rb", "sh", "co", "ga", "li", "ha", "hz", "no", "bi", "di", "hi", "qa", "pi", "os", "uh", "wm", "an", "me", "mo", "na", "la", "st", "er", "sc", "ne", "mn", "mi", "am", "ex", "pt", "io", "be", "fm", "ta", "tb", "ni", "mr", "pa", "he", "lr", "sq", "ye")))
        }
    }
}