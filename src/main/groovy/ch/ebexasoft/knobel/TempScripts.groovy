/**
 * 
 */
package ch.ebexasoft.knobel

import java.util.List

import groovy.lang.Closure

def testLetters = ['E', 'I', 'N', 'S']

def duplicate = { String s -> s + s }
assert KnobelTools.transform (testLetters, duplicate) == ['EE', 'II', 'NN', 'SS']

def toMapString = { it, i -> "$it=$i" }
assert KnobelTools.transformWithIndex(testLetters, toMapString) == ['E=0', 'I=1', 'N=2', 'S=3'] 

def toMap = { list ->
  def m = [:]
  list.eachWithIndex { it, i -> m[it] = i }
  m
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

def testSolutions = KnobelTools.filterPermutations (testLetters, isTestSolution)
KnobelTools.printList(testSolutions)
assert testSolutions == [['E', 'I', 'N', 'S'], ['E', 'I', 'S', 'N']]

// extract only some letters with their index
def extractKeyMapping = { List letters, List keyLetters ->
  Map mapping = [:]
  letters.eachWithIndex { c, i ->
    if (c in keyLetters) mapping[c] = i
  }
  mapping
}
def map1 = extractKeyMapping (testLetters, ['E', 'I'])
assert map1 == ['E':0, 'I':1]
def map2 = extractKeyMapping (testLetters, ['S', 'N', 'I']) 
assert map2 == ['S':3, 'N':2, 'I':1]
def map3 = ['N':2, 'S':3, 'I':1]
def maps = [map1, map2, map3]
def uniqueMaps = maps.unique()
assert uniqueMaps == [map1, map2]


def testSolMaps = testSolutions.collect { map ->
  def solMap = [:]
  map.eachWithIndex { it, i ->
    if (it in ['E', 'N'])  solMap[it] = i
  }
  solMap
}
KnobelTools.printList(testSolMaps.unique())

