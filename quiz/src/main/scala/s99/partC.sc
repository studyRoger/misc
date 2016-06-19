def duplicate[T](list: List[T]) = list.flatMap(x => List(x, x))
duplicate(List('a, 'b, 'c, 'c, 'd))

def duplicateN[T](i: Int, list: List[T]) = list.flatMap(x => List.fill(i)(x))

def drop[T](i: Int, list: List[T]) = list.zipWithIndex.filter(_._2 % i != 2).map(_._1)
drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

def split[T](i: Int, list: List[T]) = (list.take(i), list.drop(i))
split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))

def slice[T](i: Int, j: Int, list: List[T]) = list.slice(i, i + j)
slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))