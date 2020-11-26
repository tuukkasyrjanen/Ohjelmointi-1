package adventure

import o1.Buffer
import o1.util.Random


/** A `Player` object represents a player character controlled by the real-life user of the program.
  *
  * A player object's state is mutable: the player's location and possessions can change, for instance.
  *
  * @param startingArea  the initial location of the player */
class Player(startingArea: Area, availableAreas: Vector[Area]) {


  /** Private variables of the game */
  private var currentLocation = startingArea        // gatherer: changes in relation to the previous location
  private var quitCommandGiven = false              // one-way flag
  private var inventoryList = Vector[Item]()
  private var canUsePracticeArea = true
  private var confidence = Buffer(0,0,0,0,0)
  private var selectedDisc = ""
  private var nextLocation = startingArea
  private var isDiscThrown = false
  private var isAllowedToAdvance = false
  private var playerScore = 0
  private var lostDiscFound = false
  private var competition = false



  /** Add discs to the inventory */
  inventoryList = inventoryList :+ new Item("aviar", "Putter. Speed: 2, Glide: 3, Turn: 0, Fade: 1") :+
    new Item("md3", "Approach disc. Speed: 5, Glide: 5, Turn: 0, Fade: 2") :+
    new Item("destroyer", "Distance driver. Speed: 12, Glide: 5, Turn: -1, Fade: 3")

  private def inventoryToString = inventoryList.map(_.name)

  def confidenceBoost = confidence.sum
  def getPlayerScore = playerScore
  def discInHand = selectedDisc


  /** Determines if the player has indicated a desire to quit the game. */
  def hasQuit = this.quitCommandGiven


  /** Returns the current location of the player. */
  def location = this.currentLocation


  /** Attempts to move the player in the given direction. This is successful if there
    * is an exit from the player's current location towards the direction name. Returns
    * a description of the result: "You go DIRECTION." or "You can't go DIRECTION." */
  def go(direction: String) :String = {
    var destination = this.location.neighbor(direction)
    if (direction != "north" && direction != "south" && direction != "east" && direction != "west" && direction != "southeast") destination = this.location.neighbor("NotDefined")
    this.currentLocation = destination.getOrElse(this.currentLocation)
    if (destination.isDefined) "You go " + direction + "." else "You can't go " + direction + "."
  }


  /** Signals that the player wants to quit the game. Returns a description of what happened within
    * the game as a result (which is the empty string, in this case). */
  def quit() :String = {
    this.quitCommandGiven = true
    "Quitter!"
  }


  /** Returns a brief description of the player's state, for debugging purposes. */
  override def toString = "Now at: " + this.location.name

  def drop(itemName: String): String = {
    if (inventoryToString.contains(itemName)){
      val dItem = inventoryToString.indexOf(itemName)
      this.currentLocation.addItem(inventoryList(dItem))
      inventoryList = inventoryList.filter(_!=inventoryList(dItem))

      s"You drop the $itemName."
    }
    else{
      "You don't have that!"
    }
  }

  def examine(itemName: String): String = {
    if(inventoryToString.contains(itemName)){
      val dItem = inventoryToString.indexOf(itemName)
      s"You look closely at the $itemName.\n${inventoryList(dItem).description}}"
    }
    else{
      "If you want to examine something, you need to pick it up first."
    }
  }

  def get(itemName: String): String = {
    val a = this.currentLocation.removeItem(itemName)
    if(a.isDefined){
      inventoryList = inventoryList :+ a.get
      s"You pick up the $itemName."
    }
    else {
     s"There is no $itemName here to pick up."
    }
  }

  def inventory : String = {
    if (inventoryList.isEmpty){
      "You are empty-handed."
    }
    else{
      s"You are carrying:\n${inventoryToString.mkString("\n")}"
    }
  }

  def has(itemName: String): Boolean = inventoryToString.contains(itemName)


