package accessRange

/**
  * Created by shota.oda on 2016/07/19.
  */
class Outer {
  class Inner {
    private def func() { println("calllllll") }

    class InnerInner {
      func()
    }
  }
  //inner.func // => compile error
  //if (java) inner.func // => calllllll
  val inner = new Inner()

  private[this] def objPrivate = "call private[this]"
  private def normalPrivate = "call normal private"

  val outer = new Outer
  /* *************************************************************
  * private[this]
  * 最も厳格な、アクセス修飾子
  * 同一インスタンスからのみアクセス可能
  * **************************************************************/
  //outer.objPrivate  // inaccessible
  outer.normalPrivate // accessible
}
