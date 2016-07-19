package richInterface

/**
  * section 12
  * Created by Shota on 2016/07/19.
  */
class Rectangle(val topLeft: Point, val botRight: Point) extends Rectangular

case class Point(x: Int, y:Int)

trait Rectangular{
  def topLeft: Point
  def botRight: Point
  def left = topLeft.x
  def right = botRight.x
  def width = right - left
}

