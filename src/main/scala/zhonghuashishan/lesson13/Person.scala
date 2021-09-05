package zhonghuashishan.lesson13

object Person {
  private val eyeNum = 2
  def getEyeNum = eyeNum
}

class Person(val name: String, val age: Int) {
  def sayHello = println("Hi, " + name + ", I guess you are " + age + " years old!" + ", and usually you must have " + Person.eyeNum + " eyes.")
}