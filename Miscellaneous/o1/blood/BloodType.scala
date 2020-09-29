package o1.blood


class BloodType(val abo: String, val rhesus: Boolean) {


  override def toString = ???


  def hasSafeABOFor(recipient: BloodType) = ???


  def hasSafeRhesusFor(recipient: BloodType) = ???


  def canDonateTo(recipient: BloodType) = ???


  def canReceiveFrom(donor: BloodType) = ???


}

