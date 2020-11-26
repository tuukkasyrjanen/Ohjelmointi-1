package o1.peeveli

import GameState.Unrevealed


/** Each instance of class `GameState` represents a single state within the Peeveli variant of
  * Hangman: What does the (partially visible) target word look like to the guesser? How many
  * incorrect guesses can the guesser still make? Which guesses have already been made? Moreover,
  * our dishonest hangman needs an additional piece of information: Which words are still credible
  * solutions given the earlier guesses?
  *
  * Chapter 9.3 of the ebook has a detailed discussion of the internal logic of the Peeveli game.
  *
  * While a player plays a game of Peeveli, the game will move from one state to another. Even so,
  * each `GameState` object is immutable. Each successive state is represented by a new `GameState`
  * object, which is generated by calling the current state's `guessLetter` method.
  *
  * @param missesAllowed      the number of incorrect guesses that the guesser can still make before
  *                           losing the game. A negative number means that the game is over.
  * @param previousGuesses    a string that contains all the previously guessed characters in order
  * @param visibleWord        the version of the target word that is visible to the guesser. In a
  *                           state that represents the beginning of the game, this will consist
  *                           of unrevealed characters only (e.g., `"_____"`; see [[GameState$ Unrevealed]]).
  *                           In later states, more and more characters will be visible (e.g., `"C___O"`).
  * @param possibleSolutions  all the words in the game's vocabulary that match the `visibleWord`
  *                           parameter and are therefore plausible correct solutions */
class GameState(val missesAllowed: Int, val previousGuesses: String, val visibleWord: String, val possibleSolutions: Vector[String])  {


  /** Creates a new `GameState` that represents the initial state of a new game of Peeveli.
    * All the letters of the target word are unrevealed.
    *
    * Note to students: This is an additional constructor for the class (see optional materials
    * in Chapter 4.1). You don’t need to use it.
    *
    * @param missesAllowed  the number of incorrect guesses the guesser is allowed to make
    * @param length         the number of characters in the target word that the guesser will look for
    * @param vocabulary     a collection of known words; all words of exactly `length` characters in
    *                       the vocabulary are potential target words */
  def this(missesAllowed: Int, length: Int, vocabulary: Vector[String]) = {
    this(missesAllowed, "", Unrevealed.toString * length, vocabulary.map( _.toUpperCase )) // This means: pass these parameters to the "default constructor" defined in the class header.
  }


  //private var amountOfMisses = 0

  /** Returns the length of the target word. */
  def wordLength = this.visibleWord.length


  /** Returns the number of all known words that are (still) possible solutions to this
    * game of Peeveli, given the guesses that have already been made. */
  def numberOfSolutions = this.possibleSolutions.size


  /** Returns `true` if the player has missed with more guesses than allowed and has therefore
    * lost the game; returns `false` otherwise. */
  def isLost = this.missesAllowed < 0  // TODO: replace with a proper implementation


  /** Returns `true` if the guesser has won the game, that is, if they haven't missed too many
    * times and all the letters in the target word are visible. Returns `false` otherwise. */
  def isWon = (!this.visibleWord.contains('_') && !isLost) // TODO: replace with a proper implementation


  /** Returns a version of the currently visible target word so that additional characters are
    * revealed as indicated by the given pattern. For example, if `visibleWord` is `"C___O"`
    * and the pattern is `"__LL_"`, returns `"C_LLO"`. This method assumes that it receives
    * a parameter of equal length as the target word. */
  private def reveal(patternToReveal: String) = { // TODO: replace with a proper implementation and use this to implement guessLetter
    var outputText = ""
    for (i <- 0 until patternToReveal.length){
      if (patternToReveal(i) == '_'){
        outputText = outputText + this.visibleWord(i)
      }else{
        outputText = outputText + patternToReveal(i)
      }
    }
    outputText
  }

  /** Returns a new `GameState` that follows this current one given that the guesser guesses a
    * particular letter. The rationale behind moving from one state to another is described in
    * Chapter 9.3 of the ebook.
    *
    * The next `GameState` will certainly have one more letter in [[previousGuesses]] than the
    * present one. In addition, it may have more visible letters in the target word, fewer misses
    * allowed, and/or fewer potential solutions remaining.
    *
    * The player will always spend a missed solution attempt if the guess did not reveal any new
    * letters. This happens even if the player had already guessed the same letter before.
    *
    * @param guess  a guessed letter; this can be in either case but is always interpreted as an upper-case character
    * @return the state of the game after the newest guess */
  def guessLetter(guess: Char) :GameState = {
    val actualGuess = guess.toUpper
    var solutionVector = Vector[String]()
    var misses = this.missesAllowed
    var currentGuess = ""

    def makeWord(word: String): String ={
      word.map(n => if(n != actualGuess) '_' else n)
    }
    
    var a = this.possibleSolutions.groupBy(pari => makeWord(pari))
    a = a.map(pari => reveal(pari._1) -> pari._2)
    var b = a.toVector.sortBy(-_._2.length)

    if(b.head._1 == this.visibleWord){
      misses -= 1
      currentGuess = this.visibleWord
      solutionVector = b.head._2
    }else{
      currentGuess = b.head._1
      solutionVector = b.head._2
    }
    new GameState(misses, this.previousGuesses + actualGuess, currentGuess, solutionVector)
  }


  /** Returns a string description of the game state. */
  override def toString =
    this.visibleWord + ", " +
      "missed guesses allowed: " + this.missesAllowed + ", " +
      "guesses made: " + (if (this.previousGuesses.isEmpty) "none" else this.previousGuesses) + ", " +
      "solutions left: " + this.numberOfSolutions

}


/** This companion object just provides a single constant.
  * @see class [[GameState]] */
object GameState {

  /** the character that is used by Peeveli to signify unrevealed letters */
  val Unrevealed = '_'

}
