package accessRange

/**
  * section 13
  * Created by Shota on 2016/07/19.
  */
object Rocket {
  private def fuel = 10

  def chooseStrategy(r: Rocket) {
    // object -> class private def
    if (r.canGoHomeAgain) goHome()
    else pickAStar()
  }

  // 副作用を伴うのメソッドなので、慣習で()つける
  def goHome()    = println("go home")
  def pickAStar() = println("pick a star")
}

class Rocket {

  // class -> object private def
  //
  private def canGoHomeAgain = Rocket.fuel > 20
}

