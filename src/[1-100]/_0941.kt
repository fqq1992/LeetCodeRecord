/**

941. Valid Mountain Array

Given an array of integers arr, return true if and only if it is a valid mountain array.

Recall that arr is a mountain array if and only if:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]

注意题目 不包含相等的条件


 */
class Solution_0941 {
    fun validMountainArray(arr: IntArray): Boolean {
        var startIndex = 1
        var state = 0

        while (startIndex < arr.size) {
            if (arr[startIndex] > arr[startIndex - 1]) {
                if (state == 0) {
                    state = 1
                } else if (state < 0) {
                    return false
                }
            } else if (arr[startIndex] == arr[startIndex - 1]) {
                return false
            } else {
                if (state == 0) {
                    return false
                } else if (state > 0) {
                    state = -1
                }
            }
            startIndex++
        }

        return state < 0
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_0941().validMountainArray(intArrayOf(1, 1, 1, 1, 1, 1, 1, 2, 1)))
        }
    }
}