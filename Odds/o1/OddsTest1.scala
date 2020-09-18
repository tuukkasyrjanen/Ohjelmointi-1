package o1

// This program is developed in Chapters 2.7 and 3.4.
// It creates a single Odds object and uses some of its methods.

import scala.io.StdIn._

object OddsTest1 extends App {

  println("Please enter the odds of an event as two integers on separate lines.")
  println("For instance, to enter the odds 5/1 (one in six chance of happening), write 5 and 1 on separate lines.")
  val firstInput = readInt()
  val secondInput = readInt()

  val tod = new Odds(firstInput,secondInput)

  println("The odds you entered are:")
  println("In fractional format: " + tod.fractional)
  println("In decimal format: " + tod.decimal)
  println("Event probability: " + tod.probability)
  println("Reverse odds: " + tod.not.fractional)
  println("Odds of happening twice: " + tod.both(tod))

  println("Please enter the size of a bet:")
  val thirdInput = readDouble()
  println("If successful, the bettor would claim " + tod.winnings(thirdInput))

  println("Please enter the odds of a second event as two integers on separate lines.")
  val toinenTod = new Odds(readInt(), readInt())

  println("The odds of both events happening are: " + toinenTod.both(tod))
  println("The odds of one or both happening are: " + toinenTod.either(tod))


}
