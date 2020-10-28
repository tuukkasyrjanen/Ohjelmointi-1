package o1

import scala.+:

package object rainfall {

  // What goes here is described in Chapter 6.4.
  def averageRainfall(vector :Vector[Int]) :Option[Int] = {

    val rainData = vector
    var average = 0
    if (rainData.filter( _ >= 0).takeWhile( _ != 999999).length == 0){
      None
    }
    else {
      val tempVector = rainData.filter( _ >= 0).takeWhile( _ != 999999)
      average = tempVector.foldLeft(0)((average, next) => average + next)
      Some(average / tempVector.length)
    }
  }
  def averageRainfallFromStrings(vector :Vector[String]) :Option[Int] = {

    if (vector.takeWhile( _ != 999999).map( _ .toIntOption).flatten.filter( _ >= 0).length == 0){
      None
    }
    else {
     averageRainfall(vector.takeWhile( _ != 999999).map( _ .toIntOption).flatten :+ 999999)
    }
  }

  def drySpell(rainData :Vector[Int], drySeason : Int) :Int = {
    var count = 0
    val vectorCollection = rainData.sliding(drySeason)
    var value = 0

    def isBetweenZeroAndFive(vector :Vector[Int]) :Boolean = {
      vector.forall( _ <= 5) && vector.forall( _ >= 0)
    }

    for (current <- rainData.sliding(drySeason)){
      if (isBetweenZeroAndFive(current)){
        return count
      }
      count += 1
    }
    -1
  }
}