package zhonghuashishan.lesson19

/**
类型参数
类型参数是什么？类型参数其实就类似于Java中的泛型。先说说Java中的泛型是什么，比如我们有List a = new ArrayList()，接着a.add(1)，
没问题，a.add("2")，然后我们a.get(1) == 2，对不对？肯定不对了，a.get(1)获取的其实是个String——"2"，
String——"2"怎么可能与一个Integer类型的2相等呢？

所以Java中提出了泛型的概念，其实也就是类型参数的概念，此时可以用泛型创建List，List a = new ArrayList[Integer]()，
那么，此时a.add(1)没问题，而a.add("2")呢？就不行了，因为泛型会限制，只能往集合中添加Integer类型，这样就避免了上述的问题。

那么Scala的类型参数是什么？其实意思与Java的泛型是一样的，也是定义一种类型参数，比如在集合，在类，在函数中，定义类型参数，
然后就可以保证使用到该类型参数的地方，就肯定，也只能是这种类型。从而实现程序更好的健壮性。

此外，类型参数是Spark源码中非常常见的，因此同样必须掌握，才能看懂spark源码。



1、泛型类（案例：新生报到）

// 泛型类，顾名思义，其实就是在类的声明中，定义一些泛型类型，然后在类内部，比如field或者method，就可以使用这些泛型类型。
// 使用泛型类，通常是需要对类中的某些成员，比如某些field和method中的参数或变量，进行统一的类型限制，这样可以保证程序更好的健壮性和稳定性。
// 如果不使用泛型进行统一的类型限制，那么在后期程序运行过程中，难免会出现问题，比如传入了不希望的类型，导致程序出问题。
// 在使用类的时候，比如创建类的对象，将类型参数替换为实际的类型，即可。
// Scala自动推断泛型类型特性：直接给使用了泛型类型的field赋值时，Scala会自动进行类型推断。

案例：新生报到，每个学生来自不同的地方，id可能是Int，可能是String

class Student[T](val localId: T) {
  def getSchoolId(hukouId: T) = "S-" + hukouId + "-" + localId
}

val leo = new Student[Int](111)



2、泛型函数（案例：卡片售卖机）

// 泛型函数，与泛型类类似，可以给某个函数在声明时指定泛型类型，然后在函数体内，多个变量或者返回值之间，就可以使用泛型类型进行声明，从而对某个特殊的变量，或者多个变量，进行强制性的类型限制。
// 与泛型类一样，你可以通过给使用了泛型类型的变量传递值来让Scala自动推断泛型的实际类型，也可以在调用函数时，手动指定泛型类型。

案例：卡片售卖机，可以指定卡片的内容，内容可以是String类型或Int类型
def getCard[T](content: T) = {
  if(content.isInstanceOf[Int]) "card: 001, " + content
  else if(content.isInstanceOf[String]) "card: this is your card, " + content
  else "card: " + content
}

getCard[String]("hello world")


3、上边界Bounds（案例：在派对上交朋友）

// 在指定泛型类型的时候，有时，我们需要对泛型类型的范围进行界定，而不是可以是任意的类型。比如，我们可能要求某个泛型类型，它就必须是某个类的子类，这样在程序中就可以放心地调用泛型类型继承的父类的方法，程序才能正常的使用和运行。此时就可以使用上下边界Bounds的特性。
// Scala的上下边界特性允许泛型类型必须是某个类的子类，或者必须是某个类的父类

案例：在派对上交朋友
class Person(val name: String) {
  def sayHello = println("Hello, I'm " + name)
  def makeFriends(p: Person) {
    sayHello
    p.sayHello
  }
}
class Student(name: String) extends Person(name)
class Party[T <: Person](p1: T, p2: T) {
  def play = p1.makeFriends(p2)
}

4、下边界Bounds（案例：领身份证）

// 除了指定泛型类型的上边界，还可以指定下边界，即指定泛型类型必须是某个类的父类

案例：领身份证
class Father(val name: String)
class Child(name: String) extends Father(name)

def getIDCard[R >: Child](person: R) {
  if (person.getClass == classOf[Child]) println("please tell us your parents' names.")
  else if (person.getClass == classOf[Father]) println("sign your name for your child's id card.")
  else println("sorry, you are not allowed to get id card.")
}


5、View Bounds（案例：跟小狗交朋友）

// 上下边界Bounds，虽然可以让一种泛型类型，支持有父子关系的多种类型。但是，在某个类与上下边界Bounds指定的父子类型范围内的类都没有任何关系，则默认是肯定不能接受的。
// 然而，View Bounds作为一种上下边界Bounds的加强版，支持可以对类型进行隐式转换，将指定的类型进行隐式转换后，再判断是否在边界指定的类型范围内

案例：跟小狗交朋友
class Person(val name: String) {
  def sayHello = println("Hello, I'm " + name)
  def makeFriends(p: Person) {
    sayHello
    p.sayHello
  }
}
class Student(name: String) extends Person(name)
class Dog(val name: String) { def sayHello = println("Wang, Wang, I'm " + name) }

implicit def dog2person(dog: Object): Person = if(dog.isInstanceOf[Dog]) {val _dog = dog.asInstanceOf[Dog]; new Person(_dog.name) } else Nil

class Party[T <% Person](p1: T, p2: T)


6、Context Bounds（案例：使用Scala内置的比较器比较大小）

  // Context Bounds是一种特殊的Bounds，它会根据泛型类型的声明，比如“T: 类型”要求必须存在一个类型为“类型[T]”的隐式值。
其实个人认为，Context Bounds之所以叫Context，是因为它基于的是一种全局的上下文，需要使用到上下文中的隐式值以及注入。

案例：使用Scala内置的比较器比较大小
class Calculator[T: Ordering] (val number1: T, val number2: T) {
  def max(implicit order: Ordering[T]) = if(order.compare(number1, number2) > 0) number1 else number2
}


7、Manifest Context Bounds（案例：打包饭菜）

// 在Scala中，如果要实例化一个泛型数组，就必须使用Manifest Context Bounds。
也就是说，如果数组元素类型为T的话，需要为类或者函数定义[T: Manifest]泛型类型，这样才能实例化Array[T]这种泛型数组。

案例：打包饭菜（一种食品打成一包）
class Meat(val name: String)
class Vegetable(val name: String)

def packageFood[T: Manifest] (food: T*) = {
  val foodPackage = new Array[T](food.length)
  for(i <- 0 until food.length) foodPackage(i) = food(i)
  foodPackage
}



8、协变和逆变（案例：进入会场）
  // Scala的协变和逆变是非常有特色的！完全解决了Java中的泛型的一大缺憾！
// 举例来说，Java中，如果有Professional是Master的子类，那么Card[Professionnal]是不是Card[Master]的子类？答案是：不是。因此对于开发程序造成了很多的麻烦。
// 而Scala中，只要灵活使用协变和逆变，就可以解决Java泛型的问题。

案例：进入会场
class Master
class Professional extends Master

// 大师以及大师级别以下的名片都可以进入会场
class Card[+T] (val name: String)
def enterMeet(card: Card[Master]) {
  println("welcome to have this meeting!")
}

// 只要专家级别的名片就可以进入会场，如果大师级别的过来了，当然可以了！
class Card[-T] (val name: String)
def enterMeet(card: Card[Professional]) {
  println("welcome to have this meeting!")
}

9、Existential Type

// 在Scala里，有一种特殊的类型参数，就是Existential Type，存在性类型。这种类型务必掌握是什么意思，因为在spark源码实在是太常见了！

Array[T] forSome { type T }
Array[_]

  */
object App {
  def main(args: Array[String]): Unit = {
    println( "Hello World!" )
  }
}
