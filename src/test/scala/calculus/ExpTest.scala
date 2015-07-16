package calculus

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by markus on 16/07/2015.
 */
class ExpTest extends FlatSpec with Matchers {

  /*
  let f x = x + x in
  let y = 10 in
  y + f ( f ( 2 ) - 1 )
   */
  val cont2 = App( Var( "add" ), Seq( Var( "y" ), App( Var( "f" ), Seq( App( Var( "sub" ), Seq( App( Var( "f" ), Seq( Lit( 2 ) ) ), Lit( 1 ) ) ) ) ) ) )
  val cont1 = Let( "y", Lit( 10 ), cont2 )
  val expr0 = Let( "f", Lambda( "x", App( Var( "add" ), Seq( Var( "x" ), Var( "x" ) ) ) ), cont1 )

  /*
  let x = 1 in
  let y = x in
  let z = y in
  x + y + z
   */
  val expr1 = Let( "x", Lit( 1 ), Let( "y", Var( "x" ), Let( "z", Var( "y" ), App( Var( "add" ), Seq( App( Var( "add" ), Seq( Var( "x" ), Var( "y" ) ) ), Var( "z" ) ) ) ) ) )

  /*
  let f x = x - 2 in
  let g y = y + 2 in
  g ( f ( 5 ) )
   */
  val cont4 = App( Var( "g" ), Seq( App( Var( "f" ), Seq( Lit( 5 ) ) ) ) )
  val cont3 = Let( "g", Lambda( "y", App( Var( "add" ), Seq( Var( "y" ), Lit( 2 ) ) ) ), cont4 )
  val expr2 = Let( "f", Lambda( "x", App( Var( "sub" ), Seq( Var( "x" ), Lit( 2 ) ) ) ), cont3 )

  "The interpreter" should "work" in {
    expr0.interpret() shouldBe 16
    expr1.interpret() shouldBe 3
    expr2.interpret() shouldBe 5
  }
}