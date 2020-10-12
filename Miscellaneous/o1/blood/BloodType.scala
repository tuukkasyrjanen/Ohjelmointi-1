package o1.blood


class BloodType(val abo: String, val rhesus: Boolean) {


  override def toString = {
    if (rhesus){
      s"$abo+"
    }else {
      s"$abo-"
      }
  }



  def hasSafeABOFor(recipient: BloodType) = {
    (this.abo == recipient.abo || this.abo == "O" || this.abo == recipient.abo.dropRight(1) || this.abo == recipient.abo.drop(1))
  }

  def hasSafeRhesusFor(recipient: BloodType) = {
    (this.rhesus == false || recipient.rhesus == this.rhesus)
  }

  def canDonateTo(recipient: BloodType) = (hasSafeABOFor(recipient) && hasSafeRhesusFor(recipient))

  def canReceiveFrom(donor: BloodType) = {
    ((donor.abo == this.abo || donor.abo == "O" || donor.abo == this.abo.dropRight(1) || donor.abo == this.abo.drop(1)) && (donor.rhesus == false || donor.rhesus == this.rhesus))
  }
}

