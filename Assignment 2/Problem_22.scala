/*
*
* 2. Write a function, pattern_count with two arguments. The first arguments is a string, lets call
it text, and the second argument is also a string, call it pattern. The function pattern_count
returns the number of times the pattern occurs in the text. For example
pattern_count(“abababa”, “aba”) == 3
pattern_count(“aaaaa”, “aa”) == 4
pattern_count(“Abcde”, “abc”) == 0
*
* */

object Problem_22 {

  //test cases
  assert(pattern_count("abababa","aba")==3)
  assert(pattern_count("aaaaa", "aa")==4)
  assert(pattern_count("Abcde", "abc") == 0)
  assert(pattern_count("","") == -1)
  assert(pattern_count("aaa","") == -1)
  assert(pattern_count("","aaa") == -1)

  def main(arg: Array[String]) {
    println("Start!")
    val text:String = "aaaaa"
    val pattern:String = "aa"

    val count: Int = pattern_count(text,pattern) //3 should be the answer
    println("Count: " + count)
  } //main

  def pattern_count(text: String, pattern: String):Int= {
    if(text.length<pattern.length || text.length == 0 || pattern.length==0){    //Testing edge conditions
      -1
    }
    else{
      text.sliding(pattern.length).count(_==pattern)
    }
  } //end of def
}//end of obj
