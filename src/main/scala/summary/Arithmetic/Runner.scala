package summary.Arithmetic

/**
  * Created by Shota on 2016/07/20.
  */
object Runner extends App{
  val f = new ExprFormatter

  val el1 = BinaryOp("*"
    , BinaryOp("/", Number(1), Number(2))
    , BinaryOp("+", Var("x"), Number(1))
  )

  val el2 = BinaryOp("*"
    , BinaryOp("/", Var("x"), Number(2))
    , BinaryOp("+", Number(1.5), Var("x"))
  )

  val el3 = BinaryOp("/", el1, el2)
}
