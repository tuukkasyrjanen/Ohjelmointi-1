package adventure

/** The class `Action` represents actions that a player may take in a text adventure game.
  * `Action` objects are constructed on the basis of textual commands and are, in effect,
  * parsers for such commands. An action object is immutable after creation.
  * @param input  a textual in-game command such as "go east" or "rest" */
class Action(input: String) {

  private val commandText = input.trim.toLowerCase
  private val verb        = commandText.takeWhile( _ != ' ' )
  private val modifiers   = commandText.drop(verb.length).trim


  /** Causes the given player to take the action represented by this object, assuming
    * that the command was understood. Returns a description of what happened as a result
    * of the action (such as "You go west."). The description is returned in an `Option`
    * wrapper; if the command was not recognized, `None` is returned. */
  def execute(actor: Player) = this.verb match {
    case "go"       => Some(actor.go(this.modifiers))
    case "quit"     => Some(actor.quit())
    case "get"      => Some(actor.get(this.modifiers))
    case "examine"  => Some(actor.examine(this.modifiers))
    case "drop"     => Some(actor.drop(this.modifiers))
    case "inventory"=> Some(actor.inventory)
    case "practice" => Some(actor.practiceThrow())
    case "select"   => Some(actor.selectDisc(this.modifiers))
    case "throw"    => Some(actor.realThrow(this.modifiers))
    case "walk"     => Some(actor.walkToTheDisc())
    case "advance"  => Some(actor.advanceToBasket())
    case "return"   => Some(actor.takeOrKeepLostDisc(verb))
    case "keep"     => Some(actor.takeOrKeepLostDisc(verb))
    case "yes"      => Some(actor.throwingCompetition(verb))
    case "no"       => Some(actor.throwingCompetition(verb))
    case "help"     => Some(help(this.modifiers))
    case other      => None
  }

  def help(action: String) :String = {
    if (action == ""){
      "The goal of the game is to finish the disc golf course with a score equal or under par.\n" +
      "The available commands are: (go, rest, quit, get, examine, drop, inventory, practice, select, throw, walk, advance). Type 'help <command>' to get more information about the command.\n" +
        "In addition there are four situational commands (return, keep, yes, no), which are used accordingly to a seperate guidance."
    }
    else{
      val map = Map("go"-> "Used for moving. Example: go north", "quit" -> "Quits the game.", "get" -> "Pick up item from the ground. Example: get aviar", "examine" -> "Examine an item. Example: examine aviar",
        "drop" -> "Drop item from inventory.", "inventory" -> "Lists the items which you are holdiing at the moment.", "practice" -> "Used for throwing discs in the practice area after selecting the disc.",
        "select" -> "Used for selecting a disc before throwing. You must select a disc before you can throw. Example: select aviar.",
        "throw" -> "Used to throw a disc with a given throwing styles. A disc must be selected before throwing. Example: throw backhand", "walk" -> "After throwing, use this to walk to your disc",
        "advance" -> "Used when you can advance straight to the basket and skip other areas. This is due to random event for example. The user is told when he can advance straight to the basket")

      map.getOrElse(action, "Unknown command.")
    }
  }


  /** Returns a textual description of the action object, for debugging purposes. */
  override def toString = this.verb + " (modifiers: " + this.modifiers + ")"

}

