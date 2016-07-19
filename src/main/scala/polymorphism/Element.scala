package polymorphism

import apple.laf.JRSUIConstants.Widget

/**
  * Created by shota.oda on 2016/07/15.
  */
abstract class Element {
  val width: Int
  val height: Int
}

class ConcElem (
                val width: Int  // override
               ,val height: Int // override
               ,val depth: Int
               ) extends Element{
}


