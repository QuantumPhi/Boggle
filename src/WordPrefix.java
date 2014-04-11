import list.sort.Searcher;

public class WordPrefix implements WordPrefixTemplate {
    private String[] wordlist;
    private String prefix;
    
    public WordPrefix(String[] wordlist) { 
        this.prefix = prefix != null ? prefix : "";
        this.wordlist = wordlist;
    }
    
    private WordPrefix(String prefix, String[] wordlist) {
        this.prefix = prefix != null ? prefix : "";
        this.wordlist = wordlist;
    }
    
    @Override
    public WordPrefix makeNewFromTemplate() { 
        return new WordPrefix(this.prefix, wordlist); 
    }
    
    public static void main(String[] args) {
        String[] wordlist = WordPrefixCanonical.readWordList("src//list//wordlist.txt");
        WordPrefixTemplate prefixTemplate = new WordPrefix(wordlist);
        new BoggleCanonical(wordlist, "tuwopsbem", 4, prefixTemplate, false);
   }
    
    @Override
    public void add(String addition) { prefix += addition; }
    
    @Override
    public String getText() { return prefix; }
    
    @Override
    public int length() { return prefix.length(); }
    
    @Override
    public boolean isWord() { return Searcher.binarySearch(wordlist, prefix) >= 0; }
    
    @Override
    public boolean isPrefix() {
        int index = Searcher.binarySearch(wordlist, prefix);
        index = index >= 0 ? index : -index - 1;
        return wordlist[index].length() <= prefix.length() || 
                wordlist[index].substring(0, prefix.length()).equals(prefix);
    }
}