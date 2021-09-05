package zhonghuashishan.lesson16

/**
函数式编程
Scala中的函数是Java中完全没有的概念。因为Java是完全面向对象的编程语言，没有任何面向过程编程语言的特性，
因此Java中的一等公民是类和对象，而且只有方法的概念，即寄存和依赖于类和对象中的方法。Java中的方法是绝对不可能脱离类和对象独立存在的。

而Scala是一门既面向对象，又面向过程的语言。因此在Scala中有非常好的面向对象的特性，
可以使用Scala来基于面向对象的思想开发大型复杂的系统和工程；而且Scala也面向过程，因此Scala中有函数的概念。
在Scala中，函数与类、对象等一样，都是一等公民。Scala中的函数可以独立存在，不需要依赖任何类和对象。

Scala的函数式编程，就是Scala面向过程的最好的佐证。也正是因为函数式编程，才让Scala具备了Java所不具备的更强大的功能和特性。

而之所以Scala一直没有替代Java，是因为Scala之前一直没有开发过太多知名的应用；而Java则不一样，Java诞生的非常早，
上个世界90年代就诞生了，基于Java开发了大量知名的工程。而且最重要的一点在于，Java现在不只是一门编程语言，还是一个庞大的，
涵盖了软件开发，甚至大数据、云计算的技术生态，Java生态中的重要框架和系统就太多了：Spring、Lucene、Activiti、Hadoop等等。


1、将函数赋值给变量
  // Scala中的函数是一等公民，可以独立定义，独立存在，而且可以直接将函数作为值赋值给变量
// Scala的语法规定，将函数赋值给变量时，必须在函数后面加上空格和下划线

def sayHello(name: String) { println("Hello, " + name) }
val sayHelloFunc = sayHello _
sayHelloFunc("leo")

2、匿名函数
  // Scala中，函数也可以不需要命名，此时函数被称为匿名函数。
// 可以直接定义函数之后，将函数赋值给某个变量；也可以将直接定义的匿名函数传入其他函数之中
// Scala定义匿名函数的语法规则就是，(参数名: 参数类型) => 函数体
// 这种匿名函数的语法必须深刻理解和掌握，在spark的中有大量这样的语法，如果没有掌握，是看不懂spark源码的

val sayHelloFunc = (name: String) => println("Hello, " + name)


3、高阶函数

  // Scala中，由于函数是一等公民，因此可以直接将某个函数传入其他函数，作为参数。这个功能是极其强大的，也是Java这种面向对象的编程语言所不具备的。
// 接收其他函数作为参数的函数，也被称作高阶函数（higher-order function）
val sayHelloFunc = (name: String) => println("Hello, " + name)
def greeting(func: (String) => Unit, name: String) { func(name) }
greeting(sayHelloFunc, "leo")

Array(1, 2, 3, 4, 5).map((num: Int) => num * num)

// 高阶函数的另外一个功能是将函数作为返回值
def getGreetingFunc(msg: String) = (name: String) => println(msg + ", " + name)
val greetingFunc = getGreetingFunc("hello")
greetingFunc("leo")


4、高阶函数的类型推断
  // 高阶函数可以自动推断出参数类型，而不需要写明类型；而且对于只有一个参数的函数，还可以省去其小括号；如果仅有的一个参数在右侧的函数体内只使用一次，则还可以将接收参数省略，并且将参数用_来替代
// 诸如3 * _的这种语法，必须掌握！！spark源码中大量使用了这种语法！

def greeting(func: (String) => Unit, name: String) { func(name) }
greeting((name: String) => println("Hello, " + name), "leo")
greeting((name) => println("Hello, " + name), "leo")
greeting(name => println("Hello, " + name), "leo")

def triple(func: (Int) => Int) = { func(3) }
triple(3 * _)



5、Scala的常用高阶函数
  // map: 对传入的每个元素都进行映射，返回一个处理后的元素
Array(1, 2, 3, 4, 5).map(2 * _)

// foreach: 对传入的每个元素都进行处理，但是没有返回值
(1 to 9).map("*" * _).foreach(println _)

// filter: 对传入的每个元素都进行条件判断，如果对元素返回true，则保留该元素，否则过滤掉该元素
(1 to 20).filter(_ % 2 == 0)

// reduceLeft: 从左侧元素开始，进行reduce操作，即先对元素1和元素2进行处理，然后将结果与元素3处理，再将结果与元素4处理，依次类推，即为reduce；reduce操作必须掌握！spark编程的重点！！！
// 下面这个操作就相当于1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9
(1 to 9).reduceLeft( _ * _)

// sortWith: 对元素进行两两相比，进行排序
Array(3, 2, 5, 4, 10, 1).sortWith(_ < _)



6、闭包
  // 闭包最简洁的解释：函数在变量不处于其有效作用域时，还能够对变量进行访问，即为闭包
def getGreetingFunc(msg: String) = (name: String) => println(msg + ", " + name)
val greetingFuncHello = getGreetingFunc("hello")
val greetingFuncHi = getGreetingFunc("hi")

// 两次调用getGreetingFunc函数，传入不同的msg，并创建不同的函数返回
// 然而，msg只是一个局部变量，却在getGreetingFunc执行完之后，还可以继续存在创建的函数之中；greetingFuncHello("leo")，调用时，值为"hello"的msg被保留在了函数体内部，可以反复的使用
// 这种变量超出了其作用域，还可以使用的情况，即为闭包

// Scala通过为每个函数创建对象来实现闭包，实际上对于getGreetingFunc函数创建的函数，msg是作为函数对象的变量存在的，因此每个函数才可以拥有不同的msg

// Scala编译器会确保上述闭包机制



7、SAM转换
  // 在Java中，不支持直接将函数传入一个方法作为参数，通常来说，唯一的办法就是定义一个实现了某个接口的类的实例对象，该对象只有一个方法；而这些接口都只有单个的抽象方法，也就是single abstract method，简称为SAM

// 由于Scala是可以调用Java的代码的，因此当我们调用Java的某个方法时，可能就不得不创建SAM传递给方法，非常麻烦；但是Scala又是支持直接传递函数的。此时就可以使用Scala提供的，在调用Java方法时，使用的功能，SAM转换，即将SAM转换为Scala函数

// 要使用SAM转换，需要使用Scala提供的特性，隐式转换
import javax.swing._
import java.awt.event._

val button = new JButton("Click")
button.addActionListener(new ActionListener {
  override def actionPerformed(event: ActionEvent) {
    println("Click Me!!!")
  }
})

implicit def getActionListener(actionProcessFunc: (ActionEvent) => Unit) = new ActionListener {
  override def actionPerformed(event: ActionEvent) {
    actionProcessFunc(event)
  }
}
button.addActionListener((event: ActionEvent) => println("Click Me!!!"))



8、Currying函数
  // Curring函数，指的是，将原来接收两个参数的一个函数，转换为两个函数，第一个函数接收原先的第一个参数，然后返回接收原先第二个参数的第二个函数。
// 在函数调用的过程中，就变为了两个函数连续调用的形式
// 在Spark的源码中，也有体现，所以对()()这种形式的Curring函数，必须掌握！

def sum(a: Int, b: Int) = a + b
sum(1, 1)

def sum2(a: Int) = (b: Int) => a + b
sum2(1)(1)

def sum3(a: Int)(b: Int) = a + b

9、return
// Scala中，不需要使用return来返回函数的值，函数最后一行语句的值，就是函数的返回值。在Scala中，return用于在匿名函数中返回值给包含匿名函数的带名函数，并作为带名函数的返回值。
// 使用return的匿名函数，是必须给出返回类型的，否则无法通过编译

def greeting(name: String) = {
  def sayHello(name: String):String = {
    return "Hello, " + name
  }
  sayHello(name)
}




  *
 */
object App {
  def main(args: Array[String]): Unit = {
    val sayHelloFunc = sayHello _
    sayHelloFunc("leo")

  }

  def sayHello(name: String) { println("Hello, " + name) }
}
