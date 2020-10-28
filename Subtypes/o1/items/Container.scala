package o1.items

import o1.Buffer




class Container (name : String) extends Item(name){

  private var tavarat = Buffer[Item]()

  def addContent(newContent: Item): Unit = {
    tavarat += newContent

  }

  override def toString = (s"$name containing ${tavarat.size} item(s)")

}