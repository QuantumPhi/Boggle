public class WordPrefix implements WordPrefixTemplate {
    private static String[] wordlist;
    private String prefix;
    private int index;
    
    public WordPrefix(String[] wordList) {
        this.prefix = "";
        wordlist = wordList;
    }
    
    private WordPrefix(String prefix) { this.prefix = prefix; }
    
    private WordPrefix(String prefix, int index) { this.prefix = prefix; this.index = index; }
    
    @Override
    public WordPrefix makeNewFromTemplate() { return new WordPrefix(this.prefix, this.index); }
    
    public static void main(String[] args) {
        String[] wordlist = WordPrefixCanonical.readWordList("src//list//wordlist.txt");
        WordPrefixTemplate prefixTemplate = new WordPrefix(wordlist);
        new BoggleCanonical(wordlist, "tuwopsbem", 4, prefixTemplate, false);
   }
    
    @Override
    public void add(String addition) { 
        prefix += addition;  
        index = binarySearch(wordlist, prefix); 
    }
    
    @Override
    public String getText() { return prefix; }
    
    @Override
    public int length() { return prefix.length(); }
    
    @Override
    public boolean isWord() { return index >= 0; }
    
    @Override
    public boolean isPrefix() {
        int nIndex = index >= 0 ? index : -index - 1;
        return wordlist[nIndex].length() >= prefix.length() && 
                wordlist[nIndex].substring(0, prefix.length()).equals(prefix);
    }
    
    public static int binarySearch(String[] list, String word) {
        int low = 0;
        int high  = list.length- 1;
        while(low <= high) {
            int mid = (high + low) >>> 1;
            int compare = list[mid].compareTo(word);
            if(compare < 0)
                low = mid + 1;
            else if(compare > 0)
                high = mid - 1;
            else if(compare == 0)
                return mid;
        }
        return -(low + 1);
    }
}
