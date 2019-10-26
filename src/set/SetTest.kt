package set

open class SetTest {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val hashSet = HashSet<TestValue>()
//            val set = HashSet<set.TestValue>()
            hashSet.add(TestValue().apply { values = "a" })
            hashSet.add(TestValue().apply { values = "b" })

            println(hashSet.size)

        }
    }
}