// バッククォート呼び出し
package `import`

/**
  * Created by shota.oda on 2016/07/19.
  */
// C#風 namespace
package Fruit {
  abstract class AnyFruit { val color: String }
  class Apple extends AnyFruit { override val color: String = "red" }
  class Banana extends AnyFruit { override val color: String = "yellow" }
  class Grape extends AnyFruit { override val color: String = "purple" }
  class Melon extends AnyFruit { override val color: String = "green" }
}

object Runner extends App {
  // 名前を変えてインポート
  import Fruit.{Apple => McIntosh, Banana}

  val mac = new McIntosh
  mac.color // => red

  // Appleをインポートから除く
  import Fruit.{Apple => _}
  //val apple = new Apple // => compile error
}