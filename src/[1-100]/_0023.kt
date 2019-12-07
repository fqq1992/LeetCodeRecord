/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution_23 {
    fun mergeKLists(lists: Array<ListNode?>): ListNode? {
        if (lists == null || lists.isEmpty()) {
            return null
        } else if (lists.size == 1) {
            return lists[0]
        } else {
            var listNode = mergeNode(lists[0], lists[1])

            if (lists.size > 2) {
                for (i in 2..(lists.size - 1)) {
                    listNode = mergeNode(listNode, lists[i])
                }
            }
            return listNode
        }
    }


    fun mergeNode(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) {
            return l2
        }
        if (l2 == null) {
            return l1
        }

        var firstNode = l1
        var secondNode = l2
        val headNode: ListNode
        if (l1!!.`val` >= l2!!.`val`) {
            headNode = ListNode(l2.`val`)
            secondNode = secondNode!!.next
        } else {
            headNode = ListNode(l1.`val`)
            firstNode = firstNode!!.next
        }


        var curNode = headNode
        while (firstNode != null && secondNode != null) {
            if (firstNode.`val` > secondNode.`val`) {
                curNode.next = secondNode
                secondNode = secondNode.next
                curNode = curNode.next!!
            } else {
                curNode.next = firstNode
                firstNode = firstNode.next
                curNode = curNode.next!!
            }
        }

        if (firstNode != null) {
            curNode.next = firstNode
        } else {
            curNode.next = secondNode
        }

        return headNode
    }
}