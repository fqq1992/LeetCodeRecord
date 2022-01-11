class Solution_0217 {
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = mutableSetOf<Int>()
        set.forEach {
            set.add(it)
        }
        return set.size != nums.size
    }
}