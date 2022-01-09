import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

class Solution_1725 {
    fun countGoodRectangles(rectangles: Array<IntArray>): Int {
        var minSquare = 0
        val mList: ArrayList<Int> = ArrayList(rectangles.size)
        rectangles.forEachIndexed { index, ints -> mList.add(min(ints[0], ints[1])) }
        mList.sort()
        for (i in mList.size - 1 downTo 0) {
            if (mList[i] == mList.last()) {
                minSquare++;
            }
        }
        return minSquare
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Solution_1725()
//            val a = arrayOf([5,8],[3,9],[5,12],[16,5])
//            print((solution.countGoodRectangles(a)))
        }
    }
}