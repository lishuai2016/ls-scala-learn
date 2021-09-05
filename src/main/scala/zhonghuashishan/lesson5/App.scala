package zhonghuashishan.lesson5

/**
函数入门
1、函数的定义与调用

在Scala中定义函数时，需要定义函数的函数名、参数、函数体。

我们的第一个函数如下所示：
def sayHello(name: String, age: Int) = {
  if (age > 18) { printf("hi %s, you are a big boy\n", name); age }
  else { printf("hi %s, you are a little boy\n", name); age
}
sayHello("leo", 30)

Scala要求必须给出所有参数的类型，但是不一定给出函数返回值的类型，只要右侧的函数体中不包含递归的语句，Scala就可以自己根据右侧的表达式推断出返回类型。

2、在代码块中定义包含多行语句的函数体

单行的函数：def sayHello(name: String) = print("Hello, " + name)

如果函数体中有多行代码，则可以使用代码块的方式包裹多行代码，代码块中最后一行的返回值就是整个函数的返回值。与Java中不同，不是使用return返回值的。

比如如下的函数，实现累加的功能：
def sum(n: Int) = {
  var sum = 0;
  for(i <- 1 to n) sum += i
  sum
}

3、递归函数与返回类型

如果在函数体内递归调用函数自身，则必须手动给出函数的返回类型。

例如，实现经典的斐波那契数列：
9 + 8; 8 + 7 + 7 + 6; 7 + 6 + 6 + 5 + 6 + 5 + 5 + 4; ....

def fab(n: Int): Int = {
  if(n <= 1) 1
  else fab(n - 1) + fab(n - 2)
}


  *
 */
object App {
  def main(args: Array[String]): Unit = {
    var res = fab(2)
    println()
    println(res)
  }

  def fab(n: Int): Int = {
    if(n <= 1) 1
    else fab(n - 1) + fab(n - 2)
  }

  def sum(n: Int) = {
    var sum = 0;
    for(i <- 1 to n) sum += i
    sum
  }

  /* 返回值是() */
  def sayHello1(name: String) = print("Hello, " + name)

  def sayHello(name: String, age: Int) = {
    if (age > 18) {
      printf("hi %s, you are a big boy\n", name); age
    }
    else {
      printf("hi %s, you are a little boy\n", name); age
    }
  }
}
