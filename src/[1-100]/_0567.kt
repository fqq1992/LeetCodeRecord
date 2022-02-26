class Solution_0567 {
    fun checkInclusion(s1: String, s2: String): Boolean {
        val array = IntArray(26)
        s1.forEach {
            array[it - 'a']++
        }

        return false
    }
}