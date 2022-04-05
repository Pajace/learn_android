
inline fun <reified T> getGenericType() = T::class.java

fun main(args: Array<String>) {
  println("Hello World!")

  // Try adding program arguments via Run/Debug configuration.
  // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
  println("Program arguments: ${args.joinToString()}")

  val result1 = getGenericType<String>()
  val result2 = getGenericType<Int>()

  println("result1 is $result1")
  println("result2 is $result2")
}