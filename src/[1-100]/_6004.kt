class Solution_6004 {
    fun countOperations(num1: Int, num2: Int): Int {
        if (num1 == num2) {
            return 1
        }
        var temp1 = num1
        var temp2 = num2
        var res = 0
        while (temp1 != 0 && temp2 != 0) {
            if (temp1 >= temp2) {
                temp1 -= temp2
            } else {
                temp2 -= temp1
            }
            res++
        }
        return res
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_6004().countOperations(2, 3))
        }
    }
}