package ch.ebexasoft.knobel

/**
 * Solves the following puzzle: 
 * <code>
 *   EINS
 * + ZWEI
 * + DREI
 * ------
 *  SECHS
 * </code>
 * What does EHI stand for?
 * 
 * @author edith
 */

// --- helper functions ----

def toMap = { list ->
  def m = [:]
  list.eachWithIndex { it, i -> m[it] = i }
  m
}

// extract only some letters with their index, then concatenate the indices to a number
def extractKeyNumber = { List letters, List keyLetters ->
  def m = toMap(letters)
  String s = ""
  keyLetters.each { c -> s = s + m[c] }
  s
}


def isSolution = { List list ->
  def m = toMap(list)
  if (m['E'] == 0 || m['D'] == 0 || m['Z'] == 0 || m['S'] == 0)  return false
  def num1 = ((m['E'] * 10 + m['I']) * 10 + m['N']) * 10 + m['S']
  def num2 = ((m['Z'] * 10 + m['W']) * 10 + m['E']) * 10 + m['I']
  def num3 = ((m['D'] * 10 + m['R']) * 10 + m['E']) * 10 + m['I']
  def num4 = (((m['S'] * 10 + m['E']) * 10 + m['C']) * 10 + m['H']) * 10 + m['S']
  num1 + num2 + num3 == num4
}
// a solution is [E:3, I:5, N:9, S:1, Z:7, W:0, D:2, R:8, C:4, H:6]
// so test with this list:
//def testLetterList = ['W', 'S', 'D', 'E', 'C', 'I', 'H', 'Z', 'R', 'N']
//assert isSolution (testLetterList)


// ---- here we start ----

def allLetters = ['E', 'I', 'N', 'S', 'Z', 'W', 'D', 'R', 'C', 'H']
assert allLetters.size == 10

println "testing and filtering permutations of $allLetters..."
def solutions = KnobelTools.filterPermutations (allLetters, isSolution)
println "\nThere are ${solutions.size} solutions for the game (digits 0..9):"
KnobelTools.printList(solutions)

// only E, H, I are important now
def extractEhiNum = extractKeyNumber.rcurry(['E', 'H', 'I'])
//assert extractEhiNum (testLetterList) == '365'

def uniqueSolNums = KnobelTools.transform (solutions, extractEhiNum).unique().sort()
println "\nThere are ${uniqueSolNums.size} solution numbers for E, H, I:"
KnobelTools.printList(uniqueSolNums)