  /** Selects the dics which the player throws with */
  def selectDisc(discName: String) :String = {
    if(this.inventoryToString.contains(discName) && selectedDisc.isEmpty){
      selectedDisc = discName
      s"You select $discName and are ready to throw."
    }
    else if (!selectedDisc.isEmpty) {
     s"You have selected a disc already."
    }
    else {
     s"You don't have $discName in your bag."
    }
  }
  /** Throwing function */
  def realThrow(style: String) :String = {
    val throwStyle = this.currentLocation.neighbor(style)
    val discName = selectedDisc
    val possibleLocation = this.currentLocation.neighbor(style)

    if(this.currentLocation.playerCanThrow) {

      if(!discName.isEmpty && throwStyle.isDefined && possibleLocation.isDefined){
        nextLocation = possibleLocation.get
        val dItem = inventoryToString.indexOf(discName)
        nextLocation.addItem(inventoryList(dItem))
        inventoryList = inventoryList.filter(_!=inventoryList(dItem))
        selectedDisc = ""
        isDiscThrown = true
        playerScore += 1
        s"Swoosh! You throw $style shot with your $discName. The disc lands somewhere. You should walk to your disc to see where it landed (walk)."
      }
      else if (selectedDisc.isEmpty) {
        "You haven't selected a disc."
      }
      else if (style.filterNot((x: Char) => x.isWhitespace) == ""){
        "You haven't decided how you throw the disc!"
      }
      else{
        "You should rethink your throwing style."
      }
    }
    else{
      "You can't throw here!"
    }
  }

  def practiceThrow() : String = {
    if(!this.currentLocation.playerInPracticeArea){
      "You are not in the practice area!"
    }
    else if(!selectedDisc.isEmpty) {

      if (canUsePracticeArea && this.currentLocation.name == "Practice area - Putting" && selectedDisc == "aviar"){
        selectedDisc = ""
        confidence(0) = 1
      "You throw your putter and fetch it back. You gain some confindence in your putting."
      }
      else if(canUsePracticeArea && this.currentLocation.name == "Practice area - Approach" && selectedDisc == "md3"){
        selectedDisc = ""
        confidence(1) = 1
        "You throw your approach disc and fetch it back. You gain some confindence in your approach shots."
      }
      else if(canUsePracticeArea && this.currentLocation.name == "Practice area - Driving" && selectedDisc == "destroyer"){
        selectedDisc = ""
        confidence(2) = 1
        "You throw your driver and fetch it back. You gain some confindence in your driving."
      }
      else if(canUsePracticeArea){
        canUsePracticeArea = false
        "You broke the rules of the practicing area and you are not allowed to throw discs in the practicing area anymore."
      }
      else{
        "You have broken the rules of the practicing area and you are no longer allowed to use it."
      }
    }
    else {
      "Select a disc before practicing!"
    }
  }

  def walkToTheDisc() :String = {

    if (isDiscThrown){
      this.currentLocation = nextLocation
      isDiscThrown = false

      if(this.currentLocation.name == "Water"){
        playerScore += 1
      }

      if (randomEvent && this.currentLocation.name != "Injured"){
        val i = Random.nextInt(3)
        if(i == 1) squirrel()
        else if (i == 2) lostDisc
        else distanceCompetition
      }
      else if (this.currentLocation.name != "Injured"){
        "You walk up to your disc."
      }
      else{
        "You lost the game!"
      }
    }
    else {
      "You should throw first before you move."
    }
  }

  /** Moves player straight to basket once called. For example in the case of hole in one */
  def advanceToBasket() :String ={
    if(isAllowedToAdvance){
      isAllowedToAdvance = false
      this.currentLocation = getBasketLocation
      "You have advanded to the basket."
    }
    else{
      "You are not allowed to advance straight to the basket! Select a disc and throw"
    }
  }

  /** Returns the location of the nearest basket => the current fairway's basket */
  def getBasketLocation = {
    var loop = true
    var tempLocation = this.currentLocation
    val tempVector = availableAreas.drop(availableAreas.indexOf(currentLocation))
    for(area <- tempVector) {
      if (area.name == "Tap in" && loop){
        tempLocation = area
        loop = false
      }
    }
    tempLocation
  }

