import java.util.*
import kotlin.collections.ArrayList

class Solution_5655 {
    fun largestSubmatrix(matrix: Array<IntArray>): Int {
        var minRow = 0
        val minColuwn = 0
        val mList: ArrayList<Int> = ArrayList(matrix.size)
        for (matrix in matrix) {
            var temp = 0

            matrix.forEachIndexed { index, i ->


                if (index >= mList.size) {
                    mList.add(0)
                }
                if (i == 1) {
                    temp++
                    mList[index]++
                }

            }
            if (temp > 0 && (minRow == 0 || temp < minRow)) {
                minRow = temp
            }
        }
        mList.sort()
        var coluwn = 0
        if (mList.isNotEmpty()) {
            coluwn = mList.last()
        }
        return minRow * coluwn
    }
}