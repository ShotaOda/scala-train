package list

import scala.annotation.tailrec

/**
  * Created by Shota on 2016/07/20.
  */
object Runner extends App {

  // ::(コンス) は右結合
  val cons = 1 :: 2 :: 3 :: 4 :: 5 :: Nil // = (1 :: (2 :: (3 :: (4 :: (5 :: Nil)))))

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

  // append を 実装してみる
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

 /* *************************************************************
 * head tail init last？
 * val list = List(1,2,3,4)
 * list.head = 1
 * list.tail = List(2,3,4)
 * list.init = List(1,2,3)
 * list.last = 4
 * **************************************************************/

  // TODO なんで？ => init & last はリスト全体をたどる動作になるから遅くなる。。。？

  // TODO rev 高速化
  // reverse の実装
  def reverse[T](li: List[T]): List[T] = {
    @tailrec
    def inner(reversed: List[T], list: List[T]): List[T] = list match {
      case Nil => reversed
      case head :: tail => inner(head :: reversed, tail)
    }
    inner(Nil, li)
  }

  val order = List(1,2,3,4,5,6,7,8,9,10)
  println(reverse(order))

  // zip / unzip
  val alphabets = List("a", "b", "c", "d", "e", "f", "g", "h")
  val nums = alphabets.indices

  println(alphabets.indices zip alphabets)
  println(alphabets.zipWithIndex)

}