package list.fold

import scala.Char

/**
  * Created by Shota on 2016/07/21.
  */
object Runner extends App{
  // fold right / left
  // /: == (コレクション)(演算)  : カリー化されたメソッド
  val list = List(1,2,3,4,5,6,7,8,9)
  (0 /: list) (_ + _)
  (1 /: list) (_ * _)

  /*
  *  fold
  *  z /: List(a,b,c)(op)    list(a,b,c) :\ z
  *  fold left               fold right
  *        op                   op
  *       /  \                 /  \
  *     op    c               a    op
  *    /  \                       /  \
  *   op   b                     b   op
  *  /  \                           /  \
  * z    a                         c    z
  *
  //TODO 不完全燃焼
  *  計算量について
  *  A ::: B のList結合の計算量は、Aの長さに比例する
  *  から、flattenRight のが効率が良い
  * */

  def flattenLeft[T](list: List[List[T]]) = (List[T]() /: list) (_ ::: _)
  def flattenRight[T](list: List[List[T]]) = (list :\ List[T]()) (_ ::: _)

  def reverseLeft[T](list: List[T]) = (List[T]() /: list)(_.::(_))
  println(reverseLeft(List("a", "b", "c", "d")))

  /* tabulate
   * ・任意の次元のListを作成
   * ・カリー化された第二引数で、要素作成の関数を定義出来る。
   */
  val alphabets = List.tabulate(26)(n => (n + 97).toChar)
  println(alphabets)

  val threeD = List.tabulate(5,5,5)(_ * _ * _)
  println(threeD.mkString("List(\n ", "\n,", "\n)"))

  

}
