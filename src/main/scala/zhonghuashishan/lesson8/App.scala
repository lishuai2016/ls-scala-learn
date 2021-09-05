package zhonghuashishan.lesson8

/**
函数入门之过程、lazy值和异常
1、过程
在Scala中，定义函数时，如果函数体直接包裹在了花括号里面，而没有使用=连接，则函数的返回值类型就是Unit。这样的函数就被称之为过程。过程通常用于不需要返回值的函数。

过程还有一种写法，就是将函数的返回值类型定义为Unit。

def sayHello(name: String) = "Hello, " + name
def sayHello(name: String) { print("Hello, " + name); "Hello, " + name }
def sayHello(name: String): Unit = "Hello, " + name


2、lazy值
在Scala中，提供了lazy值的特性，也就是说，如果将一个变量声明为lazy，则只有在第一次使用该变量时，变量对应的表达式才会发生计算。这种特性对于特别耗时的计算操作特别有用，比如打开文件进行IO，进行网络IO等。

import scala.io.Source._
lazy val lines = fromFile("C://Users//Administrator//Desktop//test.txt").mkString
即使文件不存在，也不会报错，只有第一个使用变量时会报错，证明了表达式计算的lazy特性。

val lines = fromFile("C://Users//Administrator//Desktop//test.txt").mkString
lazy val lines = fromFile("C://Users//Administrator//Desktop//test.txt").mkString
def lines = fromFile("C://Users//Administrator//Desktop//test.txt").mkString


3、异常
在Scala中，异常处理和捕获机制与Java是非常相似的。

try {
  throw new IllegalArgumentException("x should not be negative")
} catch {
  case _: IllegalArgumentException => println("Illegal Argument!")
} finally {
  print("release resources!")
}

try {
  throw new IOException("user defined exception")
} catch {
  case e1: IllegalArgumentException => println("illegal argument")
  case e2: IOException => println("io exception")
}

  */
import java.io.IOException

import scala.io.Source._
object App {
  def main(args: Array[String]): Unit = {
//    var res= sayHello3("11")
//    println( res )
//lazy val lines = fromFile("C://Users//Administrator//Desktop//test.txt").mkString


    try {
      throw new IOException("user defined exception")
    } catch {
      case e1: IllegalArgumentException => println("illegal argument")
      case e2: IOException => println("io exception")
    }
  }

  def sayHello1(name: String) = "Hello, " + name
  def sayHello2(name: String) { print("Hello, " + name); "Hello, " + name }
  def sayHello3(name: String): Unit = "Hello, " + name
}
