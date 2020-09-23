package o1

import constants._

// This class is introduced in Chapter 2.6.

class Obstacle(val radius: Int, private var currentPos: Pos) {

  def pos =  this.currentPos

  def approach() = {
    this.currentPos = this.currentPos.addX(-ObstacleSpeed)
  }

  override def toString = "center at " + this.currentPos + ", radius " + this.radius

}
