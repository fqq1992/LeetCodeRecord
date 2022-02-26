/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution_0206 {
    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        var cursorNode = head
        var prev: ListNode? = null

        while (cursorNode != null) {
            val temp = cursorNode.next
            cursorNode.next = prev
            prev = cursorNode
            cursorNode = temp
        }

        return prev
    }
}