package o1.election

import o1.Buffer

import scala.math.Ordering.Double.TotalOrdering  // This will be useful in later assignments.

// Write your code here.
class District(val name: String, val seats: Int, val candidates: Vector[Candidate]){

  override def toString = (s"$name: ${candidates.length} candidates, $seats seats")

  def printCandidates() = candidates.foreach(n => println(n.toString))


  def candidatesFrom(party :String) :Vector[Candidate] =  candidates.filter(n => n.party == party)

  def topCandidate :Candidate = {
    var mostVotes = 0
    var topCandidate = candidates(0)

    for (candidate <- candidates){
      if (candidate.votes > mostVotes){
        topCandidate = candidate
        mostVotes = candidate.votes
      }
    }
    topCandidate
  }

  def totalVotes(party :String) :Int = {
    candidates.foldLeft(0)((sum, next) => if(next.party == party) {sum + next.votes} else{sum})
  }

  def totalVotes :Int = {
    candidates.foldLeft(0)((sum, next) => sum + next.votes)
  }
}