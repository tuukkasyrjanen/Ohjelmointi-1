package o1.rainfall

import scala.io.StdIn._

object RainfallApp extends App {

  val rainData = LazyList.continually( readLine("Enter rainfall (or 999999 to stop): ") ).flatMap( _.toIntOption )
  var average = 0
  if (rainData.filter( _ >= 0).takeWhile( _ != 999999).length == 0){
    println("No valid data. Cannot compute average.")
  }
  else {
    val tempVector = rainData.filter( _ >= 0).takeWhile( _ != 999999)
    average = tempVector.foldLeft(0)((average, next) => average + next)
    println(s"The average is ${average / tempVector.length}.")
  }
}