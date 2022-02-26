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
class Solution_1305 {
    fun getAllElements(root1: TreeNode?, root2: TreeNode?): List<Int> {

        if (root1 == null && root2 == null) {
            return emptyList()
        }

        val list = mutableListOf<Int>()
        getAllFromTree(list, root1)
        getAllFromTree(list, root2)
        list.sort()
        return list
    }

    fun getAllFromTree(list: MutableList<Int>, treeNode: TreeNode?) {
        if (treeNode != null) {
            list.add(treeNode.`val`)
            getAllFromTree(list, treeNode.left)
            getAllFromTree(list, treeNode.right)
        }
    }
}