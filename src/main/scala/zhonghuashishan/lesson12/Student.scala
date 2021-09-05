package zhonghuashishan.lesson12

class Student {
//  var name = "leo"

  private var myName = "leo"
  def name = "your name is " + myName//这个是getter方法
  def name_=(newValue: String)  {//相当于setter方法
    print("you cannot edit your name!!!")
  }
}
