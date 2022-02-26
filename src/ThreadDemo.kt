import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

class ThreadDemo {

    companion object {
        var i = 0
        @JvmStatic
        fun main(args: Array<String>) {
//            val lock = Object()
//            var threadA = Thread(Runnable {
//                while (true) {
//                    synchronized(lock) {
//                        if (i > 0) {
//                            i--
//                            println("--$i")
//                            lock.notifyAll()
//                        } else {
//                            lock.wait()
//                        }
//                    }
//                }
//
//            })
//
//            var threadB = Thread(Runnable {
//                synchronized(lock) {
//                    while (true) {
//                        synchronized(lock) {
//                            if (i <= 0) {
//                                i++
//                                lock.notifyAll()
//                                println("++$i")
//                            } else {
//                                lock.wait()
//                            }
//                        }
//                    }
//                }
//
//            })
//
//            threadA.start()
//            threadB.start()

            val blockQueue = ArrayBlockingQueue<Int>(1)
            var i = AtomicInteger(1)
            val threadA = Thread(Runnable {
                while (true) {
                    blockQueue.put(i.getAndAdd(1))
                    Thread.sleep(50)
                    println("____A: size" + blockQueue.size)
                    println("____A: peek" + blockQueue.peek())
                }
            })
            val threadB = Thread(Runnable {
                while (true) {
                    println("____B take:" + blockQueue.take())
                    Thread.sleep(50)
                    println("____B size:" + blockQueue.size)
                    i.addAndGet(-1)
                }
            })

            threadA.start()
            threadB.start()

        }
    }
}

data class Node(val value: Int, var next: Node? = null)

class Test {
    fun mergeTwoNodeList(node1: Node?, node2: Node?): Node? {
        if (node1 == null && node2 == null) {
            return null
        }
        val headNode: Node = Node(-1, null)

        var curHeader = headNode
        var curNode1 = node1
        var curNode2 = node2
        if (curNode1 != null) {
            curNode1.value
        }

        while (curNode1 != null || curNode2 != null) {
            if (curNode1?.value ?: 0 > curNode2?.value ?: 0) {
                curHeader.next = curNode1
                curNode1 = curNode1?.next
            } else {
                curHeader.next = curNode2
                curNode2 = curNode2?.next
            }
            curHeader = curHeader.next!!
        }

        return headNode.next
    }
}









