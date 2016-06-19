def last[T](list : List[T]): T = list.tail match {
  case Nil => list.head
  case _ => last(list.tail)
}
println(last(List(1, 1, 2, 3, 5, 8)))
println(last(List('a', 'b', 'c', 'd')))
def last2nd[T](list : List[T]): T = list.tail.tail match {
  case Nil => list.head
  case _ => last2nd(list.tail)
}
println(last2nd(List(1, 1, 2, 3, 5, 8)))
println(last2nd(List('a', 'b', 'c', 'd')))
def nth[T](n: Int, list: List[T]): T = if(n < 0) throw new IllegalArgumentException() else n match {
  case 0 => list.head
  case _ => nth(n - 1, list.tail)
}
nth(2, List(1, 1, 2, 3, 5, 8))

def length[T](list: List[T]): Int = {
  def length0(list: List[T], l: Int): Int = list match {
    case Nil => return l;
    case _ => length0(list.tail, l + 1)
  }
  length0(list, 0)
}

length(List(1, 1, 2, 3, 5, 8))

def reverse[T](list: List[T]): List[T] = {
  def reverse0(list: List[T], revList: List[T]): List[T] = list match {
    case Nil => revList
    case _ => reverse0(list.tail, list.head :: revList)
  }
  reverse0(list, Nil)
}
reverse(List(1, 1, 2, 3, 5, 8))
