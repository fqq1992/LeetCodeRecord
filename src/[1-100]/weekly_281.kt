import kotlin.Comparator

class Solution_6012 {
    fun countEven(num: Int): Int {
        var res = 0
        for (i in 1..num) {
            var result = i % 10
            var temp = i / 10
            while (temp > 0) {
                result += temp % 10
                temp = temp / 10
            }

            if (result % 2 == 0) {
                res++
            }
        }

        return res
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println(Solution_6012().countEven(11))
        }
    }
}


/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
class Solution_6013 {
    fun mergeNodes(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }
        val resultNode = ListNode(-1)
        var curNode = resultNode

        var startNode = head
        var endNode = head.next

        while (endNode!!.next != null) {
            if (endNode.`val` == 0) {
                var temp = 0
                while (startNode != endNode) {
                    temp += startNode!!.`val`
                    startNode = startNode!!.next
                }
                curNode.next = ListNode(temp)
                curNode = curNode.next!!

            }
            endNode = endNode.next
        }
        return resultNode.next
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arr = intArrayOf(0, 3, 1, 0, 4, 5, 2, 0)

            val rootNode = ListNode(0)
            var curNode = rootNode

            arr.forEach {
                curNode.next = ListNode(it)
                curNode = curNode.next!!
            }


            println(Solution_6013().mergeNodes(rootNode.next))
        }
    }
}

class Solution_6014 {
    fun repeatLimitedString(s: String, repeatLimit: Int): String {
        val hashMap = mutableMapOf<Char, Int>()

        s.forEach {
            hashMap.put(it, hashMap.getOrDefault(it, 0) + 1)
        }
        val sortMap = hashMap.toSortedMap(Comparator<Char> { p0, p1 ->
            if (p0 < p1) {
                1
            } else if (p0 == p1) {
                0
            } else {
                -1
            }
        })

        val stringBuffer = StringBuffer()
        stringBuffer.append("_")

        if (sortMap.size == 1) {
            return chartToString(sortMap.firstKey(), sortMap[sortMap.firstKey()]!!, repeatLimit)
        }

        while (sortMap.size > 1) {
            if (sortMap.firstKey() == stringBuffer.last()) {
                val secondKey = sortMap.keys.toList()[1]
                stringBuffer.append(secondKey)
                if (sortMap[secondKey]!! == 1) {
                    sortMap.remove(secondKey)
                } else {
                    sortMap.put(secondKey, sortMap[secondKey]!! - 1)
                }
            } else {
                val tempStr = chartToString(sortMap.firstKey(), sortMap[sortMap.firstKey()]!!, repeatLimit)
                stringBuffer.append(tempStr)
                if (sortMap[sortMap.firstKey()]!! == tempStr.length) {
                    sortMap.remove(sortMap.firstKey())
                } else {
                    sortMap.put(sortMap.firstKey(), sortMap[sortMap.firstKey()]!! - tempStr.length)
                }
            }
        }

        if (sortMap.isNotEmpty() && sortMap.firstKey() != stringBuffer.last()) {
            stringBuffer.append(chartToString(sortMap.firstKey(), sortMap[sortMap.firstKey()]!!, repeatLimit))
        }

        return stringBuffer.substring(1).toString()
    }


    fun chartToString(char: Char, curSize: Int, repeatLimit: Int): String {
        val realSize = Math.min(curSize, repeatLimit)
        val buffer = StringBuffer(realSize)
        for (i in 0 until realSize) {
            buffer.append(char)
        }
        return buffer.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            "cczazcc"
//            3

            println(Solution_6014().repeatLimitedString("cczazcc", 3))
        }
    }
}


class Solution_6015 {
    fun coutPairs(nums: IntArray, k: Int): Long {

        if (k == 1){
            val n = nums.size
            return (n * (n-1).toLong())/2
        }

        var res = 0L
        val mutableMap = mutableMapOf<Int, Int>()

        nums.forEachIndexed { index, value ->
            val gcdTemp = getGCD(value, k)
            mutableMap.put(gcdTemp, mutableMap.getOrDefault(gcdTemp, 0) + 1)
        }
        val sortedKeys = mutableMap.toSortedMap().keys.toList()
        sortedKeys.forEachIndexed { index, value ->
            var curIndex = index + 1

            if (value != k) {
                if (value * value % k == 0) {
                    val count = mutableMap[value]!!
                    res += count * (count - 1) / 2
                }

                while (curIndex < sortedKeys.size) {
                    val k1 = value.toLong()
                    val k2 = sortedKeys[curIndex].toLong()
                    val temp = k1 * k2 % k
                    if (temp == 0L) {
                        res = res + mutableMap[k1.toInt()]!!.toLong() * mutableMap[k2.toInt()]!!
                    }
                    curIndex++
                }
            } else {
                val count = mutableMap[value]!!
                res += count * (count - 1) / 2
            }
        }
        return res
    }

    fun getGCD(num1: Int, num2: Int): Int {
        if (num1 < num2) {
            return getGCD(num2, num1)
        }

        val temp = num1 % num2
        if (temp == 0) {
            return num2
        }
        return getGCD(num2, temp)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            println(Solution_6015().coutPairs(intArrayOf(1, 2, 3, 4, 5), 2))

            //[8,10,2,5,9,6,3,8,2]
            //6

//            println(Solution_6015().coutPairs(intArrayOf(8, 10, 2, 5, 9, 6, 3, 8, 2), 2))

            //[10,10,6,9,3,7,4,3,8,8]
            //4
            println(Solution_6015().coutPairs(intArrayOf(10, 10, 6, 9, 3, 7, 4, 3, 8, 8), 4))
        }
    }
}