package o1.football3

import scala.collection.mutable.Buffer

class Season() {

  private val listOfMatches = Buffer[Match]()
  private var bigWin: Option[Match] = None
  private var biggestScore = 0

  def addResult(newResult: Match): Unit = {
    listOfMatches += newResult

    this.bigWin match {
      case None =>
        this.bigWin = Some(newResult)
        this.biggestScore = newResult.goalDifference.abs
      case Some(oldBigWin) =>
        if (this.biggestScore < newResult.goalDifference.abs){
          this.biggestScore = newResult.goalDifference.abs
          this.bigWin = Some(newResult)
        }
    }
  }

  def biggestWin: Option[Match] ={
    this.bigWin
  }

  def latestMatch: Option[Match] ={

    if (numberOfMatches == 0) {
      None
    } else{
      listOfMatches.lift(numberOfMatches-1)
    }
  }


  def matchNumber(number: Int): Option[Match] ={
    if (numberOfMatches == 0){
      None
    }else{
      listOfMatches.lift(number)
    }
  }

  def numberOfMatches: Int ={
    listOfMatches.size
  }

}