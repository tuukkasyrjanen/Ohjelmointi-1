package adventure


class Adventure {

  /** The title of the adventure game. */
  val title = "Scala DiscGolfPark"



  /** Game Areas */
  private val entrance = new Area ("Scala DiscGolfPark", "You are at the beginning of the disc golf course. The first tee is north of you and you see a practice area east of you. \nBefore starting you might want to warm up first. After entering the first tee, you cannot come back since that would disturb other players. ")
  private val practiceArea = new Area ("Practice area", "You are approaching the practice areas. You read the sign:\n-------------------\nNorth: Putting area\nEast: Driving range\nSouth: Approach range\nWest: First tee\n-------------------")
  private val practicePutting = new Area ("Practice area - Putting", "||Practicing area for putting||\nYou can ONLY practice putting here.\nSelect (select) disc and practice (practice).")
  private val practiceDriving = new Area ("Practice area - Driving", "||Practicing area for driving||\nYou can ONLY practice driving here.\nSelect (select) disc and practice (practice).")
  private val practiceApproach = new Area ("Practice area - Approach", "||Practicing area for approach shots||\nYou can ONLY practice approach shots here.\nSelect (select) disc and practice (practice).")

  private val firstTee = new Area("First Tee", "You are standing on the first teepad of the course. You read the teesign:\n--- First Tee ---\nStraight fairway\n      100m   \n      Par 3   \n-----------------\nSelect the disc and how will you throw it.")
  private val fairway = new Area("Fairway", "You stand in the fairway. There is still some distance to the basket.\nSelect the disc and how will you throw it.")
  private val tree = new Area("Tree", "Unlucky! Your disk has hit the tree. The tree slightly blocks your vision and there is still some distance to the basket.\nSelect the disc and how will you throw it.")
  private val putting = new Area("Putting", "Your disc lands inside the 10 meter circle of the basket. \nSelect the disc and how will you throw it.")
  private val tapIn = new Area("Tap in", "Your disc is under the basket. You can just tap it in.")
  private val inBasket = new Area("Basket", "Good job! Your disc is inside the basket. Time to move to the next hole.")

  private val secondTee = new Area("Second Tee", "You are standing on the second teepad of the course. You read the teesign:\n--- Second Tee ---\n   Dogleg right\n      133m   \n      Par 4   \n-----------------\nSelect the disc and how will you throw it.")
  private val water = new Area("Water", "Ouch! Your disc landed in the water obstacle. You get a penalty for throwing into the water. Get your disc and continue the fairway.")
  private val fairwayTwo = new Area("Fairway", "You stand in the fairway. There is still some distance to the basket.\nSelect the disc and how will you throw it.")
  private val luckShot = new Area("Lucky shot", "Wow! You have no idea how but the disc has landed just by the basket. All that practicing in the beginning might have given me an extra boost! Now I can just tap the disc in.")
  private val puttingTwo = new Area("Putting", "Your disc lands inside the 10 meter circle of the basket. \nSelect the disc and how will you throw it.")
  private val tapInTwo = new Area("Tap in", "Your disc is under the basket. You can just tap it in.")
  private val inBasketTwo = new Area("Basket", "Good job! Your disc is inside the basket. Time to move to the next hole.")

  private val thirdTee = new Area("Third Tee", "You are standing on the third teepad of the course. You read the teesign:\n--- Third Tee ---\nWooded straight fairway \n      115m   \n      Par 3   \n-----------------\nSelect the disc and how will you throw it.")
  private val teePad = new Area("Bad shot", "Your disc slipped and it only flew 5 meters. You hear other players laughing behind your back. You should probably throw fast and continiue quietly...")
  private val halfway =  new Area("Fairway", "Your disc landed in the middle of the fairway. There is still some distance to the basket.\nSelect the disc and how will you throw it.")
  private val puttingThree = new Area("Putting", "Your disc lands inside the 10 meter circle of the basket. \nSelect the disc and how will you throw it.")
  private val tapInThree = new Area("Tap in", "Your disc is under the basket. You can just tap it in.")
  private val holeInOneThree = new Area("Basket", "HOLE IN ONE! You throw an unbelievable shot straight to the basket. Good job! Time to finish the course.")
  private val luckInBasketThree = new Area("Basket", "Wow! You throw an unbelievable shot straight to the basket. Good job! Time to finish the course.")
  private val inBasketThree = new Area("Basket", "Good job! Your disc is inside the basket. Time to finish the course.")

  private val injured = new Area("Injured", "You tried to do something that you didn't know how and broke your ankle. You have to go to home and leave the course")
  private val finish = new Area("Finish", "You have finished Scala DiscGolfPark!")
  private val destination = finish

  /** List of areas*/
  private val listOfAreas = Vector(entrance, practiceArea, practiceApproach, practiceDriving, practicePutting, firstTee, tree, fairway, putting, tapIn, inBasket, secondTee,
    water, fairwayTwo, luckShot, puttingTwo, tapInTwo, inBasketTwo, thirdTee, teePad, halfway, puttingThree, tapInThree, inBasketThree, holeInOneThree, luckInBasketThree, injured, finish, destination)


