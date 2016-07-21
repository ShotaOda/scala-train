package caseClass

/**
  * Created by shota.oda on 2016/07/19.
  */
class obj(val hoge: String, val fuga: Int) {
  /* ******************************************************************
   * apply & unapply
   * apply  :引数 → インスタンス
   * unapply:インスタンス → 構成要素
   * ******************************************************************/
  def apply: obj = new obj(hoge,fuga)
  def unapply(arg: obj): Option[(String, Int)] = Some(hoge,fuga)
}