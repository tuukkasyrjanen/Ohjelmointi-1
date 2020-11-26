

package o1.stars.io

import o1._
import o1.stars._


/** This singleton object provides utilities for creating and manipulating images that
  * represent views of a night sky. */
object SkyPic {

  /** Given an image of the sky and a star, returns a version of the original image with an
    * image of the star placed on top. That is, forms an image of the star and places it
    * against the given (larger) image in a `Pos` that corresponds to the star's [[StarCoords]].
    *
    * The star is depicted as a `White` circle whose diameter is `12.0 / (M + 2)`,
    * where `M` is the star’s magnitude. Its position within the resulting image is
    * determined by [[Star.posIn]]. The given star must have a magnitude greater than -2.
    *
    * For example, say the background image is 400 by 400 pixels, and the given star has
    * the coords (0.5,0.0) and a magnitude of 0.5. The returned image will then consist of
    * the given background image with white circle of radius 4 placed upon it at (300,200).
    *
    * @param skyPic  an image to place the star upon
    * @param star    a star (of magnitude > -2) that should be depicted against the given image */
  def placeStar(skyPic: Pic, star: Star): Pic = {
    val starPic = Pic circle((12.0 / (star.magnitude + 2)), White)
    val yhdistetty = skyPic.place(starPic,star.posIn(skyPic))
    yhdistetty
  }


  /** Given a [[StarMap]] that details what is visible in the sky, produces a [[Pic]]
    * that represents that information as an image. The background of the image is a
    * `Black` square of the given size. Each star and constellation in the sky appear
    * against that background.
    *
    * @param skyData  the contents of the night sky that are to be represented as an image
    * @param bgSize   the width and height, in pixels, of the desired square image */
  def create(skyData: StarMap, bgSize: Int) = {
    val darkSky = rectangle(bgSize, bgSize, Black)
    val picture = skyData.stars.foldLeft(darkSky)(placeStar)
    skyData.constellations.foldLeft(picture)(placeConstellation)



  }

  def placeConstellation(skyPic: o1.Pic, constellation: Constellation): o1.Pic ={
    var tausta = skyPic
    val coordPairs = constellation.lines
    //var linevector = Vector[Pic]()

    for (c <- coordPairs) {
      tausta = line(c._1.coords.toImagePos(skyPic), c._2.coords.toImagePos(skyPic),Yellow).onto(tausta, c._1.coords.toImagePos(skyPic))
      //linevector = linevector :+ line(c._1.coords.toImagePos(skyPic), c._2.coords.toImagePos(skyPic),Yellow)
    }
    //linevector.foldLeft(tausta)( _.onto(_) )
    tausta
  }
}