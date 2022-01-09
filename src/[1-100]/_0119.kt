class Solution_0119 {
    fun getRow(rowIndex: Int): List<Int> {
        if (rowIndex == 0) {
            return listOf(1)
        }

        val list = IntArray(rowIndex+1)
        list[0] = 1
        for (i in 1..rowIndex) {
            for (j in i downTo 1) {
                list[j] = list[j] + list[j - 1]
            }
        }
        return list.toList()
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            println( Solution_0119().getRow(1))
            println( Solution_0119().getRow(2))
            println( Solution_0119().getRow(3))
            println( Solution_0119().getRow(4))
            println( Solution_0119().getRow(5))
        }
    }
}