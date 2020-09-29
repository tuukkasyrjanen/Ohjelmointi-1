package o1

import constants._

class Game {

  val bug = new Bug(new Pos(100,40))
  val obstacle = new Obstacle(ObstacleStartRadius)

  def timePasses() = {
    bug.fall()
    obstacle.approach()
  }

  def activateBug() = {
    bug.flap(15)
  }

  def isLost = this.obstacle.touches(this.bug)

}
