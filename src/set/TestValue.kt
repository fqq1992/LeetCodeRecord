package set

open class TestValue {
    lateinit var values: String
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TestValue

        if (values != other.values) return false

        return true
    }

    override fun hashCode(): Int {
        return 2
    }

}