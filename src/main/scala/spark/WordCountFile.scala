package spark

import org.apache.spark.{SparkConf, SparkContext}

/**
  * 结果输出到文件
  */
object  WordCountFile {
  def main(args: Array[String]) {
    System.setProperty("hadoop.home.dir", "D:\\hadoop\\hadoop-2.7.3")
    var masterUrl = "local"
    var inputPath = "D:\\opencode\\ls-scala-learn\\spark.txt"
    var outputPath = "D:\\opencode\\ls-scala-learn\\out"

    if (args.length == 1) {
      masterUrl = args(0)
    } else if (args.length == 3) {
      masterUrl = args(0)
      inputPath = args(1)
      outputPath = args(2)
    }

    println(s"masterUrl:$masterUrl, inputPath: $inputPath, outputPath: $outputPath")
    val sparkConf = new SparkConf().setMaster(masterUrl).setAppName("WordCount")
    val sc = new SparkContext(sparkConf)

    val rowRdd = sc.textFile(inputPath)
    val resultRdd = rowRdd.flatMap(line => line.split("\\s+"))
      .map(word => (word, 1)).reduceByKey(_ + _)

    resultRdd.saveAsTextFile(outputPath)
  }
}
