package zhonghuashishan.lesson17

/**
函数式编程之集合操作
1、Scala的集合体系结构
  // Scala中的集合体系主要包括：Iterable、Seq、Set、Map。其中Iterable是所有集合trait的根trait。这个结构与Java的集合体系非常相似。

// Scala中的集合是分成可变和不可变两类集合的，其中可变集合就是说，集合的元素可以动态修改，而不可变集合的元素在初始化之后，就无法修改了。分别对应scala.collection.mutable和scala.collection.immutable两个包。

// Seq下包含了Range、ArrayBuffer、List等子trait。其中Range就代表了一个序列，通常可以使用“1 to 10”这种语法来产生一个Range。 ArrayBuffer就类似于Java中的ArrayList。

2、List
  // List代表一个不可变的列表
// List的创建，val list = List(1, 2, 3, 4)
// List有head和tail，head代表List的第一个元素，tail代表第一个元素之后的所有元素，list.head，list.tail
// List有特殊的::操作符，可以用于将head和tail合并成一个List，0 :: list
// ::这种操作符要清楚，在spark源码中都是有体现的，一定要能够看懂！
// 如果一个List只有一个元素，那么它的head就是这个元素，它的tail是Nil

// 案例：用递归函数来给List中每个元素都加上指定前缀，并打印加上前缀的元素
def decorator(l: List[Int], prefix: String) {
  if (l != Nil) {
    println(prefix + l.head)
    decorator(l.tail, prefix)
  }
}

3、LinkedList
  // LinkedList代表一个可变的列表，使用elem可以引用其头部，使用next可以引用其尾部
// val l = scala.collection.mutable.LinkedList(1, 2, 3, 4, 5); l.elem; l.next

// 案例：使用while循环将LinkedList中的每个元素都乘以2
val list = scala.collection.mutable.LinkedList(1, 2, 3, 4, 5)
var currentList = list
while (currentList != Nil) {
  currentList.elem = currentList.elem * 2
  currentList = currentList.next
}

// 案例：使用while循环将LinkedList中，从第一个元素开始，每隔一个元素，乘以2
val list = scala.collection.mutable.LinkedList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
var currentList = list
var first = true
while (currentList != Nil && currentList.next != Nil) {
  if (first) { currentList.elem = currentList.elem * 2; first = false }
  currentList  = currentList.next.next
  if (currentList != Nil) currentList.elem = currentList.elem * 2
}

4、Set
  // Set代表一个没有重复元素的集合
// 将重复元素加入Set是没有用的，比如val s = Set(1, 2, 3); s + 1; s + 4
// 而且Set是不保证插入顺序的，也就是说，Set中的元素是乱序的，val s = new scala.collection.mutable.HashSet[Int](); s += 1; s += 2; s += 5

// LinkedHashSet会用一个链表维护插入顺序，val s = new scala.collection.mutable.LinkedHashSet[Int](); i += 1; s += 2; s += 5

// SrotedSet会自动根据key来进行排序，val s = scala.collection.mutable.SortedSet("orange", "apple", "banana")

5、集合的函数式编程
  // 集合的函数式编程非常非常非常之重要！！！
// 必须完全掌握和理解Scala的高阶函数是什么意思，Scala的集合类的map、flatMap、reduce、reduceLeft、foreach等这些函数，就是高阶函数，因为可以接收其他函数作为参数
// 高阶函数的使用，也是Scala与Java最大的一点不同！！！因为Java里面是没有函数式编程的，也肯定没有高阶函数，也肯定无法直接将函数传入一个方法，或者让一个方法返回一个函数
// 对Scala高阶函数的理解、掌握和使用，可以大大增强你的技术，而且也是Scala最有诱惑力、最有优势的一个功能！！！
// 此外，在Spark源码中，有大量的函数式编程，以及基于集合的高阶函数的使用！！！所以必须掌握，才能看懂spark源码

// map案例实战：为List中每个元素都添加一个前缀
List("Leo", "Jen", "Peter", "Jack").map("name is " + _)

// faltMap案例实战：将List中的多行句子拆分成单词
List("Hello World", "You Me").flatMap(_.split(" "))

// foreach案例实战：打印List中的每个单词
List("I", "have", "a", "beautiful", "house").foreach(println(_))

// zip案例实战：对学生姓名和学生成绩进行关联
List("Leo", "Jen", "Peter", "Jack").zip(List(100, 90, 75, 83))

6、函数式编程综合案例：统计多个文本内的单词总数
// 使用scala的io包将文本文件内的数据读取出来
val lines01 = scala.io.Source.fromFile("C://Users//Administrator//Desktop//test01.txt").mkString
val lines02 = scala.io.Source.fromFile("C://Users//Administrator//Desktop//test02.txt").mkString
// 使用List的伴生对象，将多个文件内的内容创建为一个List
val lines = List(lines01, lines02)

// 下面这一行才是我们的案例的核心和重点，因为有多个高阶函数的链式调用，以及大量下划线的使用，如果没有透彻掌握之前的课讲解的Scala函数式编程，那么下面这一行代码，完全可能会看不懂！！！
// 但是下面这行代码其实就是Scala编程的精髓所在，就是函数式编程，也是Scala相较于Java等编程语言最大的功能优势所在
// 而且，spark的源码中大量使用了这种复杂的链式调用的函数式编程
// 而且，spark本身提供的开发人员使用的编程api的风格，完全沿用了Scala的函数式编程，比如Spark自身的api中就提供了map、flatMap、reduce、foreach，以及更高级的reduceByKey、groupByKey等高阶函数
// 如果要使用Scala进行spark工程的开发，那么就必须掌握这种复杂的高阶函数的链式调用！！！

lines.flatMap(_.split(" ")).map((_, 1)).map(_._2).reduceLeft(_ + _)

 */
object App {
  def main(args: Array[String]): Unit = {
    println( "Hello World!" )
  }
}
