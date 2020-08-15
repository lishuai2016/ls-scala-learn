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

# 2、java.lang.NoSuchMethodError: scala.Predef$.refArrayOps([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;

将scala-sdk从2.12换为2.11

# 3、spark在window上运行出现:java.io.IOException: (null) entry in command string: null chmod 0644

下载hadoop.dll文件，拷贝到c:\windows\system32目录中即可

hadoop.dll可以在github上下载：https://github.com/4ttty/winutils

各个版本的hadoop.dll好像是通用的。

# 4、java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries.
