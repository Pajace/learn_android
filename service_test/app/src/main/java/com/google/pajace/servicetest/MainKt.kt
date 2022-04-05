package com.google.pajace.servicetest

import kotlin.reflect.KProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class MainKt {}

suspend fun printDot() {
  println(".")
  delay(1000)
}

fun test() {
  val job = Job()
  val scope = CoroutineScope(job)
  scope.launch {
    // process logic
  }
  job.cancel()
}

fun main() {
  // GlobalScope.launch {
  //   println("codes run in coroutine scope")
  //   delay(1500)
  //   println("codes run in coroutine scope finished")
  // }
  // Thread.sleep(1000)

  // val start = System.currentTimeMillis()
  // runBlocking { repeat(100000) { launch { println(".") } } }
  // val end = System.currentTimeMillis()
  // println(end - start)

  // runBlocking {
  //   val start = System.currentTimeMillis()
  //   val result1 =
  //       async {
  //             delay(1000)
  //             5 + 5
  //           }
  //           .await()
  //   val result2 =
  //       async {
  //             delay(1000)
  //             4 + 6
  //           }
  //           .await()
  //
  //   println("result is ${result1 + result2}")
  //   val end = System.currentTimeMillis()
  //   println("cost ${end - start}")
  // }

  // runBlocking {
  //   val start = System.currentTimeMillis()
  //   val deferred1 = async {
  //     delay(1000)
  //     5 + 5
  //   }
  //   val deferred2 = async {
  //     delay(1000)
  //     6 + 4
  //   }
  //   println("result is ${deferred1.await() + deferred2.await()}")
  //   val end = System.currentTimeMillis()
  //   println("cost ${end - start}")
  // }

  runBlocking {
    val start = System.currentTimeMillis()
    val result1 =
        withContext(Dispatchers.Default) {
          delay(1000)
          5 + 5
        }
    val result2 =
        withContext(Dispatchers.Default) {
          delay(1000)
          6 + 4
        }

    val end = System.currentTimeMillis()
    println(result1 + result2)
    println("cost is ${end-start}")
  }
}

class MySet<T>(val helperSet: HashSet<T>) : Set<T> {
  override val size: Int
    get() = helperSet.size

  override fun contains(element: T) = helperSet.contains(element)

  override fun containsAll(elements: Collection<T>) = helperSet.containsAll(elements)

  override fun isEmpty() = helperSet.isEmpty()

  override fun iterator() = helperSet.iterator()
}

class MySet1<T>(private val helperSet: HashSet<T>) : Set<T> by helperSet {
  fun helloWorld() = println("Hello world, my set 1")

  override fun isEmpty() = false
}

class MyClass {

  var p by MyDelegate()
}

class MyDelegate {
  var propValue: Any? = null

  operator fun getValue(myClass: MyClass, property: KProperty<*>): Any? {
    return propValue
  }

  operator fun setValue(myClass: MyClass, prop: KProperty<*>, value: Any?) {
    this.propValue = value
  }
}
