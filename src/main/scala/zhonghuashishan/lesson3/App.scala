package zhonghuashishan.lesson3

/**
  基础语法学习
Scala解释器的使用：
REPL：Read（取值）-> Evaluation（求值）-> Print（打印）-> Loop（循环）。scala解释器也被称为REPL，会快速编译scala代码为字节码，然后交给JVM来执行。

1、变量
  val 声明的变量类似于java中的final，复制完毕后不能再改变；
  var 可以改变引用的变量

  无论声明val变量，还是声明var变量，都可以手动指定其类型，如果不指定的话，scala会自动根据值，进行类型的推断。
  例如，val name: String = null
  例如，val name: Any = "leo"
   备注：这里和java中的写法不同，类型通过：写在变量的后面

  声明多个变量：可以将多个变量放在一起进行声明。
    ·例如，val name1, name2:String = null
    ·例如，val num1, num2 = 100

2、数据类型
基本数据类型：Byte、Char、Short、Int、Long、Float、Double、Boolean
乍一看与Java的基本数据类型的包装类型相同，但是scala没有基本数据类型与包装类型的概念，统一都是类。scala自己会负责基本数据类型和引用类型的转换操作。

使用以上类型，直接就可以调用大量的函数，例如，1.toString()，1.to(10)表示1到10范围数据。



3、基本操作符
scala的算术操作符与java的算术操作符也没有什么区别，比如+、-、*、/、%等，以及&、|、、>>、<<。


4、函数调用

不同的一点是，如果调用函数时，不需要传递参数，则scala允许调用函数时省略括号的。比如："Hello World".distinct

5、apply函数
Scala中的apply函数是非常特殊的一种函数，在Scala的object中，可以声明apply函数。而使用“类名()”的形式，其实就是“类名.apply()”的一种缩写。通常使用这种方式来构造类的对象，而不是使用“new 类名()”的方式。
例如，"Hello World"(6)，因为在StringOps类中有def apply(n: Int): Char的函数定义，所以"Hello World"(6)，实际上是"Hello World".apply(6)的缩写。
例如，Array(1, 2, 3, 4)，实际上是用Array object的apply()函数来创建Array类的实例，也就是一个数组。


*/


import scala.math._
object App {
  def main(args: Array[String]): Unit = {

    println("Hello World".distinct)
  }
}
