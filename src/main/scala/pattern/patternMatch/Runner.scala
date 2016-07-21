package pattern.patternMatch

/**
  * section 15
  * Created by Shota on 2016/07/19.
  */
object Runner extends App{
  def DoNothing = ()
  def sep(title: String) = println(s"\n\n================================{$title}======================================")

  def descrive(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

  def listMatcher(list: Seq[Any]) = list match {
    case List(0, _, _) => println("固定長マッチ")
    case List(0, _*)   => println("可変長マッチ")
    case _ => DoNothing
  }

  def tupleMatcher(arg: Any) = arg match {
    case (a, b, c) => println(s"tuple マッチ => ($a, $b, $c)")
  }

  /* *************************************************************
  * 型消去 / 消去モデル ・・・ ※１
  * 消去モデルのジェネリックプログラミングを用いているために
  * 実行時に型引数の情報を管理していない特徴
  * case句で
  * m: Map[Int, Int]
  * m: Map[String, String]
  * とかやっても、Mapだったら、なんでもマッチする。
  * 確認しているのは、Map[_, _]であるかどうかだけ。（`＿` は ワイルドカード）
  * **************************************************************/
  def genericMathcer(arg: Any) = arg match {
    case s: String    => s"type is String"
    case m: Map[_, _] => s"type is Map" // ※１
    case _            => s"type is not String nor Map"
  }

  case class Hoge(arg: String, op: Option[String])
  def variableBindMatcher(arg: Hoge) = arg match {
      case Hoge("key", op @ Some("value"))   => "変数の束縛"
      case Hoge(key, op) if op.contains(key) => "パターンガード"
      case Hoge("key", op)                   => "単純変数"
      case _                                 => "Not matched ..."
    }

  /* *************************************************************
  * シールドクラス？
  * sealed 修飾子： 継承をそのファイル内に制限する
  * コンパイラが、サブクラスを把握出来るようになり、パターンマッチで抜けのチェックが可能
  * => match may not be exhaustive
  * **************************************************************/
  sealed abstract class Expression
  case class Plus()     extends Expression
  case class Minus()    extends Expression
  case class Multiple() extends Expression
  case class Divide()   extends Expression
  def sealedMatcher(exp: Expression, num: Int, num2: Int): Long = exp match {
    case Plus()     => num + num2
    case Minus()    => num - num2
    case Multiple() => num * num2
    case Divide()   => num / num2
  }

  // 受ける型が自明の場合で、省略したい場合
  // param: @unchecked
  /**
    * @param exp plus or minus only allowed
    */
  def sealedMatcher2(exp: Expression, num: Int, num2: Int): Long = {
    assert(exp.isInstanceOf[Plus]|| exp.isInstanceOf[Minus])
    (exp: @unchecked) match {
      case Plus()     => num + num2
      case Minus()    => num - num2
    }
  }

}
