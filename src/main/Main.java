package main;

import java.util.Scanner;
import list.WordList;
import main.board.Board;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    
    public static void main(String[] args) {
        WordList.init("src//list//wordlist.txt");
        System.out.println("Input board:");
        String boardString = in.nextLine();
        
        System.out.println();
        
        Board board = new Board(boardString);
        System.out.println(board);
        System.out.println(parseBoardOutput(board.getWords()));
    }
    
    private static String parseBoardOutput(String[] output) {
        StringBuilder returnString = new StringBuilder();
        
        for(String word : output)
            returnString.append(word).append("\n");
        
        return returnString.toString();
    }
}
