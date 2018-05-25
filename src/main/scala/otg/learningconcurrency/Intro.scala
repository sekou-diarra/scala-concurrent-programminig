package otg.learningconcurrency

import java.util.regex.{Matcher, Pattern}

import scala.util.matching.Regex
import scala.util.matching.Regex.Match

object Hello extends App{
println("Hello world")
}

class Printer(val greeting: String){
  def printMessag():Unit = print(greeting + "!")
  def printNumber(x:Int): Unit = {
    println(s"Number: $x")
  }
}
object Exo {

  def compose[A,B,C](g:B=>C, f:A=>B):A=>C = x=>{
    g(f(x))
  }

//  def fuse [A,B](a:Option[A], b:Option[B]):Option[(A,B)]={
//    for{
//      valueA <- a.getOrElse()
//      valueB <- b.getOrElse()
//    } yield Some((valueA,valueB))
//  }

  def check[T](xs:Seq[T])(pred:T=>Boolean):Boolean = {
    xs.forall(pred)
  }

//  def permutation (x:String) : Seq[String]

  def combination(n:Int, xs:Seq[Int]):Iterator[Seq[Int]]={
    xs.combinations(n)
  }

  def matcher (regex:String) : PartialFunction[String,List[String]]= {
      val potential = new PartialFunction[String,List[String]] {
        override def isDefinedAt(x: String): Boolean = x.matches(regex)

        override def apply(v1: String): List[String] = regex.r.findAllIn(v1).toList
      }
    potential
  }
}
