package o1
object misc { // These definitions at the top are discussed in Chapter 5.2.

  // Various small assignments across several chapters will ask you to define functions in this file.
  // Please enter your code below this comment.



  def together(vektori : Vector[String], luku : Int) ={
    val newString = vektori.mkString("&") + s"/$luku"
    newString
  }



  def isInOrder(pairOfNumbers: (Int, Int)) = pairOfNumbers._1 <= pairOfNumbers._2    // This example function is introduced in Chapter 8.4. You can ignore it until then.

  def tempo(music: String) = {
    var retVal = 0
    if (music.contains("/")){
      retVal = music.split("/")(1).toInt
    }else {
      retVal = 120
    }
    retVal
  }
}