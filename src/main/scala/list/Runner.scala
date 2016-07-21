package list

/**
  * Created by Shota on 2016/07/20.
  */
object Runner extends App {

  // ::(コンス) は右結合

  // Listの実体
  val numList = List(1,2,3,4)
  val listList = List(
     List("hoge", "hoge", "hoge")
    ,List("fuga", "fuga", "fuga")
    ,List("piyo", "piyo", "piyo")
  )

  // Ok
  assert(numList == 1 :: 2 :: 3 :: 4 :: Nil)
  // Ok
  assert(listList ==
      ("hoge" :: "hoge" :: "hoge" :: Nil) ::
      ("fuga" :: "fuga" :: "fuga" :: Nil) ::
      ("piyo" :: "piyo" :: "piyo" :: Nil) :: Nil )

  //TODO ロジックよくわかってない。。。
  // 挿入ソート
  def isort(xs: List[Int]): List[Int] = xs match {
    case Nil => List()
    case x :: tail => insert(x, isort(tail))
  }

  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case Nil => List(x)
    case y :: tail =>
      if (x <= y) x :: xs
      else y :: insert(x, tail)
  }

  /* *************************************************************
  * 分割統治原則？
  * 入力を単純なケースに「分割」し
  * 個々のケースについて演算を行った後、「統治」すること
  * **************************************************************/
  def append[T](xs: List[T], ys:List[T]): List[T] = {
    xs match {
      case Nil => ys
      case head :: tail => head :: append(tail, ys)
    }
  }

  def appendJustify[T](xs: List[T], ys: List[T]): List[T] = ys match {
    case Nil => xs
    case head :: tail => appendJustify(xs :+ head, tail)
  }

  val l1 = List(1,2,3,4)
  val l2 = List(5,6,7,8)

  println(append(l1,l2))
  println(appendJustify(l1, l2))
}