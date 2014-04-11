// Use this template to check your work on WordPrefix. This is an
// example that compiles. Of course you should use your own WordPrefix
// class and main() method to test. For purposes of this test (only),
// your WordPrefix class should implement WordPrefixTemplate, but
// should not use any of the methods in WordPrefixCanonical.
//
// Usage for main() method: Create a list of words as an array of
// Strings[]. You can use WordPrefixCanonical.readWordList() if you
// want, or provide an alphabetized file of words.  Then call:
// new BoggleCanonical(wordList, <<<letters>>>, <<<minimum word length>>>,
//                     <<<reference to WordPrefixTemplate>>>, <<<trace>>>);
//
// To run with my implementation, use new WordPrefixCanonical(wordList)
// as the reference to WordPrefixTemplate. To run with your own
// implementation of WordPrefix, create an object of your type and
// supply a reference to it here. If you supply your own implementation,
// you may run with trace set to true, and the program will compare your
// implementation to mine.

public class WordPrefixTester extends WordPrefixCanonical implements WordPrefixTemplate {
  public WordPrefixTester(String[] wordList) {
      super(wordList);
   }
   
   public WordPrefixTester(WordPrefixTester src) {
       super(src);
   }

   public static void main(String[] args) {
      String[] wordList = WordPrefixCanonical.readWordList("src//list//wordlist.txt");
      WordPrefixTemplate prefixTemplate = new WordPrefixTester(wordList);
      new BoggleCanonical(wordList, "tuwopsbem", 4, prefixTemplate, false);
   }
}
