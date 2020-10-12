package o1.election

import o1.Buffer

import scala.math.Ordering.Double.TotalOrdering  // This will be useful in later assignments.

// Write your code here.
class District(val name: String, val seats: Int, val candidates: Vector[Candidate]){

  override def toString = (s"$name: ${candidates.length} candidates, $seats seats")

  def printCandidates() = {
    for (candidate <- candidates){
      println(candidate.toString)
    }

  }

  def candidatesFrom(party :String) :Vector[Candidate]= {
    val buffer = Buffer[Candidate]()
    var count = 0

    for (candidate <- candidates){
      if (candidate.party == party){
        buffer += candidate
      }
      count += 1
    }
    buffer.toVector
  }

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
    var votesCount = 0
    for (candidate <- candidates){
      if (candidate.party == party){
        votesCount += candidate.votes
      }
    }
    votesCount
  }

  def totalVotes :Int = {
    var votesCount = 0
    for (candidate <- candidates){
        votesCount += candidate.votes
    }
    votesCount
  }
}