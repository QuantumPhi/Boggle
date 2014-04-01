package list;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import list.sort.Searcher;

public class WordList {
    private static List<String> wordlist;
    
    public static void init(String pathToList) {
        wordlist = new ArrayList<>();
        
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(pathToList))));
            String line;
            while((line = reader.readLine()) != null)
                wordlist.add(line);
        } catch (FileNotFoundException ef) {
            System.err.println("OH NO! " + ef);
        } catch (IOException ei) {
            System.err.println("OH NO! " + ei);
        }
    }
    
    public static List<String> getList() { return wordlist; }
    
    public static String get(int index) { return wordlist.get(index); }
    public static String get(String word) {
        int index = wordlist.indexOf(word);
        return (index != -1 ? wordlist.get(index) : "");
    }
    
    public static char[] charsAfterPrefix(String prefix) {
        String charList = new String();
        int index = Math.abs(Searcher.binarySearch(wordlist, prefix)) + 1;
        for(int i = index; i < wordlist.size(); i++) {
            if(wordlist.get(i).length() > prefix.length()) {
                if(wordlist.get(i).substring(0, prefix.length()).equals(prefix))
                    charList = charList + wordlist.get(i).charAt(prefix.length());
                else
                    break;
            }
        }
        return simplify(charList);
    }
    
    public static boolean isWord(String word) {
        return Searcher.binarySearch(wordlist, word) >= 0;
    }
    
    public static char[] simplify(String characters) {
        String charList = new String();
        for(char character : characters.toCharArray())
            if(charList.indexOf(character) == -1)
                charList += character;
        return charList.toCharArray();
    }
}
