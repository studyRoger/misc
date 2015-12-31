import scala.annotation.tailrec


//https://leetcode.com/problems/add-digits/

@tailrec
def addDigitsCommon(num: Int): Int = {

  if (num < 10) num
  else addDigitsCommon(num / 10 + num % 10)
}
for(
  i <- 0 until 100
) yield print(s"[$i -> ${addDigitsCommon(i)}]")
def addDigits(num: Int): Int = {
  if(num ==  0) 0
  else {
    val res = num - num / 9 * 9
    if (res == 0) 9
    else res
  }

}

for(
  i <- 0 until 100
) yield print(s"[$i -> ${(addDigitsCommon(i) - addDigits(i)) == 0}]")
