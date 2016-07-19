package section10

/**
  * Created by shota.oda on 2016/07/15.
  */

// ファクトリーオブジェクト
object Element{
  def elem(c: List[String]): Element = new ArrayElement(c)
  def elem(char: Char, width: Int, height: Int) = UniformElement(char,width,height)
  def elem(line: String): Element = LineElement(line)
}

abstract class Element {
  val contents: List[String]
  def width: Int = if (height == 0) 0 else contents(0).length
  def height: Int = contents.length
  def above(that: Element): Element = ArrayElement(this.contents ::: that.contents)
  def beside(that: Element): Element = {
    ArrayElement(
      for (
        (l1, l2) <- this.contents zip that.contents
      ) yield l1 + l2
    )
  }

  override def toString: String = contents mkString "\n"
}

case class ArrayElement(contents: List[String]) extends Element

case class LineElement(s: String) extends Element{
  override val contents: List[String] = List(s)
  override def width: Int = s.length
  override def height: Int = 1
}

case class UniformElement(
                         ch: Char
                         ,override val width: Int
                         ,override val height: Int
                         ) extends Element{
  private val line = ch.toString * width
  val contents = List.fill(height)(line)
}


