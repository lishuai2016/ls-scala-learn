package zhonghuashishan.lesson4

/**
条件控制与循环

1、if表达式

·if表达式的定义：在Scala中，if表达式是有值的，就是if或者else中最后一行语句返回的值。
    ·例如，val age = 30; if (age > 18) 1 else 0
    ·可以将if表达式赋予一个变量，例如，val isAdult = if (age > 18) 1 else 0
    ·另外一种写法，var isAdult = -1; if(age > 18) isAdult = 1 else isAdult = 0，但是通常使用上一种写法
·if表达式的类型推断：由于if表达式是有值的，而if和else子句的值类型可能不同，此时if表达式的值是什么类型呢？Scala会自动进行推断，取两个类型的公共父类型。
    ·例如，if(age > 18) 1 else 0，表达式的类型是Int，因为1和0都是Int
    ·例如，if(age > 18) "adult" else 0，此时if和else的值分别是String和Int，则表达式的值是Any，Any是String和Int的公共父类型
    ·如果if后面没有跟else，则默认else的值是Unit，也用()表示，类似于java中的void或者null。例如，val age = 12; if(age > 18) "adult"。此时就相当于if(age > 18) "adult" else ()。
·将if语句放在多行中：默认情况下，REPL只能解释一行语句，但是if表达式通常需要放在多行。
    ·可以使用{}的方式，比如以下方式，或者使用:paste和ctrl+D的方式。
if(age > 18) { "adult"
} else if(age > 12) "teenager" else "children"


2、语句终结符、块表达式


默认情况下，scala不需要语句终结符，默认将每一行作为一个语句

·一行放多条语句：如果一行要放多条语句，则必须使用语句终结符
    ·例如，使用分号作为语句终结符，var a, b, c = 0; if(a < 10) { b = b + 1; c = c + 1 }
    ·通常来说，对于多行语句，还是会使用花括号的方式
if(a < 10) {
    b = b + 1
    c = c + 1
}

·块表达式：块表达式，指的就是{}中的值，其中可以包含多条语句，最后一个语句的值就是块表达式的返回值。
    ·例如，var d = if(a < 10) { b = b + 1; c + 1 }


3、输入和输出

·print和println：print打印时不会加换行符，而println打印时会加一个换行符。
    ·例如，print("Hello World"); println("Hello World")
·printf：printf可以用于进行格式化
    ·例如，printf("Hi, my name is %s, I'm %d years old.\n", "Leo", 30)
·readLine: readLine允许我们从控制台读取用户输入的数据，类似于java中的System.in和Scanner的作用。
·综合案例：游戏厅门禁
val name = readLine("Welcome to Game House. Please tell me your name: ")
print("Thanks. Then please tell me your age: ")
val age = readInt()
if(age > 18) {
  printf("Hi, %s, you are %d years old, so you are legel to come here!", name, age)
} else {
  printf("Sorry, boy, %s, you are only %d years old. you are illegal to come here!", name, age)
}

4、循环

·while do循环：Scala有while do循环，基本语义与Java相同。
var n = 10
while(n > 0) {
  println(n)
  n -= 1
}
·Scala没有for循环，只能使用while替代for循环，或者使用简易版的for语句
    ·简易版for语句：var n = 10; for(i <- 1 to n) println(i)
    ·或者使用until，表式不达到上限：for(i <- 1 until n) println(i)
    ·也可以对字符串进行遍历，类似于java的增强for循环，for(c <- "Hello World") print(c)
·跳出循环语句
    ·scala没有提供类似于java的break语句。
    ·但是可以使用boolean类型变量、return或者Breaks的break函数来替代使用。
import scala.util.control.Breaks._
breakable {
    var n = 10
    for(c <- "Hello World") {
        if(n == 5) break;
        print(c)
        n -= 1
    }
}

·多重for循环：九九乘法表
for(i <- 1 to 9; j <- 1 to 9) {
  if(j == 9) {
    println(i * j)
  } else {
    print(i * j + " ")
  }
}

·if守卫：取偶数
for(i <- 1 to 100 if i % 2 == 0) println(i)

·for推导式：构造集合
for(i <- 1 to 10) yield i

  */
import scala.util.control.Breaks._
object App {
  def main(args: Array[String]): Unit = {
    breakable {
      var n = 10
      for(c <- "Hello World") {
        if(n == 5) break;
        print(c)
        n -= 1
      }
    }
  }
}
