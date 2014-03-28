package main.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import list.WordList;
import list.node.Nexus;

public class Board {   
    private char[][] board;
    
    private Nexus nexus;
    
    public Board(String values) {
        //Checks if the board is square (Required?)
        double dimension = Math.sqrt(values.length());
        //If the board is square, init()
        if(dimension == Math.round(dimension))
            init(values, (int)dimension);
    }
    
    private void init(String values, int dimension) {
        //Set "values" string to lower case for homogeniety
        values = values.toLowerCase();
        //Set up nexus
        nexus = new Nexus(values.toCharArray());
        //Sets up board
        board = new char[dimension][dimension];
        int index = 0;
        //Inputs values into board
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = values.charAt(index);
                index++;
            }
        }
    }
    
    public String[] getWords() {
        List<String> words = new ArrayList<>();
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                words.addAll(Arrays.asList(getWordsForChar(i, j)));
        return simplify(words.toArray(new String[words.size()]));
    }
    
    private String[] getWordsForChar(int index1, int index2) {
        List<String> words = new ArrayList<>();
        boolean[][] pastIndices = new boolean[board.length][board[0].length];
        String currentString = new String();
        pastIndices[index1][index2] = true;
        currentString += board[index1][index2];
        if(WordList.isWord(currentString))
            words.add(currentString);
        if(WordList.charsAfterPrefix(currentString).length == 0)
            return words.toArray(new String[words.size()]);
        int[][] indices = getAdjacentChars(index1, index2);
        for(int[] index : indices) {
            if(isValid(index[0], index[1]) && 
                    !pastIndices[index[0]][index[1]] && 
                    WordList.charsAfterPrefix(currentString + board[index[0]][index[1]]).length != 0) {
                boolean[][] newIndices = new boolean[pastIndices.length][pastIndices[0].length];
                for(int i = 0; i < pastIndices.length; i++)
                    System.arraycopy(pastIndices[i], 0, newIndices[i], 0, pastIndices[i].length);
                words.addAll(Arrays.asList(getWordsForChar(index[0], index[1], currentString, pastIndices.clone())));
            }
        }
        return words.toArray(new String[words.size()]);
    }
    
    private String[] getWordsForChar(int index1, int index2, String currentString,  boolean[][] pastIndices) {
        List<String> words = new ArrayList<>();
        pastIndices[index1][index2] = true;
        currentString += board[index1][index2];
        if(WordList.isWord(currentString))
            words.add(currentString);
        int[][] indices = getAdjacentChars(index1, index2);
        for(int[] index : indices) {
            if(isValid(index[0], index[1]) && 
                    !pastIndices[index[0]][index[1]] && 
                    WordList.charsAfterPrefix(currentString + board[index[0]][index[1]]).length != 0) {
                boolean[][] newIndices = new boolean[pastIndices.length][pastIndices[0].length];
                for(int i = 0; i < pastIndices.length; i++)
                    System.arraycopy(pastIndices[i], 0, newIndices[i], 0, pastIndices[i].length);
                words.addAll(Arrays.asList(getWordsForChar(index[0], index[1], currentString, pastIndices.clone())));
            }
        }
        return words.toArray(new String[words.size()]);
    }
    private String[] simplify(String[] words) {
        List<String> simpleWords = new ArrayList<>();
        for(String word : words)
            //Do not add any word that has already been seen
            if(!simpleWords.contains(word))
                simpleWords.add(word);
        
        //Sort array by alphabetical order
        Collections.sort(simpleWords);
        
        return simpleWords.toArray(new String[simpleWords.size()]);
    }
    
    private int[][] getAdjacentChars(int index1, int index2) {
        //Returns a two-dimensional integer array of all adjacent indices
        return new int[][]
        { 
            {index1+1, index2},
            {index1+1, index2-1},
            {index1, index2-1},
            {index1-1, index2-1},
            {index1-1, index2},
            {index1-1, index2+1},
            {index1, index2+1},
            {index1+1, index2+1}
        };
    }
    
    private boolean isComplete(boolean[][] indices) {
        //Checks if the index array is completely true
        for(int i = 0; i < indices.length; i++)
            for(int j = 0; j < indices[i].length; j++)
                if(!indices[i][j])
                    return false;
        return true;
    }
    
    private boolean isValid(int index1, int index2) {
        //Checks if the given indices are withing the board bounds
        return ((index1 >= 0 && index1 < board.length) &&
                (index2 >= 0 && index2 < board[index1].length));
    }
    
    private static boolean contains(char[] set, char character) {
        return new String(set).indexOf(character) != -1;
    }
    
    @Override
    public String toString() {
        //Custom toString() method displaying the grid
        StringBuilder returnString = new StringBuilder();
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                returnString.append(Character.toUpperCase(board[i][j])).append(" ");
            }
            returnString.append("\n");
        }
        
        return returnString.toString();
    }
}
