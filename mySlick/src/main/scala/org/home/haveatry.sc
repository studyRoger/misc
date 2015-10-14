case class Klass(a: String, b: String) {
  println(a + b)
}
val hw = Klass tupled  ("hello", "world")
Klass.unapply(hw)
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
val f: Future[Int] = Future {
   1 + 1
}
f.onSuccess({
  case s => println("1. result is " + s)
})
f.onSuccess {
  case s => println("2. result is " + s)
}

