package o1

// This program is developed in Chapters 2.7 and 3.4.
// It creates a single Odds object and uses some of its methods.

import scala.io.StdIn._

object OddsTest1 extends App {

  println("Please enter the odds of an event as two integers on separate lines.")
  println("For instance, to enter the odds 5/1 (one in six chance of happening), write 5 and 1 on separate lines.")
  val firstInput = readInt()
  val secondInput = readInt()

  println("The odds you entered are:")
  println("In fractional format: ")
  println("In decimal format: ")
  println("Event probability: ")
  println("Reverse odds: ")
  println("Odds of happening twice: ")

  println("Please enter the size of a bet:")
  val thirdInput = readDouble()
  println("If successful, the bettor would claim ")

}
