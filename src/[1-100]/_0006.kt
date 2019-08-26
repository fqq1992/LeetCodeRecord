/**

6. ZigZag Conversion

And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I

1               7                       13
2          6       8               12      14
3     5               9       11
4                       10


numRows = n;   i in 1..n
i = 1   -> P（n-1） + 2(n-1)
i = n   -> P（n-1） + 2(n-1)
else{
if（down){
P（n-1） + 2(i-1)
}else{
P（n-1） + 2(n-i)
}
}











 *
 */
class Solution_0006 {
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s;
        val builder = StringBuilder()

        for (i in 1..numRows) {
            var isDown: Boolean = false
            var j = i - 1
            while (j < s.length) {
                builder.append(s[j])
                when (i) {
                    1 -> j += 2 * (numRows - 1)
                    numRows -> j += 2 * (numRows - 1)
                    else -> {
                        j += if (!isDown) 2 * (numRows - i) else 2 * (i - 1)
                        isDown = !isDown
                    }
                }
            }
        }
        return builder.toString()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
        }
    }
}