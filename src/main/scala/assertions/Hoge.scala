package assertions

/**
  * section 14
  * Created by Shota on 2016/07/19.
  */

object Hoge extends App{

  /* *************************************************************
  * ensuring？
  * 暗黙の型変換によって、任意の型にから、ensuringを呼び出せる。
  * T.ensuring == true -> Tを返す
  * ensuring == false  -> java.lang.AssertionErrorる
  * **************************************************************/
  val ho = "ho"
  var ge = "ge"
  val hos = List(ho,ho,ho)
  val ges = List(ge,ge,ge)
  val hoges = for {
    ho <- hos
    ge <- ges
  } yield (ho + ge) ensuring(_ == "hoge") // return ("hoge", "hoge", "hoge")

  hoges.ensuring(_ == "fuga") // assertion error
}