  def putIn :Boolean ={
    val a = 4 + confidenceBoost
    val b = 10 - a
    val vector = Vector.fill(a)(true) ++ Vector.fill(b)(false)
    Random.shuffle(vector)
    vector(Random.nextInt(vector.size))
  }

  /** 1/10 possibility for a random event after every throw */
  def randomEvent : Boolean ={
    val RandomEventVector = Vector.fill(9)(false) :+ true
    Random.shuffle(RandomEventVector)
    RandomEventVector(Random.nextInt(RandomEventVector.size))
  }

  /** Possible random events listed below */
  def squirrel() :String = {
    var name = ""
    if (this.currentLocation.itemsOnGround.nonEmpty){
      name = this.currentLocation.itemsOnGround(0).name
      val disc = this.currentLocation.removeItem(name)
      if(disc.isDefined){
      getBasketLocation.addItem(disc.get)
    }
    isAllowedToAdvance = true
    "What the heck? As you approach your disc you see a squirrel which steals your disc!\nIt runs towards the basket and crashes into the basket while the disc is blocking its view. Squirrel got scared, left the disc under the basket and runs away.\nYou quietly accept as this just improves your score. You can advance straight to the basket (advance)."
    }
    else{
      ""
    }
  }

  def takeOrKeepLostDisc(action: String) :String = {
    if (lostDiscFound) {
      if (action == "keep"){
        this.confidence(3) = -1
        val disc = this.currentLocation.removeItem("fd3")
        inventoryList = inventoryList :+ disc.get
        lostDiscFound = false
        "You decide to keep it. Deep inside you know you shouldn't have but atleast you have a new disc now. This affects your confidence negatively."
      }
      else if (action == "return"){
        this.confidence(3) = 1
        val disc = this.currentLocation.removeItem("fd3")
        lostDiscFound = false
        "You call the owner and he comes to claim the disc. He thanks and hugs you. You feel good and this increases your confidence."
      }
      else{
        "Will you return or keep it?\n\nYou find a disc on the ground with a phone number on it. Somebody has probably lost it...\nHmm... Do I keep it (keep) or call and return it to the owner (return)?"
      }
    }
    else{
      s"Unknown command: $action"
    }
  }
  def lostDisc :String = {
    this.currentLocation.addItem(new Item("fd3", "This fairway driver isn't yours. There's someone's phone number written on the disc. Speed: 9, Glide: 4, Turn: 0, Fade: 3"))
    lostDiscFound = true
    "You find a disc on the ground with a phone number on it. Somebody has probably lost it...\nHmm... Do I keep it (keep) or call and return it to the owner (return)?"
  }

  def throwingCompetition(action: String) :String = {
    if (competition){
      if (action == "yes"){
        val randDistanceYou = Random.nextInt(180+confidenceBoost)
        val randDistanceOpponent = Random.nextInt(180)
        if(randDistanceYou > randDistanceOpponent){
          confidence(4) = 1
          competition = false
          s"You won the distance competition! You threw $randDistanceYou meters and your opponent threw $randDistanceOpponent meters. You feel good and this increases your confidence."
        }
        else if(randDistanceYou < randDistanceOpponent){
          competition = false
          confidence(4) = -1
          s"You lost the distance competition. You threw $randDistanceYou meters and your opponent threw $randDistanceOpponent meters. You feel miserable after your loss and this decreases your confidence."
        }
        else {
          "It's a tie! This has no effect on your confidence."
        }
      }
      else if (action == "no"){
        "You don't want to compete and decided to focus on your own game."
      }
      else{
        "Will you accept the challenge?\n\nAnother discgolfer approaches to you and proposes a distance throwing competition. Do you want to compete (yes / no)?"
      }
    }
    else{
      s"Unknown command: $action"
    }
  }

  def distanceCompetition :String = {
     competition = true
    "Another discgolfer approaches to you and proposes a distance throwing competition. Do you want to compete (yes / no)?"
  }
}


