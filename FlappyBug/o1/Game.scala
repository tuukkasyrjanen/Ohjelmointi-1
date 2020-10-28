package o1

import constants._

class Game {

  val bug = new Bug(new Pos(100,40))
  val obstacles = Vector[Obstacle](new Obstacle(70),new Obstacle(30),new Obstacle(20))

  def timePasses() = {
    bug.fall()
    obstacles.map(n => n.approach())
  }

  def activateBug() = {
    bug.flap(15)
  }

  def isLost = {
  //  var lost = false
  //  for(obstacle <- obstacles){
  //    if (obstacle.touches(this.bug) || !this.bug.isInBounds){
  //      lost = true
  //    }
  //  }
  //lost
  obstacles.exists(n => n.touches(this.bug) || !this.bug.isInBounds)

  }



}
