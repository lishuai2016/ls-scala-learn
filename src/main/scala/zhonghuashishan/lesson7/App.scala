package zhonghuashishan.lesson7

/**
函数入门之变长参数
  1、变成参数
  在Scala中，有时我们需要将函数定义为参数个数可变的形式，则此时可以使用变长参数定义函数。
注意：这里和单个参数的区别仅仅是类型后面多了一个*
def sum(nums: Int*) = {
  var res = 0
  for (num <- nums) res += num
  res
}

sum(1, 2, 3, 4, 5)


2、使用序列调用变长参数


在如果想要将一个已有的序列直接调用变长参数函数，是不对的。比如val s = sum(1 to 5)。此时需要使用Scala特殊的语法将参数定义为序列，让Scala解释器能够识别。这种语法非常有用！一定要好好主意，在spark的源码中大量地使用到了。

val s = sum(1 to 5: _*)

案例：使用递归函数实现累加

def sum2(nums: Int*): Int = {
  if (nums.length == 0) 0
  else nums.head + sum2(nums.tail: _*)
}


  */
object App {
  def main(args: Array[String]): Unit = {
    var res = sum2(1 to 5: _*)
    println( res )
  }

  def sum2(nums: Int*): Int = {
    if (nums.length == 0) 0
    else nums.head + sum2(nums.tail: _*)
  }
  def sum(nums: Int*) = {
    var res = 0
    for (num <- nums) res += num
    res
  }
}
