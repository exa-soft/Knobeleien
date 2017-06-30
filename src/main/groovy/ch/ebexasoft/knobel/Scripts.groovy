/**
 * 
 */
package ch.ebexasoft.knobel

import java.util.List

import groovy.lang.Closure

def testLetters = ['E', 'I', 'N', 'S']
def allLetters = ['E', 'I', 'N', 'S', 'Z', 'W', 'D', 'R', 'C', 'H']
assert allLetters.size == 10

def duplicate = { String s -> s + s }
assert KnobelTools.transform (testLetters, duplicate) == ['EE', 'II', 'NN', 'SS']

def toMapString = { it, i -> "$it=$i" }
assert KnobelTools.transformWithIndex(testLetters, toMapString) == ['E=0', 'I=1', 'N=2', 'S=3'] 

def toMap = { list ->
  def solMap = [:]
  list.eachWithIndex { it, i -> solMap[it] = i }
  solMap
}
assert toMap(testLetters) == [E:0, I:1, N:2, S:3]


def isTestSolution = { List letters -> 
  if (letters[0] == 'E' && letters[1] =='I')
    true
  else
    false
}
assert isTestSolution(['E', 'I']) == true
assert isTestSolution(['E', 'I', 'S']) == true
assert isTestSolution(['E', 'S']) == false

def isSolution = { List list ->
  Map m = toMap(list) 
  def num1 = ((m['E'] * 10 + m['I']) * 10 + m['N']) * 10 + m['S']
  def num2 = ((m['Z'] * 10 + m['W']) * 10 + m['E']) * 10 + m['I']
  def num3 = ((m['D'] * 10 + m['R']) * 10 + m['E']) * 10 + m['I']
  def num4 = (((m['S'] * 10 + m['E']) * 10 + m['C']) * 10 + m['H']) * 10 + m['S']
//  println "num1=$num1"
//  println "num2=$num2"
//  println "num3=$num3"
//  println "num4=$num4"
  num1 + num2 + num3 == num4
}
// a solution is [E:3, I:5, N:9, S:1, Z:7, W:0, D:2, R:8, C:4, H:6]
// so the list is:
assert isSolution (['W', 'S', 'D', 'E', 'C', 'I', 'H', 'Z', 'R', 'N'])

def testSolutions = KnobelTools.filterPermutations (testLetters, isTestSolution)
KnobelTools.printList(testSolutions)
assert testSolutions == [['E', 'I', 'N', 'S'], ['E', 'I', 'S', 'N']]


def testSolMaps = testSolutions.collect { map ->
  def solMap = [:]
  map.eachWithIndex { it, i ->
    if (it in ['E', 'N'])  solMap[it] = i
  }
  solMap
}
KnobelTools.printList(testSolMaps.unique())


