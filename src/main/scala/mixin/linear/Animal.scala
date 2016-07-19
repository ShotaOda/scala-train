package mixin.linear

/**
  * Created by shota.oda on 2016/07/19.
  */

abstract class Animal /*extends AnyRef(extends Any)*/

trait Furry extends Animal
trait HasLegs extends Animal
trait FourLegged extends HasLegs

class Cat extends Animal with Furry with FourLegged

/**
  * ミックスインの線形化
  * ① 定義で書かれている後ろから(FourLeggedから)
  * ② 被らないように、一本化
  *      Any
  *       |
  *     AnyRef
  *       |
  *     Animal
  *   ┌─┘    └─┐
  *   |        |
  * Furry   HasLegs
  *            |
  *        FourLegged
  *
  * Any ← AnyRef ← Animal ← Furry ← HasLegs ← FourLegged
  */
