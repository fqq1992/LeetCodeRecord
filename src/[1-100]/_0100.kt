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
class Solution_0100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return if (p == null && q == null) {
            return true
        } else if (p?.`val` == q?.`val`) {
            isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
        } else {
            false
        }
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val p = TreeNode(1)
            val q = TreeNode(2)
        }
    }
}