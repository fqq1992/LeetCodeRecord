import java.util.*

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution_0142 {
    fun detectCycle(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return null
        }
        val map = hashMapOf<ListNode, Int>()
        var curNode = head
        var index = 0
        while (curNode != null) {
            if (map.containsKey(curNode)) {
                return curNode
            }
            map.put(curNode, index)
            curNode = curNode.next
            index++
        }
        return null
    }
}