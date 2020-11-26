package o1.robots

import o1._

// TODO: proper implementation missing completely
class Nosebot(name: String, body: RobotBody) extends RobotBrain(name, body) {

  def moveBody() :Unit = {
    var counter = 0
    while (counter != 4){
      val attempt = attemptMove()
      if(attempt) {
        counter = 4
      } else{
        counter += 1
      }
    }
  }

  def attemptMove(): Boolean ={
    val move = this.moveCarefully()
    if (!move) {
      this.body.spinClockwise()
    }
    move
  }

  override def mayMoveTowards(direction: o1.CompassDir): Boolean = (this.body.neighboringSquare(direction).isEmpty)
}



