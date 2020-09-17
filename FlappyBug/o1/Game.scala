package o1

import constants._

class Game {

  val bug = new Bug(new Pos(100,40))
  val obstacle = new Obstacle(ObstacleStartRadius,new Pos(1000,100))

  def timePasses() = {
    bug.fall()
    obstacle.approach()
  }

  def activateBug() = {
    bug.flap(15)
  }

}
