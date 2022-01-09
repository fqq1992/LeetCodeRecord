class Solution_1041 {

    fun isRobotBounded(instructions: String): Boolean {
        //机器人当前站位点
        var x = 0
        var y = 0

        var i = 0 //记录当前方向
        val d = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0))

        instructions.forEach {
            when (it) {
                'L' -> {
                    i = (i + 3) % 4
                }
                'R' -> {
                    i = (i + 1) % 4
                }
                else -> {
                    x += d[i][0]
                    y += d[i][1]
                }
            }
        }

        println("x=$x; y=$y i=$i")
        if (x == 0 && y == 0) {
            return true
        }
        if (i == 0) {
            return false
        }
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_1041().isRobotBounded("GG"))
        }
    }
}