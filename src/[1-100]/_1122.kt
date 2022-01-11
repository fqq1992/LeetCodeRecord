/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution_1122 {
    fun sumRootToLeaf(root: TreeNode?): Int {
        if (root == null) {
            return 0
        }

        val list = mutableListOf<String>()
        dps(root, "", list)
        var res = 0;
        list.forEach {
            println("----$it")
            res += Integer.parseInt(it, 2)
        }
        return res
    }

    fun dps(root: TreeNode, curStr: String, list: MutableList<String>) {
        if (root.right == null && root.left == null) {
            list.add(curStr + root.`val`)
        }
        var res = curStr
        if (root.left != null) {
            res += root.`val`
            dps(root.left!!, res, list)
        }
        res = curStr
        if (root.right != null) {
            res += root.`val`
            dps(root.right!!, res, list)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val root = TreeNode(1)
            val node1 = TreeNode(0)
            val node2 = TreeNode(0)
            val node3 = TreeNode(1)
            val node4 = TreeNode(1)
            val node5 = TreeNode(0)
            val node6 = TreeNode(1)
            root.left = node1
            root.right = node4
            node1.left = node2
            node1.right = node3
            node4.left = node5
            node4.right = node6

            Solution_1122().sumRootToLeaf(root)
        }
    }
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

