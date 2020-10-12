package o1.stars.io

import o1._
import o1.stars._
import o1.util._


/** This singleton object has utilities for handling files that specify the locations
  * of stars and (optionally) constellations on a star map. */
object SkyFiles {

  /** The default name of the file within a star map folder that contains a list of all the visible stars. */
  val DefaultStarListFile = "stars.csv"


  /** Given a single-line description of a star in a particular format, creates and returns
    *  a `Star` object. The given string consists of six or seven parts separated by semicolons.
    *  Here is an example with seven parts:
    *
    *  0.020891;0.991435;0.128917;39801;0.45;2061;BETELGEUSE
    *
    *  The parts are, in order:
    *
    *  1. the x coordinate of the star
    *  2. the y coordinate of the star
    *  3. the z coordinate of the star (''ignored by this method'')
    *  4. the Henry Draper number of the star (''used by this method as the star's ID'')
    *  5. the magnitude of the star
    *  6. the Harvard Revised number of the star (an alternative ID ''ignored by this method'')
    *  7. (optional:) the name of the star
    *
    *  This method assumes that the first six parts will appear in any input provided. If the
    *  optional seventh part is missing, as in the example below, the star is unnamed.
    *
    *  0.015817;0.296915;-0.954773;39810;6.54;2062
    *
    * @param starAsString  a star described in six or seven semicolon-separated parts */
  def parseStarInfo(starAsString: String): Star = {
    val list = starAsString.split(";")
    val starC = new StarCoords(list(0).toDouble,list(1).toDouble)
    list.lift(6) match{
      case None =>
        new Star(list(3).toInt, starC, list(4).toDouble, None)
      case Some(thing) =>
        new Star(list(3).toInt, starC, list(4).toDouble, Some(thing))
    }
  }


  /** Reads in a star map from the files in a given folder. The folder is expected to contain at least
    * a file that lists all the visible stars (one per line as specified under [[parseStarInfo]]). If
    * there are any other files in the folder, each of those files is expected to define a constellation:
    * the name of the file names the constellation, and each line of text within specifies a single line
    * of the constellation by listing the names of two stars separated by a comma.
    * @param starMapFolder     a path to a folder that contains the files that specify a star map
    * @param starListFileName  the name of file within the folder that lists all the stars; defaults to [[DefaultStarListFile]] */
  def readStarMap(starMapFolder: String, starListFileName: String = DefaultStarListFile): Option[StarMap] =
    for {
      starsAsStrings     <- readFileLines(starMapFolder + starListFileName)
      stars              =  starsAsStrings.flatMap( string => ifImplemented(this.parseStarInfo(string)) )
      constellationFiles <- identifyConstellationFiles(starMapFolder, starListFileName)
      constellations     =  readConstellations(constellationFiles, stars)
    } yield new StarMap(stars, constellations)


  // CONSTELLATIONS:

  // Finds all the other files in the folder except the star list file.
  private def identifyConstellationFiles(folderPath: String, starListFileName: String): Option[Vector[Path]] =
    for {
      actualFolder    <- localFile(folderPath)
      accessibleFiles <- actualFolder.listFiles
    } yield accessibleFiles.filter( _.fileName != starListFileName )


  // Parses the given files, returning a constellation from each.
  private def readConstellations(files: Vector[Path], allStars: Vector[Star]) = {
    def entryFor(star: Star) = star.name.map( _ -> star )
    val starsByName = allStars.flatMap(entryFor).toMap
    for {
      file <- files
      if allStars.nonEmpty
      lines <- file.readLines()
      starPairs = lines.map( parseConstellationLine(_, starsByName) )
      constellation <- ifImplemented( new Constellation(file.fileName, starPairs) )
    } yield constellation
  }


  private def parseConstellationLine(line: String, starsByName: Map[String, Star]): (Star, Star) = {
    def invalidLine = throw new RuntimeException(s"Invalid line in constellation definition: $line")
    def getStar(name: String) = starsByName.get(name.trim).getOrElse(throw new RuntimeException(s"Star name missing: $name."))
    val tokens = line.split(",")
    val (name1, name2) = (tokens.lift(0).getOrElse(invalidLine), tokens.lift(1).getOrElse(invalidLine))
    (getStar(name1), getStar(name2))
  }
}
