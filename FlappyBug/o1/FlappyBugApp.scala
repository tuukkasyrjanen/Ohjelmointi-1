package o1

import constants._

// This class is introduced in Chapter 2.7.

object FlappyBugApp extends App {

  val sky         = rectangle(ViewWidth, ViewHeight,  LightBlue)
  val ground      = rectangle(ViewWidth, GroundDepth, SandyBrown)
  val trunk       = rectangle(30, 250, SaddleBrown)
  val foliage     = circle(200, ForestGreen)
  val tree        = trunk.onto(foliage, TopCenter, Center)
  val rootedTree  = tree.onto(ground, BottomCenter, new Pos(ViewWidth / 2, 30))
  val scenery     = sky.place(rootedTree, BottomLeft, BottomLeft)
  val bugPic      = Pic("ladybug.png")
  val moveScenery = 2

  def rockPic(obstacle: Obstacle) = circle(obstacle.radius * 2, Black)

  val newGame = new Game

  val gui = new View(newGame,"FlappyBug") {
    var background = scenery

    def makePic = {
      //var pic = background
      // (background.place(bugPic,newGame.bug.pos)).place(rockPic(newGame.obstacle),newGame.obstacle.pos)
      //for(obstacle <- newGame.obstacles){
      //  pic = pic.place(rockPic(obstacle), obstacle.pos)
      //}
      //pic.place(bugPic,newGame.bug.pos)

      //newGame.obstacles.foreach(next => pic = pic.place(rockPic(next), next.pos))

      var pic = newGame.obstacles.foldLeft(background)((pic, next) => pic.place(rockPic(next), next.pos))
      pic.place(bugPic,newGame.bug.pos)


    }
    override def onKeyDown(painettu: Key) = {
      if (painettu == Key.Space)
        newGame.activateBug()
    }
    override def onTick() = {
      newGame.timePasses()
      this.background = this.background.shiftLeft(moveScenery)
    }

    override def isDone = newGame.isLost
  }


  gui.start()


}

