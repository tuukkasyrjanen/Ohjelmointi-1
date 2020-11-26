package o1.robots

import o1._

// TODO: proper implementation missing completely
class Psychobot(name: String, body: RobotBody) extends RobotBrain(name, body) {

  private val compassDirections = Vector(North, East, South, West)

  private def victimSeen :Option[CompassDir] = {
    var isVictim = false
    var victimDirection :Option[CompassDir] = None //alustus?

    for (direction <- compassDirections){
      val gridPosList = this.location.pathTowards(direction)
      var isWall = false
      var index = 0
      var brokenRobot = false

      while(!isWall && !isVictim && !brokenRobot){
        val square = this.world.elementAt(gridPosList(index))

        if(square == Wall) {
          isWall = true
        }

        if(!square.isEmpty && square != Wall && !isWall) {
          if(!square.robot.exists(_.isIntact)){
            brokenRobot = true
          }else{
            isVictim = true
            victimDirection = Some(direction)
          }
        }
        index += 1
      }
    }
    victimDirection
  }



  def moveBody() :Unit = {
    victimSeen match {
      case Some(dir)  => var isTrue = true
                         while(isTrue){
                           val move = this.body.moveTowards(dir)
                           if (!move) isTrue = false
                         }
      case None       =>
    }
  }
}



