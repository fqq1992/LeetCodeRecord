
class Solution_0191 {
    // you need treat n as an unsigned value
    fun hammingWeight(n:Int):Int {
        var res = 0
        Integer.toBinaryString(n).forEach {
            if (it =='1'){
                res++
            }
        }

        return res
    }
}