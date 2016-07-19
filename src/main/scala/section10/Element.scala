package section10

/**
  * Created by Shota on 2016/07/17.
  */

// コンパニオンオブジェクト -> シングルトンが保証される。
// ＋ ファクトリーオブジェクトとして実装
// ＋ 内部にprivateで具象クラスを定義することで、クライアント側から隠蔽

object Element {
  // def elem(contents: List[String]): Element = _ <- 仮実装用のアンスコ
  def elem(contents: List[String]): Element = ListElement(contents)

  def elem(char: Char, width: Int, height: Int): Element = UniformElement(char, width, height)

  def elem(line: String): Element = LineElement(line)

  // コンストラクタオーバライド
  // class ListElement(val contents: List[String]) extends Element
  // case class はデフォルトで、パラメーターに暗黙で val を付与している

  /* ******************************************************************
   * パラメーター valあり vs valなし
   * valあり：同名のパラメーターとフィールドを定義する略記法
   * valなし：単純なパラメーター
   * ******************************************************************/

  case class ListElement(contents: List[String]) extends Element

  case class LineElement(private val s: String) extends Element {
    def contents: List[String] = List(s)

    override val height: Int = s.length
    override val width: Int = 1
  }

  case class UniformElement(private val ch: Char
                            , override val width: Int
                            , override val height: Int)
    extends Element {
    private val line = ch.toString * width

    def contents: List[String] = {
      List.fill(height)(line)
    }
  }
}

abstract class Element {
  // オーバーロードメソッドなので、importは一つ
  import section10.Element.elem

  def contents: List[String]

  val height: Int = contents.length
  val width: Int =
    // case classで実装する場合、初期化時に、contentsがNil
    if (height == 0 || contents.head == null) 0
    else contents.head.length

  /* *************************************************************
  * ++ vs ::: 違い？
  * ++  -> Seq  で定義されているメソッド
  * ::: -> List で定義されているメソッド
  * **************************************************************/

  def above(that: Element): Element = {
    // thisとthatの幅調整
    val wThis = this.widen(that.width)
    val wThat = that.widen(this.width)
    elem(wThis.contents ++ wThat.contents)
  }

  def beside(that: Element): Element = {
    val hThis = this.heighten(that.height)
    val hThat = that.heighten(this.height)
    val zipped = for {
      (l1, l2) <- hThis.contents zip hThat.contents
    } yield l1 + l2
    elem(zipped)
  }

  private def widen(w: Int): Element = {
    if (w <= width) this
    else {
      /* *************************************************************
      * logic
      * w   : □□□□□□□□□□□□□□□□□□□□
      * this: □left□□_this_□right□
      * **************************************************************/
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    }
  }

  private def heighten(h: Int): Element = {
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }
  }

  override def toString: String = contents mkString "\n"
}