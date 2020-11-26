package adventure

import o1.util.Random

import scala.collection.mutable.Map

/** The class `Area` represents locations in a text adventure game world. A game world
  * consists of areas. In general, an "area" can be pretty much anything: a room, a building,
  * an acre of forest, or something completely different. What different areas have in
  * common is that players can be located in them and that they can have exits leading to
  * other, neighboring areas. An area also has a name and a description.
  * @param name         the name of the area
  * @param description  a basic description of the area (typically not including information about items) */
class Area(var name: String, var description: String) {

  private val neighbors = Map[String, Area]()
  private var itemList = Vector[Item]()

  def playerInPracticeArea = (this.name == "Practice area - Putting" || this.name == "Practice area - Driving"|| this.name == "Practice area - Approach")
  def playerInCourse = (!playerInPracticeArea && this.name != "Scala DiscGolfPark" && this.name != "Practice area")
  def playerCanThrow = (playerInCourse && this.name != "Basket")
  def itemsOnGround = itemList

    /** This randomizes the next exit */
  def randomizeExit() :Area = {

    val map = this.neighbors
    var randomVector = Vector[Area]()
    var count = 0

    for(a <- map){
      if (a._2.name != "Injured"){ //TODO EHKÄ ERRORi
        randomVector = randomVector :+ a._2
        count += 1
      }
    }
    randomVector = Random.shuffle(randomVector)
    randomVector(Random.nextInt(count))
  }

  /** Returns the area that can be reached from this area by moving in the given direction. The result
    * is returned in an `Option`; `None` is returned if there is no exit in the given direction. */
  def neighbor(direction: String) = this.neighbors.get(direction)


  /** Adds an exit from this area to the given area. The neighboring area is reached by moving in
    * the specified direction from this area. */
  def setNeighbor(direction: String, neighbor: Area) = {
    this.neighbors += direction -> neighbor
  }


  /** Adds exits from this area to the given areas. Calling this method is equivalent to calling
    * the `setNeighbor` method on each of the given direction--area pairs.
    * @param exits  contains pairs consisting of a direction and the neighboring area in that direction
    * @see [[setNeighbor]] */
  def setNeighbors(exits: Vector[(String, Area)]) = {
    this.neighbors ++= exits
  }


  /** Returns a multi-line description of the area as a player sees it. This includes a basic
    * description of the area as well as information about exits and items. The return
    * value has the form "DESCRIPTION\n\nExits available: DIRECTIONS SEPARATED BY SPACES".
    * The directions are listed in an arbitrary order. */
  def fullDescription = {
    val exitList = "\n\nExits available: " + this.neighbors.keys.mkString(" ")
    val throwList = "\n\nThrowing styles: " + this.neighbors.keys.mkString(" ")

    var tempList = Vector[String]()
    if(itemList.isEmpty){
      if(!playerCanThrow) {
        this.description + exitList
      }
      else{
        this.description + throwList
      }
    }
    else{
      for(item <- itemList) {
        tempList = itemList.map(n => n.name)
      }
      this.description + s"\nYou see here: ${tempList.mkString(" ")}" + exitList
    }
  }

  def possibleExitDirections = {
    val exitDirections = Vector[String]()
    this.neighbors.keys.foreach(n => exitDirections :+ n)
    exitDirections
  }

  def possibleExitAreas = {
    val exitAreas = Vector[Area]()
    for(element <- this.neighbors.toVector){
      exitAreas :+ element._2
    }
    exitAreas
  }

  /** Returns a single-line description of the area for debugging purposes. */
  override def toString = this.name + ": " + this.description.replaceAll("\n", " ").take(150)


  def contains(itemname :String) : Boolean = this.itemList.forall(_.name == itemname)

  def addItem(item: Item) :Unit = (itemList = itemList :+ item)

  def removeItem(itemName: String): Option[Item] = {
    for (item <- itemList){
     if (item.name == itemName){
       itemList = itemList.filter(_!=item)
       return Some(item)
     }
    }
    None
  }
}
