package o1.shapes



class RightTriangle(val sideLength: Double, val anotherSideLength: Double) extends Shape {

  def area = (this.sideLength * this.anotherSideLength ) / 2
  def hypotenuse = Math.sqrt(this.sideLength * this.sideLength + this.anotherSideLength * this.anotherSideLength)
  def perimeter = this.sideLength + this.anotherSideLength + hypotenuse

}