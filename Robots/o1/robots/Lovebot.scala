package o1.robots

import o1._

// TODO: proper implementation missing completely
class Lovebot(name: String, body: RobotBody, val beloved: RobotBody) extends RobotBrain(name, body) {


  def moveBody() :Unit = {
    var xDist = (this.location.x - this.beloved.location.x).abs
    var yDist = (this.location.y - this.beloved.location.y).abs

    if(xDist >= yDist && this.location.distance(this.beloved.location) > 1){
      val dir = this.location.xDirectionOf(this.beloved.location)
      dir.foreach(this.body.moveTowards)
    }
    else if(xDist < yDist && this.location.distance(this.beloved.location) > 1){
      val dir = this.location.yDirectionOf(this.beloved.location)
      dir.foreach(this.body.moveTowards)
    }
  }
}



