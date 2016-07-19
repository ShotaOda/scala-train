package stackableModification

/**
  * section 12
  * trait 積み重ね可能的側面
  * Created by shota.oda on 2016/07/19.
  */
abstract class IntQueue {
  def get(): Int

  def put(x: Int)
}

import scala.collection.mutable.ArrayBuffer

class BasicIntQueue extends IntQueue {
  private val buf = new ArrayBuffer[Int]

  def get(): Int = buf.remove(0)

  // tips: 返り値がUnitの場合は、= を省略して、{}で定義できる
  def put(x: Int) { buf += x }
}

/*
 * ①trait A extends B
 *  Aのミックスイン先をBの以下のサブクラスに限定する
 *  (ex)
 *  trait Doubling extends IntQueue
 *  なら、Doublingは、IntQueueもしくは、IntQueueの拡張クラスにミックスインできる
 *
 * ②super in trait
 *  abstract classでは、コンパイルエラーになるが、
 *  traitでは、ミックスイン時に動的にsuperが束縛される
 *  修飾子としてabstract override をつける
 */
trait Doubling extends IntQueue {
  abstract override def put(x: Int) { super.put(2 * x) }
}

trait Incrementing extends IntQueue {
  abstract override def put(x: Int) { super.put(x + 1) }
}

trait Filtering extends IntQueue {
  abstract override def put(x: Int) { if (x >= 0) super.put(x) }
}

class MyQueue extends BasicIntQueue with Doubling

object Runner extends App {
  // 生成時にミックスイン
  val queue = new BasicIntQueue with Doubling

}