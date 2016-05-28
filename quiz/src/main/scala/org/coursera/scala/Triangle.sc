def pascal(c: Int, r: Int): Int = {
  if (c > r || c < 0 || r < 0) throw new NoSuchElementException
  else if (c == 0 || c == r) 1
  else pascal(c - 1, r - 1) + pascal(c, r - 1)
}

//pascal(-1, 0)
//pascal(1, -1)
//pascal(3, 2)
pascal(0,2)
pascal(1,2)
pascal(1,3)

def balance(chars: List[Char]): Boolean = {

  def balanceRec(chars: List[Char], count: Int): Boolean = {
    if(count < 0) return false
    else {
      chars match {
        case Nil => count == 0
        case '(' :: cs => balanceRec(cs, count + 1)
        case ')' :: cs => balanceRec(cs, count - 1)
        case _ => balanceRec(chars.tail, count)
      }
    }
  }

  balanceRec(chars, 0)
}

val str1 = "(if (zero? x) max (/ 1 x))"
val str2 = "I told him (that it’s not (yet) done). (But he wasn’t listening)"
val str3 = ":-)"
val str4 = "())("

balance(str1.toList)
balance(str2.toList)
balance(str3.toList)
balance(str4.toList)

def countChange(money: Int, coins: List[Int]): Int = {

  def changeSolution(money: Int, coins: List[Int]): List[List[Int]] = {
    if(money == 0) List(Nil)
    else {
      val allSolutions = for{
        x <- coins
        if(money - x >= 0)
        solution <- changeSolution(money - x, coins)
      } yield x :: solution
      allSolutions
    }
  }
  val allSolutionWithDuplicated = changeSolution(money, coins)
  val allSolution = allSolutionWithDuplicated.map(list => list.sorted).distinct
  allSolution.length

}

countChange(1, List(1, 2))
countChange(2, List(1, 2))
countChange(3, List(1, 2))
countChange(4, List(1, 2))