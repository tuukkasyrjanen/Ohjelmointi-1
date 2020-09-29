package o1

import constants._

class Bug(private var currentPos: Pos) {
  val radius = BugRadius
  private var yVelocity = 0.0
  def pos = this.currentPos

  def fall() = {
    if (pos.y != 350) {
      yVelocity = yVelocity + 2
    }
    move(yVelocity)
  }
  def flap(nousu: Double) ={
    yVelocity = -nousu

  }
  def move(arvo :Double)= {
    this.currentPos = this.currentPos.addY(arvo).clampY(0,350)
  }
  override def toString = "center at " + this.currentPos + ", radius " + this.radius


}

