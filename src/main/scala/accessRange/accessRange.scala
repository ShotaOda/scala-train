/**
  * section 13
  * Created by Shota on 2016/07/19.
  */
/* *************************************************************
* パッケージオブジェクト
* 定義したものは、パッケージのメンバーとして利用できる
* 主な用途は、
* ・暗黙の型変換
* ・型の別名定義
* **************************************************************/
package object accessRange {
  def utilString: String = "this is package object member"
}


package other {

  class Hoge {
    def useOtherPackage = {
      import accessRange._
      utilString
    }
  }
}

object Runner extends App {
  import other.Hoge
  val hoge = new Hoge
  println(s"hoge: call ${hoge.useOtherPackage}")
}