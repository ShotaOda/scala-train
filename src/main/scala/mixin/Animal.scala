package mixin

/**
  * Created by Shota on 2016/07/19.
  */
class Animal {

}

trait Philosophical {
  def philosophize()
}

trait HasLegs {

}

class Frog extends Animal with HasLegs with Philosophical {
  override def philosophize(): Unit = ???
}

