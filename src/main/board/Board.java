package main.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import list.WordList;
import list.node.Nexus;
import main.board.util.ArrayUtils;

public class Board {   
    private List<String> words;
    
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
        //Sets up list of words
        words = new ArrayList<>();
        //Set up nexus
        nexus = new Nexus(values.toCharArray());
        //Sets up board
        board = new char[dimension][dimension];
        int index = 0;
        for (char[] subBoard : board) {
            for (int j = 0; j < subBoard.length; j++) {
                subBoard[j] = values.charAt(index);
                index++;
            }
        }
    }
    
    public String[] getWords() {
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[i].length; j++)
                getWordsForChar(i, j);
        return simplify(words.toArray(new String[words.size()]));
    }
    
    private void getWordsForChar(int index1, int index2) {
        String currentString = new String();
        boolean[][] pastIndices = new boolean[board.length][board[0].length];
        pastIndices[index1][index2] = true;
        currentString += board[index1][index2];
        if(WordList.isWord(currentString))
            words.add(currentString);
        if(nexus.getBranchNode(currentString) == null || 
                !nexus.getBranchNode(currentString).hasSubNodes())
            return;
        if(ArrayUtils.isComplete(pastIndices))
            return;
        int[][] indices = getAdjacentChars(index1, index2);
        for(int[] index : indices)
            if(isValid(index[0], index[1]) && !pastIndices[index[0]][index[1]])
                getWordsForChar(index[0], index[1], currentString, ArrayUtils.cloneBoolean(pastIndices));
    }
    
    private void getWordsForChar(int index1, int index2, String currentString,  boolean[][] pastIndices) {
        pastIndices[index1][index2] = true;
        currentString += board[index1][index2];
        if(WordList.isWord(currentString))
            words.add(currentString);
        if(nexus.getBranchNode(currentString) == null || 
                !nexus.getBranchNode(currentString).hasSubNodes())
            return;
        if(ArrayUtils.isComplete(pastIndices))
            return;
        int[][] indices = getAdjacentChars(index1, index2);
        for(int[] index : indices)
            if(isValid(index[0], index[1]) && !pastIndices[index[0]][index[1]])
                getWordsForChar(index[0], index[1], currentString, ArrayUtils.cloneBoolean(pastIndices));
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
    
    private boolean isValid(int index1, int index2) {
        //Checks if the given indices are withing the board bounds
        return ((index1 >= 0 && index1 < board.length) &&
                (index2 >= 0 && index2 < board[index1].length));
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
