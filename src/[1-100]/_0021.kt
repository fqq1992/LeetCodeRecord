/**
21. Merge Two Sorted Lists

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4


合并 有序链表。

18、19、20 相对简单很多啊。


 */


class Solution_0021 {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null && l2 == null) return null

        var header: ListNode? = null
        var headerNext: ListNode? = null
        var headerL1: ListNode? = l1
        var headerL2: ListNode? = l2

        while (headerL1 != null || headerL2 != null) {
            var l1Value = headerL1?.`val`
            var l2Value = headerL2?.`val`

            var values: Int = Int.MAX_VALUE
            if (l1Value != null && l2Value != null) {
                values = if (l1Value <= l2Value) {
                    headerL1 = headerL1?.next
                    l1Value
                } else {
                    headerL2 = headerL2?.next
                    l2Value
                }
            } else if (l1Value != null) {
                headerL1 = headerL1?.next
                values = l1Value
            } else if (l2Value != null) {
                headerL2 = headerL2?.next
                values = l2Value
            }

            if (values != Int.MAX_VALUE) {
                if (header == null) {
                    header = ListNode(values)
                    headerNext = header
                } else {
                    headerNext?.next = ListNode(values)
                    headerNext = headerNext?.next
                }
            }
        }
        return header
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}