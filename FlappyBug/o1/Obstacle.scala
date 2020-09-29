package o1

import constants._
import scala.util.Random

// This class is introduced in Chapter 2.6.

class Obstacle(val radius: Int) {

  private var currentPos = randomLaunchPosition()
  def pos =  this.currentPos

  def isActive = (this.currentPos.x >= -this.radius)

  private def randomLaunchPosition() = {
    val launchX = ViewWidth + this.radius + Random.nextInt(499)
    val launchY = Random.nextInt(499)
    new Pos(launchX, launchY)
  }

  def approach() = {
    if (isActive) {
      this.currentPos = this.currentPos.addX(-ObstacleSpeed)
    }
    else{
      this.currentPos = randomLaunchPosition()
    }
  }

  override def toString = "center at " + this.currentPos + ", radius " + this.radius

  def touches(bug: Bug) = {
    (this.radius + bug.radius) > this.pos.distance(bug.pos)
  }

}
