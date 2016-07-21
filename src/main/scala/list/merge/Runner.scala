package list.merge

/**
  * section 16
  * Created by Shota on 2016/07/21.
  */
object Runner extends App{
  /* *************************************************************
  * 汎用的なListのソートメソッドとは？
  * Listの型を任意のものにできる => ジェネリック
  * ソートの比較関数を選択できる  => 高階関数 ＋ カリー化
  * **************************************************************/
  // TODO 計算量
  def mergeSort[T](comparator: (T, T) => Boolean)(list: List[T]): List[T] = {
    def merge(lx: List[T], ly:List[T]): List[T] = (lx, ly) match {
      case (Nil, _) => ly
      case (_, Nil) => lx
      case (headX :: tailX, headY :: tailY) =>
        println(s"merge: $headX :: $tailX, $headY :: $tailY")
        if (comparator(headX, headY)) headX :: merge(tailX, ly)
        else headY :: merge(lx, tailY)
    }

    val n = list.length / 2
    if (n == 0) list
    else {
      val (lx, ly) = list splitAt n
      println(s"split: $lx,  $ly")
      val merged = merge(mergeSort(comparator)(lx), mergeSort(comparator)(ly))
      println(s"done : $merged")
      merged
    }
  }

  val intOrderSort = mergeSort((x: Int, y: Int) => x > y) _
  val intRevSort = mergeSort((x: Int, y: Int) => x < y) _

  val l = List(10,9,8,7,6,5,4,3,2,1)
  //mergeSort((a: Int,b: Int) => a < b)(l)

  val li = List(8,6,5,9,10,2,45,5)
  intOrderSort(li)
  intRevSort(li)
}
