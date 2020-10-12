package o1.auctionhouse

class FixedPriceSale(val description: String, val price: Int, duration: Int) {

  private var salesLeft = duration
  private var buyerName :Option[String] = None

  override def toString = description

  def daysLeft :Int = salesLeft

  def buyer :Option[String] = {
    if (daysLeft > 0) {
      buyerName match{
        case None => None
        case Some(thing) => Some(buyerName.get)
      }
    }else {
      None
    }
  }

  def isExpired :Boolean = (salesLeft == 0 && buyerName == None)

  def isOpen :Boolean = (salesLeft > 0 && buyerName == None)

  def advanceOneDay() :Unit = {
    if (isOpen) {
      salesLeft -= 1
    }
  }

  def buy(buyer: String): Boolean = {

    if (daysLeft > 0){
      buyerName match {
        case None =>
          buyerName = Some(buyer)
          true
        case Some(thing) =>
          false
      }
    }else {
      false
    }
  }
}
