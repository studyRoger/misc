/*
You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.
*/

def canWinNim(n: Int) = if(n <= 3) true else !(n % 4 == 0)


/*def canWinNim(n: Int): Boolean = {
  if(n <= 3) true
  else !(canWinNim(n - 1) && canWinNim(n - 2) && canWinNim(n - 3))
}*/


canWinNim(-10)
canWinNim(0)
canWinNim(1)
canWinNim(2)
canWinNim(3)
canWinNim(4)
canWinNim(5)
canWinNim(6)
canWinNim(7)

for(i <- Range(1, 20)) yield println(i + " " + canWinNim(i))
canWinNim(100)
canWinNim(1024)
