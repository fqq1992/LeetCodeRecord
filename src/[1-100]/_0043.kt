import kotlin.math.min

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

//思路1、 merge2个依次过去
//思路2、依次遍历merge
class Solution_0043 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        var endCount = 0
        val map = mutableMapOf<Int, ListNode>()
        val rootNode = ListNode(-1)
        var curNode = rootNode

        lists.forEachIndexed { index, listNode ->
            if (listNode != null) {
                map[index] = listNode
            }
        }

        while (endCount != lists.size) {
            var minIndex = -1
            var minValue = Int.MAX_VALUE
            var tempEndCount = 0
            for (i in lists.indices) {
                if (map[i] != null) {
                    if (map[i]!!.`val` < minValue) {
                        minValue = map[i]!!.`val`
                        minIndex = i
                    }
                } else {
                    tempEndCount++
                }
            }
            if (minIndex >= 0) {
                curNode.next = ListNode(minValue)
                curNode = curNode.next!!
                if (map[minIndex]!!.next != null) {
                    map[minIndex] = map[minIndex]!!.next!!
                } else {
                    map.remove(minIndex)
                }
            }

            endCount = tempEndCount
        }

        return rootNode.next
    }
}