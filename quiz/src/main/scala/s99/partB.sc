def compress[T](list: List[T]) : List[T] = list.foldRight(List[T]())((x, tail) =>
  if(tail.isEmpty || x != tail.head) {
    x :: tail
  } else {
    tail
  }
)
compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))


def pack[T](list: List[T]) : List[List[T]] = list.foldRight(List[List[T]]()) { (x, tail) =>
  if(tail.isEmpty || x != tail.head.head) {
    List(x) :: tail
  } else {
    (x :: tail.head) :: tail.tail
  }
}
pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))

def encode[T](list: List[T]) : List[(Int, T)] = pack(list).map(l => (l.size, l.head))
encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))


def encodeModified[T](list: List[T]) : List[Any] = pack(list).map { l =>
  if(l.size > 1) {
    (l.size, l.head)
  } else {
    l.head
  }
}
encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))

def decode[T](list: List[(Int, T)]) : List[T] = list.flatMap(tup2 => List.fill(tup2._1)(tup2._2))
decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))

def encodeDirect[T](list: List[T]) : List[(Int, T)] = list.foldRight(List[(Int, T)]()) { (x, tail) =>
  if(tail.isEmpty || x != tail.head._2) {
    (1, x) :: tail
  } else {
    (tail.head._1 + 1, tail.head._2) :: tail.tail
  }

}
encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))