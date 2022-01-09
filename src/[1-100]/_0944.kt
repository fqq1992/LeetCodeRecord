class Solution_0944 {
    fun minDeletionSize(strs: Array<String>): Int {
        var res = 0
        for (i in strs.first().indices) {
            if (!isSort(strs, i))
                res++
        }
        return res
    }

    fun isSort(strs: Array<String>, curIndex: Int): Boolean {
        if (strs.size == 1) {
            return true
        }
        strs.forEachIndexed { index, s ->
            if (index >= 1) {
                if (s[curIndex] < strs[index - 1][curIndex]) {
                    return false
                }
            }
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Solution_0944().minDeletionSize(arrayOf("cba", "daf", "ghi"))
        }
    }
}