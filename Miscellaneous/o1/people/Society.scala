package o1.people

import scala.collection.mutable.Map

// This class is introduced in Chapter 8.4. You can ignore it until then.

class Society(val name: String) {

  private val members = Map[Int, Member]()

  def add(newMember: Member) = {
    this.members(newMember.id) = newMember
  }

  def getByID(memberID: Int) = this.members.get(memberID)

  // TODO: add yearOfDeath as instructed in Chapter 8.4.

  def yearOfDeath(id :Int) :Option[Int] ={
    val memberDead = members.get(id)
    memberDead match {
      case Some(member) => member.yearOfDeath
      case None         => None
    }
  }
}
