package o1

// This class is gradually developed between Chapters 2.4 and 3.4.

class Odds(val wont: Int, val will: Int) {

  def probability = 1.0 * this.will / (this.wont + this.will)

  def fractional = s"${this.wont}/${this.will}"

  def decimal = {
    val dwont = 1.0 * this.wont
    val dwill = 1.0 * this.will
    (dwont / dwill) + 1.0
  }

  def winnings(bet: Double) = 1.0 * decimal * bet

  def not = new Odds(will, wont)

  override def toString = s"${this.wont}/${this.will}"

  def both(tod : Odds) = {
    new Odds((this.wont * tod.wont + this.wont * tod.will + this.will * tod.wont),(this.will * tod.will))
  }

  def either(tod : Odds) = {
    new Odds((this.wont * tod.wont),(this.wont * tod.will + this.will * tod.wont + this.will * tod.will))
  }

  def isLikely = {
    (probability > 0.5)
  }

  def isLikelierThan(toinen: Odds)= {
    toinen.probability < this.probability
  }

  def moneyline = {
    if (probability <= 0.5)
      (100 * ((1.0 * this.wont) / this.will)).toInt
    else
      (-100 * ((1.0 * this.will) / this.wont)).toInt

  }
}
