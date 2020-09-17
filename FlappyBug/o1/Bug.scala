package o1

import constants._

class Bug(var pos: Pos) {

  val radius = BugRadius

  def fall() = {
    this.pos = this.pos.addY(FallingSpeed)
  }

  def flap(nousu: Double) ={
    this.pos = this.pos.addY(-nousu)
  }

  override def toString = "center at " + this.pos + ", radius " + this.radius


}

