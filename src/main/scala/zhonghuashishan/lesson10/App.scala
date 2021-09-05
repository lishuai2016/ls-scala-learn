package zhonghuashishan.lesson10

/**
数组操作之数组转换
  1、使用yield和函数式编程转换数组
  // 对Array进行转换，获取的还是Array
val a = Array(1, 2, 3, 4, 5)
val a2 = for (ele <- a) yield ele * ele
// 对ArrayBuffer进行转换，获取的还是ArrayBuffer
val b = ArrayBuffer[Int]()
b += (1, 2, 3, 4, 5)
val b2 = for (ele <- b) yield ele * ele
// 结合if守卫，仅转换需要的元素
val a3 = for (ele <- if ele % 2 == 0) yield ele * ele

// 使用函数式编程转换数组（通常使用第一种方式）
a.filter(_ % 2 == 0).map(2 * _)
a.filter { _ % 2 == 0 } map { 2 * _ }


2、算法案例：移除第一个负数之后的所有负数

// 构建数组
val a = ArrayBuffer[Int]()
a += (1, 2, 3, 4, 5, -1, -3, -5, -9)

// 每发现一个第一个负数之后的负数，就进行移除，性能较差，多次移动数组
var foundFirstNegative = false
var arrayLength = a.length
var index = 0
while (index < arrayLength) {
  if (a(index) >= 0) {
    index += 1
  } else {
    if (!foundFirstNegative) { foundFirstNegative = true; index += 1 }
    else { a.remove(index); arrayLength -= 1 }
  }
}


优化后

  // 重新构建数组
val a = ArrayBuffer[Int]()
a += (1, 2, 3, 4, 5, -1, -3, -5, -9)

// 每记录所有不需要移除的元素的索引，稍后一次性移除所有需要移除的元素
// 性能较高，数组内的元素迁移只要执行一次即可
var foundFirstNegative = false
val keepIndexes = for (i <- 0 until a.length if !foundFirstNegative || a(i) >= 0) yield {
  if (a(i) < 0) foundFirstNegative = true
  i
}
for (i <- 0 until keepIndexes.length) { a(i) = a(keepIndexes(i)) }
a.trimEnd(a.length - keepIndexes.length)







  */
object App {
  def main(args: Array[String]): Unit = {
    val a = Array(1, 2, 3, 4, 5)
//    val a2 = for (ele <- a) yield ele * ele //功能是对原理的元素乘以自己
//    for (e <- a2) println(e)
    val array = a.filter(_ % 2 == 0).map(2 * _)
    for (e <- array) println(e)

  }
}
