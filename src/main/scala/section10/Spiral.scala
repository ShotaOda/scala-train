package section10

import section10.Element.elem

/**
  * Created by Shota on 2016/07/18.
  */
object Spiral {
  val space = elem(" ")
  val corner = elem("+")

  def spiral(edges: Int, direction: Int): Element = {
    if (edges == 1) return corner

    val sp = spiral(edges - 1, (direction + 3) % 4)

    // 使用しない場合があるので、遅延評価(defで定義)
    def verticalBar = elem('|', 1, sp.height)
    def horizontalBar = elem('-', sp.width, 1)

    if (direction == 0)
      (corner beside horizontalBar) above (sp beside space)
    else if (direction == 1)
      (sp above space) beside (corner above verticalBar)
    else if (direction == 2)
      (space beside sp) above (horizontalBar beside corner)
    else
      (verticalBar above corner) beside (space above sp)

  }

  def main(args: Array[String]) {

  }
}
