package o1.carsim
import o1.Pos


class Car(val fuelConsumption: Double, val tankSize: Double, initialFuel: Double, initialLocation: Pos) {

  private var currentPos = initialLocation

  private var currentFuel = initialFuel

  private var totalMeters = 0.0

  def location: Pos = currentPos

  def fuel(toBeAdded: Double): Double = {
    var returnValue = 0.0
    if (toBeAdded > 0){
      if (toBeAdded > (tankSize - currentFuel)){
        returnValue = tankSize - currentFuel
        currentFuel = tankSize

      }
      else if (toBeAdded <= (tankSize - currentFuel)){
        currentFuel += toBeAdded
        returnValue = toBeAdded
      }
    }
    returnValue
  }

  def fuel(): Double = {
    var returnValue = 0.0
    if (currentFuel < tankSize)  {
      returnValue = tankSize - currentFuel
      currentFuel = tankSize
    }
    returnValue
  }

  def fuelRatio: Double = (currentFuel / tankSize) * 100

  def metersDriven: Double = totalMeters

  def fuelRange: Double = ((currentFuel / fuelConsumption) * 100000)

  def drive(destination: Pos, metersToDestination: Double): Unit = {
    if (metersToDestination <= fuelRange){
      totalMeters += metersToDestination
      currentFuel -= ((metersToDestination / 100000) * fuelConsumption)
      currentPos = destination
    }
    else if (metersToDestination > fuelRange){
      val xDist = (destination.x - currentPos.x) * (fuelRange / metersToDestination)
      val yDist = (destination.y - currentPos.y) * (fuelRange / metersToDestination)
      totalMeters += fuelRange
      currentFuel = 0.0
      currentPos = currentPos.addX(xDist).addY(yDist)
    }
  }

}

