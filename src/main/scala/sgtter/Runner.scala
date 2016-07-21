package sgtter

/**
  * getter / setter
  * http://xawa99.blogspot.jp/2012/08/Scala-GetterSetter.html
  * Created by Shota on 2016/07/20.
  */
object Runner extends App{
  /* *************************************************************
  * そもそもなんでフィールドを非公開にするのか？
  * ・クライアント側に必要が無い / 更新を禁止したい => privateで対応
  * ・参照・更新時のフックをかけたいから           => getter / setter の定義
  * **************************************************************/

  // setter の実装  => 「_= で終わるメソッドは _= を除いた部分に対する代入文として呼び出せる」
  class Hoge (private var _fuga: String){
    var counter = 0
    // getter
    def fuga: String = _fuga

    // setter
    def fuga_=(str: String) = {
      counter = counter + 1
      _fuga = s"$str (count: $counter)"
    }
  }

  val hog = new Hoge("a")
  println(hog.fuga)

  hog.fuga = "b"
  println(hog.fuga)

  hog.fuga = "c"
  println(hog.fuga)

  hog.fuga = "d"
  println(hog.fuga)
}
