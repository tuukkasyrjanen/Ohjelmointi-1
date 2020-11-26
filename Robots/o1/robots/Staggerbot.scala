package o1.robots

import o1.CompassDir
import scala.util.Random

// TODO: proper implementation missing completely
class Staggerbot(name: String, body: RobotBody, randomSeed: Int) extends RobotBrain(name, body) {
  private val randomGenerator = new Random(randomSeed)

  private def randomDirection :CompassDir = {
    val dirList = Vector(CompassDir.North,CompassDir.East,CompassDir.South,CompassDir.West)
    dirList(randomGenerator.nextInt(4))
  }

  def moveBody() :Unit ={
    val dir = randomDirection
    if (this.body.neighboringSquare(dir).isEmpty) {
      this.body.moveTowards(dir)
      this.body.spinTowards(randomDirection)
    }
    else{
      this.body.spinTowards(dir)
      this.squareInFront.addRobot(this.body)
    }
  }
}



