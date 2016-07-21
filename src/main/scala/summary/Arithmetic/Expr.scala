package summary.Arithmetic

import summary.layout.Element
import summary.layout.Element.elem

/**
  * Created by Shota on 2016/07/20.
  */
sealed abstract class Expr

case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnaryOp(operator: String, art: Expr) extends Expr
case class BinaryOp(operator: String, left: Expr, right: Expr) extends Expr

class ExprFormatter {
  // 演算子を優先順位ごとに格納
  private val opGroups = Array(
     Set("|", "||")
    ,Set("&", "&&")
    ,Set("^")
    ,Set("==", "!=")
    ,Set("<", "<=", ">", ">=")
    ,Set("+", "-")
    ,Set("*", "%")
  )

  // 演算子から優先順位を導出する
  private val priority = {
    val assocs = for {
      i <- opGroups.indices
      op <- opGroups(i)
    } yield op -> i
    assocs.toMap
  }

  // 単項
  private val unaryPriority = opGroups.length
  private val fractionPriority = -1

  private def format(e: Expr, enclPriority: Int): Element = e match {
    case Var(name) =>
      elem(name)

    case Number(num) =>
      def stripDot(s: String) =
        if (s endsWith ".0") s.substring(0, s.length - 2)
        else s
      elem(stripDot(num.toString))

    case UnaryOp(op, arg) =>
      elem(op) beside format(arg, unaryPriority)

    case BinaryOp("/", left, right) =>
      val top = format(left, fractionPriority)
      val bot = format(right, fractionPriority)
      val line = elem('-', top.width max bot.width, 1)
      val frac = top above line above bot
      if (enclPriority != fractionPriority) frac
      else elem(" ") beside frac beside elem(" ")

    case BinaryOp(op, left, right) =>
      val opPrec = priority(op)
      val l = format(left, opPrec)
      val r = format(right, opPrec + 1)
      val oper = l beside elem(s" $op ")
      if (enclPriority <= opPrec) oper
      else elem("(") beside oper beside elem(")")
  }

  def format(e: Expr): Element = format(e, 0)
}