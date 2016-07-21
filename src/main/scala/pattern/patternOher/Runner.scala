package pattern.patternOher

/**
  * Created by Shota on 2016/07/20.
  */
object Runner extends App {

  // タプルで代入
  val tuple = ("abc", 123, List("hoge", "fuga"))
  val (str, num, list) = tuple

  // case class を分解
  case class Sample(hoge: String, fuga: Int, piyo: List[Int])

  val sample = Sample("abc", 100, List(1, 2, 3))
  val Sample(s, i, l) = sample

  // partial function <=> complete function
  /* *************************************************************
  * 部分関数 vs 全関数？
  * isDefinedAt が定義されている
  * 特定の値に対して、関数が定義されているかを判定するメソッド
  * 全関数が網羅的であるのに対して、部分関数は、取りうる値に何かしらの前提がある
  * **************************************************************/
  val getSecond: PartialFunction[List[Int], Int] = {
    case x :: y :: _ => y
  }
  /**
    * 内部的には以下に変換される。
    * new PartialFunction[List[Int],Int] {
    *   def apply(xs: List[Int]) = xs match {
    *     case x :: y :: _ => y
    *   }
    *
    *   def isDefinedAt(xs: List[Int)) = xs match {
    *     case x :: y :: _ => true
    *     case _  => false
    *   }
    * }
    */
  getSecond.isDefinedAt(List(10, 100, 1000))
  getSecond.isDefinedAt(List())
}
