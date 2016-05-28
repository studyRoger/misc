
type Set = Int => Boolean
def singletonSet(elem: Int): Set = (x: Int) => x == elem
val set1 = singletonSet(1)
set1(1)
set1(2)

def union(s: Set, t: Set): Set = (x: Int) => s(x) || t(x)
def intersect(s: Set, t: Set): Set = (x: Int) => s(x) && t(x)
def diff(s: Set, t: Set): Set = (x: Int) => s(x) && !t(x)

def filter(s: Set, p: Int => Boolean): Set = (x: Int) => s(x) && p(x)


//============================

val even: Set = (x: Int) => x % 2 == 0
val odd = (x: Int) => !even(x)


def forall(s: Set, p: Int => Boolean): Boolean = {
  def iter(a: Int): Boolean = {
    if (a > 1000) true
    else if (s(a) && !p(a)) false
    else iter(a + 1)
  }
  iter(-1000)
}

forall(even, (x: Int) => odd(x + 1))
forall(odd, (x: Int) => even(x + 1))


def exists(s: Set, p: Int => Boolean): Boolean = {
  def iter(a: Int): Boolean = {
    if (a > 1000) false
    else if (s(a) && p(a)) true
    else iter(a + 1)
  }
  iter(-1000)
}

exists(even, (x: Int) => x % 4 == 0) // true
exists(odd, (x: Int) => x % 9 == 0) // true
exists(odd, (x: Int) => x % 6 == 0) // false

def exists2(s: Set, p: Int => Boolean): Boolean = !forall(s, (x: Int) => !p(x))

exists2(even, (x: Int) => x % 4 == 0) // true
exists2(odd, (x: Int) => x % 9 == 0) // true
exists2(odd, (x: Int) => x % 6 == 0) // false
def map(s: Set, f: Int => Int): Set = (x: Int) => exists(s, (i: Int) => f(i) == x)
val evenAddThree = map(even, _ + 3) // evenAddThree is odd set
!exists(evenAddThree, _ % 2 == 0) //true

