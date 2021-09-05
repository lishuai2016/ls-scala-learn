package zhonghuashishan.lesson20

/**
隐式转换与隐式参数

Scala提供的隐式转换和隐式参数功能，是非常有特色的功能。是Java等编程语言所没有的功能。
它可以允许你手动指定，将某种类型的对象转换成其他类型的对象。通过这些功能，可以实现非常强大，而且特殊的功能。

Scala的隐式转换，其实最核心的就是定义隐式转换函数，即implicit conversion function。定义的隐式转换函数，
只要在编写的程序内引入，就会被Scala自动使用。Scala会根据隐式转换函数的签名，
在程序中使用到隐式转换函数接收的参数类型定义的对象时，会自动将其传入隐式转换函数，转换为另外一种类型的对象并返回。这就是“隐式转换”。

隐式转换函数叫什么名字是无所谓的，因为通常不会由用户手动调用，而是由Scala进行调用。但是如果要使用隐式转换，
则需要对隐式转换函数进行导入。因此通常建议将隐式转换函数的名称命名为“one2one”的形式。

Spark源码中有大量的隐式转换和隐式参数，因此必须精通这种语法。



1、隐式转换（案例：特殊售票窗口）

/ 要实现隐式转换，只要程序可见的范围内定义隐式转换函数即可。Scala会自动使用隐式转换函数。
隐式转换函数与普通函数唯一的语法区别就是，要以implicit开头，而且最好要定义函数返回类型。

// 案例：特殊售票窗口（只接受特殊人群，比如学生、老人等）
class SpecialPerson(val name: String)
class Student(val name: String)
class Older(val name: String)

implicit def object2SpecialPerson (obj: Object): SpecialPerson = {
  if (obj.getClass == classOf[Student]) { val stu = obj.asInstanceOf[Student]; new SpecialPerson(stu.name) }
  else if (obj.getClass == classOf[Older]) { val older = obj.asInstanceOf[Older]; new SpecialPerson(older.name) }
  else Nil
}

var ticketNumber = 0
def buySpecialTicket(p: SpecialPerson) = {
  ticketNumber += 1
  "T-" + ticketNumber
}


2、使用隐式转换加强现有类型（案例：超人变身）

// 隐式转换非常强大的一个功能，就是可以在不知不觉中加强现有类型的功能。
也就是说，可以为某个类定义一个加强版的类，并定义互相之间的隐式转换，从而让源类在使用加强版的方法时，
由Scala自动进行隐式转换为加强类，然后再调用该方法。

// 案例：超人变身

class Man(val name: String)
class Superman(val name: String) {
  def emitLaser = println("emit a laster!")
}

implicit def man2superman(man: Man): Superman = new Superman(man.name)

val leo = new Man("leo")
leo.emitLaser


3、隐式转换函数的作用域与导入

// Scala默认会使用两种隐式转换，一种是源类型，或者目标类型的伴生对象内的隐式转换函数；
一种是当前程序作用域内的可以用唯一标识符表示的隐式转换函数。

// 如果隐式转换函数不在上述两种情况下的话，那么就必须手动使用import语法引入某个包下的隐式转换函数，
比如import test._。通常建议，仅仅在需要进行隐式转换的地方，比如某个函数或者方法内，
用import导入隐式转换函数，这样可以缩小隐式转换函数的作用域，避免不需要的隐式转换。


4、隐式转换的发生时机（案例：特殊售票窗口加强版）

// 1、调用某个函数，但是给函数传入的参数的类型，与函数定义的接收参数类型不匹配（案例：特殊售票窗口）
// 2、使用某个类型的对象，调用某个方法，而这个方法并不存在于该类型时（案例：超人变身）
// 3、使用某个类型的对象，调用某个方法，虽然该类型有这个方法，但是给方法传入的参数类型，与方法定义的接收参数的类型不匹配（案例：特殊售票窗口加强版）

// 案例：特殊售票窗口加强版
class TicketHouse {
  var ticketNumber = 0
  def buySpecialTicket(p: SpecialPerson) = {
    ticketNumber += 1
    "T-" + ticketNumber
  }
}


5、隐式参数（案例：考试签到）

// 所谓的隐式参数，指的是在函数或者方法中，定义一个用implicit修饰的参数，此时Scala会尝试找到一个指定类型的，
用implicit修饰的对象，即隐式值，并注入参数。
// Scala会在两个范围内查找：一种是当前作用域内可见的val或var定义的隐式变量；一种是隐式参数类型的伴生对象内的隐式值

// 案例：考试签到
class SignPen {
  def write(content: String) = println(content)
}
implicit val signPen = new SignPen

def signForExam(name: String) (implicit signPen: SignPen) {
  signPen.write(name + " come to exam in time.")
}

  */
object App {
  def main(args: Array[String]): Unit = {
    println( "Hello World!" )
  }
}
