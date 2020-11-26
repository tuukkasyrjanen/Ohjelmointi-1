package o1.election

import o1.Buffer

import scala.math.Ordering.Double.TotalOrdering  // This will be useful in later assignments.

// Write your code here.
class District(val name: String, val seats: Int, val candidates: Vector[Candidate]){

  override def toString = (s"$name: ${candidates.length} candidates, $seats seats")

  def printCandidates() = candidates.foreach(n => println(n.toString))


  def candidatesFrom(party :String) :Vector[Candidate] =  candidates.filter(n => n.party == party)

  def topCandidate :Candidate = {

    this.candidates.sortBy(_.votes).head
    //var mostVotes = 0
    //var topCandidate = candidates(0)
    //for (candidate <- candidates){
      //if (candidate.votes > mostVotes){
        //topCandidate = candidate
        //mostVotes = candidate.votes
      //}
    //}
    //topCandidate
  }

  def totalVotes(party :String) :Int = {
    candidates.foldLeft(0)((sum, next) => if(next.party == party) {sum + next.votes} else{sum})
  }

  def totalVotes :Int = {
    candidates.foldLeft(0)((sum, next) => sum + next.votes)
  }

  def candidatesByParty: Map[String, Vector[Candidate]] = {
    this.candidates.groupBy(n => n.party)
  }

  def topCandidatesByParty: Map[String, Candidate] = {
    candidatesByParty.map( pari => pari._1 -> pari._2.maxBy(_.votes))
  }

  def votesByParty: Map[String, Int] = {
    candidatesByParty.map( pari => pari._1 -> pari._2.foldLeft(0)( _ + _.votes ))
  }

  def rankingsWithinParties: Map[String, Vector[Candidate]] = {
    candidatesByParty.map( pari => pari._1 -> pari._2.sortBy(-_.votes))
  }

  def rankingOfParties: Vector[String] = {
    votesByParty.toVector.sortBy(-_._2).map(_._1)

  }

  def distributionFigures: Map[Candidate, Double] = {
    var vecCand = Vector[Candidate]()
    var vecDouble = Vector[Double]()
    var a = rankingsWithinParties.toVector.map(_.swap)
    for (item <- a){
      var count = 0
      for (cand <- item._1){
        vecCand = vecCand :+ cand
        vecDouble = vecDouble :+ (votesByParty(item._2).toDouble / (count + 1).toDouble)
        count += 1
      }
    }
    vecCand.zip(vecDouble).toMap
  }

  def electedCandidates: Vector[Candidate] = {
    var candVector = Vector[Candidate]()
    val a = distributionFigures.toVector.sortBy(pari => -pari._2)
    var count = 0
    for (item <- a.take(seats)){
      candVector = candVector :+ item._1
    }
    candVector
  }
}