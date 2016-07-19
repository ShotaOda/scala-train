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


}

