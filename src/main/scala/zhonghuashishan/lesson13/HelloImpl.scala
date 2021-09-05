package zhonghuashishan.lesson13

object HelloImpl extends Hello("hello") {
  override def sayHello(name: String) = {
    println(message + ", " + name)
  }
}
