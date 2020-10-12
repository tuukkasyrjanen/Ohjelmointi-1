package o1.people

class Member(val id: Int, val name: String, val yearOfBirth: Int, val yearOfDeath: Option[Int]) {

  def isAlive :Boolean = {
    yearOfDeath match {
      case Some(joku) => false
      case None       => true
    }
  }

  override def toString = {
    yearOfDeath match {
      case Some(joku) => s"$name($yearOfBirth-$joku)"
      case None       => s"$name($yearOfBirth-)"
    }
  }


}
