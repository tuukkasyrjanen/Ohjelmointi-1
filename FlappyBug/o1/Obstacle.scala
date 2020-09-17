package o1

import constants._

// This class is introduced in Chapter 2.6.

class Obstacle(val radius: Int, var pos: Pos) {

  def approach() = {
    this.pos = this.pos.addX(-ObstacleSpeed)
  }

  override def toString = "center at " + this.pos + ", radius " + this.radius

}
