import java.util.*


/**
跳格子

arr = [100,-23,-23,404,100,23,23,23,3,404]

从index=1 跳到index=n的最小步数。

跳转的可能性有，
i+1 i-1 以及 arr[i] = arr[j]的点。


思路：
1、遍历 找到value相同的下标。
2、BFS搜索

 *
 */


class Solution_1345 {
    fun minJumps(arr: IntArray): Int {
        val n = arr.size
        val indicesOfValue = HashMap<Int, LinkedList<Int>>()
        for (i in 0 until n) {
            indicesOfValue.computeIfAbsent(arr[i]) { x: Int -> LinkedList() }.add(i)
        }
        val visited: MutableSet<Int> = HashSet()
        val queue: Queue<Int> = LinkedList()
        queue.add(0)
        visited.add(0)
        var count = 0

        while (!queue.isEmpty()) {
            var size = queue.size
            for (i in 0 until size) {
                val pop = queue.poll()
                if (pop == arr.size - 1) {
                    return count
                }
                if (pop > 0 && visited.add(pop - 1)) {
                    queue.add(pop - 1)
                }
                if (pop < arr.size - 1 && visited.add(pop + 1)) {
                    queue.add(pop + 1);
                }
                if (indicesOfValue.containsKey(arr[pop])) {
                    for (index in indicesOfValue[arr[pop]]!!) {
                        if (visited.add(index)) {
                            queue.add(index)
                        }
                    }
                    indicesOfValue.remove(arr[pop]) //防止死循环
                }
            }
            count++
        }
        return -1
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arr = intArrayOf(100, -23, -23, 404, 100, 23, 23, 23, 3, 404)
            println("____" + Solution_1345().minJumps(arr))
        }
    }
}