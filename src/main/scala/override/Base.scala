package valdef

abstract class Abs{
  def func: String
  val field: String
}

class Base{
  val field = "this is field"
  def func  = "this is function"
}

class Sub extends Base{

  // パラメーターなしメソッドのvalオーバーライド
  // 具象を再定義するので override が必要
  override val func = "this is override function"

}

class Sub2 extends  Abs{

  // パラメーターなしメソッドのvalオーバライド
  val func = "this is implementation of func by val"

  val field: String = _
}

// classパラーメーターでオーバライド
class SubParam(val field: String) extends Abs{
  def func: String = ???
}

// classパラーメーターでオーバライド
// パラメーターなしメソッドのval override
class SubBase(
               override val func: String
               , override val field: String)
  extends Base{

}

