/**
 * 
 */
package ch.ebexasoft.knobel

/**
 * Often used Closures when solving puzzles by brute-force 
 * (e.g. trying all permutations).
 *  
 * @author edith
 */
class KnobelTools {

  /**
   * Create a new list by applying an action to each element of the first list 
   * (similar to collect, except that this can be used with currying).
   * @param elements  given list
   * @param action    action that is applied to each element (must take element as parameter) 
   * @return  new list
   */
  public static Closure transform = { List elements, Closure action ->
    def result = []
    elements.each {
        result << action(it)
    }
    result
  }
 

  /**
   * Create a new list by applying an action to each element of the first list
   * (similar to collect, except that this can be used with currying).
   * @param elements  given list
   * @param action  an action that is applied to each element (must take the element and its index in the list as params)
   * @return  new list
   */
  public static Closure transformWithIndex = { List elements, Closure action ->
    def result = []
    elements.eachWithIndex { it, i ->
        result << action(it, i)
    }
    result
  }

  /**
   * Permutates the given list and applies a filter to each permutation. 
   * @param list  the list to permutate and filter
   * @param filter  the filter - must work on a list and return boolean  
   * @return  Return a list of lists (permutations) that matched the filter
   */
  public static Closure filterPermutations = { List list, Closure filter ->
    def solutions = []
    list.eachPermutation {
      if (filter(it)) {
        //println "found solution: $it"
        solutions << it
      }
    }
    solutions
  }
  
  /** 
   * Convenience method to print all elements of a list, 
   * each on a new line
   * @param elements  the list
   */
  public static Closure printList = { List elements ->
    println ""
    elements.each {
        println it
    }
  }  
  
  
}
