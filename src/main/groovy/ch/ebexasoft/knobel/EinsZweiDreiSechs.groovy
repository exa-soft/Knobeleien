/**
 * 
 */
package ch.ebexasoft.knobel

/**
 * @author edith
 *
 */

def allLetters = ['E', 'I', 'N', 'S']

def res0 = transform (allLetters)
res0.each { println it }


def res1 = isSolution (allLetters)
println "res1 is $res1"
assert res1
    

//List solutions = findSolutions (allLetters)
//
//solutions.each { println it }


//class EinsZweiDreiSechs {

//  static main(args) {

//  }


def transform(List elements, Closure action) {
  def result = []
  elements.each {
      result << action(it)
  }
  result
}

def duplicate = { String s -> s + s }
 
  
def isSolution = { List letters ->
  if (letters[0] == 'E' && letters[1] =='I')
    true
  else
    false
}
  
  /*
  
  public Closure isSolution = { ArrayList letters ->
    if (letters[0] == 'E' && letters[1] =='I')
      return true
    else
      return false    
  } 
  

  public Closure findSolutions = { List someList -> 
    
    def result = []
    someList.eachPermutation {
      if (isSolution(it)) {
        result << it
      }
    }
    return result
  }
  
  */
  
  
//}