  /** The character that the player controls in the game. */
  val player = new Player(entrance, listOfAreas)


/** The areas are updated before every event to make sure that confidence changes affect on the game */
          entrance.setNeighbors(Vector("north" -> firstTee, "east" -> practiceArea                                                 ))
  def updateNeighbors() = {
          entrance.setNeighbors(Vector("north" -> firstTee, "east" -> practiceArea                                                 ))

      practiceArea.setNeighbors(Vector("north" -> practicePutting,"east" -> practiceDriving, "south" -> practiceApproach, "west" -> entrance    ))
   practicePutting.setNeighbors(Vector("south" -> practiceArea                                                                                  ))
   practiceDriving.setNeighbors(Vector("west" -> practiceArea                                                                                   ))
  practiceApproach.setNeighbors(Vector("north" -> practiceArea                                                                                  ))

          firstTee.setNeighbors(Vector("forehand" -> tree, "backhand" -> fairway                                                                ))
              tree.setNeighbors(Vector("forehand" -> (if (this.player.confidenceBoost >= 2) {tapIn}  else {putting}), "backhand" -> putting     ))
           fairway.setNeighbors(Vector("forehand" -> putting, "backhand" -> (if (this.player.confidenceBoost >= 2) {tapIn}  else {putting})     ))
           putting.setNeighbors(Vector("putt" -> (if(this.player.putIn) {inBasket} else {tapIn})                                                ))
             tapIn.setNeighbors(Vector("tap in" -> inBasket                                                                                     ))
          inBasket.setNeighbors(Vector("southeast" -> secondTee                                                                                 ))

         secondTee.setNeighbors(Vector("backhand" -> fairwayTwo, "forehand" -> water, "roller" -> (if (this.player.confidenceBoost >= 4 && this.player.discInHand == "destroyer") {luckShot}  else {water})                   ))
             water.setNeighbors(Vector("forehand" -> fairwayTwo, "backhand" -> (if (this.player.confidenceBoost >= 3) {puttingTwo}  else {fairwayTwo}), "eyesclosed" -> injured                                               ))
        fairwayTwo.setNeighbors(Vector("forehand" -> tapInTwo, "backhand" -> (if (this.player.confidenceBoost >= 3) {inBasketTwo}  else {puttingTwo}), "eyesclosed" -> injured                                                ))
        puttingTwo.setNeighbors(Vector("putt" -> (if(this.player.putIn) {inBasketTwo} else {tapInTwo})                                                                                                                        ))
          tapInTwo.setNeighbors(Vector("tap in" -> inBasketTwo                                                                                                                                                                ))
          luckShot.setNeighbors(Vector("tap in" -> inBasketTwo                                                                                                                                                                ))
       inBasketTwo.setNeighbors(Vector("west" -> thirdTee                                                                                                                                                                     ))

          thirdTee.setNeighbors(Vector("powershot" -> (if (this.player.confidenceBoost >= 5 && this.player.discInHand == "destroyer") {holeInOneThree}  else if(this.player.confidenceBoost >= 3 && this.player.discInHand == "destroyer") {puttingThree} else if(this.player.discInHand == "md3") {halfway} else {teePad})                ))
            teePad.setNeighbors(Vector("powershot" -> (if (this.player.confidenceBoost >= 3 && this.player.discInHand == "destroyer") {luckInBasketThree}  else if(this.player.discInHand == "destroyer") {puttingThree} else {halfway})                                                                                                   ))
           halfway.setNeighbors(Vector("powershot" -> (if(this.player.discInHand == "md3") {tapInThree} else {puttingThree})                                                                                                                                                                                                               ))
        puttingThree.setNeighbors(Vector("putt" ->   (if(this.player.discInHand == "aviar" && this.player.putIn) {inBasketThree} else {tapInThree})                                                                                                                                                                                        ))
          tapInThree.setNeighbors(Vector("tap in" -> inBasketThree                                                                                                                                                                                                                                                                         ))
     inBasketThree.setNeighbors(Vector("south" -> destination                                                                                                                                                                                                                                                                              ))
    holeInOneThree.setNeighbors(Vector("south" -> destination                                                                                                                                                                                                                                                                              ))
 luckInBasketThree.setNeighbors(Vector("south" -> destination                                                                                                                                                                                                                                                                              ))
  }


  /** Scores */
  var score = 0
  var scorePar = 0




  /** Determines if the adventure is complete. */
  def isComplete = {
    (this.player.location == this.destination)
  }

  /** Determines whether the player has won, lost, or quit, thereby ending the game. */
  def isOver = this.isComplete || this.player.hasQuit || this.player.location == injured

  /** Returns a message that is to be displayed to the player at the beginning of the game. */
  def welcomeMessage = "Welcome! You have just entered to the Scala DiscGolfPark. The goal of this round is to get a score even or under par (12).\nYou take your disc golfbag with you and enter the course."


  /** Returns a message that is to be displayed to the player at the end of the game. The message
    * will be different depending on whether or not the player has completed their quest. */
  def goodbyeMessage = {
    if (this.isComplete && this.score <= 12)
      s"You won!\nYou have reached your goal and finished the course with a score of: ${this.score} (${this.scorePar})"

    else if (this.player.location.name != "Injured")
      s"You lost.\nYou finished the course but didn't reach your goals with a score of: ${this.score} (${this.scorePar}).\nBetter luck next time!"

  }


  /** Plays a turn by executing the given in-game command, such as "go west". Returns a textual
    * report of what happened, or an error message if the command was unknown. In the latter
    * case, no turns elapse. */
  def playTurn(command: String) :String = {

    val action = new Action(command)
    val outcomeReport = action.execute(this.player)
    if (outcomeReport.isDefined) {
      this.score = this.player.getPlayerScore
      this.scorePar = this.score - 12
    }
    outcomeReport.getOrElse("Unknown command: \"" + command + "\".")
  }
}