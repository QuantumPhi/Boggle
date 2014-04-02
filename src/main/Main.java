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
        long sTime = System.nanoTime();
        System.out.println(parseBoardOutput(board.getWords()));
        long eTime = System.nanoTime();
        double deltaTime = (eTime - sTime) / (double)1000000;
        System.out.printf("%4.4f Milliseconds\n", deltaTime);
    }
    
    private static String parseBoardOutput(String[] output) {
        StringBuilder returnString = new StringBuilder();
        
        int length = output.length;
        returnString.append("Found ").append(length)
                .append(length != 1 ? " words:\n" : " word:\n");
        
        for(String word : output)
            returnString.append(word).append("\n");
        
        return returnString.toString();
    }
}
