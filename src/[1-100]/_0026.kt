/**

26 Remove Duplicates from Sorted Array移除数组中重复项

Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.





 */


class Solution_0026 {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size == 1) {
            return 1
        }

        var index = 0

        for (i in 1..(nums.size - 1)) {
            if (i == (nums.size - 1)) {
                if ((nums[index] xor nums[i]) == 0) {
                    return index + 1
                } else {
                    nums[index + 1] = nums[i]
                    return index + 2
                }
            } else {
                if ((nums[index] xor nums[i]) == 0) {

                } else {
                    index++
                    nums[index] = nums[i]
                }
            }
        }

        return index
    }
}