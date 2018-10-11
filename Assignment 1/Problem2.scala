/*Write a function, patternCount with two arguments. The first arguments is a string, lets call
  it text, and the second argument is also a string, call it pattern. The function patternCount
  returns the number of times the pattern occurs in the text. For example
  pattern_count(“abababa”, “aba”) == 3
  pattern_count(“aaaaa”, “aa”) == 4
  pattern_count(“Abcde”, “abc”) == 0
  */

object Problem2 {
  def main(arg: Array[String]) {
    println("Start!")
    val count: Int = pattern_count("aaaaa", "aa")
    println("Count: " + count)

  } //main

  def pattern_count(text: String, pattern: String): Int = {
    var lastIndex: Int = 0
    var count: Int = 0

    while (lastIndex != -1) { //cursor moves on till the end of string
      lastIndex = text.indexOf(pattern, lastIndex)

      if (lastIndex != -1) {
        count = count + 1
        lastIndex = lastIndex + 1
      }
    }
    count
  } //def
}

//obj
