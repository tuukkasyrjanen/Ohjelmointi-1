package o1.legal



// TODO: define classes NaturalPerson, FullCapacityPerson, and ReducedCapacityPerson


abstract class NaturalPerson (val personID: String, name: String) extends Entity(name){

  //def contact: NaturalPerson

  def kind: String = "human"

  //override def toString = s"${this.name} ($kind)"

}

class FullCapacityPerson(personID: String, name: String) extends NaturalPerson(personID, name) {

  def contact: FullCapacityPerson = this

  override def kind: String = super.kind + " in full capacity"

  //override def toString = s"${this.name} ($kind)"

}

class ReducedCapacityPerson (personID: String, name: String, val restriction: Restriction, val guardian: FullCapacityPerson) extends NaturalPerson(personID, name){

  def contact: FullCapacityPerson = this.guardian

  override def kind: String = super.kind + " with " + this.restriction

  //override def toString = s"${this.name} ($kind)"

}