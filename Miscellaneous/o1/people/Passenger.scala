package o1.people

class Passenger(val name: String, val card: Option[TravelCard]) {

  def hasCard :Boolean = {
    card match {
      case Some(thing) => true
      case None        => false
    }
  }

  def canTravel :Boolean = {
    card match {
      case Some(thing) => thing.isValid
      case None        => false
    }
  }




}
