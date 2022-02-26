/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

class Solution_0141 {
    fun hasCycle(head: ListNode?): Boolean {
        if (head?.next == null) {
            return false
        }
        var fastNode = head.next?.next
        var slowNode = head.next

        while (fastNode != null && slowNode != null) {
            if (fastNode == slowNode) {
                return true
            }
            slowNode = slowNode.next
            fastNode = fastNode?.next?.next
        }
        return false
    }
}