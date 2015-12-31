
//https://leetcode.com/problems/search-a-2d-matrix-ii/

/*[
[1,   4,  7, 11, 15],
[2,   5,  8, 12, 19],
[3,   6,  9, 16, 22],
[10, 13, 14, 17, 24],
[18, 21, 23, 26, 30]
]*/

val row0 = Array(1,  4,  7,  11, 15)
val row1 = Array(2,  5,  8,  12, 19)
val row2 = Array(3,  6,  9,  16, 22)
val row3 = Array(10, 13, 14, 17, 24)
val row4 = Array(18, 21, 23, 26, 30)

val inTable = Array(row0, row1, row2, row3, row4)

val inTarget = 20
//val x = binarySearchRow(0, 0, row0.length - 1, -1)
//val x = binarySearchColumn(3, 0, table.length - 1, table.length)

for (
  i <- 1 until 31
) yield println(s"is $i exists: ${search(inTable, i)}")
search(inTable, 2)
def search(table: Array[Array[Int]], target: Int): Boolean = {
  val width = table(0).length
  val height = table.length
  val result = searchRow(table, target, 0, 0, table(0).length - 1)
  //println(s"result is $result")
  table(result._1)(result._2) == target
}
def searchRow(table: Array[Array[Int]], target: Int, row: Int, start: Int, end: Int): (Int, Int) = {
  //println(s"row $row from $start to $end")
  val result = binarySearchRow(table, target, row, start, end, start - 1)
  //println(s"closest value position ($row, $result)")
  if(table(row)(result) == target) {
    (row, result)
  } else if(row + 1 < table.length) {
    searchColumn(table, target, result, row + 1, table.length - 1)
  } else {
    (row, result)
  }
}
def searchColumn(table: Array[Array[Int]], target: Int, col: Int, start: Int, end: Int): (Int, Int) = {
  //println(s"col $col from $start to $end")
  val result = binarySearchColumn(table, target, col, start, end, end + 1)
  //println(s"closest value position ($result, $col)")
  if(table(result)(col) == target) {
    (result, col)
  } else if(col - 1 > 0) {
    searchRow(table, target, result, 0, col - 1)
  } else {
    (result, col)
  }
}

def binarySearchRow(table: Array[Array[Int]], target: Int, row: Int, start: Int, end: Int, prev: Int): Int = {
  val mid = start + (end - start) / 2
  //println(s"  from $start to $end - current index $mid & previous index $prev")
  val current = table(row)(mid)
  if(current == target) mid
  else if(current < target) {
    if(mid + 1 <= end && table(row)(mid + 1) <= target) {
      binarySearchRow(table, target, row, mid + 1, end, mid)
    } else {
      mid
    }
  } else { //current > target
    if(mid - 1 >= start) {
      binarySearchRow(table, target, row, start, mid - 1, prev)
    } else {
      prev
    }

  }
}
def binarySearchColumn(table: Array[Array[Int]], target: Int, col: Int, start: Int, end: Int, prev: Int): Int = {
  val mid = start + (end - start) / 2
  //println(s"  from $start to $end - current index $mid & previous indx $prev")
  val current = table(mid)(col)
  if(current == target) mid
  else if(current < target) {
    if(mid + 1 <= end) {
      binarySearchColumn(table, target, col, mid + 1, end, prev)
    } else {
      prev
    }
  } else { //current > target
    if(mid - 1 >= start) {
      binarySearchColumn(table, target, col, start, mid - 1, mid)
    } else {
      mid
    }
  }
}


