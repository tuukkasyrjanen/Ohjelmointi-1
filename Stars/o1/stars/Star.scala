

package o1.stars

import o1._


/** Each `Star` object represents a star on a star map. It records some basic information
  * (an identifier, a location, and a magnitude); in addition, some but not all stars have
  * been assigned a name. (In reality, many stars have multiple alternative names, but this
  * class does not capture that fact.)
  *
  * @param id         a number that uniquely identifies the star from the other visible stars (a so-called Henry Draper number)
  * @param coords     the location of the star on a two-dimensional star map
  * @param magnitude  the apparent magnitude (brightness) of the star: ''smaller means brighter!''
  * @param name       the star's name (wrapped in `Some`), or `None` */
class Star(val id: Int, val coords: StarCoords, val magnitude: Double, val name: Option[String]) {

  def posIn(skyPic: o1.Pic): world.Pos = {
    //val newX = (coords.x * (skyPic.width / 2)) + (skyPic.width / 2)
    //val newY = (coords.y * (skyPic.height / 2)) + (skyPic.height / 2)
    //Pos(newX, newY)
    coords.toImagePos(skyPic)
  }
  override def toString = {
    name match {
      case Some(thing) => s"#$id $thing (${coords.toString})"
      case None        =>s"#$id (${coords.toString})"
    }

  }



}

