scala语法学习



# 1、通过maven创建Scala项目

参考：https://blog.csdn.net/qq_1290259791/article/details/79052584

只需要引入一个依赖包即可

```xml
 <dependency>
      <groupId>org.scala-lang</groupId>
      <artifactId>scala-library</artifactId>
      <version>${scala.version}</version>
    </dependency>
```

```scala
object App {
  def main(args: Array[String]): Unit = {
    println( "Hello World!" )
  }
}
```
提示：找不到或无法加载主类，需要把Scala-sdk引入的项目的依赖中即可。