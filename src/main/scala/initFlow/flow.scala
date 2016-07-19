package initFlow

/**
  * Created by Shota on 2016/07/18.
  */
abstract class abs {

  def fn1: String

  val val1: String = {
    println(s"abs : val1 -> $fn1")
    fn1
  }
}

case class impl(arg: String, override val val1: String) extends abs {

  val val2 = {
    println(s"impl: val2 -> ($arg * 10)")
    arg * 10
  }

  def fn1: String = {
    println(s"impl: fn1 -> $val2")
    val2
  }
}


object run extends App{
  impl("","")
}