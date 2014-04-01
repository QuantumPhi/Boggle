interface WordPrefixTemplate {
    // implementers should just call the copy constructor on this
    // object and return a reference to the new object.
    public WordPrefixTemplate makeNewFromTemplate();
   
    public void add(String addition); // add more characters to the prefix
   
    public String getText(); // the text of the prefix represented by this
    public int length(); // length of the prefix
    public boolean isWord(); // is the prefix a word in the word list?
    public boolean isPrefix(); // is this a proper prefix of a word?
    // NOTE A CHANGE IN INSTRUCTIONS: isPrefix returns true only if
    // this is a prefix of some longer word, so words are not prefixes
    // of themselves, though a word can be a prefix of longer words.
}
