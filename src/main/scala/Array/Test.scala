package Array

/**
  * Created by shota.oda on 2016/07/15.
  */
object Test {
  val arr = Array(1,2,3,4,5,6,7,8,9,10)

  // apply 変換
  arr(0) // -> arr.apply(0) -> 1

  // update 変換
  arr(0) = 10 // -> arr.update(0, 10) -> arr(0) = 10
}
