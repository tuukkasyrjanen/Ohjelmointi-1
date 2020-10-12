package o1.football3

import scala.collection.mutable.Buffer


/** The class `Match` represents match results in a football match statistics program.
  * A match is played between teams from two clubs: a home club and an away club.
  * Goals scored by players of either team can be added to the match object with the
  * method `addGoal`.
  *
  * The class is expected to be used so that a match object with no goals is initially
  * created as a real-life match starts. Goals are added incrementally as the match
  * progresses. (A match object has mutable state.)
  *
  * @param home  the club whose team plays at home in the match
  * @param away  the club whose team plays away in the match */
class Match(val home: Club, val away: Club) {

  private val homeScorers = Buffer[Player]()    // container: goalscorers of the home team are added here
  private val awayScorers = Buffer[Player]()    // container: goalscorers of the away team are added here

  private var awayCount = 0
  private var homeCount = 0

  def winnerName = {
//    winner match {
//      case Some(thing) => if (this.goalDifference < 0) this.away.name else this.home.name
//      case None        => "no winner"
//    }

    if (this.goalDifference < 0)
      this.away.name
    else if (this.goalDifference > 0)
      this.home.name
    else {
     "no winner"
    }
  }

  def addGoal(scorer: Player): Unit = {
    if (scorer.employer == this.home) {
      this.homeScorers += scorer
      this.homeCount += 1
    }
    else if (scorer.employer == this.away){
      this.awayScorers += scorer
      this.awayCount += 1
    }
  }

  def allScorers :Vector[Player] = ((this.homeScorers ++ this.awayScorers).toVector)

  def awayGoals = this.awayCount

  def goalDifference = (this.homeCount - this.awayCount)

  def hasScorer(possibleScorer: Player) = (allScorers.contains(possibleScorer))

  def homeGoals = this.homeCount

  def isAwayWin = (this.goalDifference < 0)

  def isGoalless = (totalGoals == 0)

  def isHigherScoringThan(anotherMatch: Match) = (this.totalGoals > anotherMatch.totalGoals)

  def isHomeWin = (this.goalDifference > 0)

  def isTied = (this.goalDifference == 0)

  def location = (this.home.stadium)

  def winner :Option[Club] = {
    if (isHomeWin) {
      Some(home)
    }else if (isAwayWin) {
      Some(away)
    }else{
      None
    }
  }

  def winningScorer :Option[Player] = {

    if (goalDifference > 0){
     homeScorers.lift(this.awayCount)

    }
    else if (goalDifference < 0){
      awayScorers.lift(this.homeCount)
    }
    else {
      None
    }
  }

  override def toString = {
    var text = ""
    if (isGoalless) {
      text = s"${this.home.name} vs. ${this.away.name} at ${this.home.stadium}: tied at nil-nil"
    }
    else if (isTied) {
      text = s"${this.home.name} vs. ${this.away.name} at ${this.home.stadium}: tied at ${this.homeCount}-all"
    }
    else if (goalDifference > 0) {
      text = s"${this.home.name} vs. ${this.away.name} at ${this.home.stadium}: ${this.homeCount}-${this.awayCount} to ${this.home.name}"
    }
    else if (goalDifference < 0) {
      text = s"${this.home.name} vs. ${this.away.name} at ${this.home.stadium}: ${this.awayCount}-${this.homeCount} to ${this.away.name}"
    }
    text
  }

  def totalGoals = (this.homeCount + this.awayCount)



}


