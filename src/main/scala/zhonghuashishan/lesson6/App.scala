package zhonghuashishan.lesson6

/**
函数入门之默认参数和带名参数

1、 默认参数

在Scala中，有时我们调用某些函数时，不希望给出参数的具体值，而希望使用参数自身默认的值，此时就定义在定义函数时使用默认参数。

def sayHello(firstName: String, middleName: String = "William", lastName: String = "Croft") = firstName + " " + middleName + " " + lastName

如果给出的参数不够，则会从作往右依次应用参数。


2、Java与Scala实现默认参数的区别（java通过null值判断）

Java：
public void sayHello(String name, int age) {
  if(name == null) {
    name = "defaultName"
  }
  if(age == 0) {
    age = 18
  }
}
sayHello(null, 0)

Scala：def sayHello(name: String, age: Int = 20) {
  print("Hello, " + name + ", your age is " + age)
}
sayHello("leo")


3、带名参数
在调用函数时，也可以不按照函数定义的参数顺序来传递参数，而是使用带名参数的方式来传递。

sayHello(firstName = "Mick", lastName = "Nina", middleName = "Jack")

还可以混合使用未命名参数和带名参数，但是未命名参数必须排在带名参数前面。

sayHello("Mick", lastName = "Nina", middleName = "Jack")






  */
object App {
  def main(args: Array[String]): Unit = {
    var res = sayHello("11")
    res = sayHello("11","22")
    res = sayHello("11","22","33")
    res = sayHello("11",lastName="22",middleName="33")
    println( res )
  }

  def sayHello(firstName: String, middleName: String = "William", lastName: String = "Croft") = firstName + " " + middleName + " " + lastName
}
