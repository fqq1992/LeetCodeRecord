/**

19. Remove Nth Node From End of List.

Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.


1。第一反应首先是遍历链表 获取总数，然后找出正数第几个。


 */

class Solution_0018 {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null
        var i = 0
        var returnHeader = head
        var cur = head
        while (cur?.next != null) {
            cur = cur.next
            i++
        }

        /*确定要删除的节点为正数第几个节点*/
        var index = i - n + 1
        var ptr = head
        var j = 0
        /*找到对应节点。*/

        while (j + 1 < index) {
            ptr = ptr?.next
            j++
        }
        /*删除操作，判断要删除的节点是否为第一个节点*/
        return if (index != 0) {
            ptr?.next = ptr?.next?.next
            returnHeader
        } else {
            returnHeader = returnHeader.next
            returnHeader
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val header = ListNode(1)
            var header1 = ListNode(2)
            var header2 = ListNode(3)
            var header3 = ListNode(4)
            var header4 = ListNode(5)
            header.next = header1
            header1.next = header2
            header2.next = header3
            header3.next = header4


            var obj = Solution_0018()
            print(obj.removeNthFromEnd(header,2))

        }
    }
}


class ListNode(var `val`: Int) {
    var next: ListNode? = null
    override fun toString(): String {
        return `val`.toString() + next.let { next.toString() }
    }
}

