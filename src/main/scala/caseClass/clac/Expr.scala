package caseClass.clac

/**
  * section 15
  * Created by Shota on 2016/07/19.
  */

abstract class Expr

/* *************************************************************
* case class 汎用メソッドがdefaultで実装される
* クラスと全ての引数から構成される木構造全体が対象となる
* ・toString
* ・hashCode
* ・equals
* ・copy
* **************************************************************/
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, art: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

object Runner extends App {
  val op = BinOp("+", Number(1), Var("x"))

  // equals
  op.right == Var("x")

  // copy
  val minOp = op.copy(operator = "-")
}

class Runner {
  def simplifyTop(expr: Expr) = expr match {
    case UnOp("-", UnOp("-", e))  => e // 負の負 は元のまま
    case BinOp("+", e, Number(0)) => e // 0の加算は元のまま
    case BinOp("*", e, Number(1)) => e // 1の乗算は元のまま
  }

}
